head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradingTimeManagement.java;


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
Revesion History : 2005/11/16 �ˑq (SRA) �V�K�쐬

*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAcceptStatusDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

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
public class WEB3FeqTradingTimeManagement
         extends WEB3GentradeTradingTimeManagement
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
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTradingTimeManagement.class);    

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
     *  �|�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�́h��c�Ɠ��h�B<BR>
     * �@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A<BR>
     * �s�̉c�Ɠ��敪���擾����B�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ�<BR>
     * �敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
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
	   	l_sbWhere.append(" and start_time <= ? ");
	    l_sbWhere.append(" and end_time >= ? ");

        ArrayList l_lstObjTradingTimeWhere = new ArrayList();
        //�،���ЃR�[�h
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //���X�R�[�h
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //��t���ԋ敪
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //�c�Ɠ��敪
        l_lstObjTradingTimeWhere.add(l_bizDateType);
        //��t����
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);
        //��t����
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);

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

        
        //�C�O�c�Ɠ��`�F�b�N
		//�R�j�Ŏ擾�������R�[�h�����Ɉȉ��̏����ŊO���C�O�s��J�����_�[�e�[�u�����������A
		//�Y�����R�[�h���擾�ł����ꍇ�͒�����t�s�Ƃ��A��O���X���[����B
		//[��������]
		//�،���ЃR�[�h = ������ԃe�[�u��.�،���ЃR�[�h
		//�s��R�[�h = ������ԃe�[�u��.�s��R�[�h
		//���t = (�ȉ��̂Ƃ���)
		//	������ԃe�[�u��.�������v�Z == "����"�̏ꍇ�A
		//		ThreadLocalSystemAttributesRegistry���擾������t�����̓��t����
		//	������ԃe�[�u��.�������v�Z == "���c�Ɠ�"�̏ꍇ�A
		//		ThreadLocalSystemAttributesRegistry���擾������t�����̍������c�Ɠ�
		//�c�Ɠ��敪 = "��c�Ɠ�"
		//
		//���R�j�Ŏ擾�������R�[�h����������ꍇ�́A���ꂼ��̃��R�[�h�ɂ��ď������s���A
		//�P���ł��Y�����R�[�h�����݂��Ȃ��ꍇ�͒�����t�Ƃ���B
		
		String l_strBizDate = "";
		String l_strBizDateCalc;
		Date l_bizDate;
		WEB3GentradeBizDate l_dateCalc =
					new WEB3GentradeBizDate(l_tsOrderAcceptDate);
		for (int i = 0; i < l_intLength; i++)
		{
			l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
			//�،���ЃR�[�h
			l_strInstitutionCode = l_tradingTimeRow.getInstitutionCode();
			//�s��R�[�h
			l_strMarketCode = l_tradingTimeRow.getMarketCode();
			//���t
			l_strBizDateCalc = l_tradingTimeRow.getBizdateCalcParameter();
			if(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizDateCalc)){
				//������ԃe�[�u��.�������v�Z == "����"�̏ꍇ�A
				//ThreadLocalSystemAttributesRegistry���擾������t�����̓��t����
				SimpleDateFormat l_formatDate = GtlUtils.getThreadSafeSimpleDateFormat("yyyy/MM/dd");
				l_strBizDate = l_formatDate.format(l_tsOrderAcceptDate);
				
			}else if(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizDateCalc)){
				//������ԃe�[�u��.�������v�Z == "���c�Ɠ�"�̏ꍇ�A
				//ThreadLocalSystemAttributesRegistry���擾������t�����̍������c�Ɠ�
				l_bizDate = l_dateCalc.roll(1);
				l_strBizDate = WEB3DateUtility.formatDate(l_bizDate,"yyyy/MM/dd");
			}
			//�c�Ɠ��敪
			l_bizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
			
			//�O���C�O�s��J�����_�[�e�[�u������������
			l_sbWhere = new StringBuffer();
			l_sbWhere.append(" institution_code = ? ");
			l_sbWhere.append(" and market_code = ? ");
			l_sbWhere.append(" and biz_date = ? ");
			l_sbWhere.append(" and biz_date_type = ? ");

			ArrayList l_lstObjFeqCalendarWhere = new ArrayList();
			//�،���ЃR�[�h
			l_lstObjFeqCalendarWhere.add(l_strInstitutionCode.trim());
			//�s��R�[�h
			l_lstObjFeqCalendarWhere.add(l_strMarketCode.trim());
			//���t
			l_lstObjFeqCalendarWhere.add(l_strBizDate.trim());
			//�c�Ɠ��敪
			l_lstObjFeqCalendarWhere.add(l_bizDateType);
			
			l_intSize = l_lstObjFeqCalendarWhere.size();
			Object[] l_objFeqCalendarWhere = new Object[l_intSize];
			for (int j = 0; j < l_intSize; j++)
			{
				l_objFeqCalendarWhere[j] = l_lstObjFeqCalendarWhere.get(j);
			}
			
			List l_lisRecordsTemp = null;
			try
			{
				QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
				l_lisRecordsTemp = l_QueryProcessor.doFindAllQuery(
					FeqCalendarRow.TYPE,
					l_sbWhere.toString(),
					l_objFeqCalendarWhere);
			}
			catch (DataFindException dfe)
			{
				log.error(STR_METHOD_NAME, dfe);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					WEB3FeqTradingTimeManagement.class.getName()
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
					WEB3FeqTradingTimeManagement.class.getName()
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
					WEB3FeqTradingTimeManagement.class.getName()
						+ "."
						+ STR_METHOD_NAME,
					dne.getMessage(),
					dne);
			}
			
			//�P���ł��Y�����R�[�h�����݂��Ȃ��ꍇ�͒�����t�Ƃ���B
			int l_intLengthTemp = l_lisRecordsTemp.size();
			if (l_intLengthTemp == 0)
			{
				log.debug(STR_METHOD_NAME + "�F��t���ԑ�");
				log.exiting(STR_METHOD_NAME);
				return;

			}
		}
		
		throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02301,
				WEB3FeqTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				"�Y���s�ꂪ�x�Ɠ��ׁ̈A�����ł��܂���B");
				

    }
}@
