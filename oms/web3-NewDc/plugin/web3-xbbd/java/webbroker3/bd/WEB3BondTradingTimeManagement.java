head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ԊǗ�(WEB3BondTradingTimeManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/7 �s�p (���u) �V�K�쐬
                      : 2006/09/29 �����F (���u) ���f�� 094
                      : 2006/10/11 �����F (���u) ���f�� 120
Revesion History : 2007/07/11 �����q (���u) �d�l�ύX�E���f��198
*/

package webbroker3.bd;

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
import webbroker3.common.define.WEB3TradingTimeCheckDivDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (��������ԊǗ�)
 *
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3BondTradingTimeManagement extends WEB3GentradeTradingTimeManagement
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3BondTradingTimeManagement.class);

    /**
     * (validate������t�\)<BR>
     * ������t�\�����`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�ً}��~�A�o�b�`���`�F�b�N <BR>
     * ������t�X�e�C�^�X�e�[�u��������J�����_�R���e�L�X�g�̓��e�œǂ݁A <BR>
     * �擾�����s�̒�����t�X�e�C�^�X���h�ʏ�h�łȂ��ꍇ�͗�O���X���[����B <BR>
     * <BR>
     * �i������t�s�̃X�e�C�^�X�ɂ́A�h�o�b�`�������h�A�h�ً}��~���h������A<BR>
     * �@@�@@��O���b�Z�[�W���킯��j <BR>
     * -�o�b�`������-<BR>
     * �@@�@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00011<BR>
     * -�V�X�e���ً}��~��-<BR>
     * �@@�@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00012<BR>
     * <BR>
     * �Q�j�@@������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�ȍ~�̏����͍s�킸��return����B <BR>
     * �@@�@@�@@������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�ȉ��̏����𑱍s����B <BR>
     *�R�j�@@������.������ԃ`�F�b�N�敪 == �h������Ԃ��`�F�b�N���Ȃ�"�̏ꍇ�́A<BR>
     *�@@�@@�@@�ȍ~�̏����͍s�Ȃ킸��return����B <BR>
     *�@@�@@�@@������.������ԃ`�F�b�N�敪 �� �h������Ԃ��`�F�b�N���Ȃ�"�̏ꍇ�́A<BR>
     *�@@�@@�@@�ȉ��̏����𑱍s����B<BR>
     * <BR>
     * 4�j�@@��t�s���ԑу`�F�b�N <BR>
     * �@@������ԃe�[�u�����ȉ��̏����Ō������A<BR>
     * �@@�@@�Y���s�́u��t�\�v���ڂ�"��t�s��"�ł���΁A������t�s�Ɣ��肷��B <BR>
     * �@@�@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00013<BR>
     * �@@�Y���s�������s����ꍇ�́A�P���ł��u��t�\�v�ł���Β�����t�\�Ƃ���B <BR>
     * <BR>
     * �@@[�����L�[] <BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B <BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B <BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B <BR>
     * �@@�@@���A���A������ԃR���e�L�X�g�̎s��R�[�h�v���p�e�B��null�ł���΁A<BR>
     * �@@�@@�@@�@@�s��R�[�h�͌��������Ɋ܂߂Ȃ��i���ׂĂ̎s���ΏۂƂ���j <BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B <BR>
     * �@@�c�Ɠ��敪�F�@@(*3) <BR>
     * �@@�����R�[�h�F�@@������ԃR���e�L�X�g(*1)�̓����v���p�e�B <BR>
     * �@@�@@���A���A������ԃR���e�L�X�g�̖����R�[�h�v���p�e�B��null�ł���΁A<BR>
     * �@@�@@�@@�@@�����R�[�h�͌��������Ɋ܂߂Ȃ��i���ׂĂ̖�����ΏۂƂ���j <BR>
     * �@@�J�n���� <= ��t����(*2) <=�@@�I�����ԁ@@ <BR>
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�́A�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B <BR>
     * �@@�@@�@@�@@class�@@: WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@: SYSTEM_ERROR_80005<BR>
     * <BR>
     *�T�j�C�O�s��c�Ɠ��`�F�b�N  <BR>
     *�@@�@@this.is�C�O�s��c�Ɠ�()�̖߂�l == false �̏ꍇ�A��O���X���[����B  <BR>
     * �@@�@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@: BUSINESS_ERROR_02667<BR>
     * <BR>
     *�@@�@@[����]  <BR>
     *�@@�@@�@@�������F�@@����.������  <BR>
     *�@@�@@�@@�����������F�@@get�������i�j�̖߂�l <BR>
     * <BR>
     * ================================================================================<BR>
     * <BR>
     * (*1)�@@������ԃR���e�L�X�g�̎擾 <BR>
     * �|ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext") <BR>
     * <BR>
     * (*2) ��t���Ԃ̎擾 <BR>
     * �|ThreadLocalSystemAttributesRegistry����t�������擾���A�擾���������̎��ԕ����B <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     * (*3) �c�Ɠ��敪�̎擾 <BR>
     * �|�擾������t�����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�́h��c�Ɠ��h�B <BR>
     * �@@�ȊO�̏ꍇ�A�J�����_�e�[�u������t�����̓��t�����Ō������A�s�̉c�Ɠ��敪���擾����B<BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B <BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CEE39F0103
     */
    public static void validateOrderAccept(WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderAccept(String)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3BondTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //�،���ЃR�[�h
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //���X�R�[�h
        String l_strBranchCode = l_clendarContext.getBranchCode();
        //�s��R�[�h
        String l_strMarketCode = l_clendarContext.getMarketCode();
        //������t���i
        String l_strOrderAccProduct = l_clendarContext.getOrderAcceptProduct();
        //������t�g�����U�N�V����
        String l_strOrderAccTransaction = l_clendarContext.getOrderAcceptTransaction();
        //��t���ԋ敪
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�����R�[�h
        String l_strProductCode = l_clendarContext.getProductCode();

        String l_strOrderAcceptStatus = null;

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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // �ً}��~��
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3BondTradingTimeManagement.class.getName()
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

        //�R�j�@@������.������ԃ`�F�b�N�敪 == �h������Ԃ��`�F�b�N���Ȃ�"�̏ꍇ�́A
        //    �ȍ~�̏����͍s�Ȃ킸��return����B
        //    ������.������ԃ`�F�b�N�敪 �� �h������Ԃ��`�F�b�N���Ȃ�"�̏ꍇ�́A
        //�@@�@@�ȉ��̏����𑱍s����
        if (WEB3TradingTimeCheckDivDef.TRADING_TIME_NOT_CHECK.equals(l_bondProduct.getTradingTimeCheckDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //4�j��t�s���ԑу`�F�b�N

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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                break;
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug(STR_METHOD_NAME + "�F��t�s���ԑ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3BondTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        //�T�j�C�O�s��c�Ɠ��`�F�b�N
        //    this.is�C�O�s��c�Ɠ�()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        //    [����]
        //       �������F�@@����.������
        //       �����������F�@@get�������i�j�̖߂�l
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement = new WEB3BondTradingTimeManagement();
        boolean l_blnIsForeignMarketBizDate = l_bondTradingTimeManagement.isForeignMarketBizDate(l_bondProduct, getOrderBizDate());
        if ( !l_blnIsForeignMarketBizDate)
        {
            log.debug("���������C�O�s��c�Ɠ��ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02667,
                WEB3BondTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "���������C�O�s��c�Ɠ��ł͂���܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�C�O�s��c�Ɠ�)<BR>
     *�C�O�s��c�Ɠ��`�F�b�N���s�Ȃ��B <BR>
     *<BR>
     *�P�j������.�J�����_�[�A�g�s��R�[�h == null�̏ꍇ�Atrue��ԋp����B <BR>
     *<BR>
     *�Q�j������.�J�����_�[�A�g�s��R�[�h != null �̏ꍇ�A�ȉ��̏������s�Ȃ��B <BR>
     *<BR>
     *�Q�|�P�j�ȉ��̏����ŊO���C�O�s��J�����_�[�e�[�u�����������A<BR>
     *�Y�����R�[�h���擾�ł����ꍇ�Afalse��ԋp����B <BR>
     *�@@�@@�@@�@@����ȊO�̏ꍇ�Atrue��ԋp����B <BR>
     *<BR>
     *�@@�@@�@@�@@[��������]  <BR>
     *�@@�@@�@@�@@�،���ЃR�[�h = ������.�،���ЃR�[�h  <BR>
     *�@@�@@�@@�@@�s��R�[�h = ������.�J�����_�[�A�g�s��R�[�h  <BR>
     *�@@�@@�@@�@@���t = ����������  <BR>
     *�@@�@@�@@�@@�c�Ɠ��敪 = �h��c�Ɠ��h<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������
     * @@param l_datBizDate - (����������)<BR>
     * ����������
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isForeignMarketBizDate(
        WEB3BondProduct l_bondProduct,
        Date l_datBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isForeignMarketBizDate(WEB3BondProduct,Date) ";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3BondTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j������.�J�����_�[�A�g�s��R�[�h == null�̏ꍇ�Atrue��ԋp����B
        if (l_bondProduct.getCalLinkedMarketCode() == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�Q�j������.�J�����_�[�A�g�s��R�[�h != null �̏ꍇ�A�ȉ��̏������s�Ȃ��B
        //�Q�|�P�j�ȉ��̏����ŊO���C�O�s��J�����_�[�e�[�u�����������A�Y�����R�[�h���擾�ł����ꍇ�Afalse��ԋp����B
        //�@@�@@����ȊO�̏ꍇ�Atrue��ԋp����

        //�O���C�O�s��J�����_�[�e�[�u��������
        String l_sbWhere =
            " institution_code = ? " +
            " and market_code = ? " +
            " and biz_date = ? " +
            " and biz_date_type = ? ";

        Object[] l_objForeignMarketBizDaterWhere =
            {l_bondProduct.getInstitution().getInstitutionCode(),
             l_bondProduct.getCalLinkedMarketCode(),
             l_datBizDate,
             WEB3BizDateTypeDef.NO_BIZ_DATE};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                FeqCalendarRow.TYPE,
                l_sbWhere,
                l_objForeignMarketBizDaterWhere);
        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        if (l_lisRecords != null && !l_lisRecords.isEmpty())
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
     * (get���������؎���)<BR>
     * �������̒��؎������擾����B<BR>
     * <BR>
     * 1�jThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B<BR>
     * �@@�@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * �@@�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * 2) �c�Ɠ��敪�̎擾<BR>
     * �@@�@@get�c�Ɠ��敪���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@���t�F����.���t<BR>
     * 3) ������Ԃ��擾����B<BR>
     * �@@�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * �@@�@@[�����L�[]<BR>
     * �@@�@@�،���ЃR�[�h�F������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@���X�R�[�h�F������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@�s��R�[�h�F������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@��t���ԋ敪�F������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@���i�R�[�h�F������ԃR���e�L�X�g�̖����R�[�h<BR>
     * �@@�@@�c�Ɠ��敪�F2)�Ŏ擾�����c�Ɠ��敪<BR>
     * �@@�@@�������v�Z�F�u1�F���c�Ɠ��v<BR>
     * �@@�@@�� �擾�������R�[�h��1���ȊO�Ȃ�u�f�[�^�s�����G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@tag  :�@@SYSTEM_ERROR_80006<BR>
     * <BR>
     * 4) �擾�����������Row.�J�n���Ԃ�ԋp����B<BR>
     * <BR>
     * @@param l_datDate - (���t)<BR>
     * ���t<BR>
     * @@return String
     * @@throws WEB3BaseException
     * 
     */
    public String getBondDomesticLimitTime(Date l_datDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondDomesticLimitTime(Date)";
        log.entering(STR_METHOD_NAME);

        // 1�jThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        // ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TRADING_CAL_CONTEXT_PATH)
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // �c�Ɠ��敪�̎擾
        // get�c�Ɠ��敪���R�[������B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datDate.getTime()));

        // 3) ������Ԃ��擾����B
        // �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        // �،���ЃR�[�h�F������ԃR���e�L�X�g�̓����v���p�e�B
        // ���X�R�[�h�F������ԃR���e�L�X�g�̓����v���p�e�B
        // �s��R�[�h�F������ԃR���e�L�X�g�̓����v���p�e�B
        // ��t���ԋ敪�F������ԃR���e�L�X�g�̓����v���p�e�B
        // ���i�R�[�h�F������ԃR���e�L�X�g�̖����R�[�h
        // �c�Ɠ��敪�F2)�Ŏ擾�����c�Ɠ��敪
        // �������v�Z�F�u1�F���c�Ɠ��v
        List l_lisStartRecords = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");

        List l_lisTradingTimeWheres = new ArrayList();
        l_lisTradingTimeWheres.add(l_clendarContext.getInstitutionCode());
        l_lisTradingTimeWheres.add(l_clendarContext.getBranchCode());
        l_lisTradingTimeWheres.add(l_clendarContext.getMarketCode());
        l_lisTradingTimeWheres.add(l_clendarContext.getTradingTimeType());
        l_lisTradingTimeWheres.add(l_clendarContext.getProductCode());
        l_lisTradingTimeWheres.add(l_strBizDateType);
        l_lisTradingTimeWheres.add(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);

        Object[] l_tradingTimeWheres =
            new Object[l_lisTradingTimeWheres.size()];
        l_lisTradingTimeWheres.toArray(l_tradingTimeWheres);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_tradingTimeWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �� �擾�������R�[�h��1���ȊO�Ȃ�u�f�[�^�s�����G���[�v�̗�O���X���[����B
        int l_intSize = l_lisStartRecords.size();
        if (l_intSize != 1)
        {
            log.debug("�f�[�^�s�����G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[");
        }

        // 4) �擾�����������Row.�J�n���Ԃ�ԋp����B 
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisStartRecords.get(0);
        String l_strStartTime = l_tradingTimeRow.getStartTime();

        log.exiting(STR_METHOD_NAME);
        return l_strStartTime;
    }
}
@
