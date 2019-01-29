head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M������ԊǗ�(WEB3MutualFundTradingTimeManagement)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06  ���� (���u) �V�K�쐬
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2006/10/12 ���� (���u) ���f�� 498
Revesion History : 2007/02/07 ������ (���u) ���f�� 538
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M������ԊǗ�<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualFundTradingTimeManagement
    extends WEB3GentradeTradingTimeManagement
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundTradingTimeManagement.class);

    /**
     * ������t���؎��Ԃ��擾����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g���擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����āA<BR>
     * ������ԃJ�����_�R���e�L�X�g<BR>
     * �@@���擾����B<BR>
     * �@@�mgetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�������F "web3.tradingcalendarcontext"<BR>
     * <BR>
     * �Q�j�@@�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getInstitution()���R�[�����āA<BR>
     * �،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�mgetInstitution�ɓn���p�����^�n<BR>
     * �@@�@@�،���ЃR�[�h�F ������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@�g�����M��������I�u�W�F�N�g���擾����B<BR>
     * �@@�g�����M�����}�l�[�W��.get �A<BR>
     * �g�����M��������I�u�W�F�N�g���擾����B<BR>
     * �@@�mget���M��������ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF �擾�����،���ЃI�u�W�F�N�g<BR>
     * �@@�@@�����R�[�h�F ������ԃR���e�L�X�g.�����R�[�h<BR>
     * <BR>
     * �S�j����J�����_�ڍ׃I�u�W�F�N�g���擾����B<BR>
     * <BR>�@@GtlUtils.getFinObjectManager().getTradingCalendarModel().getTradingCalenda<BR>
     *       rDetails()���R�[������<BR>
     * �@@����J�����_�ڍ׃I�u�W�F�N�g���擾����B<BR>
     * �@@�mgetTradingCalendarDetails�ɓn���p�����^]<BR>
     * �@@�@@�������ID�F �擾�����g�����M�������.getTradedProductId()�̖߂�l<BR>
     * <BR>
     * �T�j�@@�擾��������J�����_�ڍ�.getTradeCloseTime()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40B1D31D00AB
     */
    public static String getOrderCloseTime()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCloseTime()";
        log.entering(STR_METHOD_NAME);

        //����J�����_�ڍ׃I�u�W�F�N�g��
        TradingCalendarDetails l_tradeCalendarDetails = null;

        //�g�����M��������N���X
        WEB3MutualFundTradedProduct l_mutualFundTradedProduct = null;

        //�P�j�@@����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        if (l_context == null)
           {
               log.debug("�v���I�ȃV�X�e���G���[�B");
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   "WEB3MutualFundTradingTimeManagement"+ "." + STR_METHOD_NAME,
                   "����J�����_�R���e�L�X�g���擾�ł��Ȃ�");
           }

        // �Q�j�@@�،���ЃI�u�W�F�N�g���擾����B

        //�،����
        Institution l_institution = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�g���A�J�E���g�}�l�[�W���擾����B
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //�،���Ў擾
            l_institution =
                (Institution) l_accMgr.getInstitution(
                    l_context.getInstitutionCode());

            // �R�j�@@�g�����M��������I�u�W�F�N�g���擾����B
            WEB3MutualFundProductManager l_mutualFundProductManager =
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            l_mutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) l_mutualFundProductManager.getMutualFundTradedProduct(
                    l_institution,
                    l_context.getProductCode());

            //�S�j����J�����_�ڍ׃I�u�W�F�N�g���擾����B
            long l_lngProductId =
                l_mutualFundTradedProduct.getTradedProductId();
            l_tradeCalendarDetails =
                GtlUtils.getFinObjectManager().getTradingCalendarModel().getTradingCalendarDetails(l_lngProductId);
            log.debug("����J�����_�ڍ׃I�u�W�F�N�gProductId  = " + l_lngProductId );
            log.debug("����J�����_�ڍ׃I�u�W�F�N�ggetTradeCloseTime  = " + l_tradeCalendarDetails.getTradeCloseTime() );
        }
        catch (NotFoundException l_ex)
        {
            log.error("Not Found �Y���̕⏕����  with " +
                "(�،����)l_institution =  " +
                    l_institution +
                " and (�����R�[�h)l_strProductCode = " +
                    l_context.getProductCode());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �T�j�@@�擾��������J�����_�ڍ�.getTradeCloseTime()�̖߂�l��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_tradeCalendarDetails.getTradeCloseTime();
    }

    /**
     * is�g���K���s<BR>
     * �s��փ��A���^�C���Ƀg���K�𔭍s���邩�𔻒肷��B<BR>
     * �i������ԊǗ�.is�g���K���s()�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �Q�j�@@��t���̉c�Ɠ��敪����B<BR>
     * �@@�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ��<BR>
     * �h�x���h�Ɣ��肵�Afalse��ԋp���������I������B<BR>
     * �@@�ȊO�̏ꍇ�A��t�����̓��t�����ŃJ�����_�e�[�u�����������A<BR>
     * �s�̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ�<BR>
     * �敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>�@@ThreadLocalSystemAttributesRegistry.ge
     * tAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * �S�j �O�����M�i�����c�Ɠ��j �܂��́A�O��MMF�̏ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�@@�S�|�P�j�@@�g�����M�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�g�����M�����}�l�[�W����get���M���� (�،����, �����R�[�h)���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[get���M�����̈���]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЁF�g���A�J�E���g�}�l�[�W��.get�hnstitution(����J�����_�R���e�L�X�g.�،���ЃR�[�h)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����R�[�h�F����J�����_�R���e�L�X�g.�����R�[�h <BR>
     * <BR>
     * �@@�@@�S�|�Q�j �@@�܂��͇A�̏ꍇfalse��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�c�Ɠ��敪 = �����c�Ɠ��@@���� <BR>
     * �@@�@@      �i �i�g�����M����.is�O�����M()==true�j�@@�܂��� <BR>
     * �@@�@@    �@@�@@  �i�g�����M����.isFWF()==true ���� ���M����.�ʉ݃R�[�h != �h�~�h�j �j<BR>
     * <BR>
     *     �@@�@@�A�@@�g�����M����.is�O��MMF() ==true <BR>
     * �T�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�iSONAR�j�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���i�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     * �@@�J�n���� <= �i��t�����̎��ԕ����j <= �I������ <BR>
     * <BR>�@@
     * �@@��L�Ɉ�v����s�́u�s��g���K���s�v���ڂ��hSONAR��MQ�g���K��<BR>
     * ���{����h�ł����true�A�ȊO��false��ԋp����B<BR>
     * �@@��L�Ɉ�v����s�����݂��Ȃ��ꍇ�́A�Y���f�[�^�Ȃ�<BR>
     * �i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B<BR>
     * -�Y���f�[�^�Ȃ�-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * @@param l_strOrderingCondition - ��������<BR>
     * �@@0�F DEFAULT<BR>
     * �@@1�F �t�w�l<BR>
     * �@@2�F W�w�l<BR>
     *
     * @@return java.lang.boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 4014CD9D000A
     */
    public static boolean isSubmitMarketTrigger(String l_strOrderingCondition)
        throws WEB3SystemLayerException
    {
        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strMarketCode;
        String l_strTradingTimeType;
        String l_strBizDateType;
        String l_strProductCode;
        final String STR_METHOD_NAME = "isSubmitMarketTrigger(String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@ThreadLocalSystemAttributesRegistry���A��t�����擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        // �Q�j�@@��t���̉c�Ɠ��敪����B
        //�c�Ɠ��敪�̎擾
        l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug(STR_METHOD_NAME + "�F��t�����͓y�j�����͓��j���̏ꍇ�ł��B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�S�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�S�j �O�����M�i�����c�Ɠ��j �܂��́A�O��MMF�̏ꍇ�Afalse��Ԃ��B

        //�@@�@@�S�|�P�j�@@�g�����M�����I�u�W�F�N�g�𐶐�����B
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�g�����M�����}�l�[�W����get���M���� (�،����, �����R�[�h)���R�[������B
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@[get���M�����̈���]
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЁF�g���A�J�E���g�}�l�[�W��.get�hnstitution(����J�����_�R���e�L�X�g.�،���ЃR�[�h)
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h�F����J�����_�R���e�L�X�g.�����R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                (WEB3MutualFundProduct)l_mutualManager.getMutualFundProduct(
                    l_accMgr.getInstitution(l_clendarContext.getInstitutionCode()),
                    l_clendarContext.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //�@@�@@�S�|�Q�j �@@�܂��͇A�̏ꍇfalse��Ԃ��B
        //    �@@�@@�@@�@@�c�Ɠ��敪 = �����c�Ɠ��@@����
        //�@@�@@         �i �i�g�����M����.is�O�����M()==true�j�@@�܂���
        //�@@�@@�@@�@@    �@@�@@  �i�g�����M����.isFWF()==true ���� ���M����.�ʉ݃R�[�h != �h�~�h�j �j
        //    �@@�@@�A�@@�g�����M����.is�O��MMF() ==true
        boolean l_blnIsForeignFund = l_mutualFundProduct.isForeignFund();
        boolean l_blnIsFWF = l_mutualFundProduct.isFWF();
        boolean l_blnIsFrgnMmf = l_mutualFundProduct.isFrgnMmf();
        if ((WEB3BizDateTypeDef.BIZ_DATE_AM.equals(l_strBizDateType)
                || WEB3BizDateTypeDef.BIZ_DATE_PM.equals(l_strBizDateType))
            && (l_blnIsForeignFund
                || (l_blnIsFWF 
                    && !WEB3MFOrderQuantityType.EN.equals(l_mutualFundProduct.getCurrencyCode()))))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        if (l_blnIsFrgnMmf)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //���X�R�[�h
        l_strBranchCode = l_clendarContext.getBranchCode();
        //�s��R�[�h
        l_strMarketCode = l_clendarContext.getMarketCode();
        //��t���ԋ敪
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //���i�R�[�h
        l_strProductCode = l_clendarContext.getProductCode();

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A�f�[�^�s�����Ƃ���
        //��O���X���[����B
        //   ������ԃR���e�L�X�g.�،���ЃR�[�h
        // �@@������ԃR���e�L�X�g.���X�R�[�h
        // �@@������ԃR���e�L�X�g.�s��R�[�h
        // �@@������ԃR���e�L�X�g.��t���ԋ敪
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    "�f�[�^�s����");
        }

        //��t���Ԃ̎擾(�擾���������̎��ԕ���)
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        // �T�j ������ԃe�[�u������������
        //�u�s��g���K���s�v���ڂ��hSONAR��MQ�g���K�����{����h�ł����
        // true�A�ȊO��false��ԋp����B

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and start_time <= ? ");
        l_sbWhere.append(" and end_time >= ? ");

        Object[] l_objTradingTimeWhere = {
            l_strInstitutionCode, //�،���ЃR�[�h
            l_strBranchCode,      //���X�R�[�h
            l_strMarketCode,      //�s��R�[�h
            l_strTradingTimeType, //��t���ԋ敪
            l_strProductCode,     //���i�R�[�h
            l_strBizDateType,     //�c�Ɠ��敪
            l_strAcceptTime,      //��t����
            l_strAcceptTime       //��t����
            };

        TradingTimeParams l_tradingTimeParams = null;
        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWhere);
        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(STR_METHOD_NAME, dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        //�����`�F�b�N
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                "������ԃe�[�u�������F ���� = 0");
        }
        l_tradingTimeParams = (TradingTimeParams)l_lisRecords.get(0);

        //get�s��g���K���s
        String l_strSubmit_market_trigger = l_tradingTimeParams.getSubmitMarketTrigger();

        //�u�s��g���K���s�v���ڂ��hSONAR��MQ�g���K�����{����h�ł����true�A�ȊO��false��ԋp����B
        if (l_strSubmit_market_trigger.equals(WEB3SubmitMarketTriggerDef.TRIGGER))
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
     * (get������)<BR>
     * ���������擾����B<BR>
     * �i�e�N���X�hDate get������(Date)�h�̃I�[�o�[���C�h�j<BR>
     *     this.get���M������()�ɂĔ��������擾����B <BR>
     *     �擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���� <BR>
     *     ��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00205<BR>
     * <BR>
     * (*)�����������Ŏg�p����B<BR>
     * <BR>
     * @@param l_datCheckDate - �m�F��������<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getOrderBizDate(Date l_datCheckDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        // this.get���M������()�ɂĔ��������擾����
        Date l_datCurrentBizDate = getMutualOrderBizDate();

        // �擾�����������ƈ����̊m�F�����������Ⴄ���t�ł����
        if(WEB3DateUtility.compareToDay(l_datCurrentBizDate, l_datCheckDate) != 0 )
        {
            log.debug("�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
        }

        //�@@�擾������������Ԃ��B
        log.exiting(STR_METHOD_NAME);
        log.debug("������ = " + l_datCurrentBizDate);
        return l_datCurrentBizDate;
    }

    /**
     * (get���M������)<BR>
     * �C�O�s��J�����_�[���l���������������擾����B <BR>
     *<BR>
     *�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B <BR>
     *�@@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     *      "xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     *�Q�j�@@��t���̉c�Ɠ��敪����B <BR>
     *�@@�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�͉c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B<BR>
     *�@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A�s�̉c�Ɠ��敪���擾����B <BR>
     *�@@�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     *�R�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B<BR>
     *�@@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     *�S�j�@@�����������擾 <BR>
     *�@@this.get������()��p���āA���������擾����B <BR>
     * <BR>
     *�T�j�@@�C�O�������擾 <BR>
     *�@@�|�擾�����������������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B <BR>
     *�@@�|is�x��()��true �̏ꍇ�A�������v�Z��p���ė��c�Ɠ����擾����B<BR>
     *�@@�i�C�O���������擾�ł���܂ŁA�J��Ԃ��j <BR>
     * <BR>
     *�@@�|is�x��()��false �̏ꍇ�A���̔�������ԋp����B<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getMutualOrderBizDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        //�R�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        //�@@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //�S�j�@@�����������擾
        //�@@this.get������()��p���āA���������擾����B
        Date l_datNationalBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //�T�j�@@�C�O�������擾
        //�@@�|�擾�����������������ɁA���M�C�O�s��J�����_.is�x��()���R�[������B
        WEB3AdminMutualFrgncal l_adminMutualFrgncal =
            new WEB3AdminMutualFrgncal();

        String l_strInstitutionCode = l_context.getInstitutionCode();
        String l_strProductCode = l_context.getProductCode();
        boolean l_blnIsHoliday =
            l_adminMutualFrgncal.isHoliday(
                l_strInstitutionCode,
                l_strProductCode,
                new Timestamp(l_datNationalBizDate.getTime()));

        //�@@�|is�x��()��true �̏ꍇ�A�������v�Z��p���ė��c�Ɠ����擾����B
        //�@@�i�C�O���������擾�ł���܂ŁA�J��Ԃ��j
        while(l_blnIsHoliday)
        {
            l_datNationalBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datNationalBizDate.getTime())).roll(1);
            log.debug("���������� = " + l_datNationalBizDate);

            l_blnIsHoliday =
                l_adminMutualFrgncal.isHoliday(
                    l_strInstitutionCode,
                    l_strProductCode,
                    new Timestamp(l_datNationalBizDate.getTime()));
        }

        log.exiting(STR_METHOD_NAME);
        log.debug("���M������ = " + l_datNationalBizDate);
        return l_datNationalBizDate;
    }

    /**
     * (get���M���c�Ɠ�)<BR>
     * �C�O�^�p���M�������l���������ݓ��t����̗��c�Ɠ����擾����B <BR>
     *�i�Ǘ��ҋ@@�\���������o�^�Ŏg�p�j <BR>
     * <BR>
     * <BR>
     *�P�jthis.get���M������()����A���������擾����B  <BR>
     * <BR>
     *�Q�j�@@�ȉ����J��Ԃ��B  <BR>
     *(1)�@@���c�Ɠ��i�����s��݂̂��l���j�̎擾  <BR>
     *�@@�E�c�Ɠ��v�Z�I�u�W�F�N�g�𐶐�����B  <BR>
     *�@@�@@�@@[�R���X�g���N�^�ɓn������]  <BR>
     *�@@�@@�@@�@@������O���roll()�̖߂�l  <BR>
     *�@@�Eroll()���R�[���B  <BR>
     *�@@�@@�@@[roll�ɓn������]  <BR>
     *�@@�@@�@@�@@���Z�^���Z�������P  <BR>
     * <BR>
     *(2)�@@�ȉ��̏����Łu�J�����_�[�e�[�u���v�������B <BR>
     *�@@�@@�@@[��������]  <BR>
     *�@@�@@�@@�@@���t��(1)��roll()�̖߂�l and  <BR>
     *�@@�@@�@@�@@�c�Ɠ��敪���h��c�Ɠ��h  <BR>
     * <BR>
     *(3)�@@(2)�̌������ʁ�0���̏ꍇ�A�C�O�s��J�����_�[.is�x��()���R�[������B  <BR>
     *�@@�@@�@@�@@�،���ЃR�[�h������.�،���ЃR�[�h  <BR>
     *�@@�@@�@@�@@�����R�[�h������.�����R�[�h  <BR>
     *�@@�@@�@@�@@���t��(1)��roll()�̖߂�l  <BR>
     * <BR>
     *�@@�Efalse���ԋp���ꂽ�ꍇ�A�J��Ԃ������𔲂��A(1)��roll()�̖߂�l��ԋp����B <BR>
     * <BR>
     *(4)�@@(2)�̌�������!=0���̏ꍇ�A�܂���(3)�̌��ʂ� true �̏ꍇ�A(1)�ɖ߂�B <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strMutualProductCode - �����R�[�h
     * @@return Timestamp
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Timestamp getMutualNextOrderBizDate(
            String l_strInstitutionCode,
            String l_strMutualProductCode
            ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualNextOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.get���M������()����A���������擾����B
        Date l_datBizDate = getMutualOrderBizDate();

        //�Q�j�@@�ȉ����J��Ԃ��B
        Timestamp l_datBaseDate = new Timestamp(l_datBizDate.getTime());
        boolean l_blnIsHoliday = true;
        do
        {
            //(1)�@@���c�Ɠ��i�����s��݂̂��l���j�̎擾
            //�@@�E�c�Ɠ��v�Z�I�u�W�F�N�g�𐶐�����B
            //�@@�@@�@@[�R���X�g���N�^�ɓn������]
            //�@@�@@�@@�@@������O���roll()�̖߂�l
            WEB3GentradeBizDate l_GentradeBizDate =
                new WEB3GentradeBizDate(l_datBaseDate);

            //�@@�Eroll()���R�[���B
            //�@@�@@�@@[roll�ɓn������]
            //�@@�@@�@@�@@���Z�^���Z�������P
            Timestamp l_datNextBizDate = l_GentradeBizDate.roll(1);

            //(2)�@@�ȉ��̏����Łu�J�����_�[�e�[�u���v�������B
            //�@@�@@�@@[��������]
            //�@@�@@�@@�@@���t��(1)��roll()�̖߂�l and
            //�@@�@@�@@�@@�c�Ɠ��敪���h��c�Ɠ��h
            String l_whereClause = "holiday = ? and biz_date_type = ?";
            Object l_bindVars[] = { l_datNextBizDate, WEB3BizDateTypeDef.NO_BIZ_DATE};
            List l_lisRows = null;
            try
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                        l_whereClause,
                        null,
                        l_bindVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //(3)�@@(2)�̌������ʁ�0���̏ꍇ�A�C�O�s��J�����_�[.is�x��()���R�[������B
            //�@@�@@�@@�@@�،���ЃR�[�h������.�،���ЃR�[�h
            //�@@�@@�@@�@@�����R�[�h������.�����R�[�h
            //�@@�@@�@@�@@���t��(1)��roll()�̖߂�l
            //�@@�Efalse���ԋp���ꂽ�ꍇ�A�J��Ԃ������𔲂��A(1)��roll()�̖߂�l��ԋp����B
            if(l_lisRows == null || l_lisRows.size() == 0)
            {
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                l_blnIsHoliday =
                    l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode,
                        l_strMutualProductCode,
                        l_datNextBizDate);
            }
            l_datBaseDate = l_datNextBizDate;
        }while(l_blnIsHoliday);

        log.exiting(STR_METHOD_NAME);
        log.debug("������ = " + l_datBizDate);
        return l_datBaseDate;
    }

    /**
     * (�������؎��ԍX�V)<BR>
     * �������؎��ԍX�V�������s���B <BR>
     * <BR>
     *�V�[�P���X�}�u�i���M�j�������؎��ԍX�V�v�Q��<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_arrBranchCodes[] - ���X�R�[�h�ꗗ
     * @@param l_strProductCode - �����R�[�h
     * @@param l_bizDateType - �c�Ɠ��敪
     * @@param l_strOrderCloseStartTime - �������؊J�n����
     * @@param l_strOrderCloseendTime - �������؏I������
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static void updateOrderCloseTime(
            String l_strInstitutionCode,
            String[] l_arrBranchCodes,
            String l_strProductCode,
            String l_bizDateType,
            String l_strOrderCloseStartTime,
            String l_strOrderCloseEndTime
            ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderCloseTime()";
        log.entering(STR_METHOD_NAME);

        if(l_arrBranchCodes == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "�Y���p�����[�^��Null�ł���");
        }

        // 1)���ݓ��t�̎擾
        Timestamp l_datSystemDate =
            GtlUtils.getTradingSystem( ).getSystemTimestamp();

        // 2)�g���K���s���ԑт̍X�V
        // �������؊J�n���Ԃ̓��͂�����ꍇ�̂ݎ��{
        if( l_strOrderCloseStartTime != null)
        {
            for(int i = 0; i < l_arrBranchCodes.length; i++)
            {
                // 2 - 1)�ȉ��̏����Łu������ԃe�[�u���v���������A�g���K���s���ԑт��擾����B
                // [��������]
                // �،���ЃR�[�h������.�،���ЃR�[�h and
                // ���X�R�[�h������.���X�R�[�h(*) and
                // �s��R�[�h���hDEFAULT�h and
                // ��t���ԋ敪���h�����M���h and
                // ���i�R�[�h������.�����R�[�h and
                // �c�Ɠ��敪������.�c�Ɠ��敪 and
                // �s��g���K���s���hSONAR��MQ�g���K�����{����h and
                // ��t�\���h��t�\�h and
                // �������v�Z���h�����h
                // [���я�]
                // �J�n���ԁ@@�~��
                String l_whereClause = " institution_code = ? and " +
                    " branch_code = ? and " +
                    " market_code = ? and " +
                    " trading_time_type = ? and " +
                    " product_code = ? and " +
                    " biz_date_type = ? and " +
                    " submit_market_trigger = ? and " +
                    " enable_order = ? and " +
                    " bizdate_calc_parameter = ? ";

                Object l_bindVars[] = {
                    l_strInstitutionCode,
                    l_arrBranchCodes[i],
                    WEB3MarketCodeDef.DEFAULT,
                    WEB3TradingTimeTypeDef.MUTUAL_FUND,
                    l_strProductCode,
                    l_bizDateType,
                    WEB3SubmitMarketTriggerDef.TRIGGER,
                    WEB3EnableOrderDef.ENABLE,
                    WEB3BizDateCalcParameterDef.DAY_BIZ_DATE};

                String l_strSortCond = " start_time ";
                List l_lisRows = null;
                try
                {
                    l_lisRows =
                        Processors.getDefaultProcessor().doFindAllQuery(
                            TradingTimeRow.TYPE,
                            l_whereClause,
                            l_strSortCond,
                            null,
                            l_bindVars);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("__DataNetworkException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("__DataQueryException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                TradingTimeParams l_tradingTimeParams = null;
                if(l_lisRows != null && l_lisRows.size() != 1)
                {
                    log.debug("__�f�[�^�s�����G���[�B__");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        "�f�[�^�s�����G���[�B");
                }
                else
                {
                    TradingTimeRow l_tradingTimeRow =
                        (TradingTimeRow) l_lisRows.get(0);

                    l_tradingTimeParams =
                        new TradingTimeParams(l_tradingTimeRow);

                    Date l_datStartTime =
                        WEB3DateUtility.getDate(l_strOrderCloseStartTime +"00", "HHmmss");
                    l_datStartTime = WEB3DateUtility.addSecond(
                        l_datStartTime,-1L);
                    l_tradingTimeParams.setEndTime(
                        WEB3DateUtility.formatDate(l_datStartTime, "HHmmss"));
                    l_tradingTimeParams.setLastUpdatedTimestamp(l_datSystemDate);
                }

                // 2 - 2) doUpdateQuery
                try
                {
                    log.debug("l_tradingTimeParams = " + l_tradingTimeParams);
                    Processors.getDefaultProcessor().doUpdateQuery(l_tradingTimeParams);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("__DataNetworkException__");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("__DataQueryException__");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        // 3) ����.���X�R�[�h�̗v�f�����A�J��Ԃ�
        for(int i = 0; i<l_arrBranchCodes.length; i++)
        {
            String l_strStartTime = null;
            String l_strEndTime = null;

            // 3 - 1)�ȉ��̏����Łu������ԃe�[�u���v���������A�h�����~���ԑсh���擾����B
            // [��������]
            // �،���ЃR�[�h������.�،���ЃR�[�h and
            // ���X�R�[�h������.���X�R�[�h[n](*) and
            // �s��R�[�h���hDEFAULT�h and
            // ��t���ԋ敪���h�����M���h and
            // ���i�R�[�h������.�����R�[�h and
            // �c�Ɠ��敪������.�c�Ɠ��敪 and
            // �s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h and
            // ��t�\���h��t�s�h and
            // �������v�Z���h���c�Ɠ��h
            // [���я�]
            // �J�n���ԁ@@�~��
            String l_whereClause = " institution_code = ? and " +
                " branch_code = ? and " +
                " market_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " submit_market_trigger = ? and " +
                " enable_order = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVars[] = {
                l_strInstitutionCode,
                l_arrBranchCodes[i],
                WEB3MarketCodeDef.DEFAULT,
                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                l_strProductCode,
                l_bizDateType,
                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                WEB3EnableOrderDef.DISABLED,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE};

            String l_strSortCond = " start_time ";
            List l_lisRows = null;
            try
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        TradingTimeRow.TYPE,
                        l_whereClause,
                        l_strSortCond,
                        null,
                        l_bindVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if(l_lisRows != null && l_lisRows.size() != 1)
            {
                log.debug("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    "�f�[�^�s�����G���[�B");
            }
            else
            {
                TradingTimeRow l_tradingTimeRow =
                    (TradingTimeRow) l_lisRows.get(0);

                if(l_strOrderCloseStartTime == null)
                {
                    if(l_tradingTimeRow.getStartTime().compareTo(l_tradingTimeRow.getEndTime()) == 0)
                    {
                        l_strStartTime = WEB3DateUtility.formatDate(
                            WEB3DateUtility.addSecond(
                                WEB3DateUtility.getDate(
                                    l_tradingTimeRow.getStartTime(), "HHmmss"),1L), "HHmmss");
                    }
                    else
                    {
                        l_strStartTime = l_tradingTimeRow.getStartTime();
                    }
                }
                else
                {
                    l_strStartTime = l_strOrderCloseStartTime + "00";
                }
                if(l_strOrderCloseEndTime == null)
                {
                    l_strEndTime = l_tradingTimeRow.getEndTime();
                }
                else
                {
                    l_strEndTime = l_strOrderCloseEndTime + "00";
                }
            }

            // 3 - 2) �ȉ��̏����ŁA�u������ԃe�[�u���v�ɍ폜�������s���B
            // [�폜����]
            // �،���ЃR�[�h������.�،���ЃR�[�h and
            // ���X�R�[�h������.���X�R�[�h[n] (*) and
            // �s��R�[�h���hDEFAULT�h and
            // ��t���ԋ敪���h�����M���h and
            // ���i�R�[�h������.�����R�[�h and
            // �c�Ɠ��敪������.�c�Ɠ��敪 and
            // �c�Ɠ��v�Z���h���c�Ɠ��h
            String l_whereClauseDelete = " institution_code = ? and " +
                " branch_code = ? and " +
                " market_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVarsDelete[] = {
                l_strInstitutionCode,
                l_arrBranchCodes[i],
                WEB3MarketCodeDef.DEFAULT,
                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                l_strProductCode,
                l_bizDateType,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE};

            try
            {
                log.debug("delete TradingTimeRow ! ");
                Processors.getDefaultProcessor().doDeleteAllQuery(
                    TradingTimeRow.TYPE,
                    l_whereClauseDelete,
                    l_bindVarsDelete);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 3 - 3) �ȉ��̐ݒ�l�������āu������ԃe�[�u���v�ɐV�K�s��ǉ�����B
            // [�ݒ�l]
            // �،���ЃR�[�h������.�،���ЃR�[�h
            // ���X�R�[�h������.���X�R�[�h[n] (*)
            // �s��R�[�h���hDEFAULT�h
            // ��t���ԋ敪���h�����M���h
            // ���i�R�[�h������.�����R�[�h
            // �c�Ɠ��敪������.�c�Ɠ��敪
            // �J�n���ԁ�����.�������؊J�n����(*)
            // (*) �������A����.�������؊J�n����=����.�������؏I�����Ԃ̏ꍇ�́A
            // �������؊J�n���Ԃ̂P�b�O�̎��Ԃ�ݒ肷��B
            // �I�����ԁ�����.�������؏I�����Ԃ̂P�b�O�̎���
            // �s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h
            // ��t�\���h��t�s�h
            // �������v�Z���h���c�Ɠ��h
            // �쐬���t�������ݓ��t���Ŏ擾�������t
            // �X�V���t�������ݓ��t���Ŏ擾�������t

            TradingTimeParams l_TradingTimePramas = new TradingTimeParams();
            l_TradingTimePramas.setInstitutionCode(l_strInstitutionCode);
            l_TradingTimePramas.setBranchCode(l_arrBranchCodes[i]);
            l_TradingTimePramas.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_TradingTimePramas.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            l_TradingTimePramas.setProductCode(l_strProductCode);
            l_TradingTimePramas.setBizDateType(l_bizDateType);
            l_TradingTimePramas.setStartTime(l_strOrderCloseStartTime);

            Date l_datStarTime =
                WEB3DateUtility.getDate(l_strStartTime, "HHmmss");
            Date l_datEndTime =
                WEB3DateUtility.getDate(l_strEndTime, "HHmmss");

            if (l_strOrderCloseEndTime == null)
            {
                Date l_datStarTimeMinus1s =
                    WEB3DateUtility.addSecond(l_datStarTime,-1L);
                if (WEB3DateUtility.compareTime(l_datStarTimeMinus1s, l_datEndTime) == 0)
                {
                    l_TradingTimePramas.setStartTime(
                        WEB3DateUtility.formatDate(l_datStarTimeMinus1s, "HHmmss"));
                }
                else
                {
                    l_TradingTimePramas.setStartTime(
                        WEB3DateUtility.formatDate(l_datStarTime, "HHmmss"));
                }

                l_TradingTimePramas.setEndTime(
                                WEB3DateUtility.formatDate(l_datEndTime, "HHmmss"));
            }
            else
            {
                if (WEB3DateUtility.compareTime(l_datStarTime, l_datEndTime) == 0)
                {

                    l_TradingTimePramas.setStartTime(
                        WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
                            l_datStarTime,-1L), "HHmmss"));
                }
                else
                {
                    l_TradingTimePramas.setStartTime(WEB3DateUtility.formatDate(l_datStarTime, "HHmmss"));
                }
                l_TradingTimePramas.setEndTime(
                    WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
                        l_datEndTime,-1L), "HHmmss"));
            }
            l_TradingTimePramas.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.NOT_TRIGGER);
            l_TradingTimePramas.setEnableOrder(WEB3EnableOrderDef.DISABLED);
            l_TradingTimePramas.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_TradingTimePramas.setCreatedTimestamp(l_datSystemDate);
            l_TradingTimePramas.setLastUpdatedTimestamp(l_datSystemDate);

            try
            {
                log.debug("l_TradingTimePramas = " + l_TradingTimePramas);
                Processors.getDefaultProcessor().doInsertQuery(l_TradingTimePramas);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 3 - 4)�ȉ��̐ݒ�l�������āu������ԃe�[�u���v�ɐV�K�s��ǉ�����B
            // [�ݒ�l]
            // �،���ЃR�[�h������.�،���ЃR�[�h
            // ���X�R�[�h������.���X�R�[�h[n] (*)
            // �s��R�[�h���hDEFAULT�h
            // ��t���ԋ敪���h�����M���h
            // ���i�R�[�h������.�����R�[�h
            // �c�Ɠ��敪������.�c�Ɠ��敪
            // �J�n���ԁ�����.�������؏I������
            // �I�����ԁ��h235959�h
            // �s��g���K���s���hSONAR��MQ�g���K�����{���Ȃ��h
            // ��t�\���h��t�\�h
            // �������v�Z���h���c�Ɠ��h
            // �쐬���t�������ݓ��t���Ŏ擾�������t
            // �X�V���t�������ݓ��t���Ŏ擾�������t

            TradingTimeParams l_TradingTimePramas2 = new TradingTimeParams();

            l_TradingTimePramas2.setInstitutionCode(l_strInstitutionCode);
            l_TradingTimePramas2.setBranchCode(l_arrBranchCodes[i]);
            l_TradingTimePramas2.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_TradingTimePramas2.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            l_TradingTimePramas2.setProductCode(l_strProductCode);
            l_TradingTimePramas2.setBizDateType(l_bizDateType);
            if (l_strOrderCloseEndTime == null)
            {
                l_TradingTimePramas2.setStartTime(
                    WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
                        l_datEndTime,1L), "HHmmss"));
            }
            else
            {
                l_TradingTimePramas2.setStartTime(
                    WEB3DateUtility.formatDate(l_datEndTime, "HHmmss"));
            }
            l_TradingTimePramas2.setEndTime("235959");
            l_TradingTimePramas2.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.NOT_TRIGGER);
            l_TradingTimePramas2.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_TradingTimePramas2.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_TradingTimePramas2.setCreatedTimestamp(l_datSystemDate);
            l_TradingTimePramas2.setLastUpdatedTimestamp(l_datSystemDate);

            try
            {
                log.debug("l_TradingTimePramas2 = " + l_TradingTimePramas2);
                Processors.getDefaultProcessor().doInsertQuery(l_TradingTimePramas2);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (set���t���[��)<BR>
	 * ���t���[�����Z�b�g����B<BR>
	 * <BR>
	 *  �|������ԃe�[�u���̓��e��LocalThread�ɃZ�b�g����B<BR>
	 *  �|���t���[����LocalThread�ɃZ�b�g����B<BR>
	 * <BR>
	 * �� ���t���[���̃Z�b�g<BR>
	 * ������ԊǗ��̊Y���s.�������v�Z == 0�F�����j�̏ꍇ�A�Z�b�g���Ȃ��B(*1)<BR>
	 * ������ԊǗ��̊Y���s.�������v�Z == 1�F���c�Ɠ��j�̏ꍇ�A�Z�b�g���Ȃ��B(*1)<BR>
	 * ������ԊǗ��̊Y���s.�������v�Z == 2�F2�c�Ɠ���j�̏ꍇ�A1<BR>
	 * <BR>
	 * (*1)���t���[�����Z�b�g���Ȃ��ꍇ�A�f�t�H���g�l�i0�j���Z�b�g�����B<BR>
	 *
	 * @@throws webbroker3.common.WEB3SystemLayerException
	 * @@roseuid 403D907801E4
	 */
	public static void setDateRole() throws WEB3SystemLayerException
	{
		final String STR_METHOD_NAME = "setDateRole()";
		log.entering(STR_METHOD_NAME);

		// Timestamp�̎擾
		java.sql.Timestamp l_processTime =
			(Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

		//ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����
		WEB3GentradeTradingClendarContext l_clendarContext =
			(WEB3GentradeTradingClendarContext)
				ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

		//�،���ЃR�[�h
		String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
		//�s��R�[�h
		String l_strMarketCode = l_clendarContext.getMarketCode();
		//���X�R�[�h
		String l_strBranchCode = l_clendarContext.getBranchCode();
		//��t���ԋ敪
		String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
		//�����R�[�h
		String l_strProductCode = l_clendarContext.getProductCode();
		//�c�Ɠ��敪
		String l_strBizDateType = getBizDateType(l_processTime);

		//�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A�f�[�^�s�����Ƃ���
		//��O���X���[����B
		//   ������ԃR���e�L�X�g.�،���ЃR�[�h
		// �@@������ԃR���e�L�X�g.���X�R�[�h
		// �@@������ԃR���e�L�X�g.��t���ԋ敪
		if ((l_strInstitutionCode == null)
			|| (l_strBranchCode == null)
			|| (l_strTradingTimeType == null))
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ STR_METHOD_NAME);
		}

		//������ԃe�[�u������������
		StringBuffer l_sbWhere = new StringBuffer();
		l_sbWhere.append(" institution_code = ? ");
		l_sbWhere.append(" and branch_code = ? ");
		l_sbWhere.append(" and trading_time_type = ? ");
		l_sbWhere.append(" and biz_date_type = ? ");

		ArrayList l_lstObjTradingTimeWhere = new ArrayList();
		//�،���ЃR�[�h
		l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
		//���X�R�[�h
		l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
		//��t���ԋ敪
		l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
		//�c�Ɠ��敪
		l_lstObjTradingTimeWhere.add(l_strBizDateType);

		//��t����
		SimpleDateFormat l_format =
			GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
		String l_strTime = l_format.format(l_processTime);

		//�s��R�[�h
		if (l_strMarketCode != null)
		{
			l_sbWhere.append(" and market_code = ? ");
			l_lstObjTradingTimeWhere.add(l_strMarketCode.trim());
		}
		//�����R�[�h
		if (l_strProductCode != null)
		{
			l_sbWhere.append(" and  product_code  = ? ");
			l_lstObjTradingTimeWhere.add(l_strProductCode.trim());
		}

		int l_intSize = l_lstObjTradingTimeWhere.size();
		Object[] l_objTradingTimeWhere = new Object[l_intSize];
		for (int i = 0; i < l_intSize; i++)
		{
			l_objTradingTimeWhere[i] = l_lstObjTradingTimeWhere.get(i);
		}

		List l_lisRecords = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_queryProcessor.doFindAllQuery(
				TradingTimeRow.TYPE,
				l_sbWhere.toString(),
				l_objTradingTimeWhere);
		}
		catch (DataFindException dfe)
		{
			log.error(STR_METHOD_NAME, dfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				dfe.getMessage(),
				dfe);
		}
		catch (DataQueryException dqe)
		{
			log.error(STR_METHOD_NAME, dqe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				dqe.getMessage(),
				dqe);
		}
		catch (DataNetworkException dne)
		{
			log.error(STR_METHOD_NAME, dne);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				dne.getMessage(),
				dne);
		}

		//�����`�F�b�N
		int l_intLength = l_lisRecords.size();
		if (l_intLength == 0)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "." + STR_METHOD_NAME,
				"������ԃe�[�u�������F ���� = 0");
		}
		TradingTimeRow l_tradingTimeRow = null;
		for (int i = 0; i < l_intLength; i++)
		{
			TradingTimeRow l_tmpTradingTimeRow =
				(TradingTimeRow) l_lisRecords.get(i);
			if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strTime)
				&& Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strTime))
			{
				l_tradingTimeRow = l_tmpTradingTimeRow;
				break;
			}
		}

		if (l_tradingTimeRow == null)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				"������ԃe�[�u�������F ���� = 0");
		}

		//set�c�Ɠ��敪
		l_clendarContext.setBizDateType(l_tradingTimeRow.getBizDateType());
		//set�s��g���K���s
		l_clendarContext.setSubmitMarketTrigger(
			l_tradingTimeRow.getSubmitMarketTrigger());
		//set�������v�Z
		l_clendarContext.setBizdateCalcParameter(
			l_tradingTimeRow.getBizdateCalcParameter());

		//set��t�\(�P���ł��u��t�\�v�ł���Β�����t�\�Ƃ���)
		String l_strEnableOrder = null;
		TradingTimeRow l_tempRow = null;

		for (int i = 0; i < l_intLength; i++)
		{
			l_tempRow = (TradingTimeRow) l_lisRecords.get(i);
			if (WEB3EnableOrderDef.ENABLE.equals(l_tempRow.getEnableOrder()))
			{
				l_strEnableOrder = l_tempRow.getEnableOrder();
			}
		}
		if (l_strEnableOrder == null)
		{
			l_strEnableOrder = WEB3EnableOrderDef.DISABLED;
		}
		l_clendarContext.setEnableOrder(l_strEnableOrder);

		// ������ԃR���e�L�X�g���Z�b�g����
		ThreadLocalSystemAttributesRegistry.setAttribute(
			TRADING_CAL_CONTEXT_PATH,
			l_clendarContext);

		// �c�Ɠ����[�����Z�b�g
		Integer l_offset = null;
		if (l_clendarContext.getBizdateCalcParameter().equals(
			WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE))
		{
			l_offset = new Integer(1);
		}
		else  if (l_clendarContext.getBizdateCalcParameter().equals(
			WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE))
		{
			l_offset = new Integer(0);
		}
		else
		{
			l_offset = new Integer(0);
		}
		ThreadLocalSystemAttributesRegistry.setAttribute(OFFSET_TAG, l_offset);

		log.debug(
			"����J�����_�L�[�F["
				+ "�،����="
				+ l_strInstitutionCode
				+ ", ���X="
				+ l_strBranchCode
				+ ", �s��="
				+ l_strMarketCode
				+ ", ������ԃ^�C�v="
				+ l_strTradingTimeType
				+ ", �c�Ɠ��^�C�v="
				+ l_strBizDateType
				+ ", �J�n����="
				+ l_tradingTimeRow.getStartTime()
				+ "]");
		log.debug("�c�Ɠ����[��:[" + l_clendarContext.getBizdateCalcParameter() + "]");

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (get�抷������)<BR>
     * �抷�����̔�������Ԃ��B<BR>
     * <BR>
     * �P�j����J�����_�R���e�L�X�g���抷������̏��Ń��Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �����R�[�h�F ����.�抷������R�[�h<BR>
     * <BR>
     * �P�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ������t�g�����U�N�V�����F �h���t�h<BR>
     * <BR>
     * �P�|�R�j���M������ԊǗ�.set���t���[��()���R�[������B<BR>
     * <BR>
     * �Q�j�抷������̔��������擾����B<BR>
     * <BR>
     *    this.get������()���R�[������B<BR>
     * <BR>
     *    ��(A)�Ƃ���B<BR>
     * <BR>
     * �R�j����J�����_�R���e�L�X�g���抷�������̏��Ń��Z�b�g����B<BR>
     * <BR>
     * �R�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �����R�[�h�F ����.�抷�������R�[�h<BR>
     * <BR>
     * �R�|�Q�j���M������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ������t�g�����U�N�V�����F �h���t�h<BR>
     * <BR>
     * �R�|�R�j���M������ԊǗ�.set���t���[��()���R�[������B<BR>
     * <BR>
     * �S�j�抷�������̔��������擾����B<BR>
     * <BR>
     *    this.get������()���R�[������B<BR>
     * <BR>
     *    ��(B)�Ƃ���B<BR>
     * <BR>
     * �T�j�����������肵�ԋp����B<BR>
     * <BR>
     * �T�|�P�j�抷�������̔������i=(A)�j == �抷������̔������i=(B)�j �̏ꍇ�A
     * �抷�������̔�������ԋp����B<BR>
     * <BR>
     * �T�|�Q�j(A) != (B) �̏ꍇ�A(A) == (B) �ƂȂ�܂ňȉ��̏������s���A(A)��ԋp����B<BR>
     * <BR>
     * �T�|�Q�|�P�j(A) < (B) �̏ꍇ�A(A)�̗��c�Ɠ����Z�o���A�����(A)�Ƃ���B<BR>
     * <BR>
     *     this.get���M���c�Ɠ�()���R�[������B<BR>
     * <BR>
     *     [����]<BR>
     *     �،���ЃR�[�h�F ����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR>
     *     �����R�[�h�F ����.�抷�������R�[�h<BR>
     *     ����F (A)<BR>
     * <BR>
     * �T�|�Q�|�Q�j(A) > (B) �̏ꍇ�A(B)�̗��c�Ɠ����Z�o���A�����(B)�Ƃ���B<BR>
     * <BR>
     *     this.get���M���c�Ɠ�()���R�[������B<BR>
     * <BR>
     *     [����]<BR>
     *     �،���ЃR�[�h�F ����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR>
     *     �����R�[�h�F ����.�抷������R�[�h<BR>
     *     ����F (B)<BR>
     * <BR>
     * @@param l_strProductCode - �抷�������R�[�h
     * @@param l_strSwtProductCode - �抷������R�[�h
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getSwtOrderBizDate(
    		String l_strProductCode,
    		String l_strSwtProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSwtOrderBizDate(String,String)";
        log.entering(STR_METHOD_NAME);

        // �P�j����J�����_�R���e�L�X�g���抷������̏��Ń��Z�b�g����B
        // �P�|�P�j���M������ԊǗ�.reset�����R�[�h()���R�[������B
        // [����]
        // �����R�[�h�F ����.�抷������R�[�h
		resetProductCode(l_strSwtProductCode);

        // �P�|�Q�jthis.reset������t�g�����U�N�V����()���R�[������B
        // [����]
        // ������t�g�����U�N�V�����F �h���t�h
        resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        
        // �P�|�R�jthis.set���t���[��()���R�[������B
        setDateRole();
        
        // �Q�j�抷������̔��������擾����B
        // this.get���M������()���R�[������B
        Date l_datOrderBizDate = getMutualOrderBizDate();

        // �R�j����J�����_�R���e�L�X�g���抷�������̏��Ń��Z�b�g����B
        // �R�|�P�jthis.reset�����R�[�h()���R�[������B
        // [����]
        // �����R�[�h�F ����.�抷�������R�[�h
		resetProductCode(l_strProductCode);

        // �R�|�Q�jthis.reset������t�g�����U�N�V����()���R�[������B
        // [����]<BR>
        // ������t�g�����U�N�V�����F �h���t�h
        resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        // �R�|�R�jthis.set���t���[��()���R�[������B
        setDateRole();
        
        // �S�j�抷�������̔��������擾����B
        // this.get���M������()���R�[������B
        Date l_datSwtOrderBizDate = getMutualOrderBizDate();
        
        // �T�j�����������肵�ԋp����B
        if (l_datOrderBizDate.equals(l_datSwtOrderBizDate))
        {
            // �T�|�P�j�抷������̔������i=(A)�j == �抷�������̔������i=(B)�j �̏ꍇ�A
            // �抷�������̔�������ԋp����B
        	return l_datSwtOrderBizDate;
        }
        else 
        {
            // �T�|�Q�j(A) != (B) �̏ꍇ�A(A) == (B) �ƂȂ�܂ňȉ��̏������s���A(B)��ԋp����B<BR>
        	while (!l_datOrderBizDate.equals(l_datSwtOrderBizDate))
        	{
                //����J�����_�R���e�L�X�g�擾
                WEB3GentradeTradingClendarContext l_context =
                    (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

                String l_strInstitutionCode = l_context.getInstitutionCode();

                if (l_datOrderBizDate.compareTo(l_datSwtOrderBizDate) < 0)
        		{
        	        //�T�|�Q�|�P�j(A) < (B) �̏ꍇ�A(A)�̗��c�Ɠ����Z�o���A�����(A)�Ƃ���B<BR>
        	        //     this.get���M���c�Ɠ�()���R�[������B<BR>
        	        //     [����]<BR>
        	        //     �،���ЃR�[�h�F ����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR>
        	        //     �����R�[�h�F ����.�抷������R�[�h<BR>
        	        //     ����F (A)<BR>
                	l_datOrderBizDate =
                		getMutualNextOrderBizDate(
                			l_strInstitutionCode,
                			l_strSwtProductCode,
                			l_datOrderBizDate);
        		}
        		else 
        		{
        	        // �T�|�Q�|�Q�j(A) > (B) �̏ꍇ�A(B)�̗��c�Ɠ����Z�o���A�����(B)�Ƃ���B<BR>
        	        //     this.get���M���c�Ɠ�()���R�[������B<BR>
        	        //     [����]<BR>
        	        //     �،���ЃR�[�h�F ����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR>
        	        //     �����R�[�h�F ����.�抷�������R�[�h<BR>
        	        //     ����F (B)<BR>
                	l_datSwtOrderBizDate =
                		getMutualNextOrderBizDate(
                			l_strInstitutionCode,
                			l_strProductCode,
                			l_datSwtOrderBizDate);
        		}
        	}
        }

        log.exiting(STR_METHOD_NAME);
        log.debug("�抷������ = " + l_datSwtOrderBizDate);
        
        return l_datSwtOrderBizDate;
    }

    /**
     * (get�抷������)<BR>
     * �抷�����̔��������擾����B<BR>
     *     this.get�抷������()�ɂĔ��������擾����B <BR>
     *     �擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���� <BR>
     *     ��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00205<BR>
     * <BR>
     * (*)�抷�����̊m�F�E�����������Ŏg�p����B<BR>
     * <BR>
     * @@param l_strProductCode - �抷�������R�[�h
     * @@param l_strSwtProductCode - �抷������R�[�h
     * @@param l_datCheckDate - �m�F��������<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getSwtOrderBizDate(
    		String l_strProductCode,
    		String l_strSwtProductCode,
    		Date l_datCheckDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSwtOrderBizDate(String,String,Date)";
        log.entering(STR_METHOD_NAME);

        // this.get�抷������()�ɂĔ��������擾����
        Date l_datCurrentBizDate = getSwtOrderBizDate(l_strProductCode,l_strSwtProductCode);

        // �擾�����������ƈ����̊m�F�����������Ⴄ���t�ł����
        if(WEB3DateUtility.compareToDay(l_datCurrentBizDate, l_datCheckDate) != 0 )
        {
            log.debug("�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
        }

        //�@@�擾������������Ԃ��B
        log.exiting(STR_METHOD_NAME);
        log.debug("������ = " + l_datCurrentBizDate);
        
        return l_datCurrentBizDate;
    }

    /**
     * (get���M���c�Ɠ�)<BR>
     * �C�O�^�p���M�������l�������������̗��c�Ɠ����擾����B<BR>
     * <BR>
     * ����.�����(A)�Ƃ��A�ȉ����J��Ԃ��B<BR>
     * <BR>
     * �P�j�@@���c�Ɠ��i�����s��݂̂��l���j�̎擾<BR>
     *�@@�E�c�Ɠ��v�Z�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@�@@�@@[�R���X�g���N�^�ɓn������]<BR>
     *�@@�@@�@@�@@�����(A)<BR>
     *�@@�Eroll()���R�[���B<BR>
     *�@@�@@�@@[roll�ɓn������]<BR>
     *�@@�@@�@@�@@���Z�^���Z�������P<BR>
     * <BR>
     * �Q�j�@@�ȉ��̏����Łu�J�����_�[�e�[�u���v�������B<BR>
     *�@@�@@�@@[��������]<BR>
     *�@@�@@�@@�@@���t���P�j�̖߂�l and<BR>
     *�@@�@@�@@�@@�c�Ɠ��敪���h��c�Ɠ��h<BR>
     * <BR>
     * �R�j�@@�Q�j�̌������ʁ�0���̏ꍇ�A�C�O�s��J�����_�[.is�x��()���R�[������B<BR>
     *�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *�@@�@@�@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     *�@@�@@�@@�@@���t�F �P�j�̖߂�l<BR>
     * <BR>
     *�@@�Efalse���ԋp���ꂽ�ꍇ�A�J��Ԃ������𔲂��A�P�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * �S�j�@@�Q�j�̌�������!=0�� �܂��� �R�j�̌���==true �̏ꍇ�A�P�j�̖߂�l��(A)�Ƃ��A�P�j�ɖ߂�B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strMutualProductCode - �����R�[�h
     * @@param l_datOrgBaseDate - ���
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getMutualNextOrderBizDate(
            String l_strInstitutionCode,
            String l_strMutualProductCode,
            Date l_datOrgBaseDate
            ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualNextOrderBizDate(String,String,Date)";
        log.entering(STR_METHOD_NAME);

        //�Q�j�@@����.�����(A)�Ƃ��A�ȉ����J��Ԃ��B
        Timestamp l_datBaseDate = new Timestamp(l_datOrgBaseDate.getTime());
        boolean l_blnIsHoliday = true;
        do
        {
            //�P�j�@@���c�Ɠ��i�����s��݂̂��l���j�̎擾
            //�@@�E�c�Ɠ��v�Z�I�u�W�F�N�g�𐶐�����B
            //�@@�@@�@@[�R���X�g���N�^�ɓn������]
            //�@@�@@�@@�@@�����(A)
            WEB3GentradeBizDate l_GentradeBizDate =
                new WEB3GentradeBizDate(l_datBaseDate);

            //�@@�Eroll()���R�[���B
            //�@@�@@�@@[roll�ɓn������]
            //�@@�@@�@@�@@���Z�^���Z�������P
            Timestamp l_datNextBizDate = l_GentradeBizDate.roll(1);

            //�Q�j�@@�ȉ��̏����Łu�J�����_�[�e�[�u���v�������B
            //�@@�@@�@@[��������]
            //�@@�@@�@@�@@���t���P�j�̖߂�l and
            //�@@�@@�@@�@@�c�Ɠ��敪���h��c�Ɠ��h
            String l_whereClause = "holiday = ? and biz_date_type = ?";
            Object l_bindVars[] = { l_datNextBizDate, WEB3BizDateTypeDef.NO_BIZ_DATE};
            List l_lisRows = null;
            try
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                        l_whereClause,
                        null,
                        l_bindVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�R�j�@@�Q�j�̌������ʁ�0���̏ꍇ�A�C�O�s��J�����_�[.is�x��()���R�[������B
            //�@@�@@�@@�@@[����]
            //�@@�@@�@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h
            //�@@�@@�@@�@@�����R�[�h�F ����.�����R�[�h
            //�@@�@@�@@�@@���t�F �P�j�̖߂�l
            //�@@�Efalse���ԋp���ꂽ�ꍇ�A�J��Ԃ������𔲂��A�P�j�̖߂�l��ԋp����B
            if(l_lisRows == null || l_lisRows.size() == 0)
            {
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                l_blnIsHoliday =
                    l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode,
                        l_strMutualProductCode,
                        l_datNextBizDate);
            }
            
            l_datBaseDate = l_datNextBizDate;
        }while(l_blnIsHoliday);

        log.exiting(STR_METHOD_NAME);
        log.debug("���c�Ɠ� = " + l_datBaseDate);
        
        return l_datBaseDate;
    }
    
    /**
     * (get���M������)<BR>
     * �ꊇ���M���l���������������擾����B<BR> 
     * <BR>
     * �P�j�@@���M���������擾����B<BR> 
     * �@@�@@�@@this�Dget���M������(��������)���R�[������B <BR>
     * <BR>
     * �Q) �����F�ꊇ�敪 = false�̏ꍇ <BR>
     * �@@�@@�@@�߂�l�Ƃ��āA���M��������Ԃ� <BR>
     * <BR>    
     *     �R�j�����F�ꊇ�敪 = true�̏ꍇ<BR> 
     *  <BR>   
     * �@@�@@�@@�@@�R)�|�P�@@���X���擾����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W���Dget���X()<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[get���X�̈���] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F����J�����_�[�R���e�L�X�g�D�،���ЃR�[�h<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���X�R�[�h�@@�@@�@@�F����J�����_�[�R���e�L�X�g�D���X�R�[�h<BR>
     * <BR>        
     *�@@�@@�@@�@@ �R�j�|�Q�@@�g�����M�������擾����B<BR>   
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�g�����M�����}�l�[�W���Dget���M����() <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[get���M����()�̈���] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�،���ЁF�擾�������X�DgetInstitution()<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h�F ����J�����_�R���e�L�X�g�D�����R�[�h<BR> 
     * <BR>                                      
     * �@@�@@�@@�@@�R�j�|�R wk�I�����Ɉȉ��̒l���Z�b�g����B<BR> 
     * �@@�@@�@@�@@�@@�@@�R�j�|�R�|�P�@@�����F������ʁ@@= 201�F�����M���������̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@wk�I���� =�@@���t�I�����i*1�j<BR> 
     * <BR>                                          
     * �@@�@@�@@�@@�@@�@@�R�j�|�R�|�Q�@@�����F������ʁ@@= 202�F�����M���������̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@wk�I���� =�@@���抷�I�����i*2�j<BR> 
     *   <BR>                                            
     * �@@�@@�@@�@@�@@�@@�R�j�|�R�|�R�@@�����F������ʁ@@= 203�F�����M����W�����̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@wk�I���� =�@@��W�I�����i*3�j<BR> 
     * <BR>                                                  
     * �@@�@@�@@�@@�@@�@@�R�j�|�R�|�S�@@�����F������ʂ���L�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l�Ƃ��āA���M��������Ԃ��B<BR> 
     * <BR>                                                  
     * �@@�@@�@@�@@�R�j�|�S�@@wk�I�����@@< ���M�������̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l�Ƃ��āA���M��������Ԃ��B<BR> 
     *  <BR>                                                 
     * �@@�@@�@@�@@�R�j�|�T�@@�R�j�|�S�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l�Ƃ��āAwk�I������Ԃ��B<BR> 
     * <BR>                                                  
     * �i*1�j���t�I�����@@�@@�@@ �F�@@((MutualFundProductRow) �擾�����g�����M����.getDataSourceObject()).<BR>
     * get���t�I����()<BR>
     * �i*2�j���抷�I���� �F�@@((MutualFundProductRow) �擾�����g�����M����.getDataSourceObject()).<BR>
     * get���抷�I����() <BR>
     * �i*3�j��W�I�����@@�@@�@@ �F�@@�擾�����g�����M�����Dget��W�I���� <BR>
     * @@param l_orderType - (�������)<BR>
     * ������� <BR>
     * <BR>
     * 201�F�����M�������� <BR>
     * 202�F�����M�������� <BR>
     * 203�F�����M����W����<BR>
     * @@param l_blnNorealDiv - (�ꊇ�敪)<BR>
     * �ꊇ�敪<BR> 
     * <BR>
     * �P) ��W�����̏ꍇ <BR>
     * �@@�P)�|�P ���X�p�v���t�@@�����X�D���M��W�����ꊇ���M�敪 = �u�ꊇ���M����v�̏ꍇ��true<BR> 
     * �@@�P)�|�Q ����ȊO�̏ꍇ��false<BR> 
     * <BR>
     * �Q) ���t/���p�����̏ꍇ<BR> 
     * �@@�Q�|�P) ���M�����}�X�^�[�D�������������敪 = �������������̏ꍇ��true<BR> 
     * �@@�Q�|�Q) ����ȊO�̏ꍇ��false <BR>
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public static Date getMutualOrderBizDate(
        OrderTypeEnum l_orderType, boolean l_blnNorealDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate(OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���M���������擾����B 
        // �@@this�Dget���M������(��������)���R�[������B 
        Date l_datOrderBizDate = getMutualOrderBizDate();
        
        //�Q) �����F�ꊇ�敪 = false�̏ꍇ 
        //�߂�l�Ƃ��āA���M��������Ԃ� 
        if (!l_blnNorealDiv)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datOrderBizDate;
        }

        //�R�j�����F�ꊇ�敪 = true�̏ꍇ 
        else
        {
            //�R)�|�P�@@���X���擾����B 
            // �@@�g���A�J�E���g�}�l�[�W���Dget���X() 
            // �@@[get���X�̈���] 
            // �@@�،���ЃR�[�h�F����J�����_�[�R���e�L�X�g�D�،���ЃR�[�h 
            // �@@���X�R�[�h�@@�@@�@@�F����J�����_�[�R���e�L�X�g�D���X�R�[�h 
            
            //������ԃR���e�L�X�g�̎擾
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    TRADING_CAL_CONTEXT_PATH);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = null;
            Branch l_branch = null;
            Date l_datWkEndDate = null;
            try
            {
                l_institution = l_accMgr.getInstitution(l_clendarContext.getInstitutionCode());
                l_branch = l_accMgr.getBranch(l_institution, l_clendarContext.getBranchCode());
                
                //�R�j�|�Q�@@�g�����M�������擾����B   
                //   �g�����M�����}�l�[�W���Dget���M����() 
                //    [get���M����()�̈���] 
                //    �،���ЁF�擾�������X�DgetInstitution() 
                //    �����R�[�h�F ����J�����_�R���e�L�X�g�D�����R�[�h 
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
                WEB3MutualFundProductManager l_mfProductManager = 
                    (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
                MutualFundProduct l_mfProduct = l_mfProductManager.getMutualFundProduct(
                    l_branch.getInstitution(), l_clendarContext.getProductCode());
                
                //�R�j�|�R wk�I�����Ɉȉ��̒l���Z�b�g����B 
                //   �R�j�|�R�|�P�@@�����F������ʁ@@= 201�F�����M���������̏ꍇ 
                //     �@@�@@�@@�@@�@@�@@�@@wk�I���� =�@@���t�I�����i*1�j 
                MutualFundProductRow l_mfProductRow = 
                    (MutualFundProductRow) l_mfProduct.getDataSourceObject();
                if (OrderTypeEnum.MF_BUY.equals(l_orderType))
                {
                    l_datWkEndDate = l_mfProductRow.getBuyEndDate();
                }
                else if (OrderTypeEnum.MF_SELL.equals(l_orderType))
                {
                    //   �R�j�|�R�|�Q�@@�����F������ʁ@@= 202�F�����M���������̏ꍇ 
                    //     �@@�@@�@@�@@�@@�@@�@@wk�I���� =�@@���抷�I�����i*2�j 
                    l_datWkEndDate = l_mfProductRow.getSellSwtEndDate();
                }
                else if (OrderTypeEnum.MF_RECRUIT.equals(l_orderType))
                {
                    //   �R�j�|�R�|�R�@@�����F������ʁ@@= 203�F�����M����W�����̏ꍇ 
                    //     �@@�@@�@@�@@�@@�@@�@@wk�I���� =�@@��W�I�����i*3�j 
                    l_datWkEndDate = l_mfProductRow.getRecruitEndDate();
                }
                else
                {
                    //   �R�j�|�R�|�S�@@�����F������ʂ���L�ȊO�̏ꍇ 
                    //     �@@�@@�@@�@@�@@�@@�@@�߂�l�Ƃ��āA���M��������Ԃ��B 
                    l_datWkEndDate = l_datOrderBizDate;
                }

                //   �R�j�|�S�@@wk�I�����@@< ���M�������̏ꍇ 
                //     �@@�@@�@@�@@�@@�@@�@@�߂�l�Ƃ��āA���M��������Ԃ��B 
                if (WEB3DateUtility.compare(l_datWkEndDate, l_datOrderBizDate) < 0)
                {
                    l_datWkEndDate = l_datOrderBizDate;
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("Error In ���X�A�،���Ђ܂��͖����R�[�h���擾���� ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //   �R�j�|�T�@@�R�j�|�S�ȊO�̏ꍇ 
            //     �@@�@@�@@�@@�@@�@@�@@�߂�l�Ƃ��āAwk�I������Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_datWkEndDate;
        }
    }
    
    /**
     * (get������)<BR>
     * �ꊇ���M���l���������������擾����B <BR>
     * <BR>
     * �P�j ���M���������擾����B<BR> 
     * �@@�@@this�Dget���M������() <BR>
     * �@@�@@[get���M�������̈���] <BR>
     * �@@�@@������� �F �����D������� <BR>
     * �@@�@@�ꊇ�敪 �F �����D�ꊇ�敪<BR> 
     * <BR>
     * �Q�j �擾�������M�������iYYYYMMDD�j  !=  �����D�m�F���������iYYYYMMDD�j�̏ꍇ<BR> 
     * �@@�@@��O�i�������̃`�F�b�N�G���[�FBUSINESS_ERROR_00205�j���X���[����B<BR> 
     * <BR>
     * �R�j �Q�j�ŗ�O���������Ȃ������ꍇ�́A�����D�m�F����������Ԃ��B<BR>
     * @@param l_datCheckDate - (�m�F��������)<BR>
     * �m�F��������
     * @@param l_orderType - (�������)<BR>
     * �������<BR> 
     * <BR>
     * 201�F�����M��������<BR> 
     * 202�F�����M��������<BR> 
     * 203�F�����M����W����
     * @@param l_blnNorealDiv - (�ꊇ�敪)<BR>
     * �ꊇ�敪<BR> 
     * <BR>
     * �P) ��W�����̏ꍇ<BR> 
     * �@@�P)�|�P ���X�p�v���t�@@�����X�D���M��W�����ꊇ���M�敪 = �u�ꊇ���M����v�̏ꍇ��true<BR> 
     * �@@�P)�|�Q ����ȊO�̏ꍇ��false<BR> 
     * <BR>
     * �Q) ���t/���p�����̏ꍇ<BR> 
     * �@@�Q�|�P) ���M�����}�X�^�[�D�������������敪 = �������������̏ꍇ��true<BR> 
     * �@@�Q�|�Q) ����ȊO�̏ꍇ��false 
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public static Date getOrderBizDate(
        Date l_datCheckDate, 
        OrderTypeEnum l_orderType, 
        boolean l_blnNorealDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate(OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j ���M���������擾����B 
        //  this�Dget���M������() 
        //  [get���M�������̈���] 
        //  ������� �F �����D������� 
        //  �ꊇ�敪 �F �����D�ꊇ�敪
        Date l_datMutualOrderBizDate = getMutualOrderBizDate(l_orderType, l_blnNorealDiv);
        
        //�Q�j �擾�������M�������iYYYYMMDD�j  !=  �����D�m�F���������iYYYYMMDD�j�̏ꍇ 
        //  ��O�i�������̃`�F�b�N�G���[�FBUSINESS_ERROR_00205�j���X���[����B 
        if (WEB3DateUtility.compareToDay(l_datMutualOrderBizDate, l_datCheckDate) != 0)
        {
            log.debug("�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
        }
        //�R�j �Q�j�ŗ�O���������Ȃ������ꍇ�́A�����D�m�F����������Ԃ��B
        
        log.exiting(STR_METHOD_NAME);
        return l_datCheckDate;      
    }

    
    /**
     * (get��������������񔭒���)<BR>
     * �i�m�F/���������p�j<BR>
     * �������������̉��抷�I�������擾���A�������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j ���M�����}�X�^�D���抷�I�������擾����B<BR> 
     * <BR>
     * �Q�j �擾�������抷�I�����iYYYYMMDD�j  !=  �����D�m�F���������iYYYYMMDD�j�̏ꍇ<BR> 
     * �@@�@@��O�i�������̃`�F�b�N�G���[�FBUSINESS_ERROR_00205�j���X���[����B<BR> 
     * <BR>
     * �R�j �Q�j�ŗ�O���������Ȃ������ꍇ�́A�����D�m�F����������Ԃ��B<BR>
     * <BR>
     * @@param l_datCheckDate - (�m�F��������)<BR>
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public static Date getUnitTypeProductSellOrderBizDate(
        Date l_datCheckDate ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate(OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j  ���M�����}�X�^�D���抷�I�������擾����B 
        Date l_datSellSwtEndDate = null;
        
        //�g�����M�����N���X
        MutualFundProduct l_mfProduct = null;

        //�@@����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        if (l_context == null)
           {
               log.debug("�v���I�ȃV�X�e���G���[�B");
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   "WEB3MutualFundTradingTimeManagement"+ "." + STR_METHOD_NAME,
                   "����J�����_�R���e�L�X�g���擾�ł��Ȃ�");
           }

        //�،����
        Institution l_institution = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�g���A�J�E���g�}�l�[�W���擾����B
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //�،���Ў擾
            l_institution =
                (Institution) l_accMgr.getInstitution(
                    l_context.getInstitutionCode());

            //�g�����M�����I�u�W�F�N�g���擾����B
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
            l_mfProduct = l_mutualFundProductManager.getMutualFundProduct(
            		 l_institution,
            		 l_context.getProductCode());
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_mfProduct.getDataSourceObject();

            //���M�����}�X�^�D���抷�I�������擾����B
            l_datSellSwtEndDate = l_mfProductRow.getSellSwtEndDate();

            
        }
        catch (NotFoundException l_ex)
        {
            log.error("Not Found  " +
                "(�،����)l_institution =  " +
                    l_institution +
                " and (�����R�[�h)l_strProductCode = " +
                    l_context.getProductCode());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        
        //�Q�j �擾�������抷�I�����iYYYYMMDD�j  !=  �����D�m�F���������iYYYYMMDD�j�̏ꍇ 
        //  ��O�i�������̃`�F�b�N�G���[�FBUSINESS_ERROR_00205�j���X���[����B 
        if (WEB3DateUtility.compareToDay(l_datSellSwtEndDate, l_datCheckDate) != 0)
        {
            log.debug("�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���");
        }
        //�R�j �Q�j�ŗ�O���������Ȃ������ꍇ�́A�����D�m�F����������Ԃ��B
        
        log.exiting(STR_METHOD_NAME);
        return l_datCheckDate;      
    }
 
   
}
@
