head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3IfoDepositCalcResultSaveServiceImpl�N���X(WEB3IfoDepositCalcResultSaveServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 ��(FLJ) �V�K�쐬
 */

package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.data.IfoDepositCalcLockParams;
import webbroker3.ifodeposit.data.IfoDepositCalcLockRow;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;
import webbroker3.ifodeposit.define.WEB3IfoDepositCalcResultSaveDiv;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.util.WEB3LogUtility;
import java.util.Hashtable;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (�؋����v�Z���ʕۑ��T�[�r�XImpl)<BR>
 * �؋����v�Z���ʕۑ��T�[�r�X�����N���X�B<BR>
 *
 * @@author ��(FLJ)
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultSaveServiceImpl implements WEB3IfoDepositCalcResultSaveService
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultSaveServiceImpl.class);

    /**
     * ���R�~�b�g�����ݒ�l�擾�L�[
     */
    private final static String STR_MAX_COMMIT_ROWS_KEY = "ifodeposit.calcresult.max.commit.rows";

    /**
     * �f�t�H���g���R�~�b�g����
     */
    private final static int DEFAULT_MAX_COMMIT_ROWS = 100;

    /**
     *
     */
    public WEB3IfoDepositCalcResultSaveServiceImpl()
    {
        super();
        // TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
    }

    /**
     * �؋����v�Z���ʕۑ��T�[�r�X�����{����B
     * 
     * �P�D�����X���b�h�����b�N����B
     * �Q�D�⏕�����̃��X�g���擾����
     * �R�D�ڋq���ɏ؋����v�Z���ʃf�[�^���쐬����
     * �S�D�쐬�������ʂ�Db�ɕۑ�����
     * �T�D�X���b�h���~����
     * 
     * @@param l_request
     *  - (���N�G�X�g�f�[�^)
     * ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoDepositCalcResultSaveRequest l_req = (WEB3IfoDepositCalcResultSaveRequest) l_request;
        
        //���X�|���X���쐬
        WEB3IfoDepositCalcResultSaveResponse l_res = new WEB3IfoDepositCalcResultSaveResponse();
        
        // �X���b�h�J�n
        if (lockThread(l_req.threadNo.longValue()))
        {

            //�����Ώۂ̌����f�[�^���擾
            List l_subAccList = getSubAccountList(l_req.fromAccountId, l_req.toAccountId);

            //�ڋq���ɏ؋����v�Z���ʃf�[�^���쐬����
            List l_ifoDepositCalcResultList = new ArrayList();
            if (l_subAccList != null)
            {
                int l_size = l_subAccList.size();
                for (int i = 0; i < l_size; i++)
                {
                    WEB3IfoDepositCalcResultCreatePerAccountService l_createService = (WEB3IfoDepositCalcResultCreatePerAccountService) Services
                            .getService(WEB3IfoDepositCalcResultCreatePerAccountService.class);
                    IfoDepositCalcResultParams l_param = l_createService.createIfoDepositCalcResult((WEB3GentradeSubAccount) l_subAccList.get(i));
                    if (l_param != null)
                    {
                        l_ifoDepositCalcResultList.add(l_param);
                    }
                }
            }

            //DB�ɕۑ�����
            save2DB(l_ifoDepositCalcResultList, l_res);

            // �X���b�h��~
            releaseThread(l_req.threadNo.longValue());
        }

        log.exiting(STR_METHOD_NAME);
        //���X�|���X��Ԃ�
        return l_res;
    }

    /**
     * �쐬�����؋����v�Z����Row��DB�ɕۑ�����B
     * �Œ茏�������R�~�b�g����B(�R�~�b�g������SYSTEM_PREDERENCE�ɕۑ�����A�L�[�F"ifodeposit.calcresult.max.commit.rows"�B�f�t�H�[���g��100)
     * �ۑ��������Ǝ��s���̓��X�|���X�Y�����ڂɐݒ肷��B
     * 
     * @@param depositCalcResultList �쐬�����؋����v�Z����Row�̃��X�g
     * @@param l_res ���X�|���X
     */
    private void save2DB(List l_depositCalcResultList, WEB3IfoDepositCalcResultSaveResponse l_res)
    {
        final String STR_METHOD_NAME = "save2DB(List, WEB3IfoDepositCalcResultSaveResponse)";
        log.entering(STR_METHOD_NAME);
        
        //�R�~�b�g�������擾
        String l_strCount = GtlUtils.getTradingSystem().getPreference(STR_MAX_COMMIT_ROWS_KEY);
        int l_intMaxCount = DEFAULT_MAX_COMMIT_ROWS;
        if (l_strCount != null)
        {
            try
            {
                l_intMaxCount = Integer.parseInt(l_strCount);
            }
            catch (Exception e)
            {
            }
        }

        ArrayList l_lst = new ArrayList();
        long l_lngSCount = 0;
        long l_lngFCount = 0;
        for (int i = 0; i < l_depositCalcResultList.size(); i++)
        {
            l_lst.add(l_depositCalcResultList.get(i));
            if (l_lst.size() == l_intMaxCount)
            {

                boolean l_blnRet = WEB3IfoDepositDBManager.getInstance().insert(l_lst);
                if (l_blnRet == true)
                {
                    l_lngSCount += l_intMaxCount;
                    log.info("save db success count=" + l_lngSCount);
                }
                l_lst.clear();
            }

        }

        if (l_lst.size() > 0)
        {
            boolean l_blnRet = WEB3IfoDepositDBManager.getInstance().insert(l_lst);
            if (l_blnRet == true)
            {
                l_lngSCount += l_lst.size();
                log.info("save db success count=" + l_lngSCount);
            }
            l_lst.clear();
        }

        log.info("save db all success count=" + l_lngSCount);
        l_lngFCount = l_depositCalcResultList.size() - l_lngSCount;
        log.info("save db all failure count=" + l_lngFCount);
        l_res.success = new Long(l_lngSCount);
        if (l_lngFCount > 0)
        {
            l_res.failure = new Long(l_lngFCount);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �⏕�������̃��X�g���擾����B
     * �P�A�⏕��������������B
     * �i������F
     * A�A�⏕�����^�C�v���V�F�敨�؋���
     * B�A�ڋqID�͈ȉ��̎q�����͈͓̔�
     * �@@�@@�q�����F�ڋq�}�X�^�[�ɑ΂��āA��������B
     * �@@�@@�i������F
     * �@@�@@a.�ڋqID <= �w�肷��J�n�ڋqID
     *     b.�ڋqID >= �w�肷��I���ڋqID
     *     c.�敨OP�����J�݋敪��3����(���؁A��؁A����)�̉��ꂩ��"�敨OP�����J��:3"�A�܂��́A"�敨�����J��:2"
     * �Q�A�ڋq�͈͓��̌ڋq�}�X�^�[���X�g���擾�B
     * �R�A���������⏕����Row�ƌڋq�}�X�^�[���⏕�����I�u�W�F�N�g(Gentrade)���쐬���ă��X�g�ɕۑ�����B
     * �S�A���X�g��Ԃ�
     * 
     * @@param fromAccountId �J�n�̌ڋqID
     * @@param toAccountId �I���̌ڋqID
     * @@return �⏕�������̃��X�g
     * @@throws WEB3BaseException DB�������s�̏ꍇ
     */
    private List getSubAccountList(long l_fromAccountId, long l_toAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccountList(long, long)";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "sub_account_type = ? and account_id in (select account_id from main_account where account_id >= ? and account_id <= ? and ((IFO_ACC_OPEN_DIV_TOKYO = ? or IFO_ACC_OPEN_DIV_TOKYO = ?) or (IFO_ACC_OPEN_DIV_OSAKA = ? or IFO_ACC_OPEN_DIV_OSAKA = ?) or (IFO_ACC_OPEN_DIV_NAGOYA = ? or IFO_ACC_OPEN_DIV_NAGOYA = ?)))";
        Object[] l_bindVars = new Object[9];
        l_bindVars[0] = SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;//�敨�؋����i�V�j
        l_bindVars[1] = new Long(l_fromAccountId);
        l_bindVars[2] = new Long(l_toAccountId);
        l_bindVars[3] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;//�敨�����J��
        l_bindVars[4] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;//�敨OP�����J��
        l_bindVars[5] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;//�敨�����J��
        l_bindVars[6] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;//�敨OP�����J��
        l_bindVars[7] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;//�敨�����J��
        l_bindVars[8] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;//�敨OP�����J��
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        log.debug("BindVars[2]=" + l_bindVars[2]);
        log.debug("BindVars[3]=" + l_bindVars[3]);
        log.debug("BindVars[4]=" + l_bindVars[4]);
        log.debug("BindVars[5]=" + l_bindVars[5]);
        log.debug("BindVars[6]=" + l_bindVars[6]);
        log.debug("BindVars[7]=" + l_bindVars[7]);
        log.debug("BindVars[8]=" + l_bindVars[8]);

        QueryProcessor l_queryProcessor = null;
        List l_listSearchResult = null;
        try
        {
            //�����J�n
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listSearchResult = l_queryProcessor.doFindAllQuery(SubAccountRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);
        }
        catch (DataException e)
        {
            final String l_message = "�؋����v�Z���ʕۑ��̕⏕�����擾�Ɏ��s���܂����B";
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "getSubAccount()", l_message, e);
        }
        //�ڋq���ꊇ�擾(�L���b�V���[�̂��߂�)
        Hashtable l_tblMainAcc=getMainAccountTbl(l_fromAccountId, l_toAccountId);

        int l_size = l_listSearchResult.size();
        List l_subAccList = new ArrayList(l_size);
        for (int i = 0; i < l_size; i++)
        {
            SubAccountRow l_row = (SubAccountRow) l_listSearchResult.get(i);
            MainAccountRow l_mainrow=(MainAccountRow)l_tblMainAcc.get(new Long(l_row.getAccountId()));
            //�⏕�����I�u�W�F�N�g���쐬
            if(l_mainrow==null){
              WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_row);
               l_subAccList.add(l_subAccount);
            }
            else {
              WEB3GentradeMainAccount l_mainAccount=new WEB3GentradeMainAccount(l_mainrow);
              WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_row);
               l_subAccList.add(l_subAccount);
            }


        }
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccList;
    }

    /**
     * �ڋq�����ꊇ�擾����B
     * �@@�@@�i������F
     * �@@�@@a.�ڋqID <= �w�肷��J�n�ڋqID
     *     b.�ڋqID >= �w�肷��I���ڋqID
     *     c.�敨OP�����J�݋敪��3����(���؁A��؁A����)�̉��ꂩ��"�敨OP�����J��:3"�A�܂��́A"�敨�����J��:2"
     * @@param fromAccountId �J�n�̌ڋqID
     * @@param toAccountId �I���̌ڋqID
     * @@return Hashtable�@@�ڋqID���L�[�A�ڋq�}�X�^Row�̓o�����[
     * @@throws WEB3BaseException DB�������s�̏ꍇ
     */
    private Hashtable getMainAccountTbl(long l_fromAccountId, long l_toAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccountTbl(long, long)";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "account_id >= ? and account_id <= ? and ((IFO_ACC_OPEN_DIV_TOKYO = ? or IFO_ACC_OPEN_DIV_TOKYO = ?) or (IFO_ACC_OPEN_DIV_OSAKA = ? or IFO_ACC_OPEN_DIV_OSAKA = ?) or (IFO_ACC_OPEN_DIV_NAGOYA = ? or IFO_ACC_OPEN_DIV_NAGOYA = ?))";
        Object[] l_bindVars = new Object[8];
        l_bindVars[0] = new Long(l_fromAccountId);
        l_bindVars[1] = new Long(l_toAccountId);
        l_bindVars[2] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
        l_bindVars[3] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
        l_bindVars[4] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
        l_bindVars[5] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
        l_bindVars[6] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
        l_bindVars[7] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        log.debug("BindVars[2]=" + l_bindVars[2]);
        log.debug("BindVars[3]=" + l_bindVars[3]);
        log.debug("BindVars[4]=" + l_bindVars[4]);
        log.debug("BindVars[5]=" + l_bindVars[5]);
        log.debug("BindVars[6]=" + l_bindVars[6]);
        log.debug("BindVars[7]=" + l_bindVars[7]);

        QueryProcessor l_queryProcessor = null;
        List l_listSearchResult = null;
        try
        {
            //�����J�n
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listSearchResult = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);
        }
        catch (DataException e)
        {
            final String l_message = "�؋����v�Z���ʕۑ��̌ڋq�����擾�Ɏ��s���܂����B";
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "getMainAccountTbl()", l_message, e);
        }

        int l_size = l_listSearchResult.size();
        Hashtable l_tblMainAcc = new Hashtable(l_size);
        for (int i = 0; i < l_size; i++)
        {
            MainAccountRow l_row = (MainAccountRow) l_listSearchResult.get(i);
            l_tblMainAcc.put(new Long(l_row.getAccountId()),l_row);

        }
        log.exiting(STR_METHOD_NAME);
        return l_tblMainAcc;
    }
    
    /**
     * (�X���b�h�J�n)<BR>
     * �m�����T�v�n<BR>
     * �w�肳�ꂽ�X���b�h�̃��b�N������������B
     * 1)�擾�ł��Ȃ��ꍇ�A�V�������b�N���R�[�h�ǉ��iThread_no = �X���b�hNo. �@@������ԁ@@= 1:�������j
     * 2)�擾�����ꍇ�A������Ԃ́u�P�F�������v�ł��邩�ǂ����𔻒f����F
     * 2-1)�u�P�F�������v�ł���ꍇ�Afalse��Ԃ�
     * 2-2)�u�P�F�������v�łȂ��ꍇ�A�X���b�hNo�����ɁA������Ԃ��P�F�������ɍX�V����B �X�V����������Atrue��Ԃ��B
     * 
     * �X�V�ΏۃX���b�h���̍X�V�Ɏ��s�����ꍇ<BR>
     * WEB3SystemLayerException<BR>
     * SYSTEM_ERROR_80003<BR>
     * 
     * @@param l_strThreadNo - (�X���b�hNo)
     * @@return ���b�N�����̏ꍇtrue,���̃X���b�h���b�N���̏ꍇfalse
     */
    protected boolean lockThread(
        final long l_lngThreadNo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "lockThread(String)";
        final int UPDATE_SUCCESS = 0; // �X�V����������I�������l
        final int UPDATE_FAIL = -1; // �X�V�ΏۃX���b�h���̍X�V�Ɏ��s�����ꍇ�̒l
        final int LOCKED = -2; // �X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ�̒l

        log.entering(STR_METHOD_NAME);

        try
        {
            final String l_serviceName = this.getClass().getName();
            final String l_apName = getAppName();
            final Timestamp l_tm = GtlUtils.getSystemTimestamp();
            
            final String l_strWhere = "service_name = ? and thread_no = ?";
            final String l_strCondition = "for update";

            final Object l_bindVars[] = { l_serviceName, new Long(l_lngThreadNo) };

            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    //���b�N�������X���b�h������
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                            IfoDepositCalcLockRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    //�X���b�h�L�^������ꍇ
                    if (l_lisRows.size() > 0)
                    {
                        IfoDepositCalcLockRow l_row =
                            (IfoDepositCalcLockRow) l_lisRows.get(0);

                        //���b�N���ł��邩�𔻒f����
                        if(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC.equals(l_row.getStatus()))
                        {
                            //���b�N���̏ꍇ
                            l_intResult = new Integer(LOCKED);
                        }
                        else
                        {
                            //�����b�N�̏ꍇ�A�u���b�N���v�ɍX�V
                            IfoDepositCalcLockParams l_params = new IfoDepositCalcLockParams(l_row);
                            l_params.setApHostName(l_apName);
                            l_params.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC);
                            l_params.setLastUpdatedTimestamp(l_tm);
                            l_queryProcesser.doUpdateQuery(l_params);
                            l_intResult = new Integer(UPDATE_SUCCESS);
                        }
                    }
                    else
                    {
                        //�X���b�h�L�^���Ȃ��̏ꍇ�A�V�K�C���T�[�g����
                        IfoDepositCalcLockParams l_params = new IfoDepositCalcLockParams();
                        l_params.setServiceName(l_serviceName);
                        l_params.setThreadNo(l_lngThreadNo);
                        l_params.setApHostName(l_apName);
                        l_params.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC);
                        l_params.setCreatedTimestamp(l_tm);
                        l_params.setLastUpdatedTimestamp(l_tm);
                        l_queryProcesser.doInsertQuery(l_params);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }

                    return l_intResult;
                }
            }
            );
            
            log.debug("startThread result:"+l_intResult);
            
            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
            else if (l_intResult.intValue() == LOCKED)
            {
                return false;
            }
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(), de);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (�X���b�h�J��)<BR>
     * �m�����T�v�n<BR>
     * �P�j�����Ŏw�肳�ꂽ�X���b�hNo�������ɂ��đΏۃ��R�[�h�̏�����Ԃ�<BR>
     * 0�i�F���ғ��j����B<BR>
     *<BR>
     * �X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR>
     * @@param l_strThreadNo - (�X���b�hNo)
     */
    protected void releaseThread(final long l_lngThreadNo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "releaseThread(String)";
        final int UPDATE_SUCCESS = 0; // �X�V����������I�������l
        final int UPDATE_FAIL = -1; // �X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ�̒l

        log.entering(STR_METHOD_NAME);

        try
        {
            final String l_serviceName = this.getClass().getName();
            final Timestamp l_tm = GtlUtils.getSystemTimestamp();
            
            final String l_strWhere = "service_name = ? and thread_no = ?";
            final String l_strCondition = "for update";

            final Object l_bindVars[] = { l_serviceName, new Long(l_lngThreadNo) };

            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    //�J���������X���b�h������
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                            IfoDepositCalcLockRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    if (l_lisRows.size() > 0)
                    {
                        //���R�[�h������ꍇ�A�u���ғ��v�ɍX�V����
                        IfoDepositCalcLockRow l_row =
                            (IfoDepositCalcLockRow) l_lisRows.get(0);
                        IfoDepositCalcLockParams l_params = new IfoDepositCalcLockParams(l_row);
                        l_params.setApHostName("NONE");
                        l_params.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
                        l_params.setLastUpdatedTimestamp(l_tm);
                        l_queryProcesser.doUpdateQuery(l_params);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }
                    else
                    {
                        l_intResult = new Integer(UPDATE_FAIL);
                    }
                    return l_intResult;
                }
            }
            );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(), de);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * AP�����擾����
     * 
     * @@return String AP��
     */
    private String getAppName()
    {
        try
        {
            InetAddress l_localHost = InetAddress.getLocalHost();
            return l_localHost.getHostName();
        }
        catch (UnknownHostException ex)
        {
            log.error(ex.getMessage(), ex);
            return "";
        }

    }

}
@
