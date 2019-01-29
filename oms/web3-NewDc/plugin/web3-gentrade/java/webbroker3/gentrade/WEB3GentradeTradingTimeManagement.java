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
filename	WEB3GentradeTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������ԊǗ�(WEB3GentradeTradingTimeManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 羐� (���u) �V�K�쐬
Revesion History : 2004/08/11 �Г� (���u) �ύX027��Ή�
Revesion History : 2004/08/11 WEB3-XBIFO-A-CD-0088��Ή�
Revesion History : 2005/07/08 �Г� (���u) get������(void)�ɊO��������ǉ�
Revesion History : 2005/07/08 �Г� (���u) get�s��ǌx���O���s���ǉ�
Revesion History : 2005/07/20 �Ή� (���u) isTradeCloseTimeZone()��ǉ�
Revesion History : 2005/07/20 �Ή� (���u) isTradeCloseTimeZone(Date)��ǉ�
Revesion History : 2005/07/21 �Ή� (���u) get������(void)�ɊO���A�g������ǉ�
Revesion History : 2005/07/25 �Г� (���u) is�g���K���s()���C��
Revesion History : 2005/10/07 �Г� (���u) is�g���K���s()���C��
Revesion History : 2005/10/07 �Г� (���u) validate�A��������t�\()��ǉ�
Revesion History : 2005/10/18 �Г� (���u) is�s��J�ǎ��ԑ�()���C��
Revesion History : 2005/11/30 �Г� (���u) validate�_�E�����[�h���ԑ�()��ǉ�
Revesion History : 2006/02/16 ������ (���u) ���ʎd�l�ύX�Ǘ��䒠No.174�A175
Revesion History : 2006/02/26 ������ (���u) ���ʎd�l�ύX�Ǘ��䒠No.177
Revesion History : 2006/03/24 ������ (���u) ���ʎd�l�ύX�Ǘ��䒠No.181��Ή�
Revesion History : 2006/05/11 ������ (���u)�y���ʁz�d�l�ύX�E���f��No.189
Revesion History : 2006/06/01 ������ (���u)�y���ʁz�d�l�ύX�E���f��No.195
Revesion History : 2007/06/11 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.233�A237�A239�A243�A244�A246
Revesion History : 2007/06/15 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.248
Revesion History : 2007/06/15 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.249
Revesion History : 2007/06/22 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.253
Revesion History : 2007/06/27 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.251
Revesion History : 2007/06/27 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.256
Revesion History : 2007/06/29 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.254
Revesion History : 2007/07/03 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.259
Revesion History : 2007/07/05 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.263
Revesion History : 2007/12/17 ���g (���u)�y���ʁz�d�l�ύX�E���f��No.289
Revesion History : 2007/12/18 ���n�m (���u)�y���ʁz�d�l�ύX�E���f��No.296
Revesion History : 2008/03/13 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.321
Revesion History : 2008/04/16 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.325
Revesion History : 2008/04/19 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.326
Revesion History : 2008/04/21 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.327
Revesion History : 2008/12/25 �V�� (FTL) getBizDateType(Timestamp)����L�[�����ɕύX
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAcceptStatusDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.CalendarDao;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FeqCalendarDao;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.OrderexecutionEndDao;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * @@author �� ��
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
/**
 * ������ԊǗ�<BR>
 * ������ԂɊ֘A�����葱����񋟂���N���X�B<BR>
 */
public class WEB3GentradeTradingTimeManagement
{

    /**
     * ThreadLocal�ɕۑ������t�����̕ϐ����B
     */
    public static String TIMESTAMP_TAG = "xblocks.gtl.attributes.systemtimestamp";

    /**
     * ThreadLocal�ɕۑ�����I�t�Z�b�g�̕ϐ����B
     */
    public static String OFFSET_TAG = "xblocks.gtl.attributes.bizdate.offset";

    /**
     * ThreadLocal�ɕۑ��������J�����_�R���e�L�X�g�̕ϐ����B
     */
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";

    /**
     * ThreadLocal�ɕۑ�����A�������v�Z�p�̊�����̕ϐ����B
     */
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";

    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingTimeManagement.class);

    /**
     * validate������t�\<BR>
     * ������t�\�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ً}��~�A�o�b�`���`�F�b�N<BR>
     * ������t�X�e�C�^�X�e�[�u��������J�����_�R���e�L�X�g�̓��e�œǂ݁A<BR>
     * �擾�����s�̒�����t�X�e�C�^�X���h�ʏ�h�łȂ��ꍇ�͗�O���X���[����B<BR>
     * �i������t�s�̃X�e�C�^�X�ɂ́A�h�o�b�`�������h�A�h�ً}��~���h��<BR>
     * ����A��O���b�Z�[�W���킯��j<BR>
     * -�o�b�`������-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00011<BR>
     * -�V�X�e���ً}��~��-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00012<BR>
     * <BR>
     * �Q�j�@@������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j<BR>
     *  �̏ꍇ�́A�ȍ~�̏����͍s�킸��return����B<BR>
     *  ������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j�� <BR>
     *  �ꍇ�́A�ȉ��̏����𑱍s����B <BR>
     * �R�j�@@��t�s���ԑу`�F�b�N<BR>
     * �@@������ԃe�[�u�����ȉ��̏����Ō������A�Y���s�́u��t�\�v<BR>
     * ���ڂ�"��t�s��"�ł���΁A������t�s�Ɣ��肷��B<BR>
     * -������t�X�e�C�^�X����t���ȊO-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00013<BR>
     * �@@�Y���s�������s����ꍇ�́A�P���ł��u��t�\�v�ł���Β���<BR>
     * ��t�\�Ƃ���B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�@@���A���A������ԃR���e�L�X�g�̎s��R�[�h�v���p�e�B��null�ł���΁A<BR>
     * �s��R�[�h�͌��������Ɋ܂߂Ȃ��i���ׂĂ̎s���ΏۂƂ���j<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�c�Ɠ��敪�F�@@(*3)<BR>
     * �@@�����R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     *     ���A���A������ԃR���e�L�X�g�̖����R�[�h�v���p�e�B��null�ł���΁A<BR>
     * �����R�[�h�͌��������Ɋ܂߂Ȃ��i���ׂĂ̖�����ΏۂƂ���j<BR>
     * �@@�J�n���� <= ��t����(*2) <=�@@�I������<BR>
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�́A�Y���f�[�^�Ȃ��i�V�X�e���G���[�j<BR>
     * �Ƃ��ė�O���X���[����B<BR>
     * -�Y���f�[�^�Ȃ�-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * <BR>
     *  (*1)�@@������ԃR���e�L�X�g�̎擾<BR>
     *  �|ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
     * <BR>
     * <BR>
     *  (*2) ��t���Ԃ̎擾<BR>
     *  �|ThreadLocalSystemAttributesRegistry����t�������擾���A<BR>
     * �擾���������̎��ԕ����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.system_
     * timestamp")<BR>
     * <BR>
     *  (*3) �c�Ɠ��敪�̎擾<BR>
     *  �|this.get�c�Ɠ��敪()���R�[�����Ď擾����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4014CD6801CF
     */
    public static void validateOrderAccept() throws WEB3BaseException
    {
        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strOrderAccProduct;
        String l_strOrderAccTransaction;
        String l_strOrderAcceptStatus;
        String l_strMarketCode;
        String l_strTradingTimeType;
        String l_strProductCode;
        final String STR_METHOD_NAME = "validateOrderAccept()";
        log.entering(STR_METHOD_NAME);

        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //���X�R�[�h
        l_strBranchCode = l_clendarContext.getBranchCode();
        //�s��R�[�h
        l_strMarketCode = l_clendarContext.getMarketCode();
        //������t���i
        l_strOrderAccProduct = l_clendarContext.getOrderAcceptProduct();
        //������t�g�����U�N�V����
        l_strOrderAccTransaction = l_clendarContext.getOrderAcceptTransaction();
        //��t���ԋ敪
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�����R�[�h
        l_strProductCode = l_clendarContext.getProductCode();

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A
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
                    + "."
                    + STR_METHOD_NAME);
        }

        // �P�j�@@�ً}��~�A�o�b�`���`�F�b�N
        try
        {
            //������t�X�e�C�^�X�e�[�u��
            OrderAcceptStatusRow l_row =
                OrderAcceptStatusDao.findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderAccProduct,
                    l_strOrderAccTransaction);

            if (l_row != null)
            {
                l_strOrderAcceptStatus = l_row.getOrderAcceptStatus();
            }
            else
            {
                l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
            }
        }
        catch (DataFindException nfe)
        {
            l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
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

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // �o�b�`������
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // �ً}��~��
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        // �Q�j�@@������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j
        // �̏ꍇ�́A�ȍ~�̏����͍s�킸��return����
        if(WEB3OrderAccTransactionDef.REFERENCE.equals(l_strOrderAccTransaction))
        {
            log.info("������t�g�����U�N�V�������h07�h�i�Ɖ�j �F�@@��t�\");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�R�j��t�s���ԑу`�F�b�N

        //��t���Ԃ̎擾(�擾���������̎��ԕ���)
        Timestamp l_tsOrderAcceptDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
            TIMESTAMP_TAG);
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        //�c�Ɠ��敪�̎擾
        String l_bizDateType = getBizDateType(l_tsOrderAcceptDate);

        //������ԃe�[�u������������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

        ArrayList l_lstObjTradingTimeWhere = new ArrayList();
        //�،���ЃR�[�h
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //���X�R�[�h
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //��t���ԋ敪
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //�c�Ɠ��敪
        l_lstObjTradingTimeWhere.add(l_bizDateType);
//        //��t����
//        l_lstObjTradingTimeWhere.add(l_strAcceptTime);
//        //��t����
//        l_lstObjTradingTimeWhere.add(l_strAcceptTime);

        //�s��R�[�h
        if (l_strMarketCode != null)
        {
            l_sbWhere.append(" and market_code = ? ");
            l_lstObjTradingTimeWhere.add(l_strMarketCode.trim());
        }
        //�����R�[�h
        if (l_strProductCode != null)
        {
            l_sbWhere.append(" and product_code = ? ");
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
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                "������ԃe�[�u�������F ���� = 0");
        }

        //�P���ł��u��t�\�v�ł���Β�����t�\�Ƃ���
        TradingTimeRow l_tradingTimeRow = null;
        String l_strEnableOrder = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (WEB3EnableOrderDef.ENABLE.equals(l_tradingTimeRow.getEnableOrder()) &&
                Long.parseLong(l_tradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime) &&
                Long.parseLong(l_tradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime) )
            {
                l_strEnableOrder = l_tradingTimeRow.getEnableOrder();
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug(STR_METHOD_NAME + "�F��t�s���ԑ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        log.debug(STR_METHOD_NAME + "�F��t���ԑ�");
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * is�g���K���s<BR>
     * �s��փ��A���^�C���Ƀg���K�𔭍s���邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@������������<BR>
     * �@@�������� == �h�t�w�l�h�̏ꍇ�Afalse��ԋp����B<BR>
     * �@@�������̔������� != null�̏ꍇ�̂݁A��������s���B<BR>
     * �@@�������̔������� == null�̏ꍇ�́A������͍s�킸�A�ȍ~�̏������s���B<BR> 
     * <BR>
     * �Q�jThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �R�jThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * �S�j�@@��t���̉c�Ɠ��敪����B<BR>
     * �@@�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ��<BR>
     * �h�x���h�Ɣ��肵�Afalse��ԋp���������I������B<BR>
     * �@@�ȊO�̏ꍇ�A��t�����̓��t�����ŃJ�����_�e�[�u�����������A<BR>
     * �s�̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ�<BR>
     * �敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �@@�A���A�O���̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O�������j�A<BR>
     * �O���C�O�s��J�����_�e�[�u�����ȉ��̏����Ō������A<BR>
     * �Y���s�̉c�Ɠ��敪���h��c�Ɠ��h�̏ꍇ�A�c�Ɠ��敪�h��c�Ɠ��h�Ƃ���B<BR> 
     * <BR>
     * �y������ԃR���e�L�X�g.��t���ԋ敪 == �O�������z<BR>
     * �@@[�O���C�O�s��J�����_�e�[�u����������]<BR>
     * �@@�،���ЃR�[�h = ������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h = ������ԃR���e�L�X�g.�s��R�[�h<BR>
     * �@@���t = �i��t�����̓��t�����j<BR>
     * <BR>
     * �T�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@�@@[�����L�[]<BR>
     *  �،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     *  ���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     *  �s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     *  ��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     *  ���i�R�[�h�F�@@������ԃR���e�L�X�g�̖����R�[�h<BR>
     *  �c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     *  �J�n���ԁ@@���� �i��t�����̎��ԕ����j ���� �I������<BR>
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

        // �P�j�@@������������
        //�������� == �h�t�w�l�h�̏ꍇ�Afalse��ԋp����B
        //�@@�������̔������� != null�̏ꍇ�̂݁A��������s���B 
        //  �������̔������� == null�̏ꍇ�́A������͍s�킸�A�ȍ~�̏������s���B 
        if ((l_strOrderingCondition != null) && 
            (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderingCondition)))
        {
            log.debug(STR_METHOD_NAME + "�F�������� == �h�t�w�l�h");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // �Q�j�@@ThreadLocalSystemAttributesRegistry���A��t�����擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        // �R�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // �S�j�@@��t���̉c�Ɠ��敪����B
        //�c�Ɠ��敪�̎擾
        l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);

        //��t���ԋ敪
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�s��R�[�h
        l_strMarketCode = l_clendarContext.getMarketCode();
        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode();

        //�O���̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O�������j
        if (WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType =
                getFeqBizDateType(
                    l_strInstitutionCode,
                    l_strMarketCode,
                    l_tsOrderAcceptDate);
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug(STR_METHOD_NAME + "�F��t�����͓y�j�����͓��j���̏ꍇ�ł��B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //���X�R�[�h
        l_strBranchCode = l_clendarContext.getBranchCode();
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
                    + "." + STR_METHOD_NAME);
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
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

        Object[] l_objTradingTimeWhere = {
            l_strInstitutionCode,    //�،���ЃR�[�h
            l_strBranchCode,        //���X�R�[�h
            l_strMarketCode,        //�s��R�[�h
            l_strTradingTimeType, //��t���ԋ敪
            l_strBizDateType,       //�c�Ɠ��敪
            l_strProductCode       //���i�R�[�h
//            l_strAcceptTime,     //��t����
//            l_strAcceptTime     //��t����
            };

        List l_lisRecords;
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
        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow =
                (TradingTimeRow) l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime)
                && Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime))
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

        //get�s��g���K���s
        String l_strSubmitMarketTrigger = l_tradingTimeRow.getSubmitMarketTrigger();

        //�u�s��g���K���s�v���ڂ��hSONAR��MQ�g���K�����{����h�ł����true�A�ȊO��false��ԋp����B
        if (WEB3SubmitMarketTriggerDef.TRIGGER.equals(l_strSubmitMarketTrigger))
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
     * (setTimestamp)<BR>
     * xTrade�����p���錻�ݓ������Z�b�g����B<BR>
     * �i�T�[�r�X�C���^�Z�v�^���g�p����j<BR>
     * <BR>
     *  �|�v���Z�X�J�n���̎��Ԃ�LocalThread�ɃZ�b�g����B<BR>
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
    public static void setTimestamp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setTimestamp()";
        log.entering(STR_METHOD_NAME);

        // Timestamp�̏�����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            null);

        // Timestamp�ݒ�
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            l_processTime
            );

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
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

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
//        l_lstObjTradingTimeWhere.add(l_strTime);
//        //��t����
//        l_lstObjTradingTimeWhere.add(l_strTime);

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
            //�C�� by shiyan �v���ύX�i�~�j���j
            //l_offset = new Integer(-1);
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
     * (get������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>
     *     �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     * �@@�������AThreadLocalSystemAttributesRegistry�ɁA<BR>
     * �@@�ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"<BR>
     * �@@�i�������v�Z�p�̊�����j�̒l���Z�b�g����Ă���ꍇ�́A<BR>
     * �@@�ȍ~�̏����Ŏ�t�����Ƃ��āA<BR>
     * �@@�ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"�̒l���g�p����B<BR>
     * <BR>
     * �Q�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * "web3.tradingcalendarcontext")<BR>
     * <BR>
     * �擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A<BR>
     * �f�[�^�s�����Ƃ��ė�O���X���[����B<BR>
     * -�f�[�^�s�����G���[-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80006<BR>
     * <BR>
     * �@@������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@������ԃR���e�L�X�g.���X�R�[�h<BR>
     * �@@������ԃR���e�L�X�g.�s��R�[�h<BR>
     * �@@������ԃR���e�L�X�g.��t���ԋ敪<BR>
     * �@@������ԃR���e�L�X�g.�����R�[�h<BR>
     * <BR>
     * �R�j�@@��t���̉c�Ɠ��敪����B<BR>
     * �@@�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�́h<BR>
     * ��c�Ɠ��h�Ɣ��肵�B<BR>
     * �@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A<BR>
     * �s�̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ�<BR>
     * �敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �A���A�O���E�O���U�֘A�g�̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O������ or �O�������U�֘A�g�j�A<BR>
     * �O���C�O�s��J�����_�e�[�u�����ȉ��̏����Ō������A<BR>
     * �Y���s�̉c�Ɠ��敪���h��c�Ɠ��h�̏ꍇ�A�c�Ɠ��敪�h��c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �y������ԃR���e�L�X�g.��t���ԋ敪 == �O�������z<BR> 
     * [�O���C�O�s��J�����_�e�[�u����������]<BR>
     * �،���ЃR�[�h = ������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �s��R�[�h = ������ԃR���e�L�X�g.�s��R�[�h<BR>
     * ���t = �i��t�����̓��t�����j<BR>
     * <BR>
     * �y������ԃR���e�L�X�g.��t���ԋ敪 == �O�������U�֘A�g�z<BR>
     * �@@[�O���C�O�s��J�����_�e�[�u����������]<BR>
     * �@@�،���ЃR�[�h = ������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h = ���`�i���P�j<BR>
     * �@@���t = �i��t�����̓��t�����j<BR>
     * �@@�i���P�j������ԃR���e�L�X�g.�s��R�[�h�ɂ́A���`���Z�b�g���Ȃ��B<BR> 
     * <BR>
     * �S�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B�B<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     * <BR>
     * �@@�擾�����s�̂����A<BR>
     * �@@�J�n���� <= �i��t�����̎��ԕ����j <= �I�����Ԃ�<BR>
     * �@@�Y������s�́u�������v�Z�v���ڂɉ����Ĉȉ��̒l��ԋp����B<BR>
     *      (*) �Y���s�����݂��Ȃ��ꍇ�́A�f�[�^�s�����i�V�X�e���G���[�j<BR>
     * <BR>
     * �@@�Ƃ��ė�O���X���[����B<BR>
     * -�Y���f�[�^�Ȃ�-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80006<BR>
     * <BR>
     * �i�������v�Z = �����j�F��t�����̓��t������ԋp����B<BR>
     * �i�������v�Z = ���c�Ɠ��j�F��t�����̗��c�Ɠ���ԋp����B<BR>
     * �i�������v�Z = �Q�c�Ɠ���j�F��t�����̂Q�c�Ɠ����ԋp����<BR>
     * ���O���E�O���U�֘A�g�̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O������ or �O�������U�֘A�g�j�A<BR>
     * �c�Ɠ��v�Z�ɂ͉c�Ɠ��v�Z.calc�O���c�Ɠ�()���g�p����B<BR>
     * �A���A�O���U�֘A�g�̏ꍇ�́A�c�Ɠ��v�Z.calc�O���c�Ɠ�()��<BR>
     * ����.�s��R�[�h�ɂ́A���`���Z�b�g����B <BR>
     * �i������ԃR���e�L�X�g.�s��R�[�h�ɂ́A���`���Z�b�g���Ȃ��B�j<BR> 
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 4014CEEA0375
     */
    public static Date getOrderBizDate() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        //  ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // �������AThreadLocalSystemAttributesRegistry�ɁA
        // �ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"
        // �i�������v�Z�p�̊�����j�̒l���Z�b�g����Ă���ꍇ�́A
        Object l_objOrderBizDate = ThreadLocalSystemAttributesRegistry.getAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
        if (l_objOrderBizDate != null)
        {
            // �ȍ~�̏����Ŏ�t�����Ƃ��āA
            // �ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"�̒l���g�p����B
            l_tsOrderAcceptDate = (Timestamp) l_objOrderBizDate;
        }

        //�Q�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
        if(l_clendarContext == null)
        {
            log.info("ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾�Ȃ�");
            return null;
        }
        
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
        //��t�����̎��ԕ���
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strTime = l_format.format(l_tsOrderAcceptDate);

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A�f�[�^�s�����Ƃ���
        //��O���X���[����B
        //   ������ԃR���e�L�X�g.�،���ЃR�[�h
        // �@@������ԃR���e�L�X�g.���X�R�[�h
        // �@@������ԃR���e�L�X�g.�s��R�[�h
        // �@@������ԃR���e�L�X�g.��t���ԋ敪
        // �@@������ԃR���e�L�X�g.�����R�[�h
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }

        //�R�j�@@��t���̉c�Ɠ��敪����
        //�c�Ɠ��敪�̎擾
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        
        //�O���E�O���U�֘A�g�̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O������ or �O�������U�֘A�g�j
        //*********** ���f�� No.145  by shiyan ***********
        String l_strFeqMarketCode = l_strMarketCode;
        if(WEB3TradingTimeTypeDef.FEQ_CON.equals(l_strTradingTimeType))
        {
            l_strFeqMarketCode = WEB3MarketCodeDef.HONGKONG;
        }
        
        if(WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType) || 
            WEB3TradingTimeTypeDef.FEQ_CON.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType =
                getFeqBizDateType(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate);
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        
        //������ԃe�[�u������������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

        Object[] l_objTradingTimeWhere =
        {
            l_strInstitutionCode.trim(), //�،���ЃR�[�h
            l_strBranchCode.trim(),      //���X�R�[�h
            l_strMarketCode.trim(),      //�s��R�[�h
            l_strTradingTimeType.trim(), //��t���ԋ敪
            l_strProductCode,            //�����R�[�h
            l_strBizDateType            //�c�Ɠ��敪
//            l_strTime,                   //��t����
//            l_strTime                    //��t����
        };

        List l_lisRecords;
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
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            //�Y���s�����݂��Ȃ��ꍇ�́A�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B 
            //************* ���f�� No.145 by shiyan ****************
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "������ԃe�[�u�������F ���� = 0");
        }
        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strTime) &&
                Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strTime) )
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

        // �i�������v�Z = �����j�F��t�����̓��t������ԋp����B
        // �i�������v�Z = ���c�Ɠ��j�F��t�����̗��c�Ɠ���ԋp����B
        // �i�������v�Z = �Q�c�Ɠ���j�F��t�����̂Q�c�Ɠ����ԋp����B
        Date l_bizDate;
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(l_tsOrderAcceptDate);
        String l_strBizdateCalcParameter = l_tradingTimeRow.getBizdateCalcParameter();
        //���O���E�O���U�֘A�g�̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O������ or �O�������U�֘A�g�j
        //************* ���f�� No.145 by shiyan ****************
        if(WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType) || 
            WEB3TradingTimeTypeDef.FEQ_CON.equals(l_strTradingTimeType))
        {
            if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.calcFeqBizDate(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate,
                    0);
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.calcFeqBizDate(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate,
                    1);
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.calcFeqBizDate(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate,
                    2);
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    "�������v�Z = " + l_strBizdateCalcParameter);
            }
        }
        else
        {
            if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_tsOrderAcceptDate;
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.roll(1);
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.roll(2);
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    "�������v�Z = " + l_strBizdateCalcParameter);
            }
        }
        
        //���ԕ������N���A
        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.toDay(l_bizDate);
    }

    /**
     * get������<BR>
     * ���������擾����B<BR>
     * <BR>
     * this.get������()�ɂĔ��������擾����B<BR>
     * �擾�����������ƈ����̊m�F�����������Ⴄ���t�ł����<BR>
     * ��O���X���[����B<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : BUSINESS_ERROR_00205<BR>
     * <BR>
     * (*)�����������Ŏg�p����B<BR>
     * @@param l_confirmBizDate - �m�F��������
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 406937AB0203
     */
    public static Date getOrderBizDate(Date l_confirmBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date)";
        log.entering(STR_METHOD_NAME);

        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if (l_bizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_confirmBizDate;
        }

        if (WEB3DateUtility.compareToDay(l_bizDate,l_confirmBizDate) != 0)
        {
            log.debug(STR_METHOD_NAME + " �F �擾���������� != �����̊m�F��������");
            //�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���Η�O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "������ = " + l_bizDate + " , �m�F�������� = " + l_confirmBizDate);
        }

        log.exiting(STR_METHOD_NAME);
        return l_confirmBizDate;

    }

    /**
     * get������<BR>
     * ���������擾����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@���������擾����B <BR>
     * <BR>
     * �@@�@@this.get������(����.������)�ɂĔ��������擾����B <BR>
     * <BR>
     * �Q�j�@@����敪���擾����B <BR>
     * <BR>
     * �@@�@@this.get����敪()�ɂė���敪���擾����B <BR>
     * <BR>
     * �R�j�@@��������ԋp����B <BR>
     * <BR>
     * �@@�@@�Q�j�Ŏ擾��������敪 == ����.����敪�ł���΂P�j�Ŏ擾������������ԋp����B <BR>
     * �@@�@@��L�ȊO�́A��O(*1)��throw����B <BR>
     * <BR>
     * <BR>
     * (*1) throw���郁�b�Z�[�W�F  <BR>
     * �u����Ԃ��ς��܂����B���萔�ł����A������x���͂������Ă��������B�v<BR>
     * �@@class:WEB3BusinessLayerException<BR>
     * �@@tag:�@@BUSINESS_ERROR_02842<BR>
     * @@param l_datConfirmBizDate - �m�F��������
     * @@param l_strSessionType - ����敪
     * @@return Date
     * @@throws WEB3BaseException
     */
    public static Date getOrderBizDate(Date l_datConfirmBizDate, String l_strSessionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date, String)";
        log.entering(STR_METHOD_NAME);

        //���������擾����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datConfirmBizDate);
        //����敪���擾����B
        String l_strSessionTypeTemp = WEB3GentradeTradingTimeManagement.getSessionType();
        //��������ԋp����B
        if (!WEB3Toolkit.isEquals(l_strSessionTypeTemp, l_strSessionType))
        {
            log.debug("����Ԃ��ς��܂����B���萔�ł����A������x���͂������Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02842,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "����Ԃ��ς��܂����B���萔�ł����A������x���͂������Ă��������B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }

    /**
     * is�s��J�ǎ��ԑ�<BR>
     * �s��ǎ��ԑт��ǂ�����ԋp����B<BR>
     * �s��J�ǎ��ԑт̏ꍇtrue�A�s��ǎ��ԑт̏ꍇfalse��ԋp����B
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>
     *  �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     *     "xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     * �Q�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "web3.tradingcalendarcontext")<BR>
     * �@@�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A<BR>
     * �f�[�^�s�����Ƃ��ė�O���X���[����B<BR>
     * -�f�[�^�s�����G���[-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80006<BR>
     * <BR>
     * �@@������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@������ԃR���e�L�X�g.���X�R�[�h<BR>
     * �@@������ԃR���e�L�X�g.�s��R�[�h<BR>
     * �@@������ԃR���e�L�X�g.��t���ԋ敪<BR>
     * �@@������ԃR���e�L�X�g.�����R�[�h<BR>
     * <BR>
     * �R�j�@@��t���̉c�Ɠ��敪����B<BR>
     * �@@this.get�c�Ɠ��敪()���R�[�����Ď擾����B<BR>
     * <BR>
     * �@@�A���A�O���̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O�������j�A<BR>
     * �O���C�O�s��J�����_�e�[�u�����ȉ��̏����Ō������A<BR>
     * �Y���s�̉c�Ɠ��敪���h��c�Ɠ��h�̏ꍇ�A�c�Ɠ��敪�h��c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �y������ԃR���e�L�X�g.��t���ԋ敪 == �O�������z<BR>
     * �@@[�O���C�O�s��J�����_�e�[�u����������]<BR>
     * �@@�،���ЃR�[�h = ������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h = ������ԃR���e�L�X�g.�s��R�[�h<BR>
     * �@@���t = �i��t�����̓��t�����j<BR>
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
     * �@@�s��g���K���s�F<BR>
     *      ������ԃR���e�L�X�g.��t���ԋ敪!=�h����O�����h�̏ꍇ�́A<BR>
     *      �hSONAR��MQ�g���K�����{����h<BR>
     *      ������ԃR���e�L�X�g.��t���ԋ敪==�h����O�����h�̏ꍇ�́A<BR>
     *      �����L�[�Ɏw�肵�Ȃ��B<BR>
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�́Atrue�i�J�ǒ��j��ԋp����B<BR>
     * <BR>
     * �@@�擾�����s���A�ȉ��̒ʂ�߂�l�𔻒肷��B<BR>
     * <BR>
     *  �i�s��J�ǎ��ԑ�(*1)�@@���@@�i��t�����̎��ԕ����j���@@<BR>
     *  �s��ǎ��ԑ�(*2)�j�ł����true�A�ȊO��false��ԋp����B<BR>
     *
     * �@@(*1)�s��J�ǎ��ԁF�@@��L�Ɉ�v����s�̂����A�J�n���Ԃ�<BR>
     * ��ԑ����s�̊J�n���ԁB<BR>
     * �@@(*2)�s��ǎ��ԁF�@@��L�Ɉ�v����s�̂����A�J�n���Ԃ�<BR>
     * ��Ԓx���s�̏I�����ԁB<BR>
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 4020D19D025D
     */
    public static boolean isTradeOpenTimeZone() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeOpenTimeZone()";
        log.entering(STR_METHOD_NAME);

        boolean l_boReturn;

        // �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)
                (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        // �Q�j ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A
        //�f�[�^�s�����Ƃ��ė�O���X���[����B
        //* �@@������ԃR���e�L�X�g.�،���ЃR�[�h
        //* �@@������ԃR���e�L�X�g.���X�R�[�h
        //* �@@������ԃR���e�L�X�g.�s��R�[�h
        //* �@@������ԃR���e�L�X�g.��t���ԋ敪
        //* �@@������ԃR���e�L�X�g.�����R�[�h
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        String l_strBranchCode = l_clendarContext.getBranchCode();
        String l_strMarketCode = l_clendarContext.getMarketCode();
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        String l_strProductCode = l_clendarContext.getProductCode();
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }

        // �R�j�@@��t���̉c�Ɠ��敪����B
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        
        //�O���̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O�������j
        if (WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType =
                getFeqBizDateType(
                    l_strInstitutionCode,
                    l_strMarketCode,
                    l_tsOrderAcceptDate);
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug("��t����( " + l_tsOrderAcceptDate + ") �F ��c�Ɠ��̏ꍇ�B");
            return false;
        }

        // �S�j ������Ԏ擾
        //  �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        //[�����L�[]
        //* �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@�����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪
        //* �@@�������v�Z�F�@@�h�����h
        //* �@@�s��g���K���s�F
        //*      ������ԃR���e�L�X�g.��t���ԋ敪!=�h����O�����h�̏ꍇ�́A
        //*      �hSONAR��MQ�g���K�����{����h
        //*      ������ԃR���e�L�X�g.��t���ԋ敪==�h����O�����h�̏ꍇ�́A
        //*      �����L�[�Ɏw�肵�Ȃ��B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_strTradingTimeType) == false)
        {
            l_sbWhere.append(" and submit_market_trigger = ? ");
        }
        List l_lstTradingTimeWhere = new ArrayList();
        l_lstTradingTimeWhere.add(l_strInstitutionCode);
        l_lstTradingTimeWhere.add(l_strBranchCode);
        l_lstTradingTimeWhere.add(l_strMarketCode);
        l_lstTradingTimeWhere.add(l_strTradingTimeType);
        l_lstTradingTimeWhere.add(l_strProductCode);
        l_lstTradingTimeWhere.add(l_strBizDateType);
        l_lstTradingTimeWhere.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_strTradingTimeType) == false)
        {
            l_lstTradingTimeWhere.add(WEB3EnforcementDef.ENFORCEMENT);
        }
        Object[] l_objTradingTimeWhere = 
            new Object[l_lstTradingTimeWhere.size()];
        l_lstTradingTimeWhere.toArray(l_objTradingTimeWhere);

        List l_lstStartRecords;
        List l_lstEndRecords;
        String l_strMarketStartTime;
        String l_strMarketEndTime;
        // �J�ǎ����̍ŏ��l�����߂�
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time",
                null,
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
        // �Y���s�����݂��Ȃ��ꍇ�́Atrue�i�J�ǒ��j��ԋp����B
        int l_intSize = l_lstStartRecords.size();
        if (l_intSize == 0)
        {
            log.debug("�Y���s�����݂��Ȃ��ꍇ�́Atrue�i�J�ǒ��j��ԋp����B");
            return true;
        }

        l_strMarketStartTime = ((TradingTimeRow)l_lstStartRecords.get(0)).getStartTime();

        // �ǎ����̍ő�l�����߂�
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstEndRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "end_time desc",
                null,
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
        // �Y���s�����݂��Ȃ��ꍇ�́Atrue�i�J�ǒ��j��ԋp����B
        int l_intEndSize = l_lstEndRecords.size();
        if (l_intEndSize == 0)
        {
            log.debug("�Y���s�����݂��Ȃ��ꍇ�́Atrue�i�J�ǒ��j��ԋp����B");
            return true;
        }

        l_strMarketEndTime = ((TradingTimeRow)l_lstEndRecords.get(0)).getEndTime();

        // �擾�s���߂�l�𔻒肷��
        SimpleDateFormat l_simpleDateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strTime = l_simpleDateFormat.format(l_tsOrderAcceptDate);

        log.debug("*** ������ԊǗ�*** ���莞�� = " + l_strTime);
        log.debug("*** ������ԊǗ�*** �J�ǎ��� = " + l_strMarketStartTime);
        log.debug("*** ������ԊǗ�*** �ǎ��� = " + l_strMarketEndTime);

        if ((l_strMarketStartTime.compareTo(l_strTime) <= 0)
            && (l_strTime.compareTo(l_strMarketEndTime) <= 0))
        {
            l_boReturn = true;
            log.debug("*** ������ԊǗ�***   �s��J�ǎ��ԑ�");
        }
        else
        {
            l_boReturn = false;
            log.debug("*** ������ԊǗ�***   �s��ǎ��ԑ�");
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * get�s��ǎ���<BR>
     * �s��ǎ��Ԃ��擾����B<BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>�@@
     *     ThreadLocalSystemAttributesRegistry.getAttribute(  <BR>
     *      "xblocks.gtl.attributes.systemtimestamp")<BR>
     *  <BR>
     * �Q�j�@@��t���̉c�Ɠ��敪����B  <BR>
     *  �|�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ��  <BR>
     *     �c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B <BR>
     *  �|�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A <BR>
     *     �s�̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A <BR>
     *     �c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B <BR>
     * <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
     * <BR>
     * <BR>
     * �S�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     *   �Y���s�����݂��Ȃ��ꍇ�A�@@23��59��59�b��ԋp����B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���i�R�[�h�F�@@�����̏��i�R�[�h<BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     * �@@�������v�Z�F�@@�h�����h<BR>
     * �@@�s��g���K���s�F <BR>
     *     ������ԃR���e�L�X�g.��t���ԋ敪!=�h����O�����h�̏ꍇ�́A<BR>
     *     �hSONAR��MQ�g���K�����{����h<BR>
     *    ������ԃR���e�L�X�g.��t���ԋ敪==�h����O�����h�̏ꍇ�́A<BR>
     *    �����L�[�Ɏw�肵�Ȃ��B<BR>
     * <BR>
     * �@@��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����B
     * <BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * �i���̏ꍇ�j<BR>
     * 1:���� 2:��� 3:���É� 4:���s 5:�L��<BR>
     * 6:���� 8:�D�y 9:NNM 10: JASDAQ<BR>
     * <BR>
     * �i���łȂ��ꍇ�j<BR>
     * 0�FDEFAULT�i�w��Ȃ��j<BR>
     *
     * @@param l_strProductCode - ���i�R�[�h<BR>
     * ���M�̏ꍇ�A�����R�[�h<BR>
     * �敨�^�I�v�V�����̏ꍇ�A�����Y�����R�[�h<BR>
     * �ȊO�A0�FDEFAULT<BR>
     *
     * @@return java.lang.String
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40641B76030C
     */
    public static String getTradeCloseTime(
        String l_strMarketCode,
        String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseTime(String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        Calendar l_CalendarBizDate = Calendar.getInstance();
        l_CalendarBizDate.setTime(l_tsOrderAcceptDate);

        // �Q�j ��t���̉c�Ɠ��敪����B
        String l_strBizDateType = null;
        l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptDate);

        // �R�j ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // �S�j ������Ԏ擾
        //  �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        // ������ԃe�[�u��(trading_time)����
        List l_lisStartRecords;

        // �،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
        // ���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
        // �s��R�[�h�F�@@�����̎s��R�[�h<BR>
        // ��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
        // ���i�R�[�h�F�@@�����̏��i�R�[�h<BR>
        // �c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
        // �������v�Z�F�@@�h�����h<BR>
        // �s��g���K���s�F�@@
        //    ������ԃR���e�L�X�g.��t���ԋ敪!=�h����O�����h�̏ꍇ�́A<BR>
        //    �hSONAR��MQ�g���K�����{����h<BR>
        //    ������ԃR���e�L�X�g.��t���ԋ敪==�h����O�����h�̏ꍇ�́A<BR>
        //    �����L�[�Ɏw�肵�Ȃ��B<BR>
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()) == false)
        {
            l_sbWhere.append(" and submit_market_trigger = ? ");
        }
        List l_lstTradingTimeWhere = new ArrayList();
        l_lstTradingTimeWhere.add(l_clendarContext.getInstitutionCode());
        l_lstTradingTimeWhere.add(l_clendarContext.getBranchCode());
        l_lstTradingTimeWhere.add(l_strMarketCode);
        l_lstTradingTimeWhere.add(l_clendarContext.getTradingTimeType());
        l_lstTradingTimeWhere.add(l_strProductCode);
        l_lstTradingTimeWhere.add(l_strBizDateType);
        l_lstTradingTimeWhere.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()) == false)
        {
            l_lstTradingTimeWhere.add(WEB3EnforcementDef.ENFORCEMENT);
        }
        Object[] l_objTradingTimeWhere = 
            new Object[l_lstTradingTimeWhere.size()];
        l_lstTradingTimeWhere.toArray(l_objTradingTimeWhere);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
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

        // �Y���s�����݂��Ȃ��ꍇ�A�@@23��59��59�b��ԋp����
        int l_intSize = l_lisStartRecords.size();
        if (l_intSize == 0)
        {
            log.debug("****** �s��ǎ��ԁF������ԃe�[�u�����������āA" +
                "�Y���s�����݂��Ȃ��ꍇ�A23��59��59�b��ԋp����");
            return WEB3GentradeTimeDef.MAX_RETURN;
        }

        //��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisStartRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();
        log.debug("****** �s��ǎ��ԁF[" + l_strEndTime + "]");
        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
    }

    /**
     * (get�s��ǎ���<����ԑ�>)<BR>
     * ����ԑтł̎s��ǎ��Ԃ��擾����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG) <BR>
     * <BR>
     * �Q�j�@@��t���̉c�Ɠ��敪����B <BR>
     * �@@�|�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�͉c�Ɠ��敪��  <BR>
     * �@@�@@�@@�h��c�Ɠ��h�Ƃ���B  <BR>
     * �@@�|�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A<BR>
     * �s�̉c�Ɠ��敪���擾����B  <BR>
     * �@@�@@�@@�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B  <BR>
     * <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TRADING_CAL_CONTEXT_PATH) <BR>
     * <BR>
     * �S�j�@@����J�����_�R���e�L�X�g����s��R�[�h�A���i�R�[�h���擾����B <BR>
     * �@@�������I�����Ɏ���J�����_�R���e�L�X�g�֍Đݒ���s���B <BR>
     * <BR>
     * �T�j�@@����J�����_�R���e�L�X�g�֎s��R�[�h�A���i�R�[�h(*1)�̐ݒ���s���B <BR>
     * <BR>
     * �@@(*1) ����.�s��R�[�h�A����.���i�R�[�h <BR>
     * <BR>
     * �U�j�@@������Ԏ擾 <BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B <BR>
     * <BR>
     * �@@[�����L�[] <BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�s��R�[�h�F�@@�����̎s��R�[�h <BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@���i�R�[�h�F�@@�����̏��i�R�[�h <BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪 <BR>
     * �@@�������v�Z�F�@@�h�����h <BR>
     * �@@�s��g���K���s�F <BR>
     * �@@�@@�@@������ԃR���e�L�X�g.��t���ԋ敪!=�h����O�����h�̏ꍇ�́A<BR>
     * �hSONAR��MQ�g���K�����{����h <BR>
     * �@@�@@�@@������ԃR���e�L�X�g.��t���ԋ敪==�h����O�����h�̏ꍇ�́A<BR>
     * �����L�[�Ɏw�肵�Ȃ��B <BR>
     * �@@����敪�F�@@this.get����敪() <BR>
     * <BR>
     * �@@��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ��擾����B  <BR>
     * <BR>
     * �V�j�@@����J�����_�R���e�L�X�g�֎s��R�[�h�A���i�R�[�h(*2)�̍Đݒ���s���B <BR>
     * <BR>
     * �@@(*2) �S�j�Ŏ擾�����s��R�[�h�A���i�R�[�h <BR>
     * <BR>
     * �W�j�@@�U�j�Ŏ擾�����I�����Ԃ�ԋp����B <BR>
     * �@@���U�j�̌������ʂ���Y���s�����݂��Ȃ��ꍇ�A23��59��59�b��ԋp����B<BR>
     * <BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * �i���̏ꍇ�j <BR>
     * 1:���� 2:��� 3:���É� 4:���s 5:�L�� <BR>
     * 6:���� 8:�D�y 9:NNM 10: JASDAQ <BR>
     * @@param l_strProductCode - ���i�R�[�h<BR>
     * ���M�̏ꍇ�A�����R�[�h<BR>
     * �敨�^�I�v�V�����̏ꍇ�A�����Y�����R�[�h<BR>
     * �ȊO�A0�FDEFAULT<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 40641B76030C
     */
    public static String getTradeCloseTimeSessionTimeZone(
        String l_strMarketCode,
        String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseTimeSessionTimeZone(String, String)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // �Q�j ��t���̉c�Ɠ��敪����B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptDate);

        //�R�j�@@ThreadLocalSystemAttributesRegistry���A
        //����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            TRADING_CAL_CONTEXT_PATH);

        //�S�j�@@����J�����_�R���e�L�X�g����s��R�[�h�A���i�R�[�h���擾����B
        String l_strMarketCodeTemp = l_clendarContext.getMarketCode();
        String l_strProductCodeTemp = l_clendarContext.getProductCode();

        //�T�j�@@����J�����_�R���e�L�X�g�֎s��R�[�h�A���i�R�[�h�̐ݒ���s���B
        l_clendarContext.setMarketCode(l_strMarketCode);
        l_clendarContext.setProductCode(l_strProductCode);

        //�U�j�@@������Ԏ擾
        //�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        //�@@�Y���s�����݂��Ȃ��ꍇ�A�@@23��59��59�b��ԋp����B
        List l_lisRecords;

        //�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@�s��R�[�h�F�@@�����̎s��R�[�h
        //�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���i�R�[�h�F�@@�����̏��i�R�[�h
        //�@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪
        //�@@�������v�Z�F�@@�h�����h
        //�@@�s��g���K���s�F
        //�@@�@@�@@������ԃR���e�L�X�g.��t���ԋ敪!=�h����O�����h�̏ꍇ�́A�hSONAR��MQ�g���K�����{����h
        //�@@�@@�@@������ԃR���e�L�X�g.��t���ԋ敪==�h����O�����h�̏ꍇ�́A�����L�[�Ɏw�肵�Ȃ��B
        //�@@����敪�F�@@this.get����敪()

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        if (!WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()))
        {
            l_sbWhere.append(" and submit_market_trigger = ? ");
        }
        String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
        if (l_strSessionType != null)
        {
            l_sbWhere.append(" and session_type = ? ");
        }
        else
        {
            l_sbWhere.append(" and session_type is null ");
        }
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(l_clendarContext.getInstitutionCode());
        l_lisWhere.add(l_clendarContext.getBranchCode());
        l_lisWhere.add(l_strMarketCode);
        l_lisWhere.add(l_clendarContext.getTradingTimeType());
        l_lisWhere.add(l_strProductCode);
        l_lisWhere.add(l_strBizDateType);
        l_lisWhere.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
        if (!WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()))
        {
            l_lisWhere.add(WEB3SubmitMarketTriggerDef.TRIGGER);
        }
        if (l_strSessionType != null)
        {
            l_lisWhere.add(l_strSessionType);
        }
        Object[] l_objWhere =
            new Object[l_lisWhere.size()];
        l_lisWhere.toArray(l_objWhere);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
                l_objWhere);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�V�j�@@����J�����_�R���e�L�X�g�֎s��R�[�h�A���i�R�[�h(*2)�̍Đݒ���s���B
        l_clendarContext.setMarketCode(l_strMarketCodeTemp);
        l_clendarContext.setProductCode(l_strProductCodeTemp);

        // �Y���s�����݂��Ȃ��ꍇ�A�@@23��59��59�b��ԋp����
        if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTimeDef.MAX_RETURN;
        }

        //��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();
        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
    }

    /**
     * get�s��ǌx���s��<BR>
     * ����I���x������\�����鎞�ԑтɂ���s��̎s��R�[�h��z��ŕԋp����B<BR>
     *
     * �V�[�P���X�}<BR>
     * �u�i������ԃ��f���jget�s��ǌx���s��v�Q�ƁB<BR>
     * @@param l_genBranch - ���X�I�u�W�F�N�g
     * @@param l_productTypeEnum - �����^�C�v
     * @@param l_strMargineTradeDiv - �M�p����敪 <BR>
     * <BR>
     * 0�F DEFAULT�i�M�p����ȊO�j <BR>
     * 1�F���x�M�p <BR>
     * 2�F��ʐM�p <BR>
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4020D80D0331
     */
    public static String[] getTradeCloseMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productType,
        String l_strMargineTradeDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseMarket(WEB3GentradeBranch,ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        //1 ) ��c�Ɠ��̏ꍇ�́A�x���s����擾���Ȃ����ƂƂ���B
        //ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        //��t���̉c�Ɠ��敪����B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptTime);
        if(l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            return null;
        }

        // �Q ) �����̖����^�C�v���h�����h�A
        //���� �M�p����敪���h0�FDEFAULT�h�̏ꍇ�́A
        //this.get�s��ǌx���s��i���X�s��ٍϕʁj��delegate�A
        //�߂�l��ԋp���A�������I������B
        if((l_productType.equals(ProductTypeEnum.EQUITY))
           &&( ! WEB3MarginTradingDivDef.DEFAULT.equals(l_strMargineTradeDiv)))
        {
            return getTradeCloseSuspensionMarketRepayment(l_genBranch,l_strMargineTradeDiv);
        }

        // �R) get�s��x�����\��
        long l_lngMessageSuspension = l_genBranch.getMarketMessageSuspension(
            l_productType,
            l_strMargineTradeDiv,
            WEB3FuturesOptionDivDef.DEFAULT);
        if (l_lngMessageSuspension == 0)
        {
            return null;
        }

        // �S) get��t�����̔N��������
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strOrderAcceptYMD = l_format.format(l_tsOrderAcceptTime);

        // �T) ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // �U) get�i���X�s��ʁj�戵����(���X)
        WEB3GentradeBranchMarketDealtCond[] l_handlingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_genBranch);
        if(l_handlingCondBranchMarkets == null)
        {
            return null;
        }

        // �V) �i���X�s��ʁj�戵�����I�u�W�F�N�g����LOOP
        String l_strMarketCode;
        WEB3GentradeBranchMarketDealtCond l_tmpHandlingCondBranchMarket = null;
        int l_intLength = l_handlingCondBranchMarkets.length;
        ArrayList l_lstMarketCodes = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            l_tmpHandlingCondBranchMarket = l_handlingCondBranchMarkets[i];

            l_strMarketCode = l_tmpHandlingCondBranchMarket.getMarketCode();
            //ArrayList�̗v�f���ɁA���̎s��R�[�h���܂܂�Ă��Ȃ��ꍇ�̂݁A
            //���̎s��R�[�h��ArrayList��add����
            if(l_lstMarketCodes.contains(l_strMarketCode))
            {
                continue;
            }

            //is�戵�\
            if (l_tmpHandlingCondBranchMarket.isHandlingPossible(l_productType))
            {

                //get�s��ǎ���(HHmmss)
                String l_strTradeCloseTime = getTradeCloseTime(
                    l_strMarketCode,
                    l_clendarContext.getProductCode());
                //get �i�s��ǎ��Ԃ�[%d]���O�j
                Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                    l_strOrderAcceptYMD + l_strTradeCloseTime,
                    "yyyyMMddHHmmss");
                Date l_datMessageSuspensionTime = WEB3DateUtility.addMinute(
                    l_datTradeCloseTime,
                    -l_lngMessageSuspension);

                //�i�s��ǎ��Ԃ�[%d]���O�j <= �i��t�����̎��ԕ����j <= �s��ǎ���
                //�̏ꍇ�A�s��R�[�h��ArrayList�ɒǉ�����B
                //�� [%d]�̕����́A���X.get�s��ǌx�����\��()�ɂ���Ď擾�������l�B
                if (WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                   && WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datTradeCloseTime) <= 0)
                {
                    l_lstMarketCodes.add(l_tmpHandlingCondBranchMarket.getMarketCode());
                }

            }
        }

        l_intLength = l_lstMarketCodes.size();
        String[] l_strMarkets = new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_strMarkets[i] = (String)l_lstMarketCodes.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strMarkets;
    }



    /**
     * get�s��ǌx���w��<BR>
     * ����I���x������\�����鎞�ԑтɂ���w���̌����Y�����R�[�h��<BR>
     * �z��ŕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i������ԃ��f���jget�s��ǌx���w���v�Q�ƁB<BR>
     *
     * @@param l_branch - ���X�I�u�W�F�N�g
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪) <BR>
     * �@@0�FDEFAULT�i�敨�I�v�V�����ȊO�j <BR>
     * �@@1�F�敨 <BR>
     * �@@2�F�I�v�V���� <BR>
     *
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406423ED033B
     */
    public static String[] getTradeCloseSuspension(
        WEB3GentradeBranch l_branch,
        String l_strFutureOptionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseSuspension(WEB3GentradeBranch , String )";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        
        //�x������
        //�@@�c�Ɠ��敪���擾���A
        //�h��c�Ɠ��h�̏ꍇ�͌x���s����擾�����ɏ������I������
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptTime);
        if(l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            return null;
        }
        
        //get�s��x�����\��
        long l_lngMessageSuspension = l_branch.getMarketMessageSuspension(
            ProductTypeEnum.IFO,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strFutureOptionType);
        //���X.get�s��ǌx�����\��()�̖߂�l��0�̏ꍇ�́A
        //����I���x��������\�����Ȃ��Ɣ��肵�Anull��ԋp���������I������B
        if (l_lngMessageSuspension == 0)
        {
            return null;
        }

        //1.4 get�i���X�w���ʁj�戵����(String, String, String)
        //	(���X�w���ʁj�戵����::get�i���X�w���ʁj�戵����)
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexes =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_strFutureOptionType);

        Date l_datOrderAcceptTime = new Date(l_tsOrderAcceptTime.getTime());

        //get��t�����̎��ԕ���
        String l_strOrderAcceptDate =
            WEB3DateUtility.formatDate(l_datOrderAcceptTime, "yyyyMMdd");

        List l_lstBranchIndexs = new ArrayList();

        int l_intLength = l_branchIndexes.length;
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3GentradeBranchIndexDealtCond l_branchIndex = l_branchIndexes[i];

            if (!l_branchIndex.isHandlingPossible())
            {
                continue;
            }

            //ArrayList�̗v�f���ɁA���̌����Y�����R�[�h���܂܂�Ă��Ȃ��ꍇ�̂݁A
            //���̎s��R�[�h��ArrayList��add����
            //(Bugzilla Bug 1124  �u���o��JPU00550�v  �C��)
            if(l_lstBranchIndexs.contains(l_branchIndex.getUnderlyingProductCode()))
            {
                continue;
            }

            //get�s��ǎ���<����ԑ�>
            String l_strTradeCloseTime = getTradeCloseTimeSessionTimeZone(
                WEB3MarketCodeDef.DEFAULT,
                l_branchIndex.getUnderlyingProductCode());

            //�i�s��ǎ��Ԃ�[%d]���O�j <= �i��t�����̎��ԕ����j <= �s��ǎ���
            //�̏ꍇ�A�����Y�����R�[�h ��ArrayList�ɒǉ�����B
            //�� [%d]�̕����́A���X.get�s��ǌx�����\��()�ɂ���Ď擾�������l�B

            Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                l_strOrderAcceptDate + l_strTradeCloseTime,
                "yyyyMMddHHmmss");

            Date l_datMessageSuspensionTime = WEB3DateUtility.addMinute(
                l_datTradeCloseTime,
                -l_lngMessageSuspension);

            //�i�s��ǎ��Ԃ�[%d]���O�j <= �i��t�����̎��ԕ����j <= �s��ǎ���
            if (WEB3DateUtility.compareToSecond(l_datOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                && WEB3DateUtility.compareToSecond(l_datOrderAcceptTime,l_datTradeCloseTime) <= 0)
            {
                l_lstBranchIndexs.add(l_branchIndex.getUnderlyingProductCode());
            }
        }
        
        int l_intSize = l_lstBranchIndexs.size();
        String[] l_strBranchIndexes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strBranchIndexes[i] = (String)l_lstBranchIndexs.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBranchIndexes;
    }

    /**
     * get�s��ǌx���s��i���X�s��ٍϕʁj<BR>
     * ����I���x������\�����鎞�ԑтɂ���s��̎s��R�[�h��z��ŕԋp����B<BR>
     * <BR>
     * ���戵�\�s��^�s�s����u�i���X�s��ٍϕʁj�戵�����e�[�u���v<BR>
     * ����蔻�肷��A�M�p����ɂĎg�p�B<BR>
     * �������\�b�h��protected���\�b�h�Ƃ��A<BR>
     * ���M�p����̃T�[�r�X�����get�s��ǌx���s��( )���\�b�h<BR>
     * ���̂ق����R�[��������̂Ƃ���B<BR>
     * �V�[�P���X�}<BR>
     * �u�i������ԃ��f���jget�s��ǌx���s��i���X�s��ٍϕʁj�v�Q�ƁB<BR>
     *  <BR>
     *  �P�j �����Ŏw�肳�ꂽ���X���A�s��x�������\���Ώۂ��ǂ����𔻒肷��B<BR>
     *   ���X.get�s��x�����\��(ProductTypeEnum.�h�����h�iEQUITY�j �ɂ�蔻��B<BR>
     *   �߂�l��0�i�s��x������\�����Ȃ��j�̏ꍇ�́Anull��ԋp���������I������B<BR>
     *  <BR>
     *  �Q�j�@@�����Ŏw�肳�ꂽ���X�́A����\�s��̎s��R�[�h�ꗗ�� <BR>
     *   �擾��ArrayList�ɐݒ肷��B
     *  <BR>
     *    �Q�|�P�j�@@�i���X�s��ٍϕʁj�戵����.get�i���X�s��ٍϕʁj�戵 <BR>
     *     ����(���X)�ɂ��A�����Ŏw�肳�ꂽ���X�́u�i���X�s��ٍϕʁj�戵 <BR>
     *     �����v�I�u�W�F�N�g��S�Ď擾����B
     *  <BR>
     *    �Q�|�Q�j�@@ThreadLocalSystemAttributesRegistry.getAttribute( )�ɂ��A<BR>
     *     ��t�������擾����B<BR>
     *  <BR>
     *    �Q�|�R�j�@@�����Ŏw�肳�ꂽ���X�̎���\�s��̎s��R�[�h�ꗗ�� <BR>
     *     ArrayList�ɐݒ肷��B  �Q�|�P�j�Ŏ擾�����u�i���X�s��ٍϕʁj�戵�����v <BR>
     *     �I�u�W�F�N�g�����A�ȉ��́i�P�j�`�i�S�j���s���B<BR>
     *      �@@�i�P�j�p�����[�^.�M�p����敪!="���x�^��ʐM�p(����)"�̏ꍇ�A<BR>
     *             �p�����[�^.�M�p����敪!=�u�i���X�s��ٍϕʁj�戵�����v <BR>
     *             �I�u�W�F�N�g.�ٍϋ敪�ł���u�i���X�s��ٍϕʁj�戵�����v <BR>
     *             �I�u�W�F�N�g�̓X�L�b�v����B<BR>
     *        �i�Q�jis�戵�\( )==false�̏ꍇ�A���́u�i���X�s��ٍϕʁj�戵�����v <BR>
     *              �I�u�W�F�N�g�̓X�L�b�v����B<BR>
     *        �i�R�jis�戵�\( )==true�̏ꍇ�A���́u�i���X�s��ٍϕʁj�戵�����v <BR>
     *              �I�u�W�F�N�g�̎s��R�[�h���擾����B <BR>
     *        �i�S�jArrayList�̗v�f���`�F�b�N���A�i�Q�j�Ŏ擾�����s��R�[�h���܂܂�Ă��Ȃ� <BR>
     *             �ꍇ��add����B<BR>
     *  <BR>
     *     �Q�|�S�j�@@�Q�|�R�j�ō쐬����ArrayList���A�s��x�������\���Ώۂ� <BR>
     *      �s��R�[�h�ꗗ���쐬����B �Q�|�R�j�ō쐬����ArrayList�̗v�f�����A<BR>
     *      �ȉ��́i�P�j�`�i�Q�j���s���B <BR>
     *         �i�P�jget�s��ǎ���(ArrayList�̗v�f�i�s��R�[�h�j, "0"�iDEFAULT�j) <BR>
     *              �ɂ��A�s��ǎ��Ԃ��擾����B <BR>
     *         �i�Q�j�i�s��ǎ��Ԃ�[%d]���O�j <= �i��t�����̎��ԕ����j <= �s��ǎ���<BR>
     *              �̏ꍇ�A���̎s��R�[�h��ArrayList�Ɏc���Ă����B<BR>
     *              ��L�ȊO�̏ꍇ�́A���̎s��R�[�h��ArrayList����remove����B <BR>
     *              �� [%d]�̕����́A���X.get�s��ǌx�����\��()�ɂ���Ď擾�������l�B<BR>
     *  <BR>
     *     �Q�|�T�j�@@�Q�|�S�j�̌��ʂ�ArrayList���A�z��ɕϊ����ĕԋp����B <BR>
     *  <BR>
     * @@param l_genBranch - ���X�I�u�W�F�N�g<BR>
     * @@param l_strMargineTradeDiv - �M�p����敪 <BR>
     *      0�F DEFAULT�i�M�p����ȊO�j <BR>
     *      1�F���x�M�p <BR>
     *      2�F��ʐM�p <BR>
     *      3�F���x�^��ʐM�p(����)<BR>
     * <BR>
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406423ED033B
     */
    protected static String[] getTradeCloseSuspensionMarketRepayment(
        WEB3GentradeBranch l_genBranch,
        String l_strMargineTradeDiv)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseSuspensionMarketRepayment(WEB3GentradeBranch , String )";
        log.entering(STR_METHOD_NAME);

        // 1) get�s��x�����\��
        long l_lngMessageSuspension = l_genBranch.getMarketMessageSuspension(
            ProductTypeEnum.EQUITY,
            l_strMargineTradeDiv,
            WEB3FuturesOptionDivDef.DEFAULT);
        //���X.get�s��ǌx�����\��()�̖߂�l��0�̏ꍇ�́A
        //����I���x��������\�����Ȃ��Ɣ��肵�Anull��ԋp���������I������
        if(l_lngMessageSuspension == 0)
        {
            return null;
        }

        // 2) get �i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds =
            WEB3GentradeBranchMarketRepayDealtCond.getBranchMarketRepayDealtCond(l_genBranch);
        if(l_branchMarketRepayDealtConds == null)
        {
            return null;
        }

        // 3) ��t�������擾����
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        //get��t�����̔N��������
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strOrderAcceptYMD = l_format.format(l_tsOrderAcceptTime);

        //ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // 4) �擾�����i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g����LOOP����
        WEB3GentradeBranchMarketRepayDealtCond l_tmpBranchMarketRepayDealtCond = null;
        String l_strMarketCode;
        ArrayList l_lstMarketCodes = new ArrayList();
        int l_intSize = l_branchMarketRepayDealtConds.length;
        for(int i = 0; i < l_intSize; i++)
        {
            l_tmpBranchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];

            l_strMarketCode = l_tmpBranchMarketRepayDealtCond.getMarketCode();
            //ArrayList�̗v�f���ɁA���̎s��R�[�h���܂܂�Ă��Ȃ��ꍇ�̂݁A
            //���̎s��R�[�h��ArrayList��add����
            if(l_lstMarketCodes.contains(l_strMarketCode))
            {
                continue;
            }

            //�p�����[�^.�M�p����敪!="���x�^��ʐM�p(����)"�̏ꍇ�A
            //�p�����[�^.�M�p����敪!=�u�i���X�s��ٍϕʁj�戵�����v
            //�I�u�W�F�N�g.�ٍϋ敪�ł���u�i���X�s��ٍϕʁj�戵�����v
            //�I�u�W�F�N�g�̓X�L�b�v����
            if( ! l_strMargineTradeDiv.equals(WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN))
            {
                BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow =
                    (BranchMarketRepayDealtCondRow)l_tmpBranchMarketRepayDealtCond.getDataSourceObject();
                if( ! l_strMargineTradeDiv.equals(l_branchMarketRepayDealtCondRow.getRepaymentDiv()))
                {
                    continue;
                }
            }

            //is�戵�\
            if (l_tmpBranchMarketRepayDealtCond.isHandlingPossible())
            {

                //get�s��ǎ���(HHmmss)
                String l_strTradeCloseTime =
                    getTradeCloseTime(
                        l_strMarketCode,
                        l_clendarContext.getProductCode());
                //get �i�s��ǎ��Ԃ�[%d]���O�j
                Date l_datTradeCloseTime =
                    WEB3DateUtility.getDate(
                        l_strOrderAcceptYMD + l_strTradeCloseTime,
                        "yyyyMMddHHmmss");
                Date l_datMessageSuspensionTime =
                    WEB3DateUtility.addMinute(
                        l_datTradeCloseTime,
                        -l_lngMessageSuspension);

                //�i�s��ǎ��Ԃ�[%d]���O�j <= �i��t�����̎��ԕ����j <= �s��ǎ���
                //�̏ꍇ�A���̎s��R�[�h��ArrayList�Ɏc���Ă����B
                //��L�ȊO�̏ꍇ�́A���̎s��R�[�h��ArrayList����remove����B
                //�� [%d]�̕����́A���X.get�s��ǌx�����\��()�ɂ���Ď擾�������l�B
                if (WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                    && WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datTradeCloseTime) <= 0)
                {
                    l_lstMarketCodes.add(l_strMarketCode);
                }

            }
        }

        l_intSize = l_lstMarketCodes.size();
        String[] l_strMarketCodes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strMarketCodes[i] = (String)l_lstMarketCodes.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }


    /**
     * validate�ǌ���������t�\<BR>
     * �����̒����^����̎�t���\���ǂ������肷��B<BR>
     * �i*�s��ǌ�`�،���Ж��ɒʒm���󂯂čs���o���I���ʒm�I���܂ł̊Ԃ́A<BR>
     * �@@���������^�����t��s�Ƃ���B�j<BR>
     * <BR>
     * �I�[�o�[���[�h���\�b�h�ɈϏ��ideligate�j����B<BR>
     * <BR>
     * [validate�ǌ���������t�\()�Ɏw�肷�����]<BR>
     * �����^�C�v�F�@@����.�����^�C�v<BR>
     * �敨�^�I�v�V�����敪�F�@@�hDEFAULT�h<BR>
     * @@param l_productType - �����^�C�v�B
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406C03130282
     */
    public static void validateTradeCloseChangeOrCancel(ProductTypeEnum l_productType)
        throws WEB3BaseException
    {
        validateTradeCloseChangeOrCancel(l_productType, WEB3FuturesOptionDivDef.DEFAULT);
    }

    /**
     * validate�ǌ���������t�\<BR>
     * �����̒����^����̎�t���\���ǂ������肷��B<BR>
     * �i*�s��ǌ�`�،���Ж��ɒʒm���󂯂čs���o���I���ʒm�I���܂ł̊Ԃ́A<BR>
     * �@@���������^�����t��s�Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@this.is�s��J�ǎ��ԑ�( )==true�̏ꍇ�́A<BR>
     * �@@�@@�@@��������\�Ɣ��肵�A����������return����B<BR>
     * <BR>
     * �Q�j�@@������ԊǗ�.is�s��J�ǎ��ԑ�( )==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�y�o���I���e�[�u���z�������A�y�ю���J�����_�R���e�L�X�g�̓��e<BR>
     *       �œǂ݁A�Y���f�[�^�Ȃ��̏ꍇ�͗�O��throw����B<BR>
     * -�Y���f�[�^�Ȃ�-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00812<BR>
     * <BR>
     * �@@�@@�@@������������<BR>
     * �@@�@@�@@�،���ЃR�[�h��������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�@@�@@�����^�C�v���@@����.�����^�C�v<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪���@@����.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@�o���I���敪��"DEFAULT" <BR>
     * <BR>
     *  (*1)�@@������ԃR���e�L�X�g�̎擾<BR>
     *
     * �|ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>
     * <BR>�@@
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "web3.tradingcalendarcontext")<BR>
     * @@param l_productType - �����^�C�v�B
     * @@param l_strFuturesType - �敨�^�I�v�V�����敪<BR>
     * 0�FDEFAULT<BR>
     * 1�F�敨<BR>
     * 2�F�I�v�V����<BR>
     * <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407CBA67036A
     */
    public static void validateTradeCloseChangeOrCancel(
        ProductTypeEnum l_productType,
        String l_strFuturesType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradeCloseChangeOrCancel(ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.is�s��J�ǎ��ԑ�( )==true�̏ꍇ�́A
        //    ��������\�Ɣ��肵�A����������return����B
        if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        //������ԊǗ�.is�s��J�ǎ��ԑ�( )==false�̏ꍇ�́A
        // �y�o���I���e�[�u���z�������A�y�ю���J�����_�R���e�L�X�g�̓��e�œǂ݁A
        // �Y���f�[�^�Ȃ��̏ꍇ�͗�O��throw����B
        else
        {
            //ThreadLocalSystemAttributesRegistry���A
            // ����J�����_�R���e�L�X�g���擾����B
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            OrderexecutionEndRow l_orderexecutionEndRow = null;
            try
            {
                l_orderexecutionEndRow =
                    OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    l_clendarContext.getInstitutionCode(),
                    l_productType,
                    l_strFuturesType,
                    WEB3OrderexecutionEndTypeDef.DEFAULT);
            }
            catch (DataException de)
            {
                log.error(STR_METHOD_NAME, de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }

            if (l_orderexecutionEndRow == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00812,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return;

    }

    /**
     * reset�����R�[�h<BR>
     * LocalThread���̎�����ԃR���e�L�X�g.�����R�[�h�̃��Z�b�g���s���B<BR>
     * <BR>
     * �� �敨�A�I�v�V�����A�����M���́A�������ƂɎ�����Ԃ�ݒ肵�Ă���B<BR>
     * �@@���̂��߁A�Ɖ�A�T�[�r�X�������ɕ��������������ꍇ��<BR>
     * �@@��������I�u�W�F�N�g�擾�O�ɁA<BR>
     * �@@������ԃR���e�L�X�g�̖����R�[�h�����Z�b�g����K�v������B<BR>
     * <BR>
     * �P�j�@@������ԃR���e�L�X�g�擾<BR>
     * ThreadLocalSystemAttributesRegistry��������ԃR���e�L�X�g���擾����B
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * �Q�j�@@�����R�[�h�Z�b�g<BR>
     * �擾����������ԃR���e�L�X�g�I�u�W�F�N�g�ɖ����R�[�h���Z�b�g����B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g.set�����R�[�h(�����R�[�h)<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * <BR>
     * ���M�̏ꍇ�A�����R�[�h<BR>
     * �敨�^�I�v�V�����̏ꍇ�A�����Y�����R�[�h<BR>
     * @@roseuid 407F8FCB0231
     */
    public static void resetProductCode(String l_strProductCode)
    {
        //      ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�����R�[�h�Z�b�g
        l_clendarContext.setProductCode(l_strProductCode);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * reset�s��R�[�h<BR>
     * LocalThread���̎�����ԃR���e�L�X�g.�����R�[�h�̃��Z�b�g���s���B<BR>
     * <BR>
     * �� �Ɖ�A�T�[�r�X�������ɕ����s��������ꍇ��<BR>
     * �@@��������I�u�W�F�N�g�擾�O�ɁA<BR>
     * �@@������ԃR���e�L�X�g�̎s��R�[�h�����Z�b�g����K�v������B<BR>
     * <BR>
     * �P�j�@@������ԃR���e�L�X�g�擾<BR>
     * ThreadLocalSystemAttributesRegistry��������ԃR���e�L�X�g���擾����B
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�Z�b�g<BR>
     * �擾����������ԃR���e�L�X�g�I�u�W�F�N�g�ɖ����R�[�h���Z�b�g����B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g.set�s��R�[�h(�s��R�[�h)<BR>
     * @@param l_strMarketCode - �s��R�[�h
     * @@roseuid 407F936F033B
     */
    public static void resetMarketCode(String l_strMarketCode)
    {
        //ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�s��R�[�h�Z�b�g
        l_clendarContext.setMarketCode(l_strMarketCode);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * reset��t���ԋ敪<BR>
     * LocalThread���̎�����ԃR���e�L�X�g.��t���ԋ敪�̃��Z�b�g���s���B<BR>
     * <BR>
     * �P�j�@@������ԃR���e�L�X�g�擾<BR>
     * ThreadLocalSystemAttributesRegistry��������ԃR���e�L�X�g���擾����B
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * �Q�j�@@��t���ԋ敪�Z�b�g<BR>
     * �擾����������ԃR���e�L�X�g�I�u�W�F�N�g�Ɏ�t���ԋ敪���Z�b�g����B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g.set��t���ԋ敪(��t���ԋ敪)<BR>
     * @@param l_strTradingTimeType - ��t���ԋ敪
     * @@roseuid 407F9AC602ED
     */
    public static void resetTradingTimeType(String l_strTradingTimeType)
    {
        //ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //��t���ԋ敪�Z�b�g
        l_clendarContext.setTradingTimeType(l_strTradingTimeType);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * reset������t�g�����U�N�V����<BR>
     * <BR>
     * LocalThread���̎�����ԃR���e�L�X�g.������t�g�����U�N�V������<BR>
     * ���Z�b�g���s���B<BR>
     * �P�j�@@������ԃR���e�L�X�g�擾<BR>
     * ThreadLocalSystemAttributesRegistry��������ԃR���e�L�X�g���擾����<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * �Q�j�@@������t�g�����U�N�V�����Z�b�g<BR>
     * �擾����������ԃR���e�L�X�g�I�u�W�F�N�g�ɒ�����t�g�����U�N�V������<BR>
     * �Z�b�g����B<BR>
     * ����J�����_�R���e�L�X�g.set������t�g�����U�N�V����(������t�g�����U�N�V����)<BR>
     * <BR>
     * @@param l_strOrderAcceptTransaction - ������t�g�����U�N�V����
     * @@roseuid 409EFFC401A7
     */
    public static void resetOrderAcceptTransaction(String l_strOrderAcceptTransaction)
    {
        //ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //������t�g�����U�N�V�����Z�b�g
        l_clendarContext.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }
    
    /**
     * reset������t���i<BR>
     *  <BR>
     * LocalThread���̎�����ԃR���e�L�X�g.������t�g�����U�N�V������<BR>
     * ���Z�b�g���s���B<BR>
     * <BR>
     * �P�j�@@������ԃR���e�L�X�g�擾<BR>
     * ThreadLocalSystemAttributesRegistry��������ԃR���e�L�X�g���擾����<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * �Q�j�@@������t���i�Z�b�g<BR>
     * �擾����������ԃR���e�L�X�g�I�u�W�F�N�g�ɒ�����t���i���Z�b�g����B<BR>
     * ����J�����_�R���e�L�X�g.set������t���i(������t���i)<BR>
     * <BR>
     * @@param l_strOrderAcceptProduct - ������t���i
     */
    public static void resetOrderAcceptProduct(String l_strOrderAcceptProduct)
    {
        //ThreadLocalSystemAttributesRegistry���A
        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //������t���i�Z�b�g
        l_clendarContext.setOrderAcceptProduct(l_strOrderAcceptProduct);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * (setBusinessTimestamp)<BR>
     *<BR>
     * xTrade�����p����Ɩ��������Z�b�g����B<BR>
     * �P�j�@@�Ɩ����t�擾 <BR>
     * TradingSystem.getBizDate()�ɂċƖ����t���擾����B <BR>
     * �Q�j�@@�Ɩ������ҏW <BR>
     * �ȉ��̒ʂ�A�Ɩ������iTimestamp�^�j��ҏW����B<BR>�@@
     * �Ɩ����t�{�}�V�����t��HH:MM:SS <BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry��TIMESTAMP_TAG<BR>
     * �����ɋƖ��������Z�b�g����B<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     *     TIMESTAMP_TAG, <BR>
     *     ���ݓ��� <BR>
     *     );  <BR>
     */
    public static void setBusinessTimestamp()
    {
        final String STR_METHOD_NAME = "setBusinessTimestamp";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            null);

        //�P�j�@@�Ɩ����t�擾
        // TradingSystem.getBizDate()�ɂċƖ����t���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Date l_datBizDate = l_tradingSys.getBizDate();

        //�Q�j�@@�Ɩ������ҏW
        //�ȉ��̒ʂ�A�Ɩ������iTimestamp�^�j��ҏW����B
        //�Ɩ����t�{�}�V�����t��HH:MM:SS

        //get �Ɩ����t
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strBizDateYMD = l_format.format(l_datBizDate);
        //get �}�V�����t��HH:MM:SS
        Date l_machineTime = GtlUtils.getSystemTimestamp();
        l_format = GtlUtils.getThreadSafeSimpleDateFormat("HH:mm:ss");
        String l_strMachineTime = l_format.format(l_machineTime);
        //get �Ɩ�����
        Date l_datBusinessTime = WEB3DateUtility.getDate(
            l_strBizDateYMD + l_strMachineTime,
            "yyyyMMddHH:mm:ss");

        //�R�j�@@ThreadLocalSystemAttributesRegistry��TIMESTAMP_TAG
        //�����ɋƖ��������Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            new Timestamp(l_datBusinessTime.getTime()));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�c�Ɠ��敪�擾)<BR>
     *<BR>
     * ������[��t����]Timestamp�����ɁA�j�����擾���ēy���������ꍇ�A<BR>
     * �u��c�Ɠ��v��Ԃ��B<BR>
     *<BR>
     * [��t����]���y���ȊO�̏ꍇ�A�J�����_�E�e�[�u�����������A<BR>
     * �������ʂ�0�s�̏ꍇ�A�u�c�Ɠ��v��Ԃ��B<BR>
     * ��L�ȊO�́A�u��c�Ɠ��v��Ԃ��B<BR>
     *<BR>
     * @@param l_tsOrderAcceptDate - ��t����
     * @@return
     */
    public static String getBizDateType(Timestamp l_tsOrderAcceptDate)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getBizDateType(Timestamp)";
        CalendarRow l_CalendarRow = null;

        String l_strBizDateType;

        Calendar l_calendarBizDate = Calendar.getInstance();
        l_calendarBizDate.setTime(l_tsOrderAcceptDate);

        // DB�����p�Ɏ����b�~���b��������
        l_calendarBizDate.set(Calendar.HOUR_OF_DAY, 0);
        l_calendarBizDate.set(Calendar.MINUTE, 0);
        l_calendarBizDate.set(Calendar.SECOND, 0);
        l_calendarBizDate.set(Calendar.MILLISECOND, 0);


        try
        {
            l_CalendarRow = CalendarDao.findRowByPk(new Timestamp(l_calendarBizDate.getTimeInMillis()));
        }
        catch (DataFindException l_dfe)
        {
            //no operation
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        if (l_CalendarRow != null)
        {
            l_strBizDateType = l_CalendarRow.getBizDateType();
            return l_strBizDateType;
        }

        if (l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
        {
            // �y�j��
            l_strBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
        }
        else if (
            l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            // ���j��
            l_strBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
        }
        else
        {
            l_strBizDateType = WEB3BizDateTypeDef.BIZ_DATE;
        }

        return l_strBizDateType;

    }

    /**
     * (is�~�j������I���x��)<BR>
     * <BR>
     * ����I���x������\�����鎞�ԑтɂ���ꍇtrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     *  <BR>
     * �P�j�@@�s��ǌx�����\�����擾����B<BR>
     * ���X.get�s��x�����\��()�ɂāA�x�����\�����擾����B<BR>
     * �߂�l��0�̏ꍇ�́A����I��������\�����Ȃ��Ɣ��肵�A<BR>
     * false��ԋp����B<BR>
     *  <BR>
     *    [get�s��x�����\��()�Ɏw�肷�����]<BR>
     *    �����^�C�v�F�@@ProductTypeEnum.����<BR>
     *    �M�p����敪�F�@@0�FDEFAULT<BR>
     *    �敨�^�I�v�V�����敪�F�@@0�FDEFAULT<BR>
     *  <BR>
     * �Q�j�@@�������擾<BR>
     * get������()�ɂāA���݂̔��������擾����B<BR>
     *  <BR>
     * �R�j�@@���݂̎�t�����擾<BR>
     * ThreadLocalSystemAttributesRegistry���A��t������<BR>
     * �擾����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("<BR>
     *  xblocks.gtl.attributes.systemtimestamp")<BR>
     *  <BR>
     * �S�j�@@�V�t�g��̔������擾<BR>
     * <BR>
     * �S�|�P�j�@@��t���ԃV�t�g�V�t�g <BR>
     *  �R�j�Ŏ擾�������݂̎�t������[%d]������擾���A<BR>
     *  ThreadLocalSystemAttributesRegistry��<BR>
     *  "xblocks.gtl.attributes.systemtimestamp"�����ɃZ�b�g����B<BR>
     *  <BR>
     * �� [%d]�̕����́A���X.get�s��ǌx�����\��()�ɂ����<BR>
     * �擾�������l�B<BR>
     *  <BR>
     * �S�|�Q�j�@@�V�t�g��̔������擾<BR>
     * get������()�ɂāA�V�t�g��̔��������擾����B<BR>
     *  <BR>
     * �S�|�R�j�@@��t���Ԃ�߂�<BR>
     * ThreadLocalSystemAttributesRegistry��<BR>
     * "xblocks.gtl.attributes.systemtimestamp"�������A�R�j��<BR>
     * �擾�������݂̎�t�����ɖ߂��B<BR>
     *  <BR>
     * �T�j�@@�߂�l���� <BR>
     * ���������ς���Ă���ꍇ�i�Q�j�Ŏ擾�������݂̔����� != �S�j��<BR>
     * �擾�����V�t�g��̔������j�Atrue��ԋp����B�ȊO�Afalse��ԋp����<BR>
     *  <BR>
     * @@param l_genbBranch - ���X�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return boolean
     * @@roseuid 409EFFC401A7
     */
    public static boolean isMiniStockSuspension(WEB3GentradeBranch l_genBranch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMiniStockSuspension(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngMarketMessageSuspension = 0L;
        
        //�P�j�@@�s��ǌx�����\�����擾����B
        //���X.get�s��x�����\��()�ɂāA�x�����\�����擾����B
        //�߂�l��0�̏ꍇ�́A����I��������\�����Ȃ��Ɣ��肵�Afalse��ԋp����B
        l_lngMarketMessageSuspension = l_genBranch.getMarketMessageSuspension(
            ProductTypeEnum.EQUITY,
            WEB3MarginTradingDivDef.DEFAULT,
            WEB3FuturesOptionDivDef.DEFAULT);
        if(l_lngMarketMessageSuspension == 0L)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�������擾
        //get������()�ɂāA���݂̔��������擾����B
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //�R�j�@@���݂̎�t�����擾
        //ThreadLocalSystemAttributesRegistry���A��t�������擾����
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        
        //���݂̎�t������[%d]������擾����
        Date l_acceptDateAddMinute = WEB3DateUtility.addMinute(
            l_tsOrderAcceptDate,
            l_lngMarketMessageSuspension);
        Timestamp l_tsAcceptDateAddMinute = new Timestamp(l_acceptDateAddMinute.getTime());
            
        //�S�|�P�j�@@��t���ԃV�t�g�V�t�g 
        //  �R�j�Ŏ擾�������݂̎�t������[%d]������擾���AThreadLocalSystemAttributesRegistry
        //��"xblocks.gtl.attributes.systemtimestamp"�����ɃZ�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,l_tsAcceptDateAddMinute);
        
        // �S�|�Q�j�@@�V�t�g��̔������擾
        //get������()�ɂāA�V�t�g��̔��������擾����B
        Date l_shiftedBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //�S�|�R�j�@@��t���Ԃ�߂�
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,l_tsOrderAcceptDate);
        
        //�T�j�@@�߂�l���� 
        //���������ς���Ă���ꍇ�i�Q�j�Ŏ擾�������݂̔����� != �S�j��
        //�擾�����V�t�g��̔������j�Atrue��ԋp����B�ȊO�Afalse��ԋp����
        if(WEB3DateUtility.compareToDay(l_bizDate,l_shiftedBizDate) != 0)
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
     * (get�s��ǌx���O���s��)<BR>
     * ����I���x������\�����鎞�ԑтɂ���O���s��̎s��R�[�h��<BR>
     * �z��ŕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i������ԃ��f���jget�s��ǌx���O���s��v�Q�ƁB<BR>
     * <BR>
     * @@param l_genBranch ���X�I�u�W�F�N�g
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4020D80D0331
     */
    public static String[] getTradeCloseFeqMarket(
        WEB3GentradeBranch l_genBranch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseFeqMarket(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strFeqMarkets = null;
        String l_strMarketCode = null;
        
        //2:(*1)�@@�c�Ɠ��敪���擾���A�h��c�Ɠ��h�̏ꍇ��
        //�x���s����擾�����ɏ������I������B
        //�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        //�Q�j�@@��t���̉c�Ɠ��敪����B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptTime);
        //�R�j�@@�C�O�̉c�Ɠ��敪����
        String l_strFeqBizDateType =
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(l_tsOrderAcceptTime);
        //�S�j�@@�Q�j�A�R�j�Ŕ��肵���c�Ɠ��敪�̂����ꂩ���h��c�Ɠ��h�̏ꍇ�́A
        //null��ԋp���������I������B
        if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) || 
            WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
        {
            return null;
        }

        //3:get�s��x�����\��(ProductTypeEnum, String, Strin)
        long l_lngMessageSuspension = l_genBranch.getMarketMessageSuspension(
            ProductTypeEnum.FOREIGN_EQUITY,
            WEB3MarginTradingDivDef.DEFAULT,
            WEB3FuturesOptionDivDef.DEFAULT
            );
        
        //4:(*2)�@@�߂�l����    
        //���X.get�s��ǌx�����\��()�̖߂�l��0�̏ꍇ�́A
        //����I���x��������\�����Ȃ��Ɣ��肵�Anull��ԋp���������I������B        
        if(l_lngMessageSuspension == 0)
        {
            return null;
        }

        //5:get�i���X�s���.�O���j�戵����(���X)
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqHandlingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_genBranch);
        if(l_feqHandlingCondBranchMarkets == null)
        {
            return null;
        }

        //8:(*)get�i���X�s���.�O���j�戵����()�̖߂�l�̗v�f����Loop����
        int l_intLength = l_feqHandlingCondBranchMarkets.length;

        // �S) get��t�����̔N��������
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strOrderAcceptYMD = l_format.format(l_tsOrderAcceptTime);

        //����J�����_�R���e�L�X�g���擾����
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        WEB3GentradeFeqBranchMarketDealtCond l_tmpFeqHandlingCondBranchMarket = null;
        ArrayList l_lstFeqMarketCodes = new ArrayList();

        for (int i = 0; i < l_intLength; i++)
        {
            l_tmpFeqHandlingCondBranchMarket = l_feqHandlingCondBranchMarkets[i];

            //10:get�s��R�[�h()
            l_strMarketCode = l_tmpFeqHandlingCondBranchMarket.getMarketCode();
            
            //ArrayList�̗v�f���ɁA���̎s��R�[�h���܂܂�Ă��Ȃ��ꍇ�̂݁A
            //���̎s��R�[�h��ArrayList��add����
            if(l_lstFeqMarketCodes.contains(l_strMarketCode))
            {
                continue;
            }
            
            boolean l_isHandlingPossible = 
                l_feqHandlingCondBranchMarkets[i].isHandlingPossible(ProductTypeEnum.FOREIGN_EQUITY);
            //9:�iis�戵�\() == false�j�̏ꍇ�A
            //���̗v�f�ɂ��Ă̏������s��Ȃ��B
            if(l_isHandlingPossible)
            {

                //11:get�s��ǎ���(String, String)(HHmmss)
                String l_strTradeCloseTime = getTradeCloseTime(
                    l_strMarketCode,
                    l_clendarContext.getProductCode());

                //get �i�s��ǎ��Ԃ�[%d]���O�j
                Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                    l_strOrderAcceptYMD + l_strTradeCloseTime,
                    "yyyyMMddHHmmss");
                Date l_datMessageSuspensionTime = WEB3DateUtility.addMinute(
                    l_datTradeCloseTime,
                    -l_lngMessageSuspension);
                //12:add(�s��R�[�h : Object)
                //�i�s��ǎ��Ԃ�[%d]���O�j <= �i��t�����̎��ԕ����j <= �s��ǎ��Ԃ�
                //�ꍇ�A�s��R�[�h��ArrayList�ɒǉ�����B
                //�� [%d]�̕����́A���X.get�s��ǌx�����\��()�ɂ���Ď擾�������l�B
                if (WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                   && WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datTradeCloseTime) <= 0)
                {
                    l_lstFeqMarketCodes.add(l_strMarketCode);
                }
            }
        }

        int l_intSize = l_lstFeqMarketCodes.size();
        String[] l_feqMarkets = new String[l_intSize];
        
        if(l_intSize > 0)
        {
            l_lstFeqMarketCodes.toArray(l_feqMarkets);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqMarkets;
    }

    /**
     * (�C�O�̉c�Ɠ��敪�擾)<BR>
     * �|�O���C�O�s��J�����_�e�[�u�����ȉ��̏����Ō������A<BR>
     * �Y���s�̉c�Ɠ��敪���h��c�Ɠ��h�̏ꍇ�A<BR>
     * �c�Ɠ��敪�h��c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * [�O���C�O�s��J�����_�e�[�u����������]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h = ����.�s��R�[�h<BR>
     * �@@���t = �i����.��t�����̓��t�����j<BR>
     *<BR>
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_tsOrderAcceptDate ��t����
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public static String getFeqBizDateType(
        String l_strInstitutionCode,
        String l_strMarketCode,
        Timestamp l_tsOrderAcceptDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getFeqBizDateType(String, String, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //�O���C�O�s��J�����_�[Row
        FeqCalendarRow l_feqCalendarRow = null;

        //��t�����̓��t����
        Timestamp l_strOrderAcceptYMD = 
            new Timestamp(WEB3DateUtility.toDay(l_tsOrderAcceptDate).getTime());
        
        String l_strBizDateType = WEB3BizDateTypeDef.BIZ_DATE;
        
        try
        {
            l_feqCalendarRow =
                FeqCalendarDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strMarketCode,
                    l_strOrderAcceptYMD);
            
            l_strBizDateType = l_feqCalendarRow.getBizDateType();
        }
        catch (DataFindException e)
        {
            log.debug("�O���C�O�s��J�����_�[�e�[�v����" 
                + "�،���ЃR�[�h = " + l_strInstitutionCode
                + " �s��R�[�h = " + l_strMarketCode
                + " ��t���� = " + l_strOrderAcceptYMD
                + " �̃��R�[�h�������̂ŁA�u�c�Ɠ��v�Ƃ���");
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBizDateType;
    }

    /**
     * (�C�O�̉c�Ɠ��敪�擾)<BR>
     * �|�O���C�O�s��J�����_�e�[�u�����ȉ��̏����Ō������A<BR>
     * �Y���s�̉c�Ɠ��敪���h��c�Ɠ��h�̏ꍇ�A<BR>
     * �c�Ɠ��敪�h��c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * [�O���C�O�s��J�����_�e�[�u����������]<BR>
     * �@@�،���ЃR�[�h = ������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h = ������ԃR���e�L�X�g.�s��R�[�h<BR>
     * �@@���t = �i��t�����̓��t�����j<BR>
     *<BR>
     * @@param l_tsOrderAcceptDate ��t����
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public static String getFeqBizDateType(Timestamp l_tsOrderAcceptDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getFeqBizDateType(Timestamp)";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = null;
        String l_strMarketCode = null;

        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //�s��R�[�h
        l_strMarketCode = l_clendarContext.getMarketCode();
        
        String l_strBizType;
            l_strBizType = getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsOrderAcceptDate
                );

        log.exiting(STR_METHOD_NAME);       
        return l_strBizType;
    }
    
    /**
     * is������x�e���ԑ�() <BR>
     * ��������x�e���ԑт̏ꍇ��true���A�����łȂ��ꍇ��false��ԋp����B<BR> 
     * ��������c�Ɠ��̏ꍇ�^����O�����̏ꍇ�́Afalse�i�x�e���ԑтł͂Ȃ��j��<BR>
     * �ԋp����B<BR> 
     *<BR>
     * �P�j�@@this.is�s��J�ǎ��ԑ�() == false�i�ǒ��^��c�Ɠ��j�̏ꍇ�́A<BR> 
     *      false�i�x�e���ԑтł͂Ȃ��j��ԋp����B <BR>
     *      �ȊO�i���c�Ɠ��ŏꒆ�̏ꍇ�j�A�ȉ��̏������s���B<BR> 
     *<BR>
     * �Q�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g��<BR>
     * �擾����B<BR> 
     *       ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR> 
     *<BR>
     *      �E�擾����������ԃR���e�L�X�g.��t���ԋ敪 == "����O����"�̏ꍇ�́A<BR> 
     *        false�i�x�e���ԑтł͂Ȃ��j��ԋp����B <BR>
     *<BR>
     *      �Ethis.is�[�ꎞ�ԑ�() == true�̏ꍇ�́Afalse�i�x�e���ԑтł͂Ȃ��j��ԋp����B <BR>
     *<BR>
     *      �E�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A<BR> 
     *        �f�[�^�s�����Ƃ��ė�O���X���[����B <BR>
     *<BR>
     *       ������ԃR���e�L�X�g.�،���ЃR�[�h<BR> 
     *       ������ԃR���e�L�X�g.���X�R�[�h <BR>
     *       ������ԃR���e�L�X�g.�s��R�[�h <BR>
     *       ������ԃR���e�L�X�g.��t���ԋ敪 <BR>
     *       ������ԃR���e�L�X�g.�����R�[�h <BR>
     *<BR>
     * �R�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR> 
     *       ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")<BR> 
     *<BR>
     * �S�j�@@��t���̉c�Ɠ��敪����B<BR> 
     *       �J�����_�e�[�u�����擾������t�����̓��t�����Ō������A<BR>
     * �s�̉c�Ɠ��敪���擾����B <BR>
     *       �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR> 
     *<BR>
     * �T�j�@@������Ԏ擾<BR> 
     *       �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR> 
     *<BR>
     *     �@@[�����L�[]<BR> 
     *�@@      �،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR> 
     *      �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     *      �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     *      �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     *        �����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     *      �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪 <BR>
     *�@@      �J�n���ԁ@@<= �i��t�����̎��ԕ����iHHMMSS�j�j <= �I������<BR> 
     *<BR>
     *      �@@�擾�����s���A�ȉ��̒ʂ�߂�l�𔻒肷��B <BR>
     *      �@@�|��L�Ɉ�v����s�́u�s��g���K���s�v���ڂ� <BR>
     *�@@    �@@�@@�hSONAR��MQ�g���K�����{���Ȃ��h�ł����true�i�x�e���ԑтł���j�A<BR> 
     *�@@     �@@�@@�ȊO��false�i�x�e���ԑтł͂Ȃ��j��ԋp����B <BR>
     *      �@@�|��L�Ɉ�v����s�����݂��Ȃ��ꍇ�́A<BR>
     *      �@@�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B<BR> 
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public static boolean isTradeCloseTimeZone() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeCloseTimeZone()";
        log.entering(STR_METHOD_NAME);

        boolean l_boReturn;

        // �P�j�@@this.is�s��J�ǎ��ԑ�() == false�i�ǒ��^��c�Ɠ��j�̏ꍇ�́A 
        //        false�i�x�e���ԑтł͂Ȃ��j��ԋp����B 
        if (isTradeOpenTimeZone() == false)
        {
            l_boReturn = false;
        }
        else
        {
            //�Q�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B 
            //      ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext") 
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

            //�E�擾����������ԃR���e�L�X�g.��t���ԋ敪 == "����O����"�̏ꍇ�́A 
            //   false�i�x�e���ԑтł͂Ȃ��j��ԋp����B                 
            String l_strTempTradingTimeType = l_clendarContext.getTradingTimeType();
            if (WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_strTempTradingTimeType))
            {
                l_boReturn = false;
            }
            //�Ethis.is�[�ꎞ�ԑ�() == true�̏ꍇ�́Afalse�i�x�e���ԑтł͂Ȃ��j��ԋp����B
            else if (WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
            {
                l_boReturn = false;
            }
            else
            {
                //�E�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A 
                //�@@�f�[�^�s�����Ƃ��ė�O���X���[����B 
                //
                //�@@������ԃR���e�L�X�g.�،���ЃR�[�h 
                //�@@������ԃR���e�L�X�g.���X�R�[�h 
                //�@@������ԃR���e�L�X�g.�s��R�[�h 
                //�@@������ԃR���e�L�X�g.��t���ԋ敪 
                //�@@������ԃR���e�L�X�g.�����R�[�h 
                String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
                String l_strBranchCode = l_clendarContext.getBranchCode();
                String l_strMarketCode = l_clendarContext.getMarketCode();
                String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
                String l_strProductCode = l_clendarContext.getProductCode();
                if ((l_strInstitutionCode == null)
                    || (l_strBranchCode == null)
                    || (l_strMarketCode == null)
                    || (l_strTradingTimeType == null)
                    || (l_strProductCode == null))
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        WEB3GentradeTradingTimeManagement.class.getName()
                           + "." + STR_METHOD_NAME);
                }

                //�R�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B 
                //      ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") 
                Timestamp l_tsOrderAcceptDate =
                    (Timestamp)
                        (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

                //��t�����̎��ԕ����iHHMMSS�j���擾����B 
                String l_strOrderAcceptDateHHMMSS = 
                    WEB3DateUtility.formatDate(l_tsOrderAcceptDate, "HHmmss");

                //�S�j�@@��t���̉c�Ɠ��敪����B 
                //     �J�����_�e�[�u�����擾������t�����̓��t�����Ō������A�s�̉c�Ɠ��敪���擾����B 
                //     �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B 
                String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);

                //�T�j�@@������Ԏ擾 
                //      �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B 
                //
                //     [�����L�[] 
                //     �،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B 
                //     ���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B 
                //     �s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B 
                //     ��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B 
                //     �����R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B 
                //     �c�Ɠ��敪�F�@@���肵���c�Ɠ��敪 
                //     �J�n���ԁ@@<= �i��t�����̎��ԕ����iHHMMSS�j�j <= �I������ 
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");
                l_sbWhere.append(" and branch_code = ? ");
                l_sbWhere.append(" and market_code = ? ");
                l_sbWhere.append(" and trading_time_type = ? ");
                l_sbWhere.append(" and product_code = ? ");
                l_sbWhere.append(" and biz_date_type = ? ");
                l_sbWhere.append(" and start_time <= ? ");
                l_sbWhere.append(" and end_time >= ? ");

                List l_lstTradingTimeWhere = new ArrayList();
                l_lstTradingTimeWhere.add(l_strInstitutionCode);
                l_lstTradingTimeWhere.add(l_strBranchCode);
                l_lstTradingTimeWhere.add(l_strMarketCode);
                l_lstTradingTimeWhere.add(l_strTradingTimeType);
                l_lstTradingTimeWhere.add(l_strProductCode);
                l_lstTradingTimeWhere.add(l_strBizDateType);
                l_lstTradingTimeWhere.add(l_strOrderAcceptDateHHMMSS);
                l_lstTradingTimeWhere.add(l_strOrderAcceptDateHHMMSS);

                Object[] l_objTradingTimeWhere = 
                    new Object[l_lstTradingTimeWhere.size()];
                l_lstTradingTimeWhere.toArray(l_objTradingTimeWhere);

                List l_lstRecords;

                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lstRecords = l_queryProcessor.doFindAllQuery(
                        TradingTimeRow.TYPE,
                        l_sbWhere.toString(),
                        l_objTradingTimeWhere);

                }
                catch (DataException de)
                {
                    log.error(STR_METHOD_NAME, de);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        WEB3GentradeTradingTimeManagement.class.getName() + "." 
                            + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }

                // ��L�Ɉ�v����s�����݂��Ȃ��ꍇ�́A�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B 
                int l_intSize = l_lstRecords.size();
                if (l_intSize == 0)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
                }
        
                //�@@�|��L�Ɉ�v����s�́u�s��g���K���s�v���ڂ� 
                //  �hSONAR��MQ�g���K�����{���Ȃ��h�ł����true�i�x�e���ԑтł���j�A 
                //  �ȊO��false�i�x�e���ԑтł͂Ȃ��j��ԋp����B 

                String l_strSubmitMarketTrigger = 
                    ((TradingTimeRow)l_lstRecords.get(0)).getSubmitMarketTrigger();
                if (WEB3SubmitMarketTriggerDef.NOT_TRIGGER.equals(l_strSubmitMarketTrigger))
                {
                    l_boReturn = true;
                    log.debug("*** ������ԊǗ�***   �x�e���ԑтł���");
                }
                else
                {
                    l_boReturn = false;
                    log.debug("*** ������ԊǗ�***   �x�e���ԑтłȂ�");
                }                
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * is������x�e���ԑ� (Date ����Ώۓ���) <BR>
     * �����Ŏw�肳�ꂽ����Ώۓ����ɂ����āA <BR>
     * ��������x�e���ԑт̏ꍇ��true���A�����łȂ��ꍇ��false��ԋp����B<BR> 
     * ��������c�Ɠ��̏ꍇ�^����O�����̏ꍇ�́Afalse�i�x�e���ԑтł͂Ȃ��j��<BR>
     * �ԋp����B<BR> 
     *<BR>
     *  �P�j�@@TIMESTAMP_TAG �̒l���擾���A���\�b�h���̃��[�J���ϐ��ɋL��������B<BR> 
     *        TIMESTAMP_TAG�̒l�̎擾�F <BR>
     *        ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)<BR> 
     *<BR>
     *  �Q�j�@@TIMESTAMP_TAG �ɁA�����́u����Ώۓ����v���Z�b�g����B<BR> 
     *        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,<BR> 
     * �����́u����Ώۓ����v)<BR> 
     *<BR>
     *  �R�j�@@this.is������x�e���ԑ�(void)���R�[������B<BR> 
     *        ��O��throw���ꂽ�ꍇ�A�S�j���s���Ă���A<BR>
     *        throw���ꂽ��O�����̂܂�throw����B<BR> 
     *<BR>
     *  �S�j�@@TIMESTAMP_TAG �̐ݒ�l���A���ɖ߂��B<BR>
     *        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, �P�j��<BR>
     *        �L�������Ă��������̒l)<BR> 
     *<BR>
     *  �T�j�@@�R�j�̖߂�l��ԋp����B<BR> 
     *<BR>
     * @@param l_date ����Ώۓ���
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public static boolean isTradeCloseTimeZone(
        Date l_date) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeCloseTimeZone(Date)";
        log.entering(STR_METHOD_NAME);

        boolean l_boReturn;

        //�P�j�@@TIMESTAMP_TAG �̒l���擾���A���\�b�h���̃��[�J���ϐ��ɋL��������B 
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)(ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        //�Q�j�@@TIMESTAMP_TAG �ɁA�����́u����Ώۓ����v���Z�b�g����B 
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_date);

        //�R�j�@@this.is������x�e���ԑ�(void)���R�[������B 
        try
        {
            l_boReturn = isTradeCloseTimeZone();
        }
        catch (WEB3SystemLayerException l_e)
        {
            //�S�j�@@TIMESTAMP_TAG �̐ݒ�l���A���ɖ߂��B 
            ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_tsOrderAcceptDate);
            throw new WEB3SystemLayerException(
                l_e.getErrorInfo(),
                WEB3GentradeTradingTimeManagement.class.getName() + "." 
                    + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        //�S�j�@@TIMESTAMP_TAG �̐ݒ�l���A���ɖ߂��B 
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_tsOrderAcceptDate);

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * (validate�A��������t�\)<BR>
     * �A�������̎�t���\���ǂ������肷��B<BR>
     * �i*�s��ǌ�`�����J�z�I���܂ł̊Ԃ́A�A��������t��s�Ƃ���B�j<BR>
     * <BR>
     * �P�j���ݓ������擾����B<BR>
     * <BR>
     * �Q�j������ԊǗ�.get�s��ǎ���()�ŁA�ŏI�ǎ��Ԃ��擾����B<BR>
     * <BR>
     * �@@�@@[�����ݒ�d�l]<BR>
     * �@@�@@�@@�s��R�[�h�F�@@����J�����_�R���e�L�X�g�̓�����<BR>
     * �@@�@@�@@�����R�[�h�F�@@����J�����_�R���e�L�X�g�̓�����<BR>
     * <BR>
     * �R�j�ȉ��̏����ɊY������ꍇ�A�p�����[�^.�،����.get�����J�z�����敪()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�E���X.is�[����{() == true and ������ԊǗ�.is�[�ꎞ�ԑ�() == true <BR>
     * and ������ԊǗ�.is�g���K���s() == false�j (*1)<BR>
     * �@@�@@�@@�@@�����X�͈���.�،���ЁA����J�����_�R���e�L�X�g.���X�R�[�h����擾�������� <BR>
     * �@@�@@�@@�E�P�j�Ŏ擾�������ݓ��� > �Q�j�Ŏ擾�����ŏI�ǎ��� (*2)<BR>
     * <BR>
     * �@@�@@��L���\�b�h�̖߂�l��"������"�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@[is�[����{()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����^�C�v�F�@@�p�����[�^.�����^�C�v<BR>
     * <BR>
     * �@@�@@[is�g���K���s()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���������F�@@null<BR>
     * <BR>
     * �@@�@@[get�����J�z�����敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����^�C�v�F�@@�p�����[�^.�����^�C�v<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪�F�@@�p�����[�^.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@�o���I���敪�F�@@�p�����[�^.�o���I���敪<BR>
     * <BR>
     * (*1) ��������ԏI���`�[�ꗧ��ԊJ�n�̎��ԑ�<BR>
     * (*2) �ŏI�ǎ��Ԉȍ~�̎��ԑ�<BR>
     * @@param l_institution �،����
     * @@param l_productTypeEnum �����^�C�v
     * @@param l_strFutureOptionType �敨�^�I�v�V�����敪
     * �i0�FDEFAULT�@@1�F�敨�@@2�F�I�v�V�����j<BR>
     * @@param l_strExecutionEndType �o���I���敪
     * �i1�F�[��O�o���I���i�[����{�����Ёj�@@DEFAULT 0�i�o���I���i�ŏI�j�j�j<BR>
     * @@throws WEB3BaseException
     */
    public static void validateTriggerOrderAccept(
        WEB3GentradeInstitution l_institution,
        ProductTypeEnum l_productTypeEnum,
        String l_strFutureOptionType,
        String l_strExecutionEndType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateTriggerOrderAccept(Institution, ProductTypeEnum, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
        String l_strMarketCode = l_clendarContext.getMarketCode();
        String l_strProductCode = l_clendarContext.getProductCode();
        String l_strBranchCode = l_clendarContext.getBranchCode();
        WEB3GentradeBranch l_branch = null;

        //���X�͈���.�،���ЁA����J�����_�R���e�L�X�g.���X�R�[�h����擾��������
        try
        {
            l_branch = new WEB3GentradeBranch(l_institution, l_strBranchCode);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        String l_strTradeCloseTime =
            WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                l_strMarketCode,
                l_strProductCode);
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptTime);

        //�ȉ��̏����ɊY������ꍇ�A�p�����[�^.�،����.get�����J�z�����敪()���R�[������B
        //  ��������ԏI���`�[�ꗧ��ԊJ�n�̎��ԑ�
        //  �ŏI�ǎ��Ԉȍ~�̎��ԑ�
        if ((l_strAcceptTime.compareTo(l_strTradeCloseTime) > 0)
            || (l_branch.isEveningSessionEnforcemented(l_productTypeEnum)
                && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                && !WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null)))
        {
            String l_strCarryoverEndType = 
                l_institution.getCarryoverEndType(
                    l_productTypeEnum,
                    l_strFutureOptionType,
                    l_strExecutionEndType);
            if (!WEB3CarryoverEndTypeDef.COMPLETE_PROCESS.equals(l_strCarryoverEndType))
            {
                log.debug("�s��ǌ�`�����J�z�I���܂ł̊Ԃ́A�A��������t��s�Ƃ���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02240,
                    WEB3GentradeTradingTimeManagement.class.getName() +
                    "." + STR_METHOD_NAME,
                    "�s��ǌ�`�����J�z�I���܂ł̊Ԃ́A�A��������t��s�Ƃ���B");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�_�E�����[�h���ԑ�)<BR>
     * �_�E�����[�h���\�Ȏ��ԑт����`�F�b�N����<BR>
     * <BR>
     * �P�j�@@�_�E�����[�h���ԑу`�F�b�N<BR>
     * �@@������ԃe�[�u�����ȉ��̏����Ō������A�Y���s�́u��t�\�v���ڂ�"<BR>
     * ��t�s��"�ł���΁A�_�E�����[�h�s�Ɣ��肷��B<BR>
     * �@@�Y���s�������s����ꍇ�́A�P���ł��u��t�\�v�ł���΃_�E�����[�h�\�Ƃ���B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     *�@@�@@���A���A������ԃR���e�L�X�g�̎s��R�[�h�v���p�e�B��null�ł���΁A<BR>
     * �s��R�[�h�͌��������Ɋ܂߂Ȃ��i���ׂĂ̎s���ΏۂƂ���j<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�c�Ɠ��敪�F�@@(*3)<BR>
     * �@@�����R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B<BR>
     *�@@�@@���A���A������ԃR���e�L�X�g�̖����R�[�h�v���p�e�B��null�ł���΁A<BR>
     * �����R�[�h�͌��������Ɋ܂߂Ȃ��i���ׂĂ̖�����ΏۂƂ���j<BR>
     * �@@�J�n���� <= ��t����(*2) <=�@@�I������<BR>
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�́A�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B<BR>
     * <BR>
     * (*1)�@@������ԃR���e�L�X�g�̎擾<BR>
     * �|ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * (*2) ��t���Ԃ̎擾<BR>
     * �|ThreadLocalSystemAttributesRegistry����t�������擾���A�擾���������̎��ԕ����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * (*3) �c�Ɠ��敪�̎擾<BR>
     * �|�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�́h��c�Ɠ��h�B<BR>
     * �@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A�s�̉c�Ɠ��敪���擾����B<BR>
     * �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * @@throws WEB3BaseException
     */
    public static void validateDownloadTimeZone()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDownloadTimeZone()";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strMarketCode = null;
        String l_strTradingTimeType = null;
        String l_strProductCode = null;

        //(*1)�@@������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        //���X�R�[�h
        l_strBranchCode = l_clendarContext.getBranchCode();
        //�s��R�[�h
        l_strMarketCode = l_clendarContext.getMarketCode();
        //��t���ԋ敪
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�����R�[�h
        l_strProductCode = l_clendarContext.getProductCode();

        //(*2) ��t���Ԃ̎擾
        Timestamp l_tsOrderAcceptDate = 
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        //(*3) �c�Ɠ��敪
        String l_bizDateType = getBizDateType(l_tsOrderAcceptDate);

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A
        //��O���X���[����B
        //   ������ԃR���e�L�X�g.�،���ЃR�[�h
        // �@@������ԃR���e�L�X�g.���X�R�[�h
        // �@@������ԃR���e�L�X�g.��t���ԋ敪
        if ((l_strInstitutionCode == null) || 
            (l_strBranchCode == null) || 
            (l_strTradingTimeType == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_lstObjTradingTimeWhere = new ArrayList();

        //�،���ЃR�[�h
        l_sbWhere.append(" institution_code = ? ");
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //���X�R�[�h
        l_sbWhere.append(" and branch_code = ? ");
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //�s��R�[�h
        if (l_strMarketCode != null)
        {
            l_sbWhere.append(" and market_code = ? ");
            l_lstObjTradingTimeWhere.add(l_strMarketCode.trim());
        }
        //��t���ԋ敪
        l_sbWhere.append(" and trading_time_type = ? ");
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //�c�Ɠ��敪
        l_sbWhere.append(" and biz_date_type = ? ");
        l_lstObjTradingTimeWhere.add(l_bizDateType);
        //�����R�[�h
        if (l_strProductCode != null)
        {
            l_sbWhere.append(" and product_code = ? ");
            l_lstObjTradingTimeWhere.add(l_strProductCode.trim());
        }
        //�J�n���� <= ��t����(*2) <=�@@�I������
        l_sbWhere.append(" and start_time <= ? and end_time >= ?");
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);

        int l_intSize = l_lstObjTradingTimeWhere.size();
        Object[] l_objTradingTimeWhere = new Object[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_objTradingTimeWhere[i] = l_lstObjTradingTimeWhere.get(i);
        }

        List l_lisRecords = null;
        try
        {
            //������ԃe�[�u������������
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�����`�F�b�N
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                "������ԃe�[�u�������F ���� = 0");
        }

        //�����s����ꍇ�́A�P���ł��u��t�\�v�ł���Β�����t�\�Ƃ���
        TradingTimeRow l_tradingTimeRow = null;
        String l_strEnableOrder = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (WEB3EnableOrderDef.ENABLE.equals(l_tradingTimeRow.getEnableOrder()))
            {
                l_strEnableOrder = l_tradingTimeRow.getEnableOrder();
                break;
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug(STR_METHOD_NAME + "�F�_�E�����[�h�s���ԑ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02302,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������v�Z�p�����)<BR>
     * <BR>
     * LocalThread���̔������v�Z�p�̊�����̃Z�b�g���s���B<BR>
     * <BR>
     * �� ������ɂ��ꍞ��œ�����������L���[���A�������������ŏ�������K�v������ꍇ�A<BR>
     * �� �����\�b�h�œ������������ƂȂ������ݒ肷��B<BR>
     * <BR>
     * �����̊�������ALocalThread��BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE�ɃZ�b�g����B<BR>
     * ��ThreadLocalSystemAttributesRegistry.setAttribute()�ŃZ�b�g�B<BR>
     * @@param l_strTradingTimeType - �����<BR>
     * �������v�Z�p�̊�����B<BR>
     */
    public static void setBaseTimestampForOrderBizDate(Timestamp l_tsBizDate)
    {
        final String STR_METHOD_NAME = "setBaseTimestampForOrderBizDate(Timestamp)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
            l_tsBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (clear�������v�Z�p�����)<BR>
     * <BR>
     * LocalThread���̔������v�Z�p�̊�����̃N���A���s���B<BR>
     * <BR>
     * LocalThread��BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE���N���A����B<BR>
     * ��ThreadLocalSystemAttributesRegistry.setAttribute()��null���Z�b�g�B<BR>
     */
    public static void clearBaseTimestampForOrderBizDate()
    {
        final String STR_METHOD_NAME = "clearBaseTimestampForOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�������t�O���ԑ�)<BR>
     * <BR>
     * Web�V���ԂŃI�����C���i�J�ǁj���ԑт̏ꍇ�ɁA<BR> 
     * ��������ԂŁA�Y�����������t�O�i�J�ǑO�j�̎��ԑт��ǂ����𔻒肷��B<BR> 
     * �i��������ԁF�@@�s��}�X�^.�o������t�J�n�����A������t�I�������p�Őݒ�j<BR> 
     * <BR>
     * �P�j�@@this.is�s��J�ǎ��ԑ�()==false�̏ꍇ�́Afalse��ԋp����B<BR> 
     * <BR>
     * �Q�j�@@this.is�s��J�ǎ��ԑ�()==true�A���� <BR>
     * �@@�@@�i�s��̒�����t�J�n����(*1) >  ���ݓ���(*2)��HHMMSS�j �̏ꍇ�Atrue��ԋp����B<BR> 
     * �@@�@@�ȊO�Afalse��ԋp����B <BR>
     * <BR>
     * (*1)������t�J�n���� <BR>
     * ����J�����_�R���e�L�X�g.�،���ЃR�[�h�A�s��R�[�h�ɊY������ <BR>
     * �s��I�u�W�F�N�g.������t�J�n�����B<BR>
     * ���s��}�X�^�ɂ�"HH:MM"�t�H�[�}�b�g�Őݒ肳��Ă���̂ŁA<BR> 
     * ��"HHMM00"�Ń`�F�b�N�B<BR> 
     * <BR>
     * (*2)���ݓ��� <BR>
     * GtlUtils.getSystemTimestamp()�̖߂�l�B<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public static boolean isTradeAcceptBeforeTimeZone() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradeAcceptBeforeTimeZone()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //������ԃR���e�L�X�g�̎擾
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    TRADING_CAL_CONTEXT_PATH);

            //�،���Ђ��擾
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(
                l_context.getInstitutionCode());
            
            //�s����擾
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(
                l_institution,
                l_context.getMarketCode());
            MarketRow l_marketRow = (MarketRow) l_market.getDataSourceObject();
            
            //(*1)������t�J�n�������擾
            String l_strOpenTime = l_marketRow.getOpenTime();
            
            //������t�J�n�����t�H�[�}�b�g
            SimpleDateFormat l_dateParse = GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.TIME_PARSE_HM);
            Date l_datOpenTime = l_dateParse.parse(l_strOpenTime);
            
            SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.TIME_FORMAT_HM0);
            l_strOpenTime = l_dateFormat.format(l_datOpenTime);
            
            // (*2)���ݓ��� 
            Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
            String l_strSystemTime = WEB3DateUtility.formatDate(
                l_tsSystemTimestamp, 
                WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            
            //is�s��J�ǎ��ԑ�()���擾
            boolean l_blnIsTradeOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            if (l_blnIsTradeOpenTimeZone && l_strOpenTime.compareTo(l_strSystemTime) > 0)
            {
                //�Q�j�@@this.is�s��J�ǎ��ԑ�()==true�A���� 
                //�i�s��̒�����t�J�n����(*1) >  ���ݓ���(*2)��HHMMSS�j �̏ꍇ�A
                // true��ԋp����B 
                log.exiting(STR_METHOD_NAME);
            	return true;
            }

            //�P�jthis.is�s��J�ǎ��ԑ�()==false�̏ꍇ�́Afalse��ԋp����B
            //�@@�ȊO�Afalse��ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataNetworkException l_exp)
        {
            log.error(STR_METHOD_NAME, l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        catch (DataQueryException l_exp)
        {
            log.error(STR_METHOD_NAME, l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        catch (Exception l_exp)
        {
            log.error(STR_METHOD_NAME, l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
    }

    /**
     * (is�I�����C���T�[�r�X�J�n��)<BR>
     * <BR>
     * �I�����C���T�[�r�X�J�n�ォ���肷��B<BR>
     * <BR>
     * �P�j�@@�V�X�e���v���t�@@�����X����I�����C���T�[�r�X�J�n���Ԃ��擾����B<BR>
     * <BR>
     * �Q�j�@@�ȉ������ɊY������ꍇ�Atrue�i�I�����C���T�[�r�X�J�n��j��ԋp����B<BR>
     * �@@�@@�@@�ȊO�Afalse�i�I�����C���T�[�r�X�J�n�O�j��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@���ݓ����̎��ԕ����@@���@@�I�����C���T�[�r�X�J�n����<BR>
     * <BR>
     * @@return boolean
     */
    public static boolean isOnlineServiceStartAfter()
    {
        final String STR_METHOD_NAME = "isOnlineServiceStartAfter()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�V�X�e���v���t�@@�����X����I�����C���T�[�r�X�J�n���Ԃ��擾����B
        String l_strValue = GtlUtils.getTradingSystem().getPreference(
                                WEB3SystemPreferencesNameDef.ONLINE_SERVICE_START_TIME);

        //�l���擾����B
        if (l_strValue == null)
        {
        	log.exiting(STR_METHOD_NAME);
        	return false;
        }

        //���ݓ������擾
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        String l_strSystemTime = WEB3DateUtility.formatDate(
            l_tsSystemTimestamp, 
            WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        //�Q�j�@@�ȉ������ɊY������ꍇ�Atrue�i�I�����C���T�[�r�X�J�n��j��ԋp����B 
        // ���ݓ����̎��ԕ����@@���@@�I�����C���T�[�r�X�J�n����
        if (l_strValue != null 
        	&& !"".equals(l_strValue.trim()) 
        	&& l_strSystemTime.compareTo(l_strValue.trim()) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        // �ȊO�Afalse�i�I�����C���T�[�r�X�J�n�O�j��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�[�ꎞ�ԑ�)<BR>
     * <BR>
     * �[�ꎞ�ԑт��ǂ����𔻒肷��B <BR>
     * <BR>
     * �P�j�@@this.get����敪()���R�[������B <BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l���h�[��h�ł����true�A�ȊO��false��ԋp����B <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public static boolean isEveningSessionTimeZone() throws WEB3SystemLayerException
    {
        return WEB3SessionTypeDef.EVENING_SESSION.equals(
            WEB3GentradeTradingTimeManagement.getSessionType());
    }

    /**
     * (get����敪)<BR>
     * <BR>
     * �Y�����Ԃ̗���敪���擾����B <BR>
     * �i�[����{��Ђŗ[�ꎞ�ԑт̏ꍇ�͗[��B�ȊO��NULL�B�j <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * ������ԊǗ�.TIMESTAMP_TAG) <BR>
     * <BR>
     * �Q�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH) <BR>
     * <BR>
     * �R�j�@@��t���̉c�Ɠ��敪����B <BR>
     * �@@�擾������t�����̗j�����擾���A<BR>
     * �y�j���܂��͓��j���̏ꍇ�́h�x���h�Ɣ��肵�A�c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B <BR>
     * �@@�ȊO�̏ꍇ�A��t�����̓��t�����ŃJ�����_�e�[�u�����������A<BR>
     * �s�̉c�Ɠ��敪���擾����B<BR>
     * �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B <BR>
     * <BR>
     * �@@�A���A�O���̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O�������j�A<BR>
     * �O���C�O�s��J�����_�e�[�u�����ȉ��̏����Ō������A<BR>
     * �Y���s�̉c�Ɠ��敪���h��c�Ɠ��h�̏ꍇ�A�c�Ɠ��敪�h��c�Ɠ��h�Ƃ���B <BR>
     * <BR>
     *  �y������ԃR���e�L�X�g.��t���ԋ敪 == �O�������z <BR>
     * �@@[�O���C�O�s��J�����_�e�[�u����������] <BR>
     * �@@�،���ЃR�[�h = ������ԃR���e�L�X�g.�،���ЃR�[�h <BR>
     * �@@�s��R�[�h = ������ԃR���e�L�X�g.�s��R�[�h <BR>
     * �@@���t = �i��t�����̓��t�����j <BR>
     * <BR>
     * �S�j�@@������Ԏ擾 <BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B <BR>
     * <BR>
     * �@@[�����L�[] <BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@���i�R�[�h�F�@@������ԃR���e�L�X�g�̖����R�[�h <BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪 <BR>
     * �@@�J�n���ԁ@@<= �i��t�����̎��ԕ����j <= �I������ <BR>
     * <BR>
     * �@@��L�Ɉ�v����s�́u����敪�v��ԋp����B <BR>
     * �@@��L�Ɉ�v����s�����݂��Ȃ��ꍇ�́A<BR>
     * �f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B <BR>
     * �@@class: WEB3SystemLayerException<BR>
     * �@@tag:   SYSTEM_ERROR_80006<BR>
     * <BR>
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public static String getSessionType() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSessionType()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        //�Q�j�@@ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            TRADING_CAL_CONTEXT_PATH);

        //�R�j�@@��t���̉c�Ɠ��敪����B
        //�c�Ɠ��敪�̎擾
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        //��t���ԋ敪
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�،���ЃR�[�h
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        //�s��R�[�h
        String l_strMarketCode = l_clendarContext.getMarketCode();

        //�O���̏ꍇ�i������ԃR���e�L�X�g.��t���ԋ敪 == �O�������j
        if (WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType = getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsOrderAcceptDate);
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        //�S�j�@@������Ԏ擾
        //���X�R�[�h
        String l_strBranchCode = l_clendarContext.getBranchCode();
        //���i�R�[�h
        String l_strProductCode = l_clendarContext.getProductCode();

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A
        //�f�[�^�s�����Ƃ��ė�O���X���[����B
        //   ������ԃR���e�L�X�g.�،���ЃR�[�h
        // �@@������ԃR���e�L�X�g.���X�R�[�h
        // �@@������ԃR���e�L�X�g.�s��R�[�h
        // �@@������ԃR���e�L�X�g.��t���ԋ敪
        // �@@������ԃR���e�L�X�g.�����R�[�h
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        // �T�j ������ԃe�[�u������������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");

        Object[] l_objWhere = {
            l_strInstitutionCode,  //�،���ЃR�[�h
            l_strBranchCode,       //���X�R�[�h
            l_strMarketCode,       //�s��R�[�h
            l_strTradingTimeType,  //��t���ԋ敪
            l_strProductCode,      //���i�R�[�h
            l_strBizDateType,      //�c�Ɠ��敪
            };

        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�����`�F�b�N
        if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //��t���Ԃ̎擾(�擾���������̎��ԕ���)
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        TradingTimeRow l_tradingTimeRow = null;
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime)
                && Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime))
            {
                l_tradingTimeRow = l_tmpTradingTimeRow;
                break;
            }
        }
        if (l_tradingTimeRow == null)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }
        //get����敪
        String l_strSessionType = l_tradingTimeRow.getSessionType();

        log.exiting(STR_METHOD_NAME);
        return l_strSessionType;
    }

    /**
     * (validate�ǌ���������t�\)<BR>
     * �����̒����^����̎�t���\���ǂ������肷��B  <BR>
     * �i*���ǌ�`���o���I���܂��́A  <BR>
     * �@@�s��ǌ�`�s��ǌ�o���I���̊ԁA  <BR>
     * �@@���������^�����s�Ƃ���B�j  <BR>
     * <BR>
     * ������,����敪�E�������ɂ́A��������������̒l��ݒ肷��B <BR>
     * <BR>
     * �P�j�@@����.���X.is�[����{()���R�[������B  <BR>
     * <BR>
     * �@@�@@�@@[����]  <BR>
     * �@@�@@�@@�����^�C�v�F�@@����.�����^�C�v  <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�[����{��Ёi�u�P�j�v�̖߂�l �� true�j���� <BR>
     * �@@�@@�@@�s��J�ǎ��ԑсithis.is�s��J�ǎ��ԑ�() �� true�j����  <BR>
     * �@@�@@�@@this.get����敪() �� ����.����敪�̏ꍇ <BR>
     * �@@�@@�@@�i�����o�^���������j <BR>
     * <BR>
     * �@@�@@�@@���L�����Ły�o���I���e�[�u���z���������A<BR>
     * �Y���f�[�^�Ȃ��̏ꍇ�͗�O(*1)��throw����B  <BR>
     * �@@�@@�@@�Y���f�[�^����̏ꍇ�A��������return����B <BR>
     * <BR>
     * �@@�@@�@@������������  <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g(*3)�̓����v���p�e�B  <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@����.�����^�C�v  <BR>
     * �@@�@@�@@�@@�敨�^�I�v�V�����敪�F�@@����.�敨�^�I�v�V�����敪  <BR>
     * �@@�@@�@@�@@�o���I���敪�F�@@�h�[��O�o���I���h <BR>
     * <BR>
     * <BR>
     * �R�j�@@this.get������() �� ����.�������̏ꍇ  <BR>
     * <BR>
     * �@@�@@�@@���L�����Ły�o���I���e�[�u���z���������A<BR>
     * �Y���f�[�^�Ȃ��̏ꍇ�͗�O(*2)��throw����B  <BR>
     * �@@�@@�@@�Y���f�[�^����̏ꍇ�A��������return����B <BR>
     * <BR>
     * �@@�@@�@@������������  <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g(*3)�̓����v���p�e�B  <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@����.�����^�C�v  <BR>
     * �@@�@@�@@�@@�敨�^�I�v�V�����敪�F�@@����.�敨�^�I�v�V�����敪  <BR>
     * �@@�@@�@@�@@�o���I���敪�F�@@DEFAULT <BR>
     * <BR>
     * <BR>
     * �S�j�@@��L�����ɊY�����Ȃ��ꍇ�A��������return����B  <BR>
     * <BR>
     * <BR>
     * (*1) throw���郁�b�Z�[�W�F  <BR>
     * �u���ǌ�\�،���Ж��ɒʒm���󂯂čs�����ǌ�̏o���I���ʒm�I���܂ł̊Ԃ́A  <BR>
     * ���������^�����t��s�Ƃ��܂��B�v  <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02824<BR>
     * <BR>
     * (*2) throw���郁�b�Z�[�W�F  <BR>
     * �u�s��ǌ�\�،���Ж��ɒʒm���󂯂čs���o���I���ʒm�I���܂ł̊Ԃ́A  <BR>
     * ���������^�����t��s�Ƃ��܂��B�v  <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00812<BR>
     * <BR>
     * (*3)�@@������ԃR���e�L�X�g�̎擾  <BR>
     * ?ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B  <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TRADING_CAL_CONTEXT_PATH) <BR>
     * <BR>
     * @@param l_productType �����^�C�v<BR>
     * @@param l_strFutureOptionDiv �敨�^�I�v�V�����敪<BR>
     * @@param l_branch ���X<BR>
     * @@param l_strSessionType ����敪<BR>
     * �����P��.����敪��ݒ肷��B<BR>
     * @@param l_datBizDate ������<BR>
     * �����P��.��������ݒ肷��B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public static void validateTradeCloseChangeOrCancel(
        ProductTypeEnum l_productType,
        String l_strFutureOptionDiv,
        WEB3GentradeBranch l_branch,
        String l_strSessionType,
        Date l_datBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradeCloseChangeOrCancel(ProductTypeEnum, String, WEB3GentradeBranch, String, Date)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            TRADING_CAL_CONTEXT_PATH);
        //�،���ЃR�[�h
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();

        //�[����{��Ёi�u����.���X.is�[����{()�̖߂�l �� true�j����
        //�s��J�ǎ��ԑсithis.is�s��J�ǎ��ԑ�() �� true�j����
        //this.get����敪() �� ����.����敪�̏ꍇ
        //�i�����o�^���������j
        if (l_branch.isEveningSessionEnforcemented(l_productType)
            && WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
            && !WEB3Toolkit.isEquals(WEB3GentradeTradingTimeManagement.getSessionType(), l_strSessionType))
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and future_option_div = ? ");
            l_sbWhere.append(" and orderexecution_end_type = ? ");

            Object[] l_objWheres = {
                l_strInstitutionCode,                      //�،���ЃR�[�h
                l_productType,                             //�����^�C�v
                l_strFutureOptionDiv,                      //�敨�^�I�v�V�����敪
                WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END,
                };

            List l_lisRecords;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    OrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWheres);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
            {
                log.debug("��t���ԊO�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02824,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    "���ǌ�\�،���Ж��ɒʒm���󂯂čs�����ǌ�̏o���I���ʒm�I���܂ł̊Ԃ́A"
                        + "���������^�����t��s�Ƃ��܂��B");
            }
            log.exiting(STR_METHOD_NAME);
            return;
        }
        //this.get������() �� ����.�������̏ꍇ
        else if (WEB3DateUtility.compare(WEB3GentradeTradingTimeManagement.getOrderBizDate(), l_datBizDate) > 0)
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and future_option_div = ? ");
            l_sbWhere.append(" and orderexecution_end_type = ? ");

            Object[] l_objWhere = {
                l_strInstitutionCode,                      //�،���ЃR�[�h
                l_productType,                             //�����^�C�v
                l_strFutureOptionDiv,                      //�敨�^�I�v�V�����敪
                WEB3OrderexecutionEndTypeDef.DEFAULT,
                };

            List l_lisRecords;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    OrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
            {
                log.debug("��t���ԊO�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00812,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    "�s��ǌ�\�،���Ж��ɒʒm���󂯂čs���o���I���ʒm�I���܂ł̊Ԃ́A"
                        + "���������^�����t��s�Ƃ��܂��B");
            }
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
    }

    /**
     * (is����ԑ�)<BR>
     * ����ԑт��ǂ����ԋp����B <BR>
     * ����ԑтł����true�A����ԑтłȂ����false��ԋp����B <BR>
     * <BR>
     * �P�j �ȉ��̏���������s���B <BR>
     * <BR>
     * �@@�@@�s��փ��A���^�C���Ƀg���K�𔭍s����ithis.is�g���K���s() == true�j �܂��́A <BR>
     * �@@�@@�s��x�e���ԑтł���ithis.is������x�e���ԑ�() == true�j <BR>
     * <BR>
     * �@@�@@�y�����ɊY������ꍇ�z <BR>
     * <BR>
     * �@@�@@�@@�@@true�i����ԑсj��ԋp����B <BR>
     * <BR>
     * �@@�@@�y��L�ȊO�̏ꍇ�z <BR>
     * <BR>
     * �@@�@@�@@�@@false�i����ԑтł͂Ȃ��j��ԋp����B <BR>
     * <BR>
     * <BR>
     * �@@�@@[is�g���K���s()�Ɏw�肷������n <BR>
     * <BR>
     * �@@�@@�@@�@@�������� �F null <BR>
     * <BR>
     * �@@�@@[is������x�e���ԑ�()�Ɏw�肷������n <BR>
     * <BR>
     * �@@�@@�@@�@@�����Ȃ�<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@return boolean
     */
    public static boolean isSessionTimeZone()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isSessionTimeZone()";
        log.entering(STR_METHOD_NAME);

        if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null)
            || WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isRLS�񓯊��������ԑ�)<BR>
     * ���[���G���W���T�[�o�ւ̒ʒm��񓯊��ŏ������鎞�ԑт𔻒肷��B <BR>
     * �s��ǎ��ԑ� or �[��Ή����i�̌J�z�����͔񓯊��������s���B <BR>
     * �ȊO�́A�������������B <BR>
     * <BR>
     * �P�j ThreadLocalSystemAttributesRegistry���ARLS�ւ̔񓯊��ʒm�t���O���擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * �@@�@@WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY <BR>
     * �@@�@@)  <BR>
     * <BR>
     * �Q�j������s���B <BR>
     * �@@�Q�|�P�j�@@�񓯊��������ԑт̔��� <BR>
     * �@@�@@this.is����ԑ�() == false <BR>
     * �@@�@@�@@�܂��́A�P�j�Ŏ擾�����l��BooleanEnum.TRUE�̏ꍇ�A <BR>
     * �@@�@@true��ԋp����B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����������ԑт̔��� <BR>
     * �@@�@@�ȊO�̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public static boolean isRlsAsyncTreatmentTimeZone() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isRLSAsyncTreatmentTimeZone()";
        log.entering(STR_METHOD_NAME);

        //�P�j ThreadLocalSystemAttributesRegistry���ARLS�ւ̔񓯊��ʒm�t���O���擾����B
        BooleanEnum l_rlsAsyncNotifyFlag =
            (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY);

        //�Q�|�P�j�@@�񓯊��������ԑт̔���
        if ((!WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            || (BooleanEnum.TRUE.equals(l_rlsAsyncNotifyFlag)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�Q�|�Q�j�@@�����������ԑт̔���
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (getPTS������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.<BR>
     * �@@getAttribute("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �@@�������AThreadLocalSystemAttributesRegistry�ɁA<BR>
     * �@@�ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"<BR>
     * �@@�i�������v�Z�p�̊�����j�̒l���Z�b�g����Ă���ꍇ�́A<BR>
     * �@@�ȍ~�̏����Ŏ�t�����Ƃ��āA<BR>
     * �@@�ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"�̒l���g�p����B<BR>
     * <BR>
     * <BR>
     * �Q�j ThreadLocalSystemAttributesRegistry���A<BR>
     * �@@����J�����_�R���e�L�X�g���擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.<BR>
     * �@@getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * �@@�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A<BR>
     * �@@�f�[�^�s�����Ƃ��ė�O���X���[����B<BR>
     * <BR>
     * �@@�@@������ԃR���e�L�X�g.�،���ЃR�[�h<BR>
     * �@@�@@������ԃR���e�L�X�g.���X�R�[�h<BR>
     * �@@�@@������ԃR���e�L�X�g.�s��R�[�h<BR>
     * �@@�@@������ԃR���e�L�X�g.��t���ԋ敪<BR>
     * �@@�@@������ԃR���e�L�X�g.�����R�[�h<BR>
     * <BR>
     * <BR>
     * �R�j ��t���̉c�Ɠ��敪����<BR>
     * �@@this.getPTS�c�Ɠ��敪()���R�[������B<BR>
     * <BR>
     * �@@[getPTS�c�Ɠ��敪()�ɐݒ肷�����]<BR>
     * �@@���t�F �P�j�Ŏ擾������t�����̓��t����<BR>
     * <BR>
     * <BR>
     * �S�j ������Ԏ擾<BR>
     * <BR>
     * �@@�S�|�P�j �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@�@@[�����L�[]<BR>
     * �@@�@@�،���ЃR�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@���X�R�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@�s��R�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@��t���ԋ敪�F ������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@�����R�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@�c�Ɠ��敪�F �R�j�Ŏ擾�����c�Ɠ��敪<BR>
     * <BR>
     * �S�|�Q�j �S�|�P�j�Ŏ擾�����s�̂����A<BR>
     * �@@�@@�J�n���� <= �i��t�����̎��ԕ����j <= �I������<BR>
     *�@@�@@ �ɊY������s�́u�������v�Z�v���ڂɉ����Ĉȉ��̒l��ԋp����B<BR>
     * �@@�@@(*) �Y���s�����݂��Ȃ��ꍇ�́A<BR>
     * �@@�@@�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B<BR>
     * <BR>
     * �S�|�Q�|�P�j �������v�Z = �O�c�Ɠ� �̏ꍇ�A��t�����̑O�c�Ɠ���ԋp����B<BR>
     * �@@�@@�@@�@@�c�Ɠ��v�Z.calcPTS�c�Ɠ�()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[calcPTS�c�Ɠ�()�̈���]<BR>
     * �@@�@@�@@�@@����F ��t����<BR>
     * �@@�@@�@@�@@���Z�^���Z�����F -1 �i�O�c�Ɠ��j<BR>
     * <BR>
     * �S�|�Q�|�Q�j �������v�Z = ���� �̏ꍇ�A��t�����̓��t������ԋp����B<BR>
     * <BR>
     * �S�|�Q�|�R�j �������v�Z = ���c�Ɠ� �̏ꍇ�A��t�����̗��c�Ɠ���ԋp����B<BR>
     * �@@�@@�@@�@@�c�Ɠ��v�Z.calcPTS�c�Ɠ�()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[calcPTS�c�Ɠ�()�̈���]<BR>
     * �@@�@@�@@�@@����F ��t����<BR>
     * �@@�@@�@@�@@���Z�^���Z�����F 1 �i���c�Ɠ��j<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public static Date getPTSOrderBizDate() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getPTSOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datPTSOrderBizDate = null;

        // �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        // ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // �������AThreadLocalSystemAttributesRegistry�ɁA
        // �ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"
        // �i�������v�Z�p�̊�����j�̒l���Z�b�g����Ă���ꍇ�́A
        Object l_objOrderBizDate =
            ThreadLocalSystemAttributesRegistry.getAttribute(BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
        if (l_objOrderBizDate != null)
        {
            // �ȍ~�̏����Ŏ�t�����Ƃ��āA
            // �ݒ�L�[�F"web3.attributes.basetimestampfororderbizdate"�̒l���g�p����B
            l_tsOrderAcceptDate = (Timestamp)l_objOrderBizDate;
        }

        // �Q�j ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        // ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        if (l_clendarContext == null)
        {
            log.info("ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾�Ȃ�");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // �،���ЃR�[�h
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        // �s��R�[�h
        String l_strMarketCode = l_clendarContext.getMarketCode();
        // ���X�R�[�h
        String l_strBranchCode = l_clendarContext.getBranchCode();
        // ��t���ԋ敪
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        // �����R�[�h
        String l_strProductCode = l_clendarContext.getProductCode();

        // �擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A
        // �f�[�^�s�����Ƃ��ė�O���X���[����B
        // ������ԃR���e�L�X�g.�،���ЃR�[�h
        // ������ԃR���e�L�X�g.���X�R�[�h
        // ������ԃR���e�L�X�g.�s��R�[�h
        // ������ԃR���e�L�X�g.��t���ԋ敪
        // ������ԃR���e�L�X�g.�����R�[�h
        if (l_strInstitutionCode == null
                || l_strBranchCode == null
                || l_strMarketCode == null
                || l_strTradingTimeType == null
                || l_strProductCode == null)
        {
            log.error("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        // �R�j ��t���̉c�Ɠ��敪����<BR>
        // this.getPTS�c�Ɠ��敪()���R�[������B<BR>
        // [getPTS�c�Ɠ��敪()�ɐݒ肷�����]<BR>
        // ���t�F �P�j�Ŏ擾������t�����̓��t����
        Timestamp l_tsOrderAcceptDateDay =
            new Timestamp(WEB3DateUtility.toDay(l_tsOrderAcceptDate).getTime());
        String l_strBizDateType = getPTSBizDateType(l_tsOrderAcceptDateDay);

        // �S�j ������Ԏ擾
        // �S�|�P�j �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        // [�����L�[]
        // �،���ЃR�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B
        // ���X�R�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B
        // �s��R�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B
        // ��t���ԋ敪�F ������ԃR���e�L�X�g�̓����v���p�e�B
        // �����R�[�h�F ������ԃR���e�L�X�g�̓����v���p�e�B
        // �c�Ɠ��敪�F �R�j�Ŏ擾�����c�Ɠ��敪
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");

        Object[] l_objTradingTimeWhere =
        {
            l_strInstitutionCode.trim(),
            l_strBranchCode.trim(),
            l_strMarketCode.trim(),
            l_strTradingTimeType.trim(),
            l_strProductCode,
            l_strBizDateType
        };

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_sbWhere.toString(),
                    l_objTradingTimeWhere);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        // �S�|�Q�j �S�|�P�j�Ŏ擾�����s�̂����A
        // �J�n���� <= �i��t�����̎��ԕ����j <= �I������
        // �ɊY������s�́u�������v�Z�v���ڂɉ����Ĉȉ��̒l��ԋp����B
        // (*) �Y���s�����݂��Ȃ��ꍇ�́A
        // �f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        // ��t�����̎��ԕ���
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strOrderAcceptDateTime = l_format.format(l_tsOrderAcceptDate);

        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);

            if (l_tmpTradingTimeRow.getStartTime().compareTo(l_strOrderAcceptDateTime) <= 0
                    && l_strOrderAcceptDateTime.compareTo(l_tmpTradingTimeRow.getEndTime()) <= 0)
            {
                l_tradingTimeRow = l_tmpTradingTimeRow;
                break;
            }
        }

        if (l_tradingTimeRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        String l_strBizdateCalcParameter = l_tradingTimeRow.getBizdateCalcParameter();
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsOrderAcceptDate);

        if (WEB3BizDateCalcParameterDef.BEFORE_BIZ_DATE.equals(l_strBizdateCalcParameter))
        {
            // �S�|�Q�|�P�j �������v�Z = �O�c�Ɠ� �̏ꍇ�A��t�����̑O�c�Ɠ���ԋp����B
            // �c�Ɠ��v�Z.calcPTS�c�Ɠ�()���R�[������B
            // [calcPTS�c�Ɠ�()�̈���]
            // ����F ��t����
            // ���Z�^���Z�����F -1 �i�O�c�Ɠ��j
            Date l_datPTSOrderBizDateBefore =
                l_gentradeBizDate.calcPTSBizDate(l_tsOrderAcceptDate, -1);
            l_datPTSOrderBizDate = WEB3DateUtility.toDay(l_datPTSOrderBizDateBefore);
        }
        else if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizdateCalcParameter))
        {
            // �S�|�Q�|�Q�j �������v�Z = ���� �̏ꍇ�A��t�����̓��t������ԋp����B
            l_datPTSOrderBizDate = WEB3DateUtility.toDay(l_tsOrderAcceptDate);
        }
        else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizdateCalcParameter))
        {
            // �S�|�Q�|�R�j �������v�Z = ���c�Ɠ� �̏ꍇ�A��t�����̗��c�Ɠ���ԋp����B
            // �c�Ɠ��v�Z.calcPTS�c�Ɠ�()���R�[������B
            // [calcPTS�c�Ɠ�()�̈���]
            // ����F ��t����
            // ���Z�^���Z�����F 1 �i���c�Ɠ��j
            Date l_datPTSOrderBizDateAfter =
                l_gentradeBizDate.calcPTSBizDate(l_tsOrderAcceptDate, 1);
            l_datPTSOrderBizDate = WEB3DateUtility.toDay(l_datPTSOrderBizDateAfter);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datPTSOrderBizDate;
    }

    /**
     * (getPTS�c�Ɠ��敪)<BR>
     * ����.���t�̉c�Ɠ��敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@����.���t�̗j�����擾����B<BR>
     * <BR>
     * �@@�@@�@@�擾�����j�������j���̏ꍇ�A�h��c�Ɠ��h��ԋp����B<BR>
     * �@@�@@�@@�ȊO�̏ꍇ�A�ȍ~�̏����𑱂���B<BR>
     * <BR>
     * �Q�j�@@����.���t�̓��t�����ŃJ�����_�[�e�[�u������������B<BR>
     * <BR>
     * �R�j�@@this.get�c�Ɠ��敪()���R�[�����A�O���̉c�Ɠ��敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@[get�c�Ɠ��敪()�ɐݒ肷�����]<BR>
     * �@@�@@�@@���t�F�@@����.���t�̑O��<BR>
     * <BR>
     * �S�j�@@�P�j�Ŏ擾�����j�����y�j���̏ꍇ�A<BR>
     * �@@�@@�@@�������́A�Q�j�ɂĊY���f�[�^���擾�ł����ꍇ<BR>
     * <BR>
     * �@@�S�|�P�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h�I���c�Ɠ��h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h�I���c�Ɠ��i�ߑO�̂݁j�h��ԋp����B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h��c�Ɠ��h�܂��́h�����c�Ɠ��h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h��c�Ɠ��h��ԋp����B<BR>
     * <BR>
     * �T�j�@@�S�j�̏����ɊY�����Ȃ��ꍇ�A<BR>
     * <BR>
     * �@@�T�|�P�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h�I���c�Ɠ��h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h�I���c�Ɠ��h��ԋp����B<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h��c�Ɠ��h�܂��́h�����c�Ɠ��h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h�I���c�Ɠ��i�ߌ�̂݁j�h��ԋp����B<BR>
     * @@param l_tsDate - (���t)<BR>
     * ���t<BR>
     * @@return String
     */
    public static String getPTSBizDateType(Timestamp l_tsDate)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getPTSBizDateType(Timestamp)";
        CalendarRow l_calendarRow = null;

        String l_strPTSBizDateType = null;

        Calendar l_calendarBizDate = Calendar.getInstance();
        l_calendarBizDate.setTime(l_tsDate);

        // DB�����p�Ɏ����b�~���b��������
        l_calendarBizDate.set(Calendar.HOUR_OF_DAY, 0);
        l_calendarBizDate.set(Calendar.MINUTE, 0);
        l_calendarBizDate.set(Calendar.SECOND, 0);
        l_calendarBizDate.set(Calendar.MILLISECOND, 0);

        // �P�j�@@����.���t�̗j�����擾����B
        // �擾�����j�������j���̏ꍇ�A�h��c�Ɠ��h��ԋp����B
        // �ȊO�̏ꍇ�A�ȍ~�̏����𑱂���B
        if (l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            l_strPTSBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
            return l_strPTSBizDateType;
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�Q�j�@@����.���t�̓��t�����ŃJ�����_�[�e�[�u������������B
            List l_lisResults = l_queryProcessor.doFindAllQuery(CalendarRow.TYPE);
            boolean l_blnIsTrue = false;
            for (int i = 0; i < l_lisResults.size(); i++)
            {
                l_calendarRow = (CalendarRow)l_lisResults.get(i);
                if (WEB3DateUtility.compareToDay(l_calendarRow.getHoliday(), l_calendarBizDate.getTime()) == 0)
                {
                    l_blnIsTrue = true;
                    break;
                }
            }

            if (!l_blnIsTrue)
            {
                l_calendarRow = null;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R�j�@@this.get�c�Ɠ��敪()���R�[�����A�O���̉c�Ɠ��敪���擾����B
        Date l_datDateBefore = WEB3DateUtility.addDay(l_tsDate, -1);
        String l_strBizDateTypeBefore = getBizDateType(new Timestamp(l_datDateBefore.getTime()));

        //�S�j�@@�P�j�Ŏ擾�����j�����y�j���̏ꍇ�A�������́A�Q�j�ɂĊY���f�[�^���擾�ł����ꍇ
        if (l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
            || l_calendarRow != null)
        {
            //�S�|�P�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h�I���c�Ɠ��h�̏ꍇ
            //�h�I���c�Ɠ��i�ߑO�̂݁j�h��ԋp����B
            if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateTypeBefore))
            {
                l_strPTSBizDateType = WEB3BizDateTypeDef.ALL_BIZ_DATE_AM;
            }
            else if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_AM.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_PM.equals(l_strBizDateTypeBefore))
            {
                //�S�|�Q�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h��c�Ɠ��h�܂��́h�����c�Ɠ��h�̏ꍇ
                //�h��c�Ɠ��h��ԋp����B
                l_strPTSBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
            }
        }
        else
        {
            if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateTypeBefore))
            {
                //�T�|�P�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h�I���c�Ɠ��h�̏ꍇ
                //�h�I���c�Ɠ��h��ԋp����B
                l_strPTSBizDateType = WEB3BizDateTypeDef.BIZ_DATE;
            }
            else if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_AM.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_PM.equals(l_strBizDateTypeBefore))
            {
                //�T�|�Q�j�@@�R�j�Ŏ擾�����O���̉c�Ɠ��敪���h��c�Ɠ��h�܂��́h�����c�Ɠ��h�̏ꍇ
                //�h�I���c�Ɠ��i�ߌ�̂݁j�h��ԋp����B
                l_strPTSBizDateType = WEB3BizDateTypeDef.ALL_BIZ_DATE_PM;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPTSBizDateType;
    }

    /**
     * validate������t�X�e�C�^�X<BR>
     * ������t�\�����`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�ً}��~�A�o�b�`���`�F�b�N <BR>
     * ������t�X�e�C�^�X�e�[�u��������J�����_�R���e�L�X�g�̓��e�œǂ݁A <BR>
     * �擾�����s�̒�����t�X�e�C�^�X���h�ʏ�h�łȂ��ꍇ�͗�O���X���[����B <BR>
     * <BR>
     * �i������t�s�̃X�e�C�^�X�ɂ́A�h�o�b�`�������h�A�h�ً}��~���h������A��O���b�Z�[�W���킯��j<BR>
     * -�o�b�`������-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00011<BR>
     * -�V�X�e���ً}��~��-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00012<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4014CD6801CF
     */
    public static void validateOrderAcceptStatus() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderAcceptStatus()";
        log.entering(STR_METHOD_NAME);

        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strOrderAccProduct;
        String l_strOrderAccTransaction;
        String l_strOrderAcceptStatus;

        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //���X�R�[�h
        l_strBranchCode = l_clendarContext.getBranchCode();
        //������t���i
        l_strOrderAccProduct = l_clendarContext.getOrderAcceptProduct();
        //������t�g�����U�N�V����
        l_strOrderAccTransaction = l_clendarContext.getOrderAcceptTransaction();

        //�擾�����R���e�L�X�g�̈ȉ��̍��ڂ�null���i�[����Ă����ꍇ�́A
        //��O���X���[����B
        //   ������ԃR���e�L�X�g.�،���ЃR�[�h
        // �@@������ԃR���e�L�X�g.���X�R�[�h
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        // �P�j�@@�ً}��~�A�o�b�`���`�F�b�N
        try
        {
            //������t�X�e�C�^�X�e�[�u��
            OrderAcceptStatusRow l_row =
                OrderAcceptStatusDao.findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderAccProduct,
                    l_strOrderAccTransaction);

            if (l_row != null)
            {
                l_strOrderAcceptStatus = l_row.getOrderAcceptStatus();
            }
            else
            {
                l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
            }
        }
        catch (DataFindException l_dfe)
        {
            l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // �o�b�`������
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // �ً}��~��
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
