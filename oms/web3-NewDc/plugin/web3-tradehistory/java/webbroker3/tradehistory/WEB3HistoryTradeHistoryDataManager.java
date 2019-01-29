head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������f�[�^�}�l�[�W��(WEB3HistoryTradeHistoryDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26�͌d�� (���u) �V�K�쐬
Revesion History : 2004/10/29�Ɍ��t (���u) �쐬
Revesion History : 2004/11/11�Ɍ��t (���u) �쐬
                   2006/10/19  �����F (���u) ���f�� 056
*/
package webbroker3.tradehistory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BookValueRecordDivDef;
import webbroker3.common.define.WEB3BookValueRemarkDef;
import webbroker3.common.define.WEB3DepositMarginDivDef;
import webbroker3.common.define.WEB3ProfitLossRecordDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.tradehistory.data.BookValueSpecParams;
import webbroker3.tradehistory.data.BookValueSpecRow;
import webbroker3.tradehistory.data.ProfitLossSpecParams;
import webbroker3.tradehistory.data.ProfitLossSpecRow;
import webbroker3.tradehistory.data.SettleDetailHistoryDao;
import webbroker3.tradehistory.data.SettleDetailHistoryParams;
import webbroker3.tradehistory.data.SettleDetailHistoryRow;
import webbroker3.tradehistory.data.TradeDetailHistoryDao;
import webbroker3.tradehistory.data.TradeDetailHistoryParams;
import webbroker3.tradehistory.data.TradeDetailHistoryRow;
import webbroker3.tradehistory.data.TradeHistoryRow;
import webbroker3.tradehistory.data.TransactionHistoryRow;
import webbroker3.tradehistory.define.WEB3PlsBvsDetailOrderRecDef;
import webbroker3.tradehistory.define.WEB3PlsBvsDisplayTermDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTransactionDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��������f�[�^�}�l�[�W��)<BR>
 * ���������DB I/O���Ǘ�����N���X<BR>
 *
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryDataManager
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryDataManager.class);


    /**
     * @@roseuid 41789C2F01C5
     */
    public WEB3HistoryTradeHistoryDataManager()
    {

    }

    /**
     * ( get��������ꗗ)<BR>
     * (static���\�b�h)<BR>
     * �w�肳�ꂽ���������ɊY�������������e�[�u���̃f�[�^���擾���A�ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���R�[������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * arg0�F�@@�h��������e�[�u��(trade_history)�h<BR>
     * arg1�F�@@�p�����[�^.��������������<BR>
     * arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * arg3�F�@@null<BR>
     * arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryDataContainer - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 413C1B470250
     */
    public static List getTradeHistoryList(String l_strQueryString, String[] l_strQueryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeHistoryList()";

        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {

            //�P�jQueryProcessor.doFindAllQuery()���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradeHistoryRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        //�Q�j�������ʂ�ԋp����B
        return l_lisRecords;
    }

    /**
     * (get�ڋq����c�������ꗗ)<BR>
     * (static���\�b�h)<BR>
     * �����̏����ɊY������ڋq����c�������f�[�^��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�ڋq����c�������e�[�u������������B<BR>
     * <BR>
     * �@@�y���������z<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����<BR>
     * �@@���X�R�[�h = �p�����[�^.���X�R�[�h�@@����<BR>
     * �@@�ڋq�R�[�h = �p�����[�^.�����R�[�h�@@����<BR>
     * �@@��n�� = �p�����[�^.��n���@@����<BR>
     * �@@�a��敪 IN ("1�F�a���", "4�F�ۏ؋�",<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"5�F�敨�؋���", "M�FMRF")<BR>
     * <BR>
     * �@@�P�|�P�j��L�����ɊY�����錟��������������쐬����B<BR>
     * �@@�@@�@@�@@�������������� = "institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and branch_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and account_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and delivery_date = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and deposit_margin_div in (?, ?, ?, ?) "<BR>
     * <BR>
     * �@@�P�|�Q�jArrayList�𐶐�����B<BR>
     * �@@�P�|�R�j���������������"?"�ɃZ�b�g����p�����[�^��<BR>
     * �@@�@@�@@�@@�@@�ȉ��̏��Ő�������ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.��n��<BR>
     * �@@�@@�@@�@@�@@�@@�E"1�F�a���"<BR>
     * �@@�@@�@@�@@�@@�@@�E"4�F�ۏ؋�"<BR>
     * �@@�@@�@@�@@�@@�@@�E"5�F�敨�؋���"<BR>
     * �@@�@@�@@�@@�@@�@@�E"M�FMRF"<BR>
     * �@@�P�|�S�jQueryProcessor.doFindAllQuery()���\�b�h���R�[�����A<BR>
     * �@@�@@�@@�@@�@@DB����������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@arg0�F�@@"�ڋq����c�������e�[�u��(transaction_history)"<BR>
     * �@@�@@�@@�@@�@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@�@@arg2�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�擾�����������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 413C1E7201C9
     */
    public static List getTransactionHistoryList(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTransactionHistoryList()";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�P�|�P�j����������������쐬����
            String l_strWhere = "institution_code = ? " +
                 "and branch_code = ? " +
                 "and account_code = ? " +
                 "and delivery_date = ? " +
                 "and deposit_margin_div in (?, ?, ?, ?) ";

            Object l_bindVars[] = {l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_datDeliveryDate,
                WEB3DepositMarginDivDef.FROM_DEPOSIT,
                WEB3DepositMarginDivDef.GUARANTEE,
                WEB3DepositMarginDivDef.FUTURES_DEPOSIT,
                WEB3DepositMarginDivDef.MRF
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TransactionHistoryRow.TYPE,
                l_strWhere,
                l_bindVars
                );

            //�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
            if (l_lisRecords != null)
            {
                int l_intCnt = l_lisRecords.size();
                if (l_intCnt == 0)
                {
                    l_lisRecords = null;
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        //�Q�j�擾�����������ʂ�ԋp����B
        return l_lisRecords;
    }

    /**
     * (get������ח���)<BR>
     * (static���\�b�h)<BR>
     * �����̖��׊Ǘ��ԍ��ɊY������<BR>
     * ������ח���Params��ԋp����B<BR>
     * <BR>
     * �P�j������ח����e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�y���������z<BR>
     * �@@������ח���ID�@@���@@�p�����[�^.���׊Ǘ��ԍ�<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strDetailsManagementNo - (���׊Ǘ��ԍ�)<BR>
     * ���׊Ǘ��ԍ�<BR>
     * @@return ������ח���Params
     * @@throws WEB3BaseException
     * @@roseuid 413C23FE00E0
     */
    public static TradeDetailHistoryParams getTradeDetailHistory(String l_strDetailsManagementNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeDetailHistory()";
        log.entering(STR_METHOD_NAME);

        TradeDetailHistoryParams l_tradeDetailHistoryParams = null;
        if (l_strDetailsManagementNo == null)
        {
            return l_tradeDetailHistoryParams;
        }

        try
        {
            TradeDetailHistoryRow l_tradeDetailHistoryRow =
                TradeDetailHistoryDao.findRowByTradeDetailHistoryId(Long.parseLong(l_strDetailsManagementNo));
            if (l_tradeDetailHistoryRow != null)
            {
                l_tradeDetailHistoryParams = new TradeDetailHistoryParams(l_tradeDetailHistoryRow);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɏd������Y���f�[�^�����݂��܂�", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        //�Q�j�擾�����������ʂ�ԋp����B
        return l_tradeDetailHistoryParams;

    }

    /**
     * (get���ϖ��ח���)<BR>
     * (static���\�b�h)<BR>
     * �����̖��׊Ǘ��ԍ��ɊY������<BR>
     * ���ϖ��ח���Params��ԋp����B<BR>
     * <BR>
     * �P�j���ϖ��ח����e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�y���������z<BR>
     * �@@���ϖ��ח���ID�@@���@@�p�����[�^.���׊Ǘ��ԍ�<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strDetailsManagementNo - (���׊Ǘ��ԍ�)<BR>
     * ���׊Ǘ��ԍ�<BR>
     * @@return SettleDetailHistoryParams
     * @@throws WEB3BaseException
     * @@roseuid 413C2C2700EB
     */
    public static SettleDetailHistoryParams getSettleDetailHistory(String l_strDetailsManagementNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleDetailHistory()";
        log.entering(STR_METHOD_NAME);

        SettleDetailHistoryParams l_settleDetailHistoryParams = null;
        if (l_strDetailsManagementNo == null)
        {
            return l_settleDetailHistoryParams;
        }

        try
        {
            SettleDetailHistoryRow l_settleDetailHistoryRow =
                SettleDetailHistoryDao.findRowBySettleDetailHistoryId(Long.parseLong(l_strDetailsManagementNo));
            if (l_settleDetailHistoryRow != null)
            {
                l_settleDetailHistoryParams = new SettleDetailHistoryParams(l_settleDetailHistoryRow);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɏd������Y���f�[�^�����݂��܂��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3HistoryTradeHistoryDataManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        //�Q�j�擾�����������ʂ�ԋp����B
        return l_settleDetailHistoryParams;
    }

    /**
     * (get���v���׌���)<BR>
     *�ڋq�ɊY�����鑹�v���׃f�[�^�̌�����ԋp����B<BR> 
     *<BR>
     *�P�j2�����\���i�p�����[�^.�����敪 = null�j�̏ꍇ <BR>
     *<BR>
     *�@@�P�|�P�j��������������&���������f�[�^�R���e�i  <BR>
     *�@@�@@�ȉ��̌���������\���A���������������  <BR>
     *�@@�@@ArrayList(���������f�[�^�R���e�i)���쐬����B  <BR>
     *<BR>
     *�@@�@@[��������]  <BR>
     *�@@�@@�@@�،���ЃR�[�h = �p�����[�^.�ڋq.�،���ЃR�[�h�@@����  <BR>
     *�@@�@@�@@���X�R�[�h = �p�����[�^.�ڋq.���X�R�[�h�@@���� <BR>
     *�@@�@@�@@�ڋq�R�[�h = �p�����[�^.�ڋq.�����R�[�h�@@���� <BR>
     *�@@�@@�@@( <BR>
     *�@@�@@�@@�@@���R�[�h�敪 = "10�F�c�����R�[�h"�@@�܂��� <BR>
     *�@@�@@�@@�@@(���R�[�h�敪 in ("20�F���׃��R�[�h", "21�F���o�����R�[�h") <BR>
     * �@@�@@�@@�@@���@@�v�Z�N���� <= ��ƔN����) <BR><BR>
     *�@@�@@�@@) <BR>
     *<BR>
     *�@@�P�|�Q�j��L������������ɁA����������������쐬����B  <BR>
     *<BR>
     *�@@�@@�@@�������������� = "institution_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(rec_div = ? or "  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(rec_div in (?, ?) and calc_date <= work_date)) " <BR>
     *<BR>
     *�@@�@@�P�|�R�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B  <BR>
     *�@@�@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B  <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.���X�R�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.�����R�[�h <BR>
     *�@@�@@�@@�@@�E"10�F�c�����R�[�h" <BR>
     *�@@�@@�@@�@@�E"20�F���׃��R�[�h" <BR>
     *�@@�@@�@@�@@�E"21�F���o�����R�[�h" <BR>
     *<BR>
     *�Q�j18�����\���i�p�����[�^.�����敪 = 01�j�̏ꍇ <BR>
     *<BR>
     *�@@�Q�|�P�j��������������&���������f�[�^�R���e�i  <BR>
     *�@@�@@�ȉ��̌���������\���A���������������  <BR>
     *�@@�@@ArrayList(���������f�[�^�R���e�i)���쐬����B  <BR>
     *<BR>
     *�@@�@@[��������]  <BR>
     *�@@�@@�@@�،���ЃR�[�h = �p�����[�^.�ڋq.�،���ЃR�[�h�@@����  <BR>
     *�@@�@@�@@���X�R�[�h = �p�����[�^.�ڋq.���X�R�[�h�@@���� <BR>
     *�@@�@@�@@�ڋq�R�[�h = �p�����[�^.�ڋq.�����R�[�h�@@���� <BR>
     *�@@�@@�@@( <BR>
     *�@@�@@�@@�@@���R�[�h�敪 = "10�F�c�����R�[�h"�@@�܂��� <BR>
     *�@@�@@�@@�@@���R�[�h�敪 in ("20�F���׃��R�[�h", "21�F���o�����R�[�h") <BR>
     *�@@�@@�@@) <BR>
     *<BR>
     *�@@�Q�|�Q�j��L������������ɁA����������������쐬����B  <BR>
     *<BR>
     *�@@�@@�@@�������������� = "institution_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(rec_div = ? or "  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(rec_div in (?, ?) )) " <BR>
     *<BR>
     *�@@�@@�Q�|�R�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B  <BR>
     *�@@�@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B  <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.���X�R�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.�����R�[�h <BR>
     *�@@�@@�@@�@@�E"10�F�c�����R�[�h" <BR>
     *�@@�@@�@@�@@�E"20�F���׃��R�[�h" <BR>
     *�@@�@@�@@�@@�E"21�F���o�����R�[�h" <BR>
     *<BR>
     *�R�jQueryProcessor.doGetCountQuery()�ɂāA���v���׌������擾����B <BR> 
     *<BR>
     *�@@[doGetCountQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *�@@arg0�F�@@"���v���׃e�[�u��(profit_loss_spec)"  <BR>
     *�@@arg1�F�@@�쐬������������������ <BR>
     *�@@arg2�F�@@�쐬����ArrayList.toArray()  <BR>
     *<BR>
     *�S�j�擾����������ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strTransactionDiv - (�����敪)<BR>
     * ���N�G�X�g�f�[�^.�����敪<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 417DCF6900A9
     */
    public int getProfitLossSpecCount(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strTransactionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getProfitLossSpecCount(WEB3GentradeMainAccount l_mainAccount, String l_strTransactionDiv) ";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //�P�j2�����\���i�p�����[�^.�����敪 = null�j�̏ꍇ 
        if (WEB3StringTypeUtility.isEmpty(l_strTransactionDiv))
        {
            //�P�|�P�j��������������&���������f�[�^�R���e�i
            //�P�|�Q�j��L������������ɁA����������������쐬����B
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("account_code = ? and ");
            l_sbQueryString.append("(rec_div = ? or ");
            l_sbQueryString.append("(rec_div in (?, ?) and calc_date <= work_date)) ");

            //�P�|�R�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
            l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
            l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
            l_lisQueryVars.add(l_mainAccount.getAccountCode());
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
        
        }
        // �Q�j18�����\���i�p�����[�^.�����敪 = 01�j�̏ꍇ
        else if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_strTransactionDiv))
        {
            //�Q�|�P�j��������������&���������f�[�^�R���e�i
            //�Q�|�Q�j��L������������ɁA����������������쐬����B
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("account_code = ? and ");
            l_sbQueryString.append("(rec_div = ? or ");
            l_sbQueryString.append("(rec_div in (?, ?))) ");

            //�Q�|�R�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
            l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
            l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
            l_lisQueryVars.add(l_mainAccount.getAccountCode());
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisQueryVars.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
            
        }

        //�R�jQueryProcessor.doGetCountQuery()�ɂāA���v���׌������擾����B
        int l_intReturnRecordCnt = 0;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);
            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(ProfitLossSpecRow.TYPE, l_sbQueryString.toString(), l_objVars);
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
        //�S�j�擾����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_intReturnRecordCnt;
    }

    /**
     * (get�݌v���v)<BR>
     * �݌v���v���擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�\������ == "0�F�O�������ȍ~"�̏ꍇ<BR>
     * �@@�P�|�P�jthis.get�c�����R�[�hfrom���v����()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[get�c�����R�[�hfrom���v����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * <BR>
     * �@@�P�|�Q�j�P�|�P�j�̃��\�b�h�̖߂�l(���v����Params).���v�̒l��ԋp����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�\������ != "0�F�O�������ȍ~"�̏ꍇ<BR>
     * �@@�Q�|�P�jthis.get�ŏI�v�Z�N����from���v����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�ŏI�v�Z�N����from���v����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@�@@�@@�\�����ԁF�@@�p�����[�^.�\������<BR>
     * <BR>
     * �@@�@@�@@�@@���\�b�h�̖߂�l��null�̏ꍇ�Athis.get�݌v���v()���\�b�h���R�[�����A<BR>
     * �@@�@@�@@�@@�߂�l��ԋp���ďI������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�݌v���v�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@�@@�@@�\�����ԁF�@@"0�F�O�������ȍ~"<BR>
     * <BR>
     * �@@�Q�|�Q�j�ȉ��̌���������\���A����������������쐬����B <BR>
     * <BR>
     * �@@�@@[��������] <BR>
     * �@@�@@�@@���R�[�h�敪 = "20�F���׃��R�[�h"�@@����<BR>
     * �@@�@@�@@�v�Z�N���� = �Q�|�P�j�̖߂�l<BR>
     * <BR>
     * �@@�@@��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = "and rec_div = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and calc_date = ?"<BR>
     * <BR>
     * �@@�Q�|�R�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B <BR>
     * �@@�@@�@@�E"20�F���׃��R�[�h"<BR>
     * �@@�@@�@@�E�Q�|�P�j�̖߂�l<BR>
     * <BR>
     * �@@�Q�|�S�j�ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�@@�@@�ESORT-NO �~��<BR>
     * <BR>
     * �@@�@@�@@�\�[�g���� = " sort_no desc"<BR>
     * <BR>
     * �@@�Q�|�T�jthis.get���v���׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get���v���׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�\�[�g�����F�@@�쐬�����\�[�g����<BR>
     * <BR>
     * �@@�@@�@@�@@null���ԋp���ꂽ�ꍇ�́A0��ԋp���A�I������B<BR>
     * <BR>
     * �@@�Q�|�U�j��������(���v����Params��List)��0�Ԗڂ̗v�f.�݌v���v<BR>
     * �@@�@@�@@�@@�@@�̒l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@return double
     * @@roseuid 417DCF6900B9
     */
    public double getTotalProlossAmount(WEB3GentradeMainAccount l_mainAccount, String l_strDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTotalProlossAmount(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�p�����[�^.�\������ == "0�F�O�������ȍ~"�̏ꍇ
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
        {
            //�P�|�P�jthis.get�c�����R�[�hfrom���v����()���\�b�h���R�[������B
            ProfitLossSpecParams l_dblprofitLossSpecParams = this.getBalanceRecordFromProfitLoss(l_mainAccount);
            //�P�|�Q�j�P�|�P�j�̃��\�b�h�̖߂�l(���v����Params).���v�̒l��ԋp����B
            if (l_dblprofitLossSpecParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            log.exiting(STR_METHOD_NAME);
            return l_dblprofitLossSpecParams.getProlossAmount();

        }
        //�Q�j�p�����[�^.�\������ != "0�F�O�������ȍ~"�̏ꍇ
        else
        {
            //�Q�|�P�jthis.get�ŏI�v�Z�N����from���v����()���R�[������B
            Date l_datfinalCalcDateFromProfitLossSpec = this.getFinalCalcDateFromProfitLossSpec(l_mainAccount, l_strDisplayTerm);
            if (l_datfinalCalcDateFromProfitLossSpec == null)
            {
                log.exiting(STR_METHOD_NAME);
                return this.getTotalProlossAmount(l_mainAccount, WEB3PlsBvsDisplayTermDef.DEFAULT);
            }
            //�Q�|�Q�j�ȉ��̌���������\���A����������������쐬����B
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("and rec_div = ? ");
            l_sbQueryString.append("and calc_date = ?");
            List l_lisArrayList = new ArrayList();
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisArrayList.add(l_datfinalCalcDateFromProfitLossSpec);
            Object[] l_objArray = new Object[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_objArray);
            String l_strSortCond = " sort_no desc";
            //�Q�|�T�jthis.get���v���׈ꗗ()���\�b�h���R�[������B
            List l_lisprofitLossSpecParams =
                    this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, l_strSortCond);
            if (l_lisprofitLossSpecParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�Q�|�U�j��������(���v����Params��List)��0�Ԗڂ̗v�f.�݌v���v
            double l_dblTotalProlossAmount =
                    ((ProfitLossSpecParams)(l_lisprofitLossSpecParams.get(0))).getTotalProlossAmount();
            log.exiting(STR_METHOD_NAME);
            return l_dblTotalProlossAmount;
        }
    }

    /**
     * (get�ŏI�v�Z�N����from���v����)<BR>
     * �w�肳�ꂽ�\�����Ԃ̕\�����ԑO�ŏI�v�Z����ԋp����B<BR>
     * (���v���חp)<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@���R�[�h�敪 = "20�F���׃��R�[�h"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = " and rec_div = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B <BR>
     * �@@�@@�@@�E"20�F���׃��R�[�h"<BR>
     * <BR>
     * �@@�P�|�R�j�p�����[�^.�\�����Ԃ̒l�ɂ��A�ȉ��̏�����<BR>
     * �@@�@@�@@�@@���������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�p�����[�^.�\�����Ԃ��A<BR>
     * �@@�@@�@@�@@�@@["1�F1������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= add_months(work_date, -1)"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["2�F1�T�ԕ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date - 7"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["3�F�O��1����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date - 1"<BR>
     * <BR>
     * �@@�P�|�S�j�ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�@@�@@�@@�E�v�Z�N���� �~��<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� = " calc_date desc"<BR>
     * <BR>
     * �@@�P�|�T�jthis.get���v���׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get���v���׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@�@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�\�[�g�����F�@@�쐬�����\�[�g����<BR>
     * <BR>
     * �@@�@@�@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �@@�P�|�U�j��������(���v����Params��List)��0�Ԗڂ̗v�f.�v�Z�N����<BR>
     * �@@�@@�@@�@@�@@�̒l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@return Date
     * @@roseuid 417DCF6900C8
     */
    public Date getFinalCalcDateFromProfitLossSpec(WEB3GentradeMainAccount l_mainAccount, String l_strDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinalCalcDateFromProfitLossSpec(WEB3GentradeMainAccount, String) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A���������������ArrayList(���������f�[�^�R���e�i)���쐬����B
        //�P�|�P�j��L������������ɁA����������������쐬����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");

        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
       
        //�P�|�R�j�p�����[�^.�\�����Ԃ̒l�ɂ��A�ȉ��̏��������������ɒǉ�����B
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
        {
            l_sbQueryString.append("and calc_date <= add_months(work_date, ?)");
            l_lisArrayList.add(new Integer(-1));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisArrayList.add(new Integer(7));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisArrayList.add(new Integer(1));
        }
        //�P�|�S�j�ȉ��̃\�[�g�������쐬����B
        String l_strSortCond = " calc_date desc";
        
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        //�P�|�T�jthis.get���v���׈ꗗ()���\�b�h���R�[������B
        List l_lisProfitLossSpec =
                this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, l_strSortCond);
        //�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B
        if (l_lisProfitLossSpec == null || l_lisProfitLossSpec.size() == 0)
        {
            return null;
        }
        //�P�|�U�j��������(���v����Params��List)��0�Ԗڂ̗v�f.�v�Z�N�����̒l��ԋp����B
        Date l_datFinalCalcDateFromProfitLossSpec =
                (Timestamp)(((ProfitLossSpecParams)(l_lisProfitLossSpec.get(0))).getCalcDate());
        log.exiting(STR_METHOD_NAME);
        return l_datFinalCalcDateFromProfitLossSpec;
    }

    /**
     * (get���v���׈ꗗ)<BR>
     * �����̌��������ɊY�����鑹�v���ׂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̌ڋq�������������ɒǉ�����B<BR>
     * �@@�E�،���ЃR�[�h<BR>
     * �@@�E���X�R�[�h<BR>
     * �@@�E�����R�[�h<BR>
     * <BR>
     * �@@���ǉ��������������� =  "institution_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? "<BR>
     * <BR>
     * �@@�@@�@@�쐬������������������ = �ǉ���������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �p�����[�^.��������������Ƃ���B<BR>
     * <BR>
     * �@@���ǉ����������f�[�^�R���e�i<BR>
     * �@@(ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɒǉ�����)<BR>
     * �@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�E�p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�@@�E�p�����[�^.�ڋq.�����R�[�h<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i�̗v�f�S��(0�Ԗڂ��珇���ǉ�)<BR>
     * <BR>
     * �@@�@@�쐬�������������f�[�^�R���e�i = �ǉ����������f�[�^�R���e�i�Ƃ���B<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuary()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"���v���׃e�[�u��(profit_loss_spec)"<BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�쐬�������������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌��ʂ�ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_queryDataContainer - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@roseuid 417DCF6900D8
     */
    public List getProfitLossSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strQueryString, Object[] l_queryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProfitLossSpecList(WEB3GentradeMainAccount, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌ڋq�������������ɒǉ�����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? ");
        l_sbQueryString.append(l_strQueryString);
        
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisArrayList.add(l_mainAccount.getBranch().getBranchCode());
        l_lisArrayList.add(l_mainAccount.getAccountCode());
        for (int i = 0; i < l_queryDataContainer.length; i++)
        {
            l_lisArrayList.add(l_queryDataContainer[i]);
        }
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        List l_lisReslut = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReslut = l_queryProcessor.doFindAllQuery(
                    ProfitLossSpecRow.TYPE, l_sbQueryString.toString(), l_strSortCond, null, l_objArray);
                if (l_lisReslut == null || l_lisReslut.size() == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                 }
        }
        catch (DataFindException l_ex)
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

        //�R�j�Q�j�̌��ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisReslut;
    }

    /**
     * (get���v���׈ꗗ)<BR>
     * (get���v���׈ꗗ�̃I�[�o�[���[�h)<BR>
     * �����̌ڋq�A�\�����ԁA���R�[�h�敪�A<BR>
     * ��ƔN�����ɊY�����鑹�v���׈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@���R�[�h�敪 = (�p�����[�^.�擾�Ώۃ��R�[�h�敪�ɂ��)<BR>
     * <BR>
     * �@@�P�|�P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �@@�P�|�Q�j��L������������Ɍ�������������A�p�����[�^�Z�b�g(ArrayList)<BR>
     * �@@�@@�@@�@@�@@���쐬����B<BR>
     * <BR>
     * �@@�@@�@@�p�����[�^.�擾�Ώۃ��R�[�h�敪���A<BR>
     * �@@�@@�@@["0�F�c�����R�[�h"�̏ꍇ]<BR>
     * �@@�@@�@@�@@���������������� = " and rec_div = ? "<BR>
     * �@@�@@�@@�@@����������ArrayList��"10�F�c�����R�[�h"���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@["1�F���׃��R�[�h"�̏ꍇ]<BR>
     * �@@�@@�@@�@@���������������� = " and rec_div = ? "<BR>
     * �@@�@@�@@�@@����������ArrayList��"20�F���׃��R�[�h"���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@["2�F���o�����R�[�h"�̏ꍇ]<BR>
     * �@@�@@�@@�@@���������������� = " and rec_div = ? "<BR>
     * �@@�@@�@@�@@����������ArrayList��"21�F���o�����R�[�h"���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@["3�F����&���o�����R�[�h"�̏ꍇ]<BR>
     * �@@�@@�@@�@@���������������� = " and rec_div in (?, ?) "<BR>
     * �@@�@@�@@�@@����������ArrayList�Ɉȉ��̒l���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�E"20�F���׃��R�[�h"<BR>
     * �@@�@@�@@�@@�@@�@@�E"21�F���o�����R�[�h"<BR>
     * <BR>
     * �@@�P�|�R�j�p�����[�^.��ƔN���� != null�̏ꍇ�A�ȉ��̏�����<BR>
     * �@@�@@�@@�@@���������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������������� += " and to_char(delivery_date, YYYY) = ?"<BR>
     * �@@�@@�@@�@@��������ArrayList��this.get�����N�x()���\�b�h�̖߂�l��ǉ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[get�����N�x()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@����F�@@�p�����[�^.��ƔN����<BR>
     * <BR>
     * �@@�P�|�S�j�p�����[�^.�\�����Ԃ̒l�ɂ��A�ȉ��̏�����<BR>
     * �@@�@@�@@�@@���������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�p�����[�^.�\�����Ԃ��A<BR>
     * �@@�@@�@@�@@�@@["0�F�O�������ȍ~"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date "<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["1�F1������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date > add_months(work_date, -1) "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and calc_date <= work_date "<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["2�F1�T�ԕ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date > work_date - 7"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and calc_date <= work_date "<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["3�F�O��1����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date = work_date "<BR>
     * <BR>
     * �Q�j�ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�|�v�Z�N�����@@����<BR>
     * �@@�|SORT-NO�@@����<BR>
     * <BR>
     * �@@�\�[�g���� = "calc_date asc, sort_no asc "<BR>
     * <BR>
     * �R�jthis.get���v���׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get���v���׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�\�[�g�����F�@@�쐬�����\�[�g����<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �S�j�R�j�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strGetObjectRecDiv - (�擾�Ώۃ��R�[�h�敪)<BR>
     * �擾�Ώۃ��R�[�h�敪<BR>
     * <BR>
     * 0�F�@@�c�����R�[�h<BR>
     * 1�F�@@���׃��R�[�h<BR>
     * 2�F�@@���o�����R�[�h<BR>
     * 3�F�@@����&���o�����R�[�h<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@param l_datWorkDate - (��ƔN����)<BR>
     * �c�����R�[�h�̍�ƔN����<BR>
     * @@return List
     * @@roseuid 417DCF6900E7
     */
    public List getProfitLossSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strGetObjectRecDiv, String l_strDisplayTerm, Date l_datWorkDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProfitLossSpecList(WEB3GentradeMainAccount, String, String, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A���������������ArrayList(���������f�[�^�R���e�i)���쐬����B
        //�P�|�P�jArrayList�𐶐�����B
        List l_lisArrayList = new ArrayList();
        StringBuffer l_sbQueryString = new StringBuffer();
        //�P�|�Q�j��L������������Ɍ�������������A�p�����[�^�Z�b�g(ArrayList)
        if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.BALANCE_REC))
        {
            l_sbQueryString.append(" and rec_div = ? ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
        }
        else if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.DETAIL_REC))
        {
            l_sbQueryString.append(" and rec_div = ? ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
        }
        else if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.PAYMENT_REC))
        {
            l_sbQueryString.append(" and rec_div = ? ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
        }
        else if (l_strGetObjectRecDiv.equals(WEB3PlsBvsDetailOrderRecDef.DETAIL_ORDER_REC))
        {
            l_sbQueryString.append(" and rec_div in (?, ?) ");
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
            l_lisArrayList.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
        }

        //�P�|�R�j�p�����[�^.��ƔN���� != null�̏ꍇ�A�ȉ��̏��������������ɒǉ�����B
        if (l_datWorkDate != null)
        {
            l_sbQueryString.append(" and to_char(delivery_date, 'YYYY') = ?");
            l_lisArrayList.add(this.getCurrentYear(l_datWorkDate));
        }

        //�P�|�S�j�p�����[�^.�\�����Ԃ̒l�ɂ��A�ȉ��̏��������������ɒǉ�����B
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
        {
            l_sbQueryString.append("and calc_date <= work_date ");
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
        {
            l_sbQueryString.append("and calc_date > add_months(work_date, ?) ");
            l_sbQueryString.append("and calc_date <= work_date ");
            l_lisArrayList.add(new Integer(-1));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
        {
            l_sbQueryString.append("and calc_date > work_date - ?");
            l_sbQueryString.append("and calc_date <= work_date ");
            l_lisArrayList.add(new Integer(7));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
        {
            l_sbQueryString.append("and calc_date = work_date ");
        }
        //�\�[�g���� = "calc_date asc, sort_no asc "
        String l_strSortCond = "calc_date asc, sort_no asc ";

        //�R�jthis.get���v���׈ꗗ()���\�b�h���R�[������B
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        
        List l_lisgetProfitLossSpecList =
                this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, l_strSortCond);

        if (l_lisgetProfitLossSpecList == null || l_lisgetProfitLossSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�S�j�R�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisgetProfitLossSpecList;
    }

    /**
     * (get�c�����R�[�hfrom���v����)<BR>
     * �ڋq�ɊY�����鑹�v���ׂ̎c�����R�[�h���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@���R�[�h�敪 = "10�F�c�����R�[�h"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = " and rec_div = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B <BR>
     * �@@�@@�@@�E"10�F�c�����R�[�h"<BR>
     * <BR>
     * �@@�P�|�R�jthis.get���v���׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get���v���׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@�@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�\�[�g�����F�@@null<BR>
     * <BR>
     * �@@�@@�@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �@@�P�|�S�j�P�|�R�j�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return ���v����Params
     * @@roseuid 417DCF6900F7
     */
    public ProfitLossSpecParams getBalanceRecordFromProfitLoss(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = " getBalanceRecordFromProfitLoss(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A���������������
        //ArrayList(���������f�[�^�R���e�i)���쐬����B
        //�P�|�P�j��L������������ɁA����������������쐬����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");

        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(WEB3ProfitLossRecordDivDef.BALANCE_REC);
        Object[] l_objArray = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objArray);
        
        //�P�|�R�jthis.get���v���׈ꗗ()���\�b�h���R�[������B
        List l_lisProfitLossSpecList =
                (this.getProfitLossSpecList(l_mainAccount, l_sbQueryString.toString(), l_objArray, null));

        if (l_lisProfitLossSpecList == null || l_lisProfitLossSpecList.size() == 0)
        {
            return null;
        }
                
        ProfitLossSpecParams l_profitLossSpecParams = (ProfitLossSpecParams)(l_lisProfitLossSpecList.get(0));
        
        log.exiting(STR_METHOD_NAME);
        return l_profitLossSpecParams;
    }

    /**
     * (get�����N�x)<BR>
     * �����̔N�x(yyyy)��ԋp����B<BR>
     * <BR>
     * �����̊���̗��c�Ɠ���12���̏ꍇ�A<BR>
     * �����̊���̔N��ԋp����B<BR>
     * �ȊO�́A����̗��c�Ɠ��̔N��ԋp����B<BR>
     * <BR>
     * �P�jthis.is�P�Q��()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[is�P�Q��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@����F�@@�p�����[�^.���<BR>
     * <BR>
     * �R�j�P�j�̖߂�l��true�ł���΁A�p�����[�^.��ƔN������<BR>
     * �@@�@@������ϊ�(�t�H�[�}�b�g�Fyyyy)���ĕԋp����B<BR>
     * �@@�ȊO�Athis.get���c�Ɠ�(�p�����[�^.���)�̖߂�l��<BR>
     * �@@������ϊ�(�t�H�[�}�b�g�Fyyyy)���ĕԋp����B<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@return String
     * @@roseuid 417DCF6900F9
     */
    public String getCurrentYear(Date l_datBaseDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCurrentYear(Date l_datBaseDate)";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.is�P�Q��()���\�b�h���R�[������B
        boolean l_bln = this.isDecember(l_datBaseDate);

        //�R�j�P�j�̖߂�l��true�ł���΁A�p�����[�^.��ƔN������
        // ������ϊ�(�t�H�[�}�b�g�Fyyyy)���ĕԋp����B
        if (l_bln)
        {
            String l_strBaseDate = WEB3DateUtility.formatDate(l_datBaseDate, "yyyy");
            return l_strBaseDate;
        }
        
        //this.get���c�Ɠ�(�p�����[�^.���)�̖߂�l��
        //������ϊ�(�t�H�[�}�b�g�Fyyyy)���ĕԋp����B
        Date l_datNextBizDate = this.getNextBizDate(l_datBaseDate);
        String l_strNextBizDate = WEB3DateUtility.formatDate(l_datNextBizDate, "yyyy");

        log.exiting(STR_METHOD_NAME);
        return l_strNextBizDate;
    }

    /**
     * (get���c�Ɠ�)<BR>
     * �����̊���̗��c�Ɠ����擾����B<BR>
     * <BR>
     * �P�j�c�Ɠ��v�Z�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@����F�@@�p�����[�^.����@@��Timestamp�^�ɕϊ�����B<BR>
     * <BR>
     * �Q�j�c�Ɠ��v�Z.roll()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[roll()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@���Z�^���Z�����F�@@1(���c�Ɠ�)<BR>
     * <BR>
     * �R�j�Q�j�̖߂�l��ԋp����B<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@return Date
     * @@roseuid 417DCF690107
     */
    public Date getNextBizDate(Date l_datBaseDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNextBizDate(Date l_datBaseDate) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�c�Ɠ��v�Z�C���X�^���X�𐶐�����B
        Timestamp l_tsBaseDate = new Timestamp(l_datBaseDate.getTime());
        log.info("l_tsBaseDate = " + l_tsBaseDate );

        //�Q�j�c�Ɠ��v�Z.roll()���\�b�h���R�[������B
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsBaseDate);
        Date l_datNextBizDate = l_bizDate.roll(1);
        log.info("l_datNextBizDate = " + l_datNextBizDate );

        //�R�j�Q�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_datNextBizDate;
    }
    /**
     * (is�P�Q��)<BR>
     * �����̊���̗��c�Ɠ��̌���12���ł��邩���ʂ���B<BR>
     * <BR>
     * 12���ł���ꍇ�́Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �P�jthis.get���c�Ɠ�()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get���c�Ɠ�()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@����F�@@�p�����[�^.���<BR>
     * <BR>
     * �R�j�P�j�̖߂�l�̌���12���ł���΁Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@return boolean
     * @@roseuid 417DCF690116
     */
    public boolean isDecember(Date l_datBaseDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isDecember(Date l_datBaseDate) ";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.get���c�Ɠ�()���\�b�h���R�[������B
        Date l_datNextBizDate = this.getNextBizDate(l_datBaseDate);

        //�R�j�P�j�̖߂�l�̌���12���ł���΁Atrue��ԋp����B
        String l_strMonth = WEB3DateUtility.formatDate(l_datNextBizDate, "MM");

        if (l_strMonth.equals("12"))
        {
            return true;
        }
        //false��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�뉿�P�����׌���)<BR>
     * �ڋq�ɊY������뉿�P�����׃f�[�^�̌�����ԋp����B<BR>
     * <BR>
     * �P�j��������������&���������f�[�^�R���e�i <BR>
     * �@@�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�ڋq.�،���ЃR�[�h�@@���� <BR>
     * �@@�@@���X�R�[�h = �p�����[�^.�ڋq.���X�R�[�h�@@����<BR>
     * �@@�@@�ڋq�R�[�h = �p�����[�^.�ڋq.�����R�[�h�@@����<BR>
     * �@@�@@(<BR>
     * �@@�@@���R�[�h�敪 = "1�F�c�����R�[�h"�@@�܂���<BR>
     * �@@�@@�@@(���R�[�h�敪 = "2�F���׃��R�[�h"�@@����<BR>
     * �@@�@@�@@�@@�v�Z�N���� <= ��ƔN����)<BR>
     * �@@�@@)�@@����<BR>
     * �@@�@@���i�R�[�h = �p�����[�^.���i�R�[�h�@@����<BR>
     * �@@�@@�����R�[�h = �p�����[�^.�����R�[�h�@@����<BR>
     * �@@�@@���l != "1�F�����"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = "institution_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(rec_div = ? or " <BR>
     *                             + "(rec_div = ? and calc_date <= work_date)) and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "commodity_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "product_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "remark != ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B <BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�ڋq.�����R�[�h<BR>
     * �@@�@@�@@�E"1�F�c�����R�[�h"<BR>
     * �@@�@@�@@�E"2�F���׃��R�[�h"<BR>
     * �@@�@@�@@�E�p�����[�^.���i�R�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�E"1�F�����"<BR>
     * <BR>
     * �Q�jQueryProcessor.doGetCountQuery()�ɂāA�뉿�P�����׌������擾����B <BR>
     * <BR>
     * �@@[doGetCountQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@arg0�F�@@"�뉿�P�����׃e�[�u��(book_value_spec)" <BR>
     * �@@arg1�F�@@�쐬������������������<BR>
     * �@@arg2�F�@@�쐬����ArrayList.toArray() <BR>
     * <BR>
     * �R�j�擾����������ԋp����B <BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51:�@@���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return int
     * @@roseuid 417DCF690126
     */
    public int getBookValueSpecCount(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecCount(WEB3GentradeMainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j��������������&���������f�[�^�R���e�i
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? and ");
        l_sbQueryString.append("(rec_div = ? or ");
        l_sbQueryString.append("(rec_div = ? and calc_date <= work_date)) and ");
        l_sbQueryString.append("commodity_code = ? and ");
        l_sbQueryString.append("product_code = ? and ");
        l_sbQueryString.append("remark != ? ");

        //"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.BALANCE_REC);
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.DETAIL_REC);
        l_lisQueryVars.add(l_strCommodityCode);
        l_lisQueryVars.add(l_strProductCode);
        l_lisQueryVars.add(WEB3BookValueRemarkDef.CANCEL);

        //�Q�jQueryProcessor.doGetCountQuery()�ɂāA�뉿�P�����׌������擾����B
        int l_intReturnRecordCnt = 0;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);
            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(BookValueSpecRow.TYPE, l_sbQueryString.toString(), l_objVars);
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

        //�R�j�擾����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_intReturnRecordCnt;
    }

    /**
     * (get�ŏI�v�Z�N����from�뉿�P������)<BR>
     * �w�肳�ꂽ�\�����Ԃ̕\�����ԑO�ŏI�v�Z����ԋp����B<BR>
     * (�뉿�P�����חp)<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@���R�[�h�敪 = "2�F���׃��R�[�h"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = " and rec_div = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B <BR>
     * �@@�@@�@@�E"2�F���׃��R�[�h"<BR>
     * <BR>
     * �@@�P�|�R�j�p�����[�^.�\�����Ԃ̒l�ɂ��A�ȉ��̏�����<BR>
     * �@@�@@�@@�@@���������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�p�����[�^.�\�����Ԃ��A<BR>
     * �@@�@@�@@�@@�@@["0�F�O�������ȍ~"]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= last_day(add_months(work_date, -2))"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["1�F1������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= add_months(work_date, -1)"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["2�F1�T�ԕ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date - 7"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@["3�F�O��1����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date - 1"<BR>
     * <BR>
     * �@@�P�|�S�j�ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�@@�@@�@@�E�v�Z�N���� �~��<BR>
     * <BR>
     * �@@�@@�@@�@@�\�[�g���� = " calc_date desc"<BR>
     * <BR>
     * �@@�P�|�T�jthis.get�뉿�P�����׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�뉿�P�����׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@�@@�@@���i�R�[�h�F�@@�p�����[�^.���i�R�[�h<BR>
     * �@@�@@�@@�@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�\�[�g�����F�@@�쐬�����\�[�g����<BR>
     * <BR>
     * �@@�@@�@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �@@�P�|�U�j��������(���v����Params��List)��0�Ԗڂ̗v�f.�v�Z�N����<BR>
     * �@@�@@�@@�@@�@@�̒l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51:�@@���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@return Date
     * @@roseuid 417DCF690136
     */
    public Date getFinalCalcDateFromBookValueSpec(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecCount(WEB3GentradeMainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A���������������ArrayList(���������f�[�^�R���e�i)���쐬����B
        //�P�|�P�j����������������쐬����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");
        
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.DETAIL_REC);

        
        //�P�|�R�j�p�����[�^.�\�����Ԃ̒l�ɂ��A�ȉ��̏��������������ɒǉ�����B
        if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
        {
            l_sbQueryString.append("and calc_date <= last_day(add_months(work_date, ?))");
            l_lisQueryVars.add(new Integer(-2));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
        {
            l_sbQueryString.append("and calc_date <= add_months(work_date, ?)");
            l_lisQueryVars.add(new Integer(-1));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisQueryVars.add(new Integer(7));
        }
        else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
        {
            l_sbQueryString.append("and calc_date <= work_date - ?");
            l_lisQueryVars.add(new Integer(1));
        }

        //�P�|�S�j�ȉ��̃\�[�g�������쐬����B
        String l_strSortCond = " calc_date desc";
        Object[] l_objVars = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objVars);
        //�P�|�T�jthis.get�뉿�P�����׈ꗗ()���\�b�h���R�[������B
        List l_lisgetBookValueSpecList =
                this.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_sbQueryString.toString(), l_objVars, l_strSortCond);

        if (l_lisgetBookValueSpecList == null || l_lisgetBookValueSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�P�|�U�j��������(���v����Params��List)��0�Ԗڂ̗v�f.�v�Z�N�����̒l��ԋp����B
        Date l_datResult = (Timestamp)(((BookValueSpecParams)(l_lisgetBookValueSpecList.get(0))).getCalcDate());
        log.exiting(STR_METHOD_NAME);
        return l_datResult;
    }

    /**
     * (get�뉿�P�����׈ꗗ)<BR>
     * �����̌��������ɊY������뉿�P�����ׂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̌ڋq�������������ɒǉ�����B<BR>
     * �@@�E�،���ЃR�[�h<BR>
     * �@@�E���X�R�[�h<BR>
     * �@@�E�����R�[�h<BR>
     * �@@�E���i�R�[�h<BR>
     * �@@�E�����R�[�h<BR>
     * �@@�E���l<BR>
     * <BR>
     * �@@���ǉ��������������� =  "institution_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "commodity_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "product_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "remark != ? "<BR>
     * <BR>
     * �@@�@@�@@�쐬������������������ = �ǉ���������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �p�����[�^.��������������Ƃ���B<BR>
     * <BR>
     * �@@���ǉ����������f�[�^�R���e�i<BR>
     * �@@(ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɒǉ�����)<BR>
     * �@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�E�p�����[�^.�ڋq.���X�R�[�h<BR>
     * �@@�@@�E�p�����[�^.�ڋq.�����R�[�h<BR>
     * �@@�@@�E�p�����[�^.���i�R�[�h<BR>
     * �@@�@@�E�p�����[�^.�����R�[�h<BR>
     * �@@�@@�E"1�F����ς�"<BR>
     * �@@�@@�E�p�����[�^.���������f�[�^�R���e�i�̗v�f�S��(0�Ԗڂ��珇���ǉ�)<BR>
     * <BR>
     * �@@�@@�쐬�������������f�[�^�R���e�i = �ǉ����������f�[�^�R���e�i�Ƃ���B<BR>
     * <BR>
     * �Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuary()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�뉿�P�����׃e�[�u��(book_value_spec)"<BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�쐬�������������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌��ʂ�ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51:�@@���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_queryDataContainer - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@roseuid 417DCF690164
     */
    public List getBookValueSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strQueryString, Object[] l_queryDataContainer, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecList(WEB3GentradeMainAccount, String, String, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌ڋq�������������ɒǉ�����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? and ");
        l_sbQueryString.append("commodity_code = ? and ");
        l_sbQueryString.append("product_code = ? and ");
        l_sbQueryString.append("remark != ? ");
        l_sbQueryString.append(l_strQueryString);

        //�ǉ����������f�[�^�R���e�i
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        l_lisQueryVars.add(l_strCommodityCode);
        l_lisQueryVars.add(l_strProductCode);
        l_lisQueryVars.add(WEB3BookValueRemarkDef.CANCEL);
        for (int i = 0; i < l_queryDataContainer.length; i++)
        {
            l_lisQueryVars.add(l_queryDataContainer[i]);
        }
        Object[] l_objArray = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArray);
        //�Q�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������
        List l_lisRecords = null;
        try
        {

            //�P�jQueryProcessor.doFindAllQuery()���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                BookValueSpecRow.TYPE,
                l_sbQueryString.toString(),
                l_strSortCond,
                null,
                l_objArray
                );
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        }
        //�R�j�Q�j�̌��ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (get�c�����R�[�hfrom�뉿�P������)<BR>
     * �ڋq�ɊY������뉿�P�����ׂ̎c�����R�[�h���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@���R�[�h�敪 = "1�F�c�����R�[�h"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = " and rec_div = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B <BR>
     * �@@�@@�@@�E"1�F�c�����R�[�h"<BR>
     * <BR>
     * �@@�P�|�R�jthis.get�뉿�P�����׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�뉿�P�����׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@�@@�@@���i�R�[�h�F�@@�p�����[�^.���i�R�[�h<BR>
     * �@@�@@�@@�@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�\�[�g�����F�@@null<BR>
     * <BR>
     * �@@�@@�@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �@@�P�|�S�j�P�|�R�j�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51:�@@���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return List
     * @@roseuid 417DCF690174
     */
    public List getBalanceRecordFromBookValueSpec(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBalanceRecordFromBookValueSpec(WEB3GentradeMainAccount, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�P�|�P�j��L������������ɁA����������������쐬����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");

        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.BALANCE_REC);
        Object[] l_objArray = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArray);
        //�P�|�R�jthis.get�뉿�P�����׈ꗗ()���\�b�h���R�[������B
        List l_lisgetBookValueSpecList = 
            this.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_sbQueryString.toString(), l_objArray, null);
        if (l_lisgetBookValueSpecList == null || l_lisgetBookValueSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�P�|�S�j�P�|�R�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisgetBookValueSpecList;
    }

    /**
     * (get�뉿�P�����׈ꗗ)<BR>
     * (get�뉿�P�����׈ꗗ�̃I�[�o�[���[�h)<BR>
     * �����̌ڋq�A���i�R�[�h�A�����R�[�h�A�\�����ԁA<BR>
     * �ɊY������뉿�P�����׈ꗗ(���׃��R�[�h�̂�)���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A��������������� <BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@���R�[�h�敪 = "2�F���׃��R�[�h"<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B <BR>
     * <BR>
     * �@@�@@�������������� = " and rec_div = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B <BR>
     * �@@�@@�@@�E"2�F���׃��R�[�h"<BR>
     * <BR>
     * �@@�P�|�R�j�p�����[�^.�\�����ԁAis�\�����ԑO�̒l�ɂ��A�ȉ��̏�����<BR>
     * �@@�@@�@@�@@���������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�y�p�����[�^.is�\�����ԑO == true�̏ꍇ�z<BR>
     * �@@�@@�@@�@@(�\�����ԑO�̖��׃f�[�^���擾����B)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�p�����[�^.�\�����Ԃ��A<BR>
     * �@@�@@�@@�@@�@@�@@["0�F�O�������ȍ~"]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= last_day(add_months(work_date, -2))"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@["1�F1������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= add_months(work_date, -1)"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@["2�F1�T�ԕ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date - 7"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@["3�F�O��1����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date <= work_date - 1"<BR>
     * <BR>
     * �@@�@@�@@�@@�y��L�ȊO�̏ꍇ�z<BR>
     * �@@�@@�@@�@@(�\�����Ԉȍ~�̖��׃f�[�^���擾����B)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�p�����[�^.�\�����Ԃ��A<BR>
     * �@@�@@�@@�@@�@@�@@["0�F�O�������ȍ~"]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date > last_day(add_months(work_date, -2)) "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and calc_date <= work_date "<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@["1�F1������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date > add_months(work_date, -1)"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and calc_date <= work_date "<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@["2�F1�T�ԕ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date > work_date - 7"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and calc_date <= work_date "<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@["3�F�O��1����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������������� += "and calc_date = work_date "<BR>
     * <BR>
     * �Q�j�ȉ��̃\�[�g�������쐬����B<BR>
     * �@@�|�v�Z�N�����@@����<BR>
     * �@@�|SORT-NO�@@����<BR>
     * <BR>
     * �@@�\�[�g���� = "calc_date asc, sort_no asc "<BR>
     * <BR>
     * �R�jthis.get�뉿�P�����׈ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get�뉿�P�����׈ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@���i�R�[�h�F�@@�p�����[�^.���i�R�[�h<BR>
     * �@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()�̖߂�l<BR>
     * �@@�@@�\�[�g�����F�@@�쐬�����\�[�g����<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp���A�I������B<BR>
     * <BR>
     * �S�j�R�j�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10:�@@����<BR>
     * 11:�@@�M�p<BR>
     * 20:�@@���M<BR>
     * 21:�@@�O��<BR>
     * 22:�@@�ݓ�<BR>
     * 23:�@@MRF<BR>
     * 30:�@@��<BR>
     * 40:�@@�O��<BR>
     * 50:�@@����<BR>
     * 51:�@@���w��OP<BR>
     * 52:�@@��<BR>
     * 53:�@@��OP<BR>
     * 54:�@@�XOP<BR>
     * 55:�@@�O��<BR>
     * 56:�@@�O��OP<BR>
     * 57:�@@��OP<BR>
     * 60:�@@�O��<BR>
     * 70:�@@��<BR>
     * 71:�@@��GP<BR>
     * 80:�@@����<BR>
     * 91:�@@CD<BR>
     * 92:�@@CP<BR>
     * 93:�@@BA<BR>
     * 99:�@@���K<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strDisplayTerm - (�\������)<BR>
     * �\������<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     * @@param l_isBeforeDisplayTerm - (is�\�����ԑO)<BR>
     * �\�����ԑO�̃f�[�^���擾���邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�\�����Ԉȍ~�̃f�[�^���擾<BR>
     * true�F�@@�\�����ԑO�̃f�[�^���擾<BR>
     * @@return List
     * @@roseuid 417DCF690184
     */
    public List getBookValueSpecList(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strDisplayTerm, boolean l_isBeforeDisplayTerm) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBookValueSpecList(WEB3GentradeMainAccount, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A���������������ArrayList(���������f�[�^�R���e�i)���쐬����B
        //�P�|�P�j��L������������ɁA����������������쐬����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" and rec_div = ? ");
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B <BR>
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(WEB3BookValueRecordDivDef.DETAIL_REC);

        //�P�|�R�j�p�����[�^.�\�����ԁAis�\�����ԑO�̒l�ɂ��A�ȉ��̏��������������ɒǉ�����B
        if (l_isBeforeDisplayTerm)
        {
            if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
            {
                l_sbQueryString.append("and calc_date <= last_day(add_months(work_date, ?))");
                l_lisQueryVars.add(new Integer(-2));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
            {
                l_sbQueryString.append("and calc_date <= add_months(work_date, ?)");
                l_lisQueryVars.add(new Integer(-1));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
            {
                l_sbQueryString.append("and calc_date <= work_date - ?");
                l_lisQueryVars.add(new Integer(7));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
            {
                l_sbQueryString.append("and calc_date <= work_date - ?");
                l_lisQueryVars.add(new Integer(1));
            }
        }
        else
        {
            if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.DEFAULT))
            {
                l_sbQueryString.append("and calc_date > last_day(add_months(work_date, ?)) ");
                l_sbQueryString.append(" and calc_date <= work_date ");
                l_lisQueryVars.add(new Integer(-2));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_MONTH))
            {
                l_sbQueryString.append("and calc_date > add_months(work_date, ?)");
                l_sbQueryString.append(" and calc_date <= work_date ");
                l_lisQueryVars.add(new Integer(-1));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.ONE_WEEK))
            {
                l_sbQueryString.append("and calc_date > work_date - ?");
                l_sbQueryString.append(" and calc_date <= work_date ");
                l_lisQueryVars.add(new Integer(7));
            }
            else if (l_strDisplayTerm.equals(WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY))
            {
                l_sbQueryString.append("and calc_date = work_date ");

            }
        }
        //�Q�j�ȉ��̃\�[�g�������쐬����B
        String l_strSortCond = "calc_date asc, sort_no asc ";
        Object[] l_objArray = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArray);
        //�R�jthis.get�뉿�P�����׈ꗗ()���\�b�h���R�[������B
        List l_lisgetBookValueSpecList =
                this.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_sbQueryString.toString(), l_objArray, l_strSortCond);
        if (l_lisgetBookValueSpecList == null || l_lisgetBookValueSpecList.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�S�j�R�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisgetBookValueSpecList;
    }
    
    /**
     * (get�v�Z�N����)<BR>
     *�����̌��������ɊY������u���v���׃��R�[�h�v����u�v�Z�N�����v��ԋp����B <BR>
     *<BR>
     *�P�j �ȉ��̌ڋq�������������ɒǉ�����B <BR>
     *�@@�@@�@@�E�،���ЃR�[�h <BR>
     *�@@�@@�@@�E���X�R�[�h <BR>
     *�@@�@@�@@�E�����R�[�h <BR>
     *<BR>
     *�@@���ǉ��������������� =  "institution_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "branch_code = ? and "  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "account_code = ? " <BR>
     *<BR>
     *�@@���쐬������������������ = �ǉ��������������� + �p�����[�^.��������������Ƃ���B <BR>
     *<BR>
     *�@@���ǉ����������f�[�^�R���e�i <BR>
     *�@@�@@�@@(ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɒǉ�����) <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.�،���ЃR�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.���X�R�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.�ڋq.�����R�[�h <BR>
     *�@@�@@�@@�@@�E�p�����[�^.���������f�[�^�R���e�i�̗v�f�S��(0�Ԗڂ��珇���ǉ�) <BR>
     *<BR>
     *�@@���쐬�������������f�[�^�R���e�i = �ǉ����������f�[�^�R���e�i�Ƃ���B <BR>
     *<BR>
     *�Q�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     *<BR>
     *�@@[doFindAllQuary()�ɃZ�b�g����p�����[�^] <BR>
     *�@@�@@arg0�F�@@"���v���׃e�[�u��(profit_loss_spec)" <BR>
     *�@@�@@arg1�F�@@�쐬������������������ <BR>
     *�@@�@@arg2�F�@@�p�����[�^.�\�[�g���� <BR>
     *�@@�@@arg3�F�@@null <BR>
     *�@@�@@arg4�F�@@�쐬�������������f�[�^�R���e�i <BR>
     *�@@�@@arg5�F�@@1 <BR>
     *�@@�@@arg6�F�@@0 <BR>
     *<BR>
     *�R�j �Q�j�̖߂�lListPage���u�v�Z�N�����v���擾���ԋp����B  <BR>
     *�@@��ListPage�̒���==0 OR ListPage==null�̏ꍇ�A null��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getCalcDate(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strQueryString, 
        Object[] l_objQueryDataContainers, 
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCalcDate(WEB3GentradeMainAccount, String, Object[], String)";
        log.entering(STR_METHOD_NAME);
        //�P�j�ȉ��̌ڋq�������������ɒǉ�����B
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("institution_code = ? and ");
        l_sbQueryString.append("branch_code = ? and ");
        l_sbQueryString.append("account_code = ? ");
        l_sbQueryString.append(l_strQueryString);
        
        //�ǉ����������f�[�^�R���e�i
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        int l_intLength = l_objQueryDataContainers.length;
        for (int i = 0; i < l_intLength; i++)
        {
            l_lisQueryVars.add(l_objQueryDataContainers[i]);
        }
        
        //�Q�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        Object[] l_objArrays = new Object[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_objArrays);
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ProfitLossSpecRow.TYPE,
                l_sbQueryString.toString(),
                l_strSortCond,
                null,
                l_objArrays,
                1,
                0);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�R�j �Q�j�̖߂�lListPage���u�v�Z�N�����v���擾���ԋp����B  
        //�@@��ListPage�̒���==0 OR ListPage==null�̏ꍇ�A null��ԋp����B
        Date l_datCalcDate = null;
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            ProfitLossSpecRow l_profitLossSpecRow = (ProfitLossSpecRow)l_lisRecords.get(0);
            l_datCalcDate = l_profitLossSpecRow.getCalcDate();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_datCalcDate;
    }
    
}
@
