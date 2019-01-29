head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTradingClendarDetailsImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����J�����_�ڍ�(WEB3GentradeTradingClendarDetailsImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/05 �a�c�@@�F��(SRA) �V�K�쐬
Revesion History : 2004/02/16 ����@@���j(SRA) ����
Revesion History : 2004/05/14 羐� (���u) �C��
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (����J�����_�ڍ�)<BR>
 * �iTradingCalendarDetail�C���^�t�F�C�X�̎����N���X�j<BR>
 */
public class WEB3GentradeTradingClendarDetailsImpl
    implements TradingCalendarDetails
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingClendarDetailsImpl.class);

    /**
     * @@roseuid 40A430F7033D
     */
    public WEB3GentradeTradingClendarDetailsImpl()
    {

    }

    /**
     * (get�x��)<BR>
     * �igetHolidays�̎����j<BR>
     * <BR>
     * �J�����_�[�e�[�u�����ȉ��̏����Ō������A��v����s�̓��t�����ׂĕԋp����B<BR>
     * �@@[�����L�[]<BR>
     * �@@�c�Ɠ��敪�F�@@�h��c�Ɠ��h<BR>
     * @@return Date[]
     * @@roseuid 4014AE5802D8
     */
    public Date[] getHolidays()
    {

        final String STR_METHOD_NAME = "getHolidays()";
        log.entering(STR_METHOD_NAME);

        //�J�����_�[�e�[�u�����ȉ��̏����Ō������A��v����s�̓��t�����ׂĕԋp����B
        //[�����L�[]
        //�c�Ɠ��敪�F�h��c�Ɠ��h
        List l_lisRecords = null;
        try
        {
            String l_strWhere = " biz_date_type = ? ";
            String l_strOrderBy = " holiday ";

            Object[] l_objCalendarWhere = { WEB3BizDateTypeDef.NO_BIZ_DATE };

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CalendarRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_objCalendarWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        int l_intLoop = 0;
        int l_intSize = l_lisRecords.size();
        CalendarRow l_calendarRow;
        Date[] l_datReturn = new Date[l_intSize];
        Date l_tmpDate;

        while (l_intLoop < l_intSize)
        {
            l_calendarRow = (CalendarRow)l_lisRecords.get(l_intLoop);
            l_tmpDate = new Date(l_calendarRow.getHoliday().getTime());
            l_datReturn[l_intLoop] = WEB3DateUtility.toDay(l_tmpDate);
            l_intLoop++;
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturn;
    }

    /**
     * (get�s��J�ǎ���)<BR>
     * <BR>
     * �s��J�ǎ��Ԃ��擾����B<BR>
     * �igetTradeOpenTime�̎����j<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A��t������<BR>
     * �擾����BThreadLocalSystemAttributesRegistry.getAttribute("<BR>
     * xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     * �Q�j�@@��t���̉c�Ɠ��敪����B<BR>
     * �@@�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ��<BR>
     * �h��c�Ɠ��h�Ɣ��肵�A"000000"��ԋp���������I������B<BR>
     * �@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A�s<BR>
     * �̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪<BR>
     * ���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g��<BR>�@@
     * �擾����BThreadLocalSystemAttributesRegistry.getAttribute(" <BR>
     * web3.tradingcalendarcontext")<BR>
     * <BR>
     * �S�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     * �@@�������v�Z�F�@@�h�����h<BR>
     * <BR>�@@
     * �@@%��L�Ɉ�v����s�̂����A�J�n���Ԃ���ԑ����s�̊J�n���Ԃ�ԋp����<BR>
     * �@@%�Y���s�����݂��Ȃ��ꍇ�A�@@�h000000�h��ԋp����B<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4014AE5802D9
     */
    public String getTradeOpenTime()
    {
        final String STR_METHOD_NAME = "getTradeOpenTime()";
        log.entering(STR_METHOD_NAME);

        // �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B

        // ��t�����擾
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        // �Q�j ��t���̉c�Ɠ��敪����B
        String l_strBizDateType = null;
        try
        {
            l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(
                    l_tsOrderAcceptDate);

            // �y���̏ꍇ�A��c�Ɠ��Ƃ���"000000"��Ԃ�
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            {
                return WEB3GentradeTimeDef.MIN_RETURN;
            }
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(STR_METHOD_NAME,wse);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                wse.getErrorMethod(),
                wse.getMessage(),
                wse);
        }

        // �R�j ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g
        // ���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strInstitutionCode =
            l_clendarContext.getInstitutionCode().trim();
        String l_strMarketCode = l_clendarContext.getMarketCode().trim();
        String l_strBranchCode = l_clendarContext.getBranchCode().trim();
        String l_strTradingTimeType =
            l_clendarContext.getTradingTimeType().trim();
        String l_strProductCode = l_clendarContext.getProductCode().trim();

        // ������ԃe�[�u������
        List l_lisRecords = null;
        try
        {
            // * �@@[�����L�[]
            // * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@�����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪
            // * �@@�������v�Z�F�@@�h�����h
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("       institution_code       = ? ");
            l_sbWhere.append(" and   branch_code            = ? ");
            l_sbWhere.append(" and   market_code            = ? ");
            l_sbWhere.append(" and   trading_time_type      = ? ");
            l_sbWhere.append(" and   product_code           = ? ");
            l_sbWhere.append(" and   biz_date_type          = ? ");
            l_sbWhere.append(" and   bizdate_calc_parameter = ? ");

            Object[] l_objTradingTimeWhere =
                {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strMarketCode,
                    l_strTradingTimeType,
                    l_strProductCode,
                    l_strBizDateType,
                    WEB3BizDateCalcParameterDef.DAY_BIZ_DATE 
                };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time",
                null,
                l_objTradingTimeWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Y���s�����݂��Ȃ��ꍇ�A�@@�h000000�h��ԋp����
        if (l_lisRecords.size() == 0)
        {
            log.debug("�Y���s�����݂��Ȃ��ꍇ�A�@@�h000000�h��ԋp����");
            return WEB3GentradeTimeDef.MIN_RETURN;
        }
        
        //��L�Ɉ�v����s�̂����A�J�n���Ԃ���ԑ����s�̊J�n���Ԃ�ԋp����
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow) l_lisRecords.get(0);
        String l_strStartTime = l_tradingTimeRow.getStartTime();
        log.debug("�s��J�ǎ��ԁF[" + l_strStartTime + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_strStartTime;
    }

    /**
     * (get�s��ǎ���)<BR>
     * �s��ǎ��Ԃ��擾����B<BR>
     * �igetTradeCloseTime�̎����j<BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry���A��t�������擾����B
     * <BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �Q�j�@@��t���̉c�Ɠ��敪����B<BR>
     * �@@�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ��<BR>
     * �h��c�Ɠ��h�Ɣ��肵�A"000000"��ԋp���������I������B<BR>
     * �@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A�s<BR>
     * �̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪<BR>
     * ���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute
     * ("web3.tradingcalendarcontext")<BR>
     * <BR>
     * �S�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     * �@@�������v�Z�F�@@�h�����h<BR>
     * <BR>�@@
     * �@@��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����B
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�A�@@�h235959�h��ԋp����B<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4014AE5802DA
     */
    public String getTradeCloseTime()
    {
        final String STR_METHOD_NAME = "getTradeCloseTime()";
        log.entering(STR_METHOD_NAME);

        // �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B

        // ��t�����擾
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        // �Q�j ��t���̉c�Ɠ��敪����B
        String l_strBizDateType = null;
        try
        {
            l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(
                l_tsOrderAcceptDate);

            // �y���̏ꍇ�A��c�Ɠ��Ƃ���"000000"��Ԃ�
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            {
                return WEB3GentradeTimeDef.MIN_RETURN;
            }
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(STR_METHOD_NAME,wse);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                wse.getErrorMethod(),
                wse.getMessage(),
                wse);
        }

        // �R�j ThreadLocalSystemAttributesRegistry���A����J�����_
        // �R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        String l_strMarketCode = l_clendarContext.getMarketCode().trim();
        String l_strBranchCode = l_clendarContext.getBranchCode().trim();
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType().trim();
        String l_strProductCode = l_clendarContext.getProductCode().trim();

        // ������ԃe�[�u������
        List l_lisRecords = null;
        try
        {
            // * �@@[�����L�[]
            // * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@�����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
            // * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪
            // * �@@�������v�Z�F�@@�h�����h
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_code = ? ");
            l_sbWhere.append(" and market_code = ? ");
            l_sbWhere.append(" and trading_time_type = ? ");
            l_sbWhere.append(" and product_code = ? ");
            l_sbWhere.append(" and biz_date_type = ? ");
            l_sbWhere.append(" and bizdate_calc_parameter = ? ");

            Object[] l_objTradingTimeWhere =
                {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strMarketCode,
                    l_strTradingTimeType,
                    l_strProductCode,
                    l_strBizDateType,
                    WEB3BizDateCalcParameterDef.DAY_BIZ_DATE 
                };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
                l_objTradingTimeWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    
        // �Y���s�����݂��Ȃ��ꍇ�A�@@�h235959�h��ԋp����
        if (l_lisRecords.size() == 0)
        {
            log.debug("�Y���s�����݂��Ȃ��ꍇ�A�@@�h235959�h��ԋp����");
            return WEB3GentradeTimeDef.MAX_RETURN;
        }
        
        //��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow) l_lisRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();
        log.debug("�s��ǎ��ԁF[" + l_strEndTime + "]");

        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
            
    }
}
@
