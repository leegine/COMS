head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformHostReqOrderNumberManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���Ǘ����ʃR�[�h�̔ԃT�[�r�XImpl(WEB3InformHostReqOrderNumberManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.inform.data.InformCtrlRequestNumberParams;
import webbroker3.inform.data.InformCtrlRequestNumberRow;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�A���Ǘ����ʃR�[�h�̔ԃT�[�r�XImpl)<BR>
 * �A���Ǘ����ʃR�[�h�̔ԃT�[�r�X�����N���X<BR>
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3InformHostReqOrderNumberManageServiceImpl
    implements WEB3InformHostReqOrderNumberManageService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformHostReqOrderNumberManageServiceImpl.class);

    /**
     * @@roseuid 41EE632B034B
     */
    public WEB3InformHostReqOrderNumberManageServiceImpl()
    {

    }

    /**
     * (get�V�K���ʃR�[�h)<BR>
     * �e��A���̎��ʃR�[�h�������̔Ԃ���B<BR>
     * <BR>
     * ���ʃR�[�h�̃R�[�h�̌n�͈ȉ��̒ʂ�B <BR>
     * <BR>
     * �@@�E�P�`�W���ځiindex=0�`7�j�F�@@���t�Z�N�V�����iyyyyMMdd�j <BR>
     * �@@�E�X�`�P�R���ځiindex=8�`12�j�F�@@5���̒ʔ� <BR>
     * �@@�i�،���ЁA�A����ʖ��ɁA�e��A�����ʃR�[�h�e�[�u���ɍŏI�̔Ԓl��ۑ��j <BR>
     * <BR>
     * �P�j�@@���t�Z�N�V�����i�hyyyyMMdd�h�擾�j <BR>
     * �@@�@@TradingSystem.getBizDate() ���hyyyyMMdd�h�Ƀt�H�[�}�b�g������������擾����B <BR>
     * <BR>
     * �Q�j�@@���ʃR�[�h�e�[�u�����X�V����B <BR>
     * <BR>
     * �@@�e��A�����ʃR�[�h�e�[�u�����ȉ��̏����Ō�������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@�A����� = ����.�A����� <BR>
     * <BR>
     * �@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A���ʃR�[�h�ő�l��ԋp����B <BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@�A����� = ����.�A����� <BR>
     * �@@�@@���ʃR�[�h�ő�l = yyyyMMdd + "00001" <BR>
     * �@@�@@�쐬���� = TradingSystem.getSystemTimestamp() <BR>
     * �@@�@@�X�V���� = TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �@@�|�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A���ʃR�[�h�ő�l��ԋp����B<BR> 
     * <BR>
     * �@@�@@���ʃR�[�h�ő�l = yyyyMMdd + �i���P�j <BR>
     * �@@�@@�X�V���� = TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �@@�@@�i���P�j�@@���ʃR�[�h�ő�l�i�ʔԕ����j�̌v�Z <BR>
     * <BR>
     * �@@�@@���@@�����l�Ɠ�����̏ꍇ <BR>
     * �@@�@@�@@�P�j�Ŏ擾�������t�Z�N�V����������ƁA�����l�̓��t�Z�N�V���������񂪓����ꍇ�A <BR>
     * �@@�@@�@@�����l�̒ʔԁ{�P�̐�����5���̕�����ɕҏW����B <BR>
     * <BR>
     * �@@�@@���@@�����l�Ɠ�����łȂ��ꍇ <BR>
     * �@@�@@�@@"00001"��ҏW����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����
     * @@return String
     * @@roseuid 41BD568A02E3
     */
    public String getNewOrderRequestCode(
        String l_strInstitutionCode,
        String l_strInformDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewOrderRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���t�Z�N�V�����i�hyyyyMMdd�h�擾�j
        //TradingSystem.getBizDate() ���hyyyyMMdd�h�Ƀt�H�[�}�b�g������������擾����B
        TradingSystem l_tradingSystem = GtlUtils.getTradingSystem();
        Date l_datBizDate = l_tradingSystem.getBizDate();
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");

        // ���ʃR�[�h�������̔Ԃ���
        String l_strRequestNumber = null;

        try
        {    
            //�Q�j�@@���ʃR�[�h�e�[�u�����X�V����B <BR>
            // �e��A�����ʃR�[�h�e�[�u�����ȉ��̏����Ō�������B <BR>
            // [����] <BR>
            //�@@  �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
            //  �@@�A����� = ����.�A����� <BR>
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_code = ? ");
            l_strWhere.append(" and inform_div = ? ");
    
            Object[] l_objWhere = {
                l_strInstitutionCode,
                l_strInformDiv};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformCtrlRequestNumberRow.TYPE,
                l_strWhere.toString(),
                null,
                "for update",
                l_objWhere);
    
            int l_intListRecordCnt = 0;
            if (l_lisRecords != null)
            {
                l_intListRecordCnt = l_lisRecords.size();
            }
            log.debug("==========�����̌���:==========" + l_intListRecordCnt);

            //�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A
            //  ���ʃR�[�h�ő�l��ԋp����B<BR>
            if (l_intListRecordCnt == 0)
            {
                InformCtrlRequestNumberParams l_requestNumberParams = new InformCtrlRequestNumberParams();

                //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
                l_requestNumberParams.setInstitutionCode(l_strInstitutionCode);

                //�@@�A����� = ����.�A����� <BR>
                l_requestNumberParams.setInformDiv(l_strInformDiv);

                //�@@���ʃR�[�h�ő�l = yyyyMMdd + "00001" <BR>
                l_strRequestNumber = l_strBizDate + "00001";
                l_requestNumberParams.setLastInfoCtrlRequestNumber(l_strRequestNumber);

                //�@@�쐬���� = TradingSystem.getSystemTimestamp() <BR>
                l_requestNumberParams.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());

                //�@@�X�V���� = TradingSystem.getSystemTimestamp() <BR>
                l_requestNumberParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

                l_queryProcessor.doInsertQuery(l_requestNumberParams);
            }
            //�|�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A
            //  ���ʃR�[�h�ő�l��ԋp����B<BR> 
            else
            {
                InformCtrlRequestNumberRow l_requestNumberRow = (InformCtrlRequestNumberRow)l_lisRecords.get(0);
                InformCtrlRequestNumberParams l_requestNumberParams = new InformCtrlRequestNumberParams(l_requestNumberRow);

                // ���ʃR�[�h�̃R�[�h�̌n�͈ȉ��̒ʂ�B <BR>
                // �E�P�`�W���ځiindex=0�`7�j�F�@@���t�Z�N�V�����iyyyyMMdd�j <BR>
                String l_strDateSection = l_requestNumberParams.getLastInfoCtrlRequestNumber().substring(0, 8);
                log.debug("���ʃR�[�h�̃R�[�h:" + l_requestNumberParams.getLastInfoCtrlRequestNumber());
                log.debug("l_strDateSection:" + l_strDateSection);
 
                // �E�X�`�P�R���ځiindex=8�`12�j�F�@@5���̒ʔ� <BR>
                String l_strLastNo = l_requestNumberParams.getLastInfoCtrlRequestNumber().substring(8, 13);
                long l_lngLastNo = Long.parseLong(l_strLastNo) + 1;
                log.debug("l_strLastNo:" + l_strLastNo);
 
                //�@@���ʃR�[�h�ő�l = yyyyMMdd + �i���P�j <BR>
                // �i���P�j�@@���ʃR�[�h�ő�l�i�ʔԕ����j�̌v�Z <BR>
                if (l_strBizDate.equals(l_strDateSection))
                {
                    log.debug("�����l�Ɠ����:" + l_strBizDate);
                    //�� �����l�Ɠ�����̏ꍇ <BR>
                    //�@@ �P�j�Ŏ擾�������t�Z�N�V����������ƁA�����l�̓��t�Z�N�V���������񂪓����ꍇ�A<BR>
                    //�@@ �����l�̒ʔԁ{�P�̐�����5���̕�����ɕҏW����B <BR>
                    l_strRequestNumber = l_strBizDate + WEB3StringTypeUtility.formatNumber(l_lngLastNo, 5);
                    l_requestNumberParams.setLastInfoCtrlRequestNumber(l_strRequestNumber);
                }
                else
                {
                    log.debug("�����l�Ɠ�����łȂ�:" + l_strBizDate);
                    // �� �����l�Ɠ�����łȂ��ꍇ�A"00001"��ҏW����B
                    l_strRequestNumber = l_strBizDate + "00001";
                    l_requestNumberParams.setLastInfoCtrlRequestNumber(l_strRequestNumber);
                }

                //�@@�X�V���� = TradingSystem.getSystemTimestamp() <BR>
                l_requestNumberParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_requestNumberParams);
            }
        }
        catch (DataNetworkException l_dnEx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dnEx.getMessage(),
                l_dnEx);
        }
        catch (DataQueryException l_dqEx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqEx.getMessage(),
                l_dqEx);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strRequestNumber;
    }
}
@
