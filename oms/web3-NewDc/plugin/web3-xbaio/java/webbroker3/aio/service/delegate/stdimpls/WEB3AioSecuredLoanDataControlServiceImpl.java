head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanDataControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���f�[�^����T�[�r�XImpl(WEB3AioSecuredLoanDataControlServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �đo�g (���u) �V�K�쐬 �d�l�ύX ���f��No.755�ANo.761�ANo.776
Revision History : 2007/09/20 �đo�g (���u) ���f��No.786�ANo.787�ANo.788�ANo.795�ANo.796
Revision History : 2007/10/18 �đo�g (���u) ���f��No.809
Revision History : 2007/10/25 �đo�g (���u) ���f��No.815
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.aio.define.WEB3AioSecuredLoanOfferStateSortKeyDef;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.gentrade.data.CommSerialNumbersPK;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��S�ۃ��[���f�[�^����T�[�r�XImpl)<BR>
 * �،��S�ۃ��[���f�[�^����T�[�r�XImpl<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AioSecuredLoanDataControlServiceImpl implements WEB3AioSecuredLoanDataControlService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanDataControlServiceImpl.class);

    /**
     * @@roseuid 46E0BE470242
     */
    public WEB3AioSecuredLoanDataControlServiceImpl()
    {

    }

    /**
     * (insert�����S�ۃ��[��)<BR>
     * �����S�ۃ��[�������e�[�u����insert�������s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�،��S�ۃ��[��_�����S�ۃ��[�������e�[�u��.xls�v<BR>
     * <BR>
     * @@param l_strStockLoanAccountCode - (�X�g�b�N���[�������ԍ�)<BR>
     * �X�g�b�N���[�������ԍ�<BR>
     * @@param l_mainAccountParams - (�ڋqParams)<BR>
     * �ڋqParams<BR>
     * @@throws WEB3BaseException
     */
    public void insertStockSecuredLoan(String l_strStockLoanAccountCode,
        MainAccountParams l_mainAccountParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertStockBondSecurityLoan(String, MainAccountParams)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccountParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �����S�ۃ��[�������e�[�u����insert�������s���B
        // �}������s�̓��e�͉��L���Q�ƁB
        // �u�،��S�ۃ��[��_�����S�ۃ��[�������e�[�u��.xls�v
        StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();

        // �X�g�b�N���[�������ԍ�:  ����.�X�g�b�N���[�������ԍ�
        l_stockSecuredLoanParams.setStockLoanAccountCode(l_strStockLoanAccountCode);

        // ����ID:  get�ڋq�s.����ID
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());

        // �،���ЃR�[�h:  get�ڋq�s.�،���ЃR�[�h
        l_stockSecuredLoanParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());

        // ���X�R�[�h:  get�ڋq�s.���X�R�[�h
        l_stockSecuredLoanParams.setBranchCode(l_mainAccountParams.getBranchCode());

        // �����R�[�h:  get�ڋq�s.�����R�[�h
        l_stockSecuredLoanParams.setAccountCode(l_mainAccountParams.getAccountCode());

        // �J�ݏ�:  0�F�\��
        l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.REQUEST);

        // �\������:  ���ݓ���
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_stockSecuredLoanParams.setAppliDate(l_tsSystemTime);

        // �\����Y�q���:  get�ڋq�s.�x�q�敪
        l_stockSecuredLoanParams.setYCustomerData(l_mainAccountParams.getYellowCustomer());

        // �\�������b�N�q���i�l�����b�N�j:  get�ڋq�s.�l�����b�N
        l_stockSecuredLoanParams.setExaminLockFlag(l_mainAccountParams.getExaminLockFlag());

        // �\�������b�N�q���i�x�X���b�N�j:  get�ڋq�s.�x�X���b�N
        l_stockSecuredLoanParams.setBranchLock(l_mainAccountParams.getBranchLock());

        // �\�������b�N�q���i�Ǘ����b�N�j:  get�ڋq�s.�Ǘ����b�N
        l_stockSecuredLoanParams.setMngLockFlag(l_mainAccountParams.getMngLockFlag());

        // �\�������b�N�q���i�Ǘ����b�N���R�E���֋��j
        // get�ڋq�s.�Ǘ����b�N���R�t���O�i���֋��j
        l_stockSecuredLoanParams.setMngLockFlagAdvance(l_mainAccountParams.getMngLockFlagAdvance());

        // �\�������b�N�q���i�Ǘ����b�N���R�E�ۏ؋������j
        // get�ڋq�s.�Ǘ����b�N���R�t���O�i�ۏ؋������j
        l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(l_mainAccountParams.getMngLockFlagUnpayMargin());

        // �\�������b�N�q���i�Ǘ����b�N���R�E�K�i�S�ەs���j
        // get�ڋq�s.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j
        l_stockSecuredLoanParams.setMngLockFlagShortSecurity(l_mainAccountParams.getMngLockFlagShortSecurity());

        // �\�������b�N�q���i�Ǘ����b�N���R�E�a��ؒ����������j
        // get�ڋq�s.�Ǘ����b�N���R�t���O�i�a��ؒ����������j
        l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(l_mainAccountParams.getMngLockFlagUnsubstitDepo());

        // �X�V�҃R�[�h:  �Z�b�V�������擾�������O�C��ID
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        long l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();
        l_stockSecuredLoanParams.setLastUpdater(l_lngLoginId + "");

        // �쐬����:  ���ݓ���
        l_stockSecuredLoanParams.setCreatedTimestamp(l_tsSystemTime);

        // �X�V����:  ���ݓ���
        l_stockSecuredLoanParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����S�ۃ��[���ڋq���)<BR>
     * �����S�ۃ��[�������I�u�W�F�N�g��ԋp����B<BR>
     * �����S�ۃ��[�������e�[�u�����f�[�^���擾����B<BR>
     * <BR>
     * �P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ�<BR>
     * Object[0]�i�����j����ID<BR>
     * Object[1]�i�����j�\������<BR>
     * <BR>
     * �Q�j�@@�����S�ۃ��[�������e�[�u���Ƀ��R�[�h�����݂��邩��������B<BR>
     * QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * arg0�F �����S�ۃ��[�������e�[�u��RowType<BR>
     * arg1�F "account_id=?<BR>
     * and to_char(appli_date, 'YYYYMMDD')=?"<BR>
     * <BR>
     * �R�j�@@�Q�j�̖߂�lList�̒��� > 0 �̏ꍇ�A�Q�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * �S�j�@@�Q�j�̖߂�lList�̒��� = 0 �̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_tsDate - (���t)<BR>
     * YYYYMMDD<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getStockSecuredLoanAccInfo(long l_lngAccountId, Timestamp l_tsDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStockBondSecurityLoanAccInfo(long, Timestamp)";
        log.entering(STR_METHOD_NAME);

        // �����S�ۃ��[�������I�u�W�F�N�g��ԋp����B
        // �P�j �����S�ۃ��[�������e�[�u�����f�[�^���擾����B
        // �P�j Object�z��𐶐����A�ȉ���v�f�ɐݒ�
        Object[] l_values = new Object[]{
            new Long(
                l_lngAccountId),
                WEB3DateUtility.formatDate(l_tsDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD)};
        String l_strSql = " account_id=? and to_char(appli_date, 'YYYYMMDD') = ? ";

        List l_lisRecords = new ArrayList(); 
        // �Q�j �����S�ۃ��[�������e�[�u���Ƀ��R�[�h�����݂��邩��������B
        // QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                StockSecuredLoanRow.TYPE,
                l_strSql,
                l_values);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �R�j �Q�j�̖߂�lList�̒��� > 0 �̏ꍇ�A�Q�j�̖߂�l��ԋp����B
        int l_intLength = l_lisRecords.size();
        if (l_intLength > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisRecords;
        }
        // �S�j �Q�j�̖߂�lList�̒��� = 0 �̏ꍇ�Anull��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get�����S�ۃ��[���ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v���銔���S�ۃ��[�������e�[�u�����������A<BR>
     * ���̌��ʂ������S�ۃ��[��Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR>
     * <BR>
     * 1) �\�[�g�����̍쐬<BR>
     * �@@����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ�A<BR>
     * �@@����.�\�[�g�����̌������A�ȉ����J��Ԃ��B<BR>
     *  1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@�@@�E�ڋq�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@=�����S�ۃ��[�������e�[�u��.�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@�E�X�g�b�N���[�������ԍ��@@=�����S�ۃ��[�������e�[�u��.�X�g�b�N���[�������ԍ�<BR>
     * �@@�@@�@@�@@�E�\�����@@�@@�@@�@@�@@�@@�@@�@@�@@=�����S�ۃ��[�������e�[�u��.�\����<BR>
     * <BR>
     * �@@�@@�������^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B<BR>
     * <BR>
     * 2) �ȉ��̌��������ŁA�u�����S�ۃ��[�������e�[�u���v����������B<BR>
     * �@@[��������] <BR>
     * �@@�@@���،���ЃR�[�h=����.�،���ЃR�[�h <BR>
     * �@@�@@�����X�R�[�h=����.���X�R�[�h<BR>
     * �@@�@@�@@�@@---------�i�������A����.���X�R�[�h��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@�������R�[�h like ����.�ڋq�R�[�h + "%" <BR>
     * �@@�@@�@@�@@---------�i�������A����.�ڋq�R�[�h��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���X�g�b�N���[�������ԍ� like ����.�X�g�b�N���[�������ԍ� + "%"<BR>
     * �@@�@@�@@�i�O����v�������s���B�������A����.�X�g�b�N���[�������ԍ���null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���\����=����.�\����<BR>
     * �@@�@@�@@�@@---------�i�������A����.�\���󋵂�null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���\����<BR>
     * �@@�@@�@@*�\����From != null && �\����To == null�ꍇ<BR>
     * �@@�@@�@@ �\����(YYYYMMDD) >= ����.�\����From<BR>
     * �@@�@@�@@*�\����From == null && �\����To != null�ꍇ<BR>
     * �@@�@@�@@ �\����(YYYYMMDD) <= ����.�\����To<BR>
     * �@@�@@�@@*�\����From != null && �\����To != null�ꍇ<BR>
     * �@@�@@�@@ �\����(YYYYMMDD) between ����.�\����From and ����.�\����To<BR>
     * <BR>
     * �@@�@@���J�ݓ�<BR>
     * �@@�@@�@@*�J�ݓ�From != null && �J�ݓ�To == null�ꍇ<BR>
     * �@@�@@�@@ �J�ݓ� >= ����.�J�ݓ�From<BR>
     * �@@�@@�@@*�J�ݓ�From == null && �J�ݓ�To != null�ꍇ<BR>
     * �@@�@@�@@ �J�ݓ� <= ����.�J�ݓ�To<BR>
     * �@@�@@�@@*�J�ݓ�From != null && �J�ݓ�To != null�ꍇ<BR>
     * �@@�@@�@@ �J�ݓ� between ����.�J�ݓ�From and ����.�J�ݓ�To<BR>
     * <BR>
     * �@@�@@�������<BR>
     * �@@�@@�@@*�����From != null && �����To == null�ꍇ<BR>
     * �@@�@@�@@ �����(YYYYMMDD) >= ����.�����From<BR>
     * �@@�@@�@@*�����From == null && �����To != null�ꍇ<BR>
     * �@@�@@�@@ �����(YYYYMMDD) <= ����.�����To<BR>
     * �@@�@@�@@*�����From != null && �����To != null�ꍇ<BR>
     * �@@�@@�@@ �����(YYYYMMDD) between ����.�����From and ����.�����To<BR>
     * <BR>
     * �@@�@@������<BR>
     * �@@�@@�@@*����From != null && ����To == null�ꍇ<BR>
     * �@@�@@�@@ ����(YYYYMMDD) >= ����.����From<BR>
     * �@@�@@�@@*����From == null && ����To != null�ꍇ<BR>
     * �@@�@@�@@ ����(YYYYMMDD) <= ����.����To<BR>
     * �@@�@@�@@*����From != null && ����To != null�ꍇ<BR>
     * �@@�@@�@@ ����(YYYYMMDD) between ����.����From and ����.����To<BR>
     * <BR>
     * �@@�@@������<BR>
     * �@@�@@�@@*����From != null && ����To == null�ꍇ<BR>
     * �@@�@@�@@ ���� >= ����.����From<BR>
     * �@@�@@�@@*����From == null && ����To != null�ꍇ<BR>
     * �@@�@@�@@ ���� <= ����.����To<BR>
     * �@@�@@�@@*����From != null && ����To != null�ꍇ<BR>
     * �@@�@@�@@ ���� between ����.����From and ����.����To<BR>
     * <BR>
     * �@@[���я�]<BR>
     * �@@�@@1)�Ő��������\�[�g����<BR>
     * <BR>
     * 3) 2)�̌������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_stockSecuredLoans - (�����S�ۃ��[���z��[])<BR>
     * �����S�ۃ��[���z��[]<BR>
     * @@return StockSecuredLoanParams[]
     * @@throws WEB3BaseException
     * @@roseuid 46CE708F01B3
     */
    public StockSecuredLoanParams[] getStockSecuredLoanList(Object[] l_stockSecuredLoans)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStockBondSecurityLoanListo(Object[])";
        log.entering(STR_METHOD_NAME);

        //�\�[�g�����̍쐬
        //����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ
        StringBuffer l_sbSortKey = new StringBuffer();
        if (l_stockSecuredLoans[15] != null
            && ((Object[])l_stockSecuredLoans[15]).length > 0)
        {
            Object[] l_queryValues = (Object[])l_stockSecuredLoans[15];
            for (int i = 0; i < l_queryValues.length; i++)
            {
                WEB3SLSortKey l_sortKey = (WEB3SLSortKey)l_queryValues[i];
                // �E�ڋq�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@=����.�ڋq�R�[�h
                if (WEB3AioSecuredLoanOfferStateSortKeyDef.ACCOUNT_CODE.equals(l_sortKey.keyItem))
                {
                    l_sbSortKey.append(" account_code ");
                }
                // �E�X�g�b�N���[�������ԍ��@@=����.�X�g�b�N���[�������ԍ�
                else if (WEB3AioSecuredLoanOfferStateSortKeyDef.STOCK_LOAN_ACCOUNT.equals(l_sortKey.keyItem))
                {
                    l_sbSortKey.append(" stock_loan_account_code ");
                }
                //�E�\�����@@�@@�@@�@@�@@�@@�@@�@@�@@=����.�\����
                else if (WEB3AioSecuredLoanOfferStateSortKeyDef.APPLY_DATE.equals(l_sortKey.keyItem))
                {
                    l_sbSortKey.append(" appli_date ");
                }
                else
                {
                    continue;
                }

                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_sbSortKey.append(" ASC ");
                }
                else
                {
                    l_sbSortKey.append(" DESC ");
                }

                if (i < l_queryValues.length - 1)
                {
                    l_sbSortKey.append(" , ");
                }
            }
        }

        //�ȉ��̌��������ŁA�u�����S�ۃ��[�������e�[�u���v����������B
        //[��������]
        StringBuffer l_sbSql = new StringBuffer();
        List l_lisValues = new ArrayList();

        //�@@�@@���،���ЃR�[�h=����.�،���ЃR�[�h
        l_sbSql.append(" institution_code = ? ");
        l_lisValues.add(l_stockSecuredLoans[0]);

        //�@@�@@�����X�R�[�h=����.���X�R�[�h
        //---------�i�������A����.���X�R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_stockSecuredLoans[1] != null)
        {
            l_sbSql.append(" and branch_code = ? ");
            l_lisValues.add(l_stockSecuredLoans[1]);
        }

        //�@@�@@�������R�[�h like ����.�ڋq�R�[�h + "%"
        //---------�i�������A����.�ڋq�R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_stockSecuredLoans[2] != null)
        {
            l_sbSql.append(" and account_code like ? || '%' ");
            l_lisValues.add(l_stockSecuredLoans[2]);
        }

        //�@@�@@���X�g�b�N���[�������ԍ� like ����.�X�g�b�N���[�������ԍ� + "%"
        //       �i�O����v�������s���B�������A����.�X�g�b�N���[�������ԍ���null�ł͖����ꍇ�Ɍ���j
        if (l_stockSecuredLoans[3] != null)
        {
            l_sbSql.append(" and stock_loan_account_code like ? || '%' ");
            l_lisValues.add(l_stockSecuredLoans[3]);
        }

        //�@@�@@���\����=����.�\����
        //---------�i�������A����.�\���󋵂�null�ł͖����ꍇ�Ɍ���j
        if (l_stockSecuredLoans[4] != null)
        {
            l_sbSql.append(" and account_open_status = ? ");
            l_lisValues.add(l_stockSecuredLoans[4]);
        }

        //�@@�@@���\����
        //�@@�@@�@@*�\����From != null && �\����To == null�ꍇ
        //�@@�@@�@@ �\����(YYYYMMDD) >= ����.�\����From
        if (l_stockSecuredLoans[5] != null && l_stockSecuredLoans[6] == null)
        {
            l_sbSql.append(" and to_date(to_char(appli_date, 'YYYYMMDD')) >= ? ");
            l_lisValues.add(l_stockSecuredLoans[5]);
        }

        //�@@�@@�@@*�\����From == null && �\����To != null�ꍇ
        //�@@�@@�@@ �\����(YYYYMMDD) <= ����.�\����To
        if (l_stockSecuredLoans[5] == null && l_stockSecuredLoans[6] != null)
        {
            l_sbSql.append(" and to_date(to_char(appli_date, 'YYYYMMDD')) <= ? ");
            l_lisValues.add(l_stockSecuredLoans[6]);
        }

        //�@@�@@�@@*�\����From != null && �\����To != null�ꍇ
        //�@@�@@�@@ �\����(YYYYMMDD) between ����.�\����From and ����.�\����To
        if (l_stockSecuredLoans[5] != null && l_stockSecuredLoans[6] != null)
        {
            l_sbSql.append(" and to_date(to_char(appli_date, 'YYYYMMDD')) between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[5]);
            l_lisValues.add(l_stockSecuredLoans[6]);
        }

        //�@@�@@���J�ݓ�
        //�@@�@@�@@*�J�ݓ�From != null && �J�ݓ�To == null�ꍇ
        //�@@�@@�@@ �J�ݓ� >= ����.�J�ݓ�From
        if (l_stockSecuredLoans[7] != null && l_stockSecuredLoans[8] == null)
        {
            l_sbSql.append(" and account_open_date >= ? ");
            l_lisValues.add(l_stockSecuredLoans[7]);
        }

        //�@@�@@�@@*�J�ݓ�From == null && �J�ݓ�To != null�ꍇ
        //�@@�@@�@@ �J�ݓ� <= ����.�J�ݓ�To
        if (l_stockSecuredLoans[7] == null && l_stockSecuredLoans[8] != null)
        {
            l_sbSql.append(" and account_open_date <= ? ");
            l_lisValues.add(l_stockSecuredLoans[8]);
        }

        //�@@�@@�@@*�J�ݓ�From != null && �J�ݓ�To != null�ꍇ
        //�@@�@@�@@ �J�ݓ� between ����.�J�ݓ�From and ����.�J�ݓ�To
        if (l_stockSecuredLoans[7] != null && l_stockSecuredLoans[8] != null)
        {
            l_sbSql.append(" and account_open_date between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[7]);
            l_lisValues.add(l_stockSecuredLoans[8]);
        }

        //�@@�@@�������
        //�@@�@@�@@*�����From != null && �����To == null�ꍇ
        //�@@�@@�@@ �����(YYYYMMDD) >= ����.�����From
        if (l_stockSecuredLoans[9] != null && l_stockSecuredLoans[10] == null)
        {
            l_sbSql.append(" and to_date(to_char(order_data_reception_date, 'YYYYMMDD')) >= ? ");
            l_lisValues.add(l_stockSecuredLoans[9]);
        }

        //�@@�@@�@@*�����From == null && �����To != null�ꍇ
        //�@@�@@�@@ �����(YYYYMMDD) <= ����.�����To
        if (l_stockSecuredLoans[9] == null && l_stockSecuredLoans[10] != null)
        {
            l_sbSql.append(" and to_date(to_char(order_data_reception_date, 'YYYYMMDD')) <= ? ");
            l_lisValues.add(l_stockSecuredLoans[10]);
        }

        //�@@�@@�@@*�����From != null && �����To != null�ꍇ
        //�@@�@@�@@ �����(YYYYMMDD) between ����.�����From and ����.�����To
        if (l_stockSecuredLoans[9] != null && l_stockSecuredLoans[10] != null)
        {
            l_sbSql.append(" and to_date(to_char(order_data_reception_date, 'YYYYMMDD')) between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[9]);
            l_lisValues.add(l_stockSecuredLoans[10]);
        }

        //�@@�@@������
        //�@@�@@�@@*����From != null && ����To == null�ꍇ
        //�@@�@@�@@ ����(YYYYMMDD) >= ����.����From
        if (l_stockSecuredLoans[11] != null && l_stockSecuredLoans[12] == null)
        {
            l_sbSql.append(" and to_date(to_char(cancel_data_reception_date, 'YYYYMMDD')) >= ? ");
            l_lisValues.add(l_stockSecuredLoans[11]);
        }

        //�@@�@@�@@*����From == null && ����To != null�ꍇ
        //�@@�@@�@@ ����(YYYYMMDD) <= ����.����To
        if (l_stockSecuredLoans[11] == null && l_stockSecuredLoans[12] != null)
        {
            l_sbSql.append(" and to_date(to_char(cancel_data_reception_date, 'YYYYMMDD')) <= ? ");
            l_lisValues.add(l_stockSecuredLoans[12]);
        }

        //�@@�@@�@@*����From != null && ����To != null�ꍇ
        //�@@�@@�@@ ����(YYYYMMDD) between ����.����From and ����.����To
        if (l_stockSecuredLoans[11] != null && l_stockSecuredLoans[12] != null)
        {
            l_sbSql.append(" and to_date(to_char(cancel_data_reception_date, 'YYYYMMDD')) between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[11]);
            l_lisValues.add(l_stockSecuredLoans[12]);
        }

        //�@@�@@������
        //�@@�@@�@@*����From != null && ����To == null�ꍇ
        //�@@�@@�@@ ���� >= ����.����From
        if (l_stockSecuredLoans[13] != null && l_stockSecuredLoans[14] == null)
        {
            l_sbSql.append(" and close_date >= ? ");
            l_lisValues.add(l_stockSecuredLoans[13]);
        }

        //�@@�@@�@@*����From == null && ����To != null�ꍇ
        //�@@�@@�@@ ���� <= ����.����To
        if (l_stockSecuredLoans[13] == null && l_stockSecuredLoans[14] != null)
        {
            l_sbSql.append(" and close_date <= ? ");
            l_lisValues.add(l_stockSecuredLoans[14]);
        }

        //�@@�@@�@@*����From != null && ����To != null�ꍇ
        //�@@�@@�@@ ���� between ����.����From and ����.����To
        if (l_stockSecuredLoans[13] != null && l_stockSecuredLoans[14] != null)
        {
            l_sbSql.append(" and close_date between ? and ? ");
            l_lisValues.add(l_stockSecuredLoans[13]);
            l_lisValues.add(l_stockSecuredLoans[14]);
        }

        //�@@[���я�]
        //�@@�@@1)�Ő��������\�[�g����
        Object[] l_sqlValues = new Object[l_lisValues.size()];
        l_lisValues.toArray(l_sqlValues);
        String l_strSortKey = null;
        if (!WEB3StringTypeUtility.isEmpty(l_sbSortKey.toString()))
        {
            l_strSortKey = l_sbSortKey.toString();
        }
        List l_lisQueryRows;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisQueryRows = l_queryProcessor.doFindAllQuery(
                StockSecuredLoanRow.TYPE,
                l_sbSql.toString(),
                l_strSortKey,
                null,
                l_sqlValues);
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

        //3) 2)�̌������ʂ�ԋp����B
        StockSecuredLoanParams[] l_stockSecuredLoanParams =
            new StockSecuredLoanParams[l_lisQueryRows.size()];
        if (!l_lisQueryRows.isEmpty())
        {
            for (int i = 0; i < l_lisQueryRows.size(); i++)
            {
                StockSecuredLoanRow l_stockSecuredLoanRow =
                    (StockSecuredLoanRow)l_lisQueryRows.get(i);
                l_stockSecuredLoanParams[i] =
                    new StockSecuredLoanParams(l_stockSecuredLoanRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_stockSecuredLoanParams;
    }

    /**
     * (update�̔ԃe�[�u��)<BR>
     * �ȉ��̍X�V���������ɁA�̔ԃe�[�u���e�[�u����update�������s���B<BR>
     * <BR>
     * �y�X�V�����z<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�̔ԍ��ږ� = ����.�̔ԍ��ږ�<BR>
     * <BR>
     * �X�V���e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�،��S�ۃ��[��_�̔ԃe�[�u��.xls�v<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSerialNumberName - (�̔ԍ��ږ�)<BR>
     * �̔ԍ��ږ�<BR>
     * @@param l_strSerialNumber - (�V���A���i���o�[)<BR>
     * �V���A���i���o�[<BR>
     * @@throws WEB3BaseException
     */
    public void updateCommSerialNumbers(
        String l_strInstitutionCode,
        String l_strSerialNumberName,
        String l_strSerialNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateCommSerialNumbers(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�y�X�V�����z
            //�،���ЃR�[�h = ����.�،���ЃR�[�h
            //�̔ԍ��ږ� = ����.�̔ԍ��ږ�
            CommSerialNumbersPK l_commSerialNumbersPK =
                new CommSerialNumbersPK(l_strInstitutionCode, l_strSerialNumberName);
            Map l_mapSpac = new HashMap();

            //�̔ԃR�[�h:  ����.�V���A���i���o�[
            l_mapSpac.put("serial_number", l_strSerialNumber);

            //�X�V����:  ���ݓ���
            l_mapSpac.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                l_commSerialNumbersPK,
                l_mapSpac);
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

        log.exiting(STR_METHOD_NAME);
    }
}
@
