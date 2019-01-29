head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS������ԊǗ��iWEB3EquityPTSTradingTimeManagement.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 �g�E�N�| (���u) �V�K�쐬
Revision History : 2007/12/20 �g�E�N�| (���u) �d�l�ύX�@@���f��1207�A1248�A1258�A1259
Revision History : 2008/2/18 ��іQ(���u) ���f��No.1303
*/
package webbroker3.equity;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAcceptStatusDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS������ԊǗ�)<BR>
 * PTS������ԊǗ��N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3EquityPTSTradingTimeManagement extends WEB3GentradeTradingTimeManagement
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSTradingTimeManagement.class);

    /**
     * @@roseuid 47661650009C
     */
    public WEB3EquityPTSTradingTimeManagement()
    {

    }

    /**
     * (get������)<BR>
     * ���������擾����B <BR>
     * �i* ������ԊǗ�.getPTS������( )�ɈϏ�����B�j <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 4734311300DB
     */
    public static Date getOrderBizDate() throws WEB3SystemLayerException
    {
        //������ԊǗ�.getPTS������( )�ɈϏ�����B
        return WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
    }

    /**
     * (get�s��ǌx���s��)<BR>
     * ����I���x������\�����鎞�ԑтɂ���s��̎s��R�[�h��z��ŕԋp����B<BR>
     * <BR>
     * �P�j�@@��t�����̎擾<BR>
     * �@@ThreadLocalSystemAttributesRegistry���A��t�������擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * �@@"xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     * �Q�j�@@��t���̉c�Ɠ��敪�̔���<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.get�c�Ɠ��敪()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get�c�Ɠ��敪()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@���t�F�@@�P�j�Ŏ擾������t�����̓��t����<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@get�c�Ɠ��敪()�̖߂�l���h��c�Ɠ��h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@null��ԋp���������I������B<BR>
     * <BR>
     * �R�j�@@�s��x�����\���̎擾<BR>
     * <BR>
     * �@@�R�|�P�j�@@���X.get�s��x�����\��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get�s��x�����\��()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�����^�C�v�F�@@����.�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@�M�p����敪�F�@@����.�M�p����敪<BR>
     * �@@�@@�@@�@@�@@�@@�敨�^�I�v�V�����敪�F�@@�敨�^�I�v�V�����敪.DEFAULT<BR>
     * <BR>
     * �@@�R�|�Q�j�@@get�s��x�����\��()�̖߂�l�� 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@����I���x��������\�����Ȃ��Ɣ��f���A<BR>
     * �@@�@@�@@�@@�@@�@@null��ԋp���������I������B<BR>
     * <BR>
     * �S�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�i���X�s��ʁEPTS�j�戵����.get�i���X�s��ʁEPTS�j�戵����()���R�[������B<BR>
     * <BR>
     * �@@�@@[get�i���X�s��ʁEPTS�j�戵����()�ɐݒ肷�����]<BR>
     * �@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �T�j�@@��t�����̎擾<BR>
     * �@@�@@����J�����_�R���e�L�X�g.getAttribute()���R�[������B<BR>
     * <BR>
     * �@@�@@[getAttribute()�ɐݒ肷�����]<BR>
     * �@@�@@arg0�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �U�j�@@�s��R�[�h�̃��X�g�̎擾<BR>
     * �@@�@@�S�j�Ŏ擾�����i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���Ɉȉ��������s���B<BR>
     * <BR>
     * �@@�@@�U�|�P�j�@@�I�[�o�[�i�C�g���{���ǂ������`�F�b�N����B<BR>
     * �@@�@@�@@�@@�@@�Q�j�̖߂�l�� "�I���c�Ɠ�(�ߑO�̂�)" �̏ꍇ�A����<BR>
     * �@@�@@�@@�@@�@@this.is�I�[�o�[�i�C�g���{() == false �̏ꍇ�A�ȍ~�̏������X�L�b�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[is�I�[�o�[�i�C�g���{()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�s��R�[�h�F�@@�S�j�̖߂�l.get�s��R�[�h()<BR>
     * <BR>
     * �@@�@@�U�|�Q�j�@@�戵�\���ǂ������`�F�b�N����B<BR>
     * �@@�@@�@@�@@�@@�i���X�s��ʁEPTS�j�戵����.is�戵�\()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�戵�s�iis�戵�\() == false�j�̏ꍇ�A�ȍ~�̏������X�L�b�v����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[is�戵�\()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�����^�C�v�F�@@����.�����^�C�v<BR>
     * <BR>
     * �@@�@@�U�|�R�j�@@�s��ǎ��Ԃ��擾����B<BR>
     * �@@�@@�@@�@@�@@this.get�s��ǎ���()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[get�s��ǎ���()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�s��R�[�h�F�@@�S�j�̖߂�l.get�s��R�[�h()<BR>
     * �@@�@@�@@�@@�@@���i�R�[�h�F�@@����J�����_�R���e�L�X�g�̓�����<BR>
     * <BR>
     * �@@�@@�U�|�S�j�@@�ȉ������ɊY������ꍇ�A�s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@��[%d]�̕����́A�R�|�P�j�Ŏ擾�������l<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�i�s��ǎ��Ԃ�[%d]���O�j�@@���@@�i��t�����̎��ԕ����j�@@���@@�i�s��ǎ��ԁj<BR>
     * <BR>
     * �V�j�@@�������ꂽArrayList��ԋp����B<BR>
     * @@param l_genBranch - (���X)<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * @@param l_strMargineTradeDiv - (�M�p����敪)<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 473431B800C3
     */
    public static String[] getTradeCloseMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productType,
        String l_strMargineTradeDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseMarket(WEB3GentradeBranch, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);

        if (l_genBranch == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@��t�����̎擾
        //ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        //�Q�j�@@��t���̉c�Ɠ��敪�̔���
        //�@@�Q�|�P�j�@@this.get�c�Ɠ��敪()���R�[������B
        String l_strBizDateType = WEB3EquityPTSTradingTimeManagement.getBizDateType(l_tsOrderAcceptDate);

        //�@@�Q�|�Q�j�@@get�c�Ɠ��敪()�̖߂�l���h��c�Ɠ��h�̏ꍇ�Anull��ԋp���������I������B
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�R�j�@@�s��x�����\���̎擾
        //�@@�R�|�P�j�@@���X.get�s��x�����\��()���R�[������B
        long l_lngMarketMessageSuspension = l_genBranch.getMarketMessageSuspension(
            l_productType, l_strMargineTradeDiv, WEB3FuturesOptionDivDef.DEFAULT);

        //�@@�R�|�Q�j�@@get�s��x�����\��()�̖߂�l�� 0 �̏ꍇ�A����I���x��������\�����Ȃ��Ɣ��f���A
        //�@@�@@�@@�@@�@@�@@null��ԋp���������I������B
        if (l_lngMarketMessageSuspension == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�S�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g�̎擾
        //�@@�@@�i���X�s��ʁEPTS�j�戵����.get�i���X�s��ʁEPTS�j�戵����()���R�[������B
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_genBranch);

        //�T�j�@@��t�����̎擾
        //�@@�@@����J�����_�R���e�L�X�g.getAttribute()���R�[������B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�U�j�@@�s��R�[�h�̃��X�g�̎擾
        List l_lisMarketCodes = new ArrayList();
        int l_intLength = l_branchMarketPTSDealtConds.length;
        WEB3GentradeBranchMarketPTSDealtCond l_branchMarketPTSDealtCond = null;
        String l_strMarketCode = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_branchMarketPTSDealtCond = l_branchMarketPTSDealtConds[i];

            l_strMarketCode = l_branchMarketPTSDealtCond.getMarketCode();

            //�U�|�P�j�@@�I�[�o�[�i�C�g���{���ǂ������`�F�b�N����B
            if (WEB3BizDateTypeDef.ALL_BIZ_DATE_AM.equals(l_strBizDateType)
                && !isOverNightExecute(l_strMarketCode))
            {
                continue;
            }

            //�U�|�Q�j�@@�戵�\���ǂ������`�F�b�N����B
            if (!l_branchMarketPTSDealtCond.isHandlingPossible(l_productType))
            {
                continue;
            }

            //�U�|�R�j�@@�s��ǎ��Ԃ��擾����B
            String l_strTradeCloseTime = getTradeCloseTime(l_strMarketCode, l_clendarContext.getProductCode());

            String l_strOrderAcceptDate =
                WEB3DateUtility.formatDate(l_tsOrderAcceptDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //�s��ǎ���
            Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                l_strOrderAcceptDate + l_strTradeCloseTime,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

            //�s��ǎ��Ԃ�[%d]���O
            Date l_datTradeCloseTimeBefore =
                WEB3DateUtility.addMinute(l_datTradeCloseTime, -l_lngMarketMessageSuspension);

            //�U�|�S�j�@@�ȉ������ɊY������ꍇ�A�s��R�[�h��ArrayList�ɒǉ�����B
            //��[%d]�̕����́A�R�|�P�j�Ŏ擾�������l
            //�i�s��ǎ��Ԃ�[%d]���O�j�@@���@@�i��t�����̎��ԕ����j�@@��  �i�s��ǎ��ԁj
            if (WEB3DateUtility.compareToSecond(l_datTradeCloseTimeBefore, l_tsOrderAcceptDate) <= 0
                && WEB3DateUtility.compareToSecond(l_tsOrderAcceptDate, l_datTradeCloseTime) <= 0)
            {
                l_lisMarketCodes.add(l_strMarketCode);
            }
        }

        //�V�j�@@�������ꂽArrayList��ԋp����B
        String[] l_strTradeCloseMarkets = new String[l_lisMarketCodes.size()];
        l_lisMarketCodes.toArray(l_strTradeCloseMarkets);

        log.exiting(STR_METHOD_NAME);
        return l_strTradeCloseMarkets;
    }

    /**
     * (get�s��ǎ���)<BR>
     * �s��ǎ��Ԃ��擾����B <BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g���擾����B <BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * �@@"web3.tradingcalendarcontext") <BR>
     * <BR>
     * �Q�j�@@������Ԏ擾 <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B <BR>
     * <BR>
     * �@@�@@�@@�@@[�����L�[] <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�@@�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h <BR>
     * �@@�@@�@@�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�@@�@@���i�R�[�h�F�@@�����̏��i�R�[�h <BR>
     * �@@�@@�@@�@@�c�Ɠ��敪�F�@@�h�I���c�Ɠ��h <BR>
     * �@@�@@�@@�@@�������v�Z�F�@@�h�O�c�Ɠ��h <BR>
     * �@@�@@�@@�@@�s��g���K���s�F �@@�hSONAR��MQ�g���K�����{����h<BR>
     * <BR>
     * �@@�@@�@@�@@��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�ŊY���s�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@�@@[�����L�[] <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�@@�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h <BR>
     * �@@�@@�@@�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�@@�@@���i�R�[�h�F�@@�����̏��i�R�[�h <BR>
     * �@@�@@�@@�@@�c�Ɠ��敪�F�@@�h�I���c�Ɠ��h<BR>
     * �@@�@@�@@�@@�������v�Z�F�@@�h�����h <BR>
     * �@@�@@�@@�@@�s��g���K���s�F �@@�hSONAR��MQ�g���K�����{����h<BR>
     * <BR>
     * �@@�@@�@@�@@��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����B <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�Q�|�Q�j�ŊY���s�����݂��Ȃ��ꍇ�A23��59��59�b��ԋp����B <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_strProductCode - (���i�R�[�h)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 47343577014C
     */
    public static String getTradeCloseTime(String l_strMarketCode, String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseTime(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�jThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�Q�j������Ԏ擾
        //�@@�Q�|�P�j�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        //�@@[�����L�[]
        //�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@�s��R�[�h�F�@@�����̎s��R�[�h
        //�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���i�R�[�h�F�@@�����̏��i�R�[�h
        //�@@�c�Ɠ��敪�F�@@�h�I���c�Ɠ��h
        //�@@�������v�Z�F�@@�h�O�c�Ɠ��h
        //�@@�s��g���K���s�F �@@�hSONAR��MQ�g���K�����{����h
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        l_sbWhere.append(" and submit_market_trigger = ? ");

        List l_lisWheres = new ArrayList();
        l_lisWheres.add(l_clendarContext.getInstitutionCode());
        l_lisWheres.add(l_clendarContext.getBranchCode());
        l_lisWheres.add(l_strMarketCode);
        l_lisWheres.add(l_clendarContext.getTradingTimeType());
        l_lisWheres.add(l_strProductCode);
        l_lisWheres.add(WEB3BizDateTypeDef.BIZ_DATE);
        l_lisWheres.add(WEB3BizDateCalcParameterDef.BEFORE_BIZ_DATE);
        l_lisWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

        Object[] l_objWheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWheres);

        List l_lisStartRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
                l_objWheres);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisStartRecords.size();

        //�Q�|�Q�j�@@�Q�|�P�j�ŊY���s�����݂��Ȃ��ꍇ�A�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        //�@@[�����L�[]
        //�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@�s��R�[�h�F�@@�����̎s��R�[�h
        //�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���i�R�[�h�F�@@�����̏��i�R�[�h
        //�@@�c�Ɠ��敪�F�@@�h�I���c�Ɠ��h
        //�@@�������v�Z�F�@@�h�����h
        //�@@�s��g���K���s�F �@@�hSONAR��MQ�g���K�����{����h
        if (l_intSize == 0)
        {
            l_lisWheres = new ArrayList();
            l_lisWheres.add(l_clendarContext.getInstitutionCode());
            l_lisWheres.add(l_clendarContext.getBranchCode());
            l_lisWheres.add(l_strMarketCode);
            l_lisWheres.add(l_clendarContext.getTradingTimeType());
            l_lisWheres.add(l_strProductCode);
            l_lisWheres.add(WEB3BizDateTypeDef.BIZ_DATE);
            l_lisWheres.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
            l_lisWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

            l_objWheres = new Object[l_lisWheres.size()];
            l_lisWheres.toArray(l_objWheres);

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_sbWhere.toString(),
                    "start_time desc",
                    null,
                    l_objWheres);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            l_intSize = l_lisStartRecords.size();
            //�Q�|�R�j�@@�Q�|�Q�j�ŊY���s�����݂��Ȃ��ꍇ�A23��59��59�b��ԋp����B
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3GentradeTimeDef.MAX_RETURN;
            }
        }

        //�@@��L�Ɉ�v����s�̂����A�J�n���Ԃ���Ԓx���s�̏I�����Ԃ�ԋp����B
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisStartRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();

        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
    }

    /**
     * (get�c�Ɠ��敪)<BR>
     * ����.���t�̉c�Ɠ��敪��ԋp����B <BR>
     * �i* ������ԊǗ�.getPTS�c�Ɠ��敪( )�ɈϏ�����B�j <BR>
     * @@param l_tsDate - (���t)<BR>
     * @@return String
     * @@throws WEB3SystemLayerException
     * @@roseuid 473954DA00BA
     */
    public static String getBizDateType(Timestamp l_tsDate) throws WEB3SystemLayerException
    {
        //������ԊǗ�.getPTS�c�Ɠ��敪( )�ɈϏ�����B
        return WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);
    }

    /**
     * xTrade�����p���錻�ݓ������Z�b�g����B <BR>
     * �i�T�[�r�X�C���^�Z�v�^���g�p����j <BR>
     * <BR>
     * �|�v���Z�X�J�n���̎��Ԃ�LocalThread�ɃZ�b�g����B <BR>
     * �|������ԃe�[�u���̓��e��LocalThread�ɃZ�b�g����B <BR>
     * �|���t���[����LocalThread�ɃZ�b�g����B �i���f�t�H���g�l�F�h0�h���Z�b�g����B�j<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 47415B55013F
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
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_processTime);

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
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //������ԃe�[�u������������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");

        List l_lisObjTradingTimeWheres = new ArrayList();
        //�،���ЃR�[�h
        l_lisObjTradingTimeWheres.add(l_strInstitutionCode.trim());
        //���X�R�[�h
        l_lisObjTradingTimeWheres.add(l_strBranchCode.trim());
        //��t���ԋ敪
        l_lisObjTradingTimeWheres.add(l_strTradingTimeType.trim());
        //�c�Ɠ��敪
        l_lisObjTradingTimeWheres.add(l_strBizDateType);

        //��t����
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strTime = l_format.format(l_processTime);

        //�s��R�[�h
        if (l_strMarketCode != null)
        {
            l_sbWhere.append(" and market_code = ? ");
            l_lisObjTradingTimeWheres.add(l_strMarketCode.trim());
        }
        //�����R�[�h
        if (l_strProductCode != null)
        {
            l_sbWhere.append(" and product_code  = ? ");
            l_lisObjTradingTimeWheres.add(l_strProductCode.trim());
        }

        int l_intSize = l_lisObjTradingTimeWheres.size();
        Object[] l_objTradingTimeWheres = new Object[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_objTradingTimeWheres[i] = l_lisObjTradingTimeWheres.get(i);
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����`�F�b�N
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intLength; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow =
                (TradingTimeRow)l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strTime)
                && Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strTime))
            {
                l_tradingTimeRow = l_tmpTradingTimeRow;
                break;
            }
        }

        if (l_tradingTimeRow == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
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
            l_tempRow = (TradingTimeRow)l_lisRecords.get(i);
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
        ThreadLocalSystemAttributesRegistry.setAttribute(TRADING_CAL_CONTEXT_PATH, l_clendarContext);

        //���t���[����LocalThread�ɃZ�b�g����B
        Integer l_intOffset = new Integer(0);
        ThreadLocalSystemAttributesRegistry.setAttribute(OFFSET_TAG, l_intOffset);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�I�[�o�[�i�C�g���{)<BR>
     * �I�[�o�[�i�C�g���{���Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g���擾����B <BR>
     * �@@�@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * �@@�@@"web3.tradingcalendarcontext") <BR>
     * <BR>
     * �Q�j�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B <BR>
     * <BR>
     * �@@�@@[�����L�[] <BR>
     * �@@�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@�s��R�[�h�F�@@�����̎s��R�[�h <BR>
     * �@@�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B <BR>
     * �@@�@@���i�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�@@�c�Ɠ��敪�F�@@"�I���c�Ɠ�"<BR>
     * �@@�@@�������v�Z�F�@@"�O�c�Ɠ�"<BR>
     * �@@�@@�s��g���K���s�F�@@"SONAR��MQ�ƃ��K�����{����"<BR>
     * <BR>
     * �R�j�@@�Q�j�Ń��R�[�h���擾�ł����ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 47590CD20284
     */
    public static boolean isOverNightExecute(String l_strMarketCode) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isOverNightExecute(String)";
        log.entering(STR_METHOD_NAME);

        //�P�jThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //�Q�j�@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        //�@@[�����L�[]
        //�@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@�s��R�[�h�F�@@�����̎s��R�[�h
        //�@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@���i�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //�@@�c�Ɠ��敪�F�@@�h�I���c�Ɠ��h
        //�@@�������v�Z�F�@@�h�O�c�Ɠ��h
        //�@@�s��g���K���s�F �@@�hSONAR��MQ�g���K�����{����h
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        l_sbWhere.append(" and submit_market_trigger = ? ");

        List l_lisWheres = new ArrayList();
        l_lisWheres.add(l_clendarContext.getInstitutionCode());
        l_lisWheres.add(l_clendarContext.getBranchCode());
        l_lisWheres.add(l_strMarketCode);
        l_lisWheres.add(l_clendarContext.getTradingTimeType());
        l_lisWheres.add(l_clendarContext.getProductCode());
        l_lisWheres.add(WEB3BizDateTypeDef.BIZ_DATE);
        l_lisWheres.add(WEB3BizDateCalcParameterDef.BEFORE_BIZ_DATE);
        l_lisWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

        Object[] l_objWheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWheres);

        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        //�R�j�@@�Q�j�Ń��R�[�h���擾�ł����ꍇ�Atrue��ԋp����B
        if (l_intSize != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�擾�ł��Ȃ������ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (validate������t�\)<BR>
     * ������t�\�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ً}��~�A�o�b�`���`�F�b�N<BR>
     * ������t�X�e�C�^�X�e�[�u��������J�����_�R���e�L�X�g�̓��e�œǂ݁A<BR>
     * �擾�����s�̒�����t�X�e�C�^�X���h�ʏ�h�łȂ��ꍇ�͗�O���X���[����B<BR>
     * �i������t�s�̃X�e�C�^�X�ɂ́A�h�o�b�`�������h�A�h�ً}��~���h������A<BR>
     * ��O���b�Z�[�W���킯��j<BR>
     * -�o�b�`������-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00011<BR>
     * -�V�X�e���ً}��~��-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00012<BR>
     * <BR>
     * �Q�j�@@������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j�̏ꍇ�́A<BR>
     *  �ȍ~�̏����͍s�킸��return����B<BR>
     *  ������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j�� �ꍇ�́A<BR>
     *  �ȉ��̏����𑱍s����B <BR>
     * �R�j�@@��t�s���ԑу`�F�b�N<BR>
     * �@@������ԃe�[�u�����ȉ��̏����Ō������A�Y���s�́u��t�\�v���ڂ�"��t�s��"�ł���΁A<BR>
     * �@@������t�s�Ɣ��肷��B<BR>
     * -������t�X�e�C�^�X����t���ȊO-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00013<BR>
     * �@@�Y���s�������s����ꍇ�́A�P���ł��u��t�\�v�ł���Β�����t�\�Ƃ���B<BR>
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
     * �@@�Y���s�����݂��Ȃ��ꍇ�́A�f�[�^�s�����i�V�X�e���G���[�j�Ƃ��ė�O���X���[����B<BR>
     * -�Y���f�[�^�Ȃ�-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * <BR>
     *  (*1)�@@������ԃR���e�L�X�g�̎擾<BR>
     *  �|ThreadLocalSystemAttributesRegistry���A<BR>
     * ����J�����_�R���e�L�X�g���擾����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     *  (*2) ��t���Ԃ̎擾<BR>
     *  �|ThreadLocalSystemAttributesRegistry����t�������擾���A<BR>
     * �擾���������̎��ԕ����B<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     *  (*3) �c�Ɠ��敪�̎擾<BR>
     *  �|this.get�c�Ɠ��敪()���R�[�����Ď擾����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4014CD6801CF
     */
    public static void validateOrderAccept() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderAccept()";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strOrderAccProduct;
        String l_strOrderAccTransaction;
        String l_strOrderAcceptStatus;
        String l_strMarketCode;
        String l_strTradingTimeType;
        String l_strProductCode;

        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //�،���ЃR�[�h
        l_strInstitutionCode = l_clendarContext.getInstitutionCode();
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
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
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
        catch (DataFindException l_ex)
        {
            l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // �o�b�`������
            log.debug("�V�X�e�����o�b�`�������ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�V�X�e�����o�b�`�������ł��B");
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // �ً}��~��
            log.debug("�V�X�e�����ً}��~���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�V�X�e�����ً}��~���ł��B");
        }

        // �Q�j�@@������ԃR���e�L�X�g(*1)�̒�����t�g�����U�N�V�������h07�h�i�Ɖ�j
        // �̏ꍇ�́A�ȍ~�̏����͍s�킸��return����
        if (WEB3OrderAccTransactionDef.REFERENCE.equals(l_strOrderAccTransaction))
        {
            log.info("������t�g�����U�N�V�������h07�h�i�Ɖ�j �F�@@��t�\");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�R�j��t�s���ԑу`�F�b�N

        //��t���Ԃ̎擾(�擾���������̎��ԕ���)
        Timestamp l_tsOrderAcceptDate = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
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

        ArrayList l_lstObjTradingTimeWhere = new ArrayList();
        //�،���ЃR�[�h
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //���X�R�[�h
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //��t���ԋ敪
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //�c�Ɠ��敪
        l_lstObjTradingTimeWhere.add(l_bizDateType);

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
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����`�F�b�N
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            log.debug("������ԃe�[�u�������F ���� = 0");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "������ԃe�[�u�������F ���� = 0");
        }

        //�P���ł��u��t�\�v�ł���Β�����t�\�Ƃ���
        TradingTimeRow l_tradingTimeRow = null;
        String l_strEnableOrder = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (WEB3EnableOrderDef.ENABLE.equals(l_tradingTimeRow.getEnableOrder())
                && Long.parseLong(l_tradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime)
                && Long.parseLong(l_tradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime))
            {
                l_strEnableOrder = l_tradingTimeRow.getEnableOrder();
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug("�V�X�e������t�\���ԊO�ł��B" + "�F��t�s���ԑ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�V�X�e������t�\���ԊO�ł��B");
        }

        log.debug(STR_METHOD_NAME + "�F��t���ԑ�");
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�s��J�ǎ��ԑ�)<BR>
     * �s��ǎ��ԑт��ǂ�����ԋp����B<BR>
     * �s��J�ǎ��ԑт̏ꍇtrue�A�s��ǎ��ԑт̏ꍇfalse��ԋp����B<BR>
     * <BR>
     * �P�jThreadLocalSystemAttributesRegistry���A��t�������擾����B<BR>
     *  �@@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     *     "xblocks.gtl.attributes.systemtimestamp")<BR>
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
     * �R�j�@@��t���̉c�Ɠ��敪�擾�B<BR>
     * �@@this.get�c�Ɠ��敪()���R�[�����Ď擾����B<BR>
     * <BR>
     * �@@��get�c�Ɠ��敪()�̖߂�l��"��c�Ɠ�"�̏ꍇ�́Afalse��ԋp����B<BR>
     * <BR>
     * �S�j�@@������Ԏ擾<BR>
     * �@@�ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B<BR>
     * <BR>
     * �@@[�����L�[]<BR>
     * �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@���i�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B<BR>
     * �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪<BR>
     * �@@�@@�s��g���K���s�F �hSONAR��MQ�g���K�����{����h<BR>
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�́Afalse�i�ǒ��j��ԋp����B <BR>
     * <BR>
     * �@@�擾�����s���A�ȉ��̒ʂ�߂�l�𔻒肷��B<BR>
     * <BR>
     *  �i�s��J�ǎ��ԑ�(*1)�@@���@@�i��t�����̎��ԕ����j�� �s��ǎ��ԑ�(*2)�j<BR>
     *  �ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
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

        boolean l_blnReturn;

        // �P�j ThreadLocalSystemAttributesRegistry���A��t�������擾����B
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

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
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //�R�j�@@��t���̉c�Ɠ��敪�擾�B
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);

        //get�c�Ɠ��敪()�̖߂�l��"��c�Ɠ�"�̏ꍇ�́Afalse��ԋp����B
        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug("��t����( " + l_tsOrderAcceptDate + ") �F ��c�Ɠ��̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // �S�j ������Ԏ擾
        //  �ȉ��̌����L�[�Ŏ�����ԃe�[�u������������B
        //[�����L�[]
        //* �@@�،���ЃR�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@���X�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@�s��R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@��t���ԋ敪�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@���i�R�[�h�F�@@������ԃR���e�L�X�g�̓����v���p�e�B
        //* �@@�c�Ɠ��敪�F�@@���肵���c�Ɠ��敪
        //* �@@�s��g���K���s�F �hSONAR��MQ�g���K�����{����h
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and submit_market_trigger = ? ");

        List l_lisTradingTimeWheres = new ArrayList();
        l_lisTradingTimeWheres.add(l_strInstitutionCode);
        l_lisTradingTimeWheres.add(l_strBranchCode);
        l_lisTradingTimeWheres.add(l_strMarketCode);
        l_lisTradingTimeWheres.add(l_strTradingTimeType);
        l_lisTradingTimeWheres.add(l_strProductCode);
        l_lisTradingTimeWheres.add(l_strBizDateType);
        l_lisTradingTimeWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

        Object[] l_objTradingTimeWheres =
            new Object[l_lisTradingTimeWheres.size()];
        l_lisTradingTimeWheres.toArray(l_objTradingTimeWheres);

        List l_lisStartRecords;
        List l_lisEndRecords;
        String l_strMarketStartTime;
        String l_strMarketEndTime;
        // �J�ǎ����̍ŏ��l�����߂�
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time",
                null,
                l_objTradingTimeWheres);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�@@�Y���s�����݂��Ȃ��ꍇ�́Afalse�i�ǒ��j��ԋp����B
        int l_intSize = l_lisStartRecords.size();
        if (l_intSize == 0)
        {
            log.debug("�@@�Y���s�����݂��Ȃ��ꍇ�́Afalse�i�ǒ��j��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        l_strMarketStartTime = ((TradingTimeRow)l_lisStartRecords.get(0)).getStartTime();

        // �ǎ����̍ő�l�����߂�
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisEndRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "end_time desc",
                null,
                l_objTradingTimeWheres);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �Y���s�����݂��Ȃ��ꍇ�́Afalse�i�ǒ��j��ԋp����B
        int l_intEndSize = l_lisEndRecords.size();
        if (l_intEndSize == 0)
        {
            log.debug("�Y���s�����݂��Ȃ��ꍇ�́Afalse�i�ǒ��j��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        l_strMarketEndTime = ((TradingTimeRow)l_lisEndRecords.get(0)).getEndTime();

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
            l_blnReturn = true;
            log.debug("*** ������ԊǗ�***   �s��J�ǎ��ԑ�");
        }
        else
        {
            l_blnReturn = false;
            log.debug("*** ������ԊǗ�***   �s��ǎ��ԑ�");
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
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
     * @@param l_datConfirmBizDate - �m�F��������<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 406937AB0203
     */
    public static Date getOrderBizDate(Date l_datConfirmBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date)";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = getOrderBizDate();
        if (l_datBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datConfirmBizDate;
        }

        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datConfirmBizDate) != 0)
        {
            log.debug("���������ς��܂����B���萔�ł����A������x���͂������Ă��������B");
            log.exiting(STR_METHOD_NAME);
            //�擾�����������ƈ����̊m�F�����������Ⴄ���t�ł���Η�O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "���������ς��܂����B���萔�ł����A������x���͂������Ă��������B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datConfirmBizDate;
    }
}
@
