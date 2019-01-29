head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminFPTForceLogoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : (�񓯊�)�Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl(WEB3AsynAdminFPTForceLogoutServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
 */
package webbroker3.docadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DeliveryTargetDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.docadmin.WEB3AdminFPTCommon;
import webbroker3.docadmin.data.DocForceLogoutRunStatusParams;
import webbroker3.docadmin.data.DocForceLogoutRunStatusRow;
import webbroker3.docadmin.define.WEB3AdminFPTForceLogoutValidityDef;
import webbroker3.docadmin.define.WEB3DocForceLogoutRunStatusStatusDef;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�񓯊�)�Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl
 * 
 * @@author ��
 * @@version 1.0
 */
public class WEB3AsynAdminFPTForceLogoutServiceImpl implements Runnable
{

    /**
     * ���O���[�e�B���e�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AsynAdminFPTForceLogoutServiceImpl.class);

    /**
     * ���N�G�X�g�f�[�^
     */
    private WEB3AdminFPTForceLogoutMainRequest requestData;

    /**
     * �����擾�L�[ �����P��
     */
    private static final String SEARCH_UNIT_KEY = ".doc.force.logout.query.unit";

    /**
     * �����擾�L�[ ���g���C��
     */
    private static final String RETRY_COUNT_KEY = ".doc.force.logout.retry.count";

    /**
     * �f�t�H���g�����P��
     */
    private long defaultSearchUnits = 1000;

    /**
     * �f�t�H���g���g���C��
     */
    private long defaultRetryCount = 1;

    /**
     * ���t�t�H�[�}�b�g
     */
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    /**
     * @@roseuid 47DF4FB700B5
     */
    public WEB3AsynAdminFPTForceLogoutServiceImpl()
    {

    }

    /**
     * �R���X�g���N�^
     * 
     * ������this.���N�G�X�g�f�[�^�ɃZ�b�g����B
     * 
     * @@param l_request -
     *            �Ǘ��� ���ʖ����� �������O�A�E�g���C�����N�G�X�g
     * @@roseuid 47D720B20025
     */
    public WEB3AsynAdminFPTForceLogoutServiceImpl(WEB3AdminFPTForceLogoutMainRequest l_request)
    {
        requestData = l_request;
    }

    /**
     * �i�񓯊��j�Ǘ��� ���ʖ����� �������O�A�E�g�������s���B
     * 
     * �V�[�P���X�} �u�i�i�񓯊��j�Ǘ��� ���ʖ����� �������O�A�E�g�jrun�v�Q�ƁB
     * 
     * @@throws DataNetworkException
     * @@throws DataFindException
     * @@roseuid 47D71F5402CE
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        WEB3DescendRacCtxService l_racService = null;

        try
        {
            //DB�̐U�蕪��������肷��
            l_racService = (WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
            l_racService.setAccountIdCtx(requestData.accountIdFrom.longValue());

            //�Ǘ��� ���ʖ����� �������O�A�E�g�f�[�����g���K�[TransactionCallback�𐶐�����B
            WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback l_daemonCallback = new WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback(
                    requestData.threadNo.longValue());

            //���ʌ�t�Ώ�Map
            Map l_docDeliveryMap = new HashMap();
            //���O�A�E�g���Map
            Map l_logoutMap = new HashMap();
            //��t�ς݃A�J�E���gMap
            Map l_deliveredAccMap = new HashMap();
            //�A�J�E���g���Map
            Map l_accMap = new HashMap();
            //���O�A�E�g���{�Z�b�V����List
            List l_logoutSessionList = new ArrayList();
            //��������is�G���[
            boolean l_isError = false;
            //��������
            int l_count = 0;

            QueryProcessor l_qp = Processors.getDefaultProcessor();

            DaemonTriggerRow l_daemonRow = (DaemonTriggerRow) l_qp.doTransaction(QueryProcessor.TX_JOIN_EXISTING, l_daemonCallback);

            //null���ԋp���ꂽ�ꍇ�́A�������I������B
            if (l_daemonRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //�Ǘ��҂��쐬����
            WEB3Administrator l_admin = new WEB3Administrator(requestData.adminId.longValue());

            //���ʖ����� �������O�A�E�g���s���ʂ̍X�V����
            //[����]
            //�،���ЃR�[�h ��this.���N�G�X�g�f�[�^.�،���ЃR�[�h
            //From����ID ��this.���N�G�X�g�f�[�^.From����ID
            //To����ID ��this.���N�G�X�g�f�[�^.To����ID
            //���ʎ�ރR�[�h�ꗗ��this.���N�G�X�g�f�[�^.�Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������.���ʎ�ރR�[�h�ꗗ
            //�X�V�҃R�[�h ���Ǘ���.getAdministratorCode()
            WEB3AdminFPTForceLogoutExecuteResultTransactionCallback l_resultCallback = new WEB3AdminFPTForceLogoutExecuteResultTransactionCallback(
                    requestData.institutionCode, requestData.accountIdFrom.longValue(), requestData.accountIdTo.longValue(),
                    requestData.forceLogoutProductCondition.documentCatCodeArr, l_admin);

            //���s���ʃg�����U�N�V�������J�n����B
            DocForceLogoutRunStatusRow l_exeResultRow = (DocForceLogoutRunStatusRow) l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW,
                    l_resultCallback);

            try
            {
                //�،���ЃR�[�h���擾����B
                String l_institutionCode = l_admin.getInstitutionCode();
    
                //�����P�ʂ��擾����B
                //[����]
                //�v���p�e�B�E�L�[�F get�،���ЃR�[�h()+".doc.force.logout.query.unit"
                //�f�t�H���g�l �F�f�t�H���g�����P��
                //���g���C�񐔂��擾����B
                //[����]
                //�v���p�e�B�E�L�[�F get�،���ЃR�[�h()+".doc.force.logout.retry.count"
                //�f�t�H���g�l �F�f�t�H���g���g���C��
                long l_searchUnits = getProperties(l_institutionCode + SEARCH_UNIT_KEY, defaultSearchUnits);
                long l_retryCount = getProperties(l_institutionCode + RETRY_COUNT_KEY, defaultRetryCount);
    
                if(log.ison())
                {
                    log.debug("�����P��=" + l_searchUnits);
                    log.debug("���g���C��=" + l_retryCount);
                }
    
                //���X�R�[�h���擾����B
                String l_branchCode = l_admin.getBranchCode();
    
                //���ʎ�ފǗ��e�[�u����������������
                //[����]
                //�E�،���ЃR�[�h �� �،���ЃR�[�h
                //�E���X�R�[�h �� ���X�R�[�h
                //�E���ʎ�ރR�[�h in �Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������.���ʎ�ރR�[�h��
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append("institution_code = ? ");
                l_sbWhere.append("and branch_code = ? ");
                l_sbWhere.append("and document_category in (");
    
                List l_objList = new ArrayList();
                l_objList.add(l_institutionCode);
                l_objList.add(l_branchCode);
    
                for (int i = 0; i < requestData.forceLogoutProductCondition.documentCatCodeArr.length; i++)
                {
                    if (i != 0)
                    {
                        l_sbWhere.append(",");
                    }
                    l_sbWhere.append("?");
                    l_objList.add(requestData.forceLogoutProductCondition.documentCatCodeArr[i]);
                }
    
                l_sbWhere.append(")");
                Object[] l_vars = l_objList.toArray();
    
                //���ʎ�ފǗ��e�[�u�����������s
                List l_docCateMngList = l_qp.doFindAllQuery(DocCategoryManagementRow.TYPE, l_sbWhere.toString(), l_vars);
    
                //���ʎ�ފǗ��e�[�u���̌������ʐ���Loop
                int l_listCnt = l_docCateMngList.size();
                for (int i = 0; i < l_listCnt; i++)
                {
                    //���ʌ�t�Ώ�Map�ɃL�[�����ʎ�ރR�[�h�A�l����t�Ώۂ��Z�b�g����B
                    DocCategoryManagementRow l_tempRow = (DocCategoryManagementRow) l_docCateMngList.get(i);
                    l_docDeliveryMap.put(l_tempRow.getDocumentCategory(), l_tempRow.getDeliveryTarget());
                }
    
                //���O�C���^�C�v�e�[�u����������
                //[����]
                //�E���O�C���^�C�v�� like getInstitutionId()+ '_customer_login_type%'
                l_sbWhere = new StringBuffer();
                l_sbWhere.append("type_name like ?");
    
                l_vars = new Object[] { l_institutionCode + "_customer_login_type%" };
    
                //���O�C���^�C�v�e�[�u��������
                List l_loginTypeList = l_qp.doFindAllQuery(LoginTypeRow.TYPE, l_sbWhere.toString(), l_vars);
    
                if (l_loginTypeList.size() > 0)
                {
                    StringBuffer l_typeConditionWhere = new StringBuffer();
                    List l_typeConditionList = new ArrayList();
                    for (int i = 0; i < l_loginTypeList.size(); i++)
                    {
                        Long l_typeId = new Long(((LoginTypeRow) l_loginTypeList.get(i)).getTypeId());
                        if(i!=0)
                        {
                            l_typeConditionWhere.append(",");
                        }
                        l_typeConditionWhere.append("?");
                        l_typeConditionList.add(l_typeId);
                    }
                    
                    if(log.ison())
                    {
                        log.debug("���������� ���O�C���^�C�vID=" + l_typeConditionList);
                    }

                    RowType[] l_rowType = new RowType[]{LoginSessionRow.TYPE};
                    
                    //�������� 0�ŏ�����
                    int l_execCount = 0;
                    //�V�X�e���������擾
                    // ���O�C���Z�b�V�����e�[�u���ւ̌����Ȃ̂ŁAGtlUtils.getSystemTimestamp()�ł͂Ȃ��ASystem.currentTimeMillis()���g�p����
                    Timestamp l_now = new Timestamp(System.currentTimeMillis());
                    String l_strNow = WEB3DateUtility.formatDate(l_now, DATE_FORMAT);
                    
                    //���[�v
                    while (true)
                    {
                        //���O�C���Z�b�V�����e�[�u���̌����̏���
                        //[����]
                        //�T�u�N�G�������s����
                        //session_id in
                        //(select session_id from
                        //     (select session_id
                        //      from login_session
                        //      where �L���t���O = �L��
                        //      and �������t > �V�X�e������
                        //      and ���O�C���^�C�vID in getTypeId()�̈ꗗ
                        //      and ���O�C��ID => this.���N�G�X�g�f�[�^.From����ID
                        //      and ���O�C��ID <= this.���N�G�X�g�f�[�^.To����ID
                        //      order by session_id
                        //      )
                        //where rownum > �����P��*���[�v��[0..n]
                        //and rownum <= �����P��*(���[�v��[0..n]+1)
                        //)
                        Long l_rownumFrom = new Long(l_searchUnits * l_execCount);
                        Long l_rownumTo = new Long(l_searchUnits * (++l_execCount));
                        l_sbWhere = new StringBuffer();
                        l_sbWhere.append("session_id in (select session_id from (select rownum as rowno,session_id from login_session where");
                        l_sbWhere.append(" validity = ?");
                        l_sbWhere.append(" and to_char(expiration_date,'YYYYMMDDHH24MISS') > ?");
                        l_sbWhere.append(" and type_id in (" +l_typeConditionWhere.toString()+ ")");
                        l_sbWhere.append(" and login_id >= ?");
                        l_sbWhere.append(" and login_id <= ?");
                        l_sbWhere.append(" order by session_id)");
                        l_sbWhere.append(" where rowno > ?");
                        l_sbWhere.append(" and rowno <= ? )");
    
                        l_objList = new ArrayList();
                        l_objList.add(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_VALID_USER_LOGOUT);
                        l_objList.add(l_strNow);
                        l_objList.addAll(l_typeConditionList);
                        l_objList.add(requestData.accountIdFrom);
                        l_objList.add(requestData.accountIdTo);
                        l_objList.add(l_rownumFrom);
                        l_objList.add(l_rownumTo);
                        
                        l_vars = l_objList.toArray();
                        
                        //���O�C���Z�b�V�����e�[�u��������
                        List l_loginSessionList = l_qp.doFindAllQuery(LoginSessionRow.TYPE, l_sbWhere.toString(), null,null,l_vars,l_rowType);
    
                        //���O�C���Z�b�V�����e�[�u���̌���������Loop
                        l_listCnt = l_loginSessionList.size();
                        for (int i = 0; i < l_listCnt; i++)
                        {
                            //���O�A�E�g���Map�ɃL�[��login_id�A �l�����O�C���Z�b�V����Row��List���Z�b�g����B
                            LoginSessionRow l_row = (LoginSessionRow) l_loginSessionList.get(i);
                            Long l_loginId = new Long(l_row.getLoginId());
                            List l_valueList = (List) l_logoutMap.get(l_loginId);
                            if (l_valueList == null)
                            {
                                l_valueList = new ArrayList();
                                l_logoutMap.put(l_loginId, l_valueList);
                            }
                            l_valueList.add(l_row);
                        }
                        //���ʌ�t�Ǘ��e�[�u���̌���������
                        //[����]
                        //�T�u�N�G�������s����
                        //account_id in
                        //(select login_id from
                        //     (select login_id
                        //      from login_session
                        //      where �L���t���O = �L��
                        //      and �������t > �V�X�e������
                        //      and ���O�C���^�C�vID in getTypeId()�̈ꗗ
                        //      and ���O�C��ID => this.���N�G�X�g�f�[�^.From����ID
                        //      and ���O�C��ID <= this.���N�G�X�g�f�[�^.To����ID
                        //      order by session_id
                        //      )
                        //where rownum > �����P��*���[�v��[0..n]
                        //and rownum <= �����P��*(���[�v��[0..n]+1)
                        //)
                        //and ���ʋ敪 in �Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������.���ʋ敪�ꗗ
                        //and �����R�[�h in �Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������.�d�q�������R�[�h�ꗗ
                        //and �폜�t���O���L��
                        //and ����ID => this.���N�G�X�g�f�[�^.From����ID
                        //and ����ID <= this.���N�G�X�g�f�[�^.To����ID
                        l_sbWhere = new StringBuffer();
                        l_sbWhere.append("account_id in (select login_id from (select rownum as rowno,login_id from login_session where");
                        l_sbWhere.append(" validity = ?");
                        l_sbWhere.append(" and to_char(expiration_date,'YYYYMMDDHH24MISS') > ?");
                        l_sbWhere.append(" and type_id in (" +l_typeConditionWhere.toString()+ ")");
                        l_sbWhere.append(" and login_id >= ?");
                        l_sbWhere.append(" and login_id <= ?");
                        l_sbWhere.append(" order by session_id)");
                        l_sbWhere.append(" where rowno > ?");
                        l_sbWhere.append(" and rowno <= ? )");
                        l_sbWhere.append(" and document_div in (");
    
                        l_objList = new ArrayList();
                        l_objList.add(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_VALID_USER_LOGOUT);
                        l_objList.add(l_strNow);
                        l_objList.addAll(l_typeConditionList);
                        l_objList.add(requestData.accountIdFrom);
                        l_objList.add(requestData.accountIdTo);
                        l_objList.add(l_rownumFrom);
                        l_objList.add(l_rownumTo);
    
                        for (int i = 0; i < requestData.forceLogoutProductCondition.documentDivArr.length; i++)
                        {
                            if (i != 0)
                            {
                                l_sbWhere.append(",");
                            }
                            l_sbWhere.append("?");
                            l_objList.add(requestData.forceLogoutProductCondition.documentDivArr[i]);
                        }
                        l_sbWhere.append(")");
                        l_sbWhere.append(" and product_code in (");
                        for (int i = 0; i < requestData.forceLogoutProductCondition.batoProductCodeArr.length; i++)
                        {
                            if (i != 0)
                            {
                                l_sbWhere.append(",");
                            }
                            l_sbWhere.append("?");
                            l_objList.add(requestData.forceLogoutProductCondition.batoProductCodeArr[i]);
                        }
                        l_sbWhere.append(")");
                        l_sbWhere.append(" and delete_flag = ?");
                        l_sbWhere.append(" and account_id >= ?");
                        l_sbWhere.append(" and account_id <= ?");
                        l_objList.add(BooleanEnum.FALSE);
                        l_objList.add(requestData.accountIdFrom);
                        l_objList.add(requestData.accountIdTo);
    
                        l_vars = l_objList.toArray();
    
                        //���ʌ�t�Ǘ��e�[�u��������
                        List l_docDeliMngList = l_qp.doFindAllQuery(DocDeliveryManagementRow.TYPE,l_sbWhere.toString(), null,null,l_vars,l_rowType);
                        //���ʌ�t�Ǘ��e�[�u���̌������ʐ���Loop
                        l_listCnt = l_docDeliMngList.size();
                        for (int i = 0; i < l_listCnt; i++)
                        {
                            //���ʂ��ƌ�t�σA�J�E���gMap�ɃL�[�����ʎ�ރR�[�h�A�l���A�J�E���gID��List���Z�b�g����B
                            DocDeliveryManagementRow l_row = (DocDeliveryManagementRow) l_docDeliMngList.get(i);
                            Long l_accountId = new Long(l_row.getAccountId());
                            String l_docCateCode = l_row.getDocumentCategory();
                            List l_valueList = (List) l_deliveredAccMap.get(l_docCateCode);
                            if (l_valueList == null)
                            {
                                l_valueList = new ArrayList();
                                l_deliveredAccMap.put(l_docCateCode, l_valueList);
                            }
                            l_valueList.add(l_accountId);
                        }
    
                        //���O�C���Z�b�V�����e�[�u���̌�������.size()<�����P�ʂ̏ꍇ break
                        if (l_loginSessionList.size() < l_searchUnits)
                        {
                            break;
                        }
                    }
    
                    if(log.ison())
                    {
                        log.debug("���������� ���O�A�E�g���=" + l_logoutMap.size());
                    }
                    
                    //���O�A�E�g��₠��ꍇ�A�p��
                    if (l_logoutMap.size() > 0)
                    {                
                        
                        //�ڋq�}�X�^�[�e�[�u������������B
                        //[����]
                        //�E�A�J�E���gID IN ���O�C��ID�ꗗ
                        //�Eand ����ID => this.���N�G�X�g�f�[�^.From����ID
                        //�Eand ����ID <= this.���N�G�X�g�f�[�^.To����ID
        
                        l_objList = new ArrayList();
        
                        //���O�A�E�g���Map�̃L�[Set�̃T�C�Y��Loop
                        long l_cnt = 0;
                        boolean l_start = true;
                        for (Iterator iter = l_logoutMap.keySet().iterator(); iter.hasNext();)
                        {
                            Long l_loginId = (Long) iter.next();
                            //IN (���O�C��ID,....)�̕�������쐬����B
                            //�����P�ʂ��ƂɃe�[�u���������s���B
                            if (!l_start)
                            {
                                l_sbWhere.append(",");
                            }
                            else
                            {
                                l_sbWhere = new StringBuffer();
                                l_sbWhere.append(" account_id in (");
                                l_start = false;
                            }
                            l_sbWhere.append("?");
                            l_objList.add(l_loginId);
                            
                            if(++l_cnt==l_searchUnits || !iter.hasNext())
                            {
                                l_sbWhere.append(") and account_id >= ?");
                                l_sbWhere.append(" and account_id <= ?");
                
                                l_objList.add(requestData.accountIdFrom);
                                l_objList.add(requestData.accountIdTo);
                
                                l_vars = l_objList.toArray();
                
                                List l_accountList = l_qp.doFindAllQuery(MainAccountRow.TYPE, l_sbWhere.toString(), l_vars);
                                //�ڋq�}�X�^�[�̌������ʐ���Loop
                                l_listCnt = l_accountList.size();
                                for (int i = 0; i < l_listCnt; i++)
                                {
                                    MainAccountRow l_row = (MainAccountRow) l_accountList.get(i);
                
                                    //�A�J�E���g���Map�ɃL�[���A�J�E���gID�A�l���ڋq�I�u�W�F�N�g���Z�b�g����B
                                    //              l_accMap.put(new Long(l_row.getAccountId()),new
                                    l_accMap.put(new Long(l_row.getAccountId()), new WEB3GentradeMainAccount(l_row));
                                }
                                l_cnt=0;
                                l_start=true;
                                l_objList.clear();
                            }
                        }
        
                        if(log.ison())
                        {
                            log.debug("���������� �A�J�E���g���=" + l_accMap.size());
                        }
        
                        //�A�J�E���g���Map�̃L�[Set�̃T�C�Y��Loop
                        for (Iterator iter = l_accMap.values().iterator(); iter.hasNext();)
                        {
                            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount) iter.next();
        
                            Long l_accId = new Long(l_account.getAccountId());
        
                            //is���O�A�E�g�K�v��false�ڋq
                            boolean l_isNeedLogout = false;
        
                            //���ʂ��ƌ�t�Ώ�Map�̃L�[Set�̃T�C�Y��Loop
                            for (Iterator iterator = l_docDeliveryMap.keySet().iterator(); iterator.hasNext();)
                            {
                                boolean l_isMustDeliver = false;
                                String l_docCateCode = (String) iterator.next();
                                //���ʂ��ƌ�t�Ώ�Map�����t�Ώۂ��擾����B
                                String l_deliveryTarget = (String) l_docDeliveryMap.get(l_docCateCode);
        
                                //��t�Ώہ����S�ڋq�̏ꍇ
                                if (WEB3DeliveryTargetDef.ALL_ACCOUNT.equals(l_deliveryTarget))
                                {
                                    l_isMustDeliver = true;
                                }
                                //��t�Ώہ����M�p�J�ݍόڋq�̏ꍇ
                                else if (WEB3DeliveryTargetDef.MARGIN_OPENED_ACCOUNT.equals(l_deliveryTarget))
                                {
                                    //�M�p�����J�ݍς����肷��B
                                    //[����]
                                    //�ٍϋ敪�F "�w��Ȃ�"
                                    if (l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
                                    {
                                        l_isMustDeliver = true;
                                    }
        
                                }
                                //��t�Ώہ����敨�E�I�v�V�����J�ݍόڋq�̏ꍇ
                                else if (WEB3DeliveryTargetDef.FUTURE_OPTION_OPENED_ACCOUNT.equals(l_deliveryTarget))
                                {
                                    //is�敨OP�����J��(�敨)����true ����
                                    // is�敨OP�����J��(�I�v�V����)����true�̏ꍇ
                                    if (l_account.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES)
                                            || l_account.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION))
                                    {
                                        l_isMustDeliver = true;
                                    }
        
                                }
                                if (l_isMustDeliver)
                                {
                                    //���ʂ��ƌ�t�σA�J�E���gMap����A�J�E���gList���擾����B
                                    List l_accList = (List) l_deliveredAccMap.get(l_docCateCode);
        
                                    //�A�J�E���gList����null ���� �A�J�E���gList�ɃA�J�E���gID���܂܂�Ă��Ȃ��ꍇ
                                    if (l_accList == null || !l_accList.contains(l_accId))
                                    {
                                        //is���O�A�E�g�K�v==true
                                        l_isNeedLogout = true;
                                        break;
                                    }
                                }
                            }
                            //is���O�A�E�g�K�v����false�̏ꍇ(�����O�A�E�g���s�v�̏ꍇ)
                            if (! l_isNeedLogout)
                            {
                                //���O�A�E�g���Map���猻�݂̃A�J�E���gID���폜����B
                                l_logoutMap.remove(l_accId);
                            }
        
                        }
        
                        if(log.ison())
                        {
                            log.debug("���͏��ʂ̌�t�Ώی����Ə��ʌ�t�� ��r�� ���O�A�E�g�Ώیڋq��=" + l_logoutMap.size());
                        }
        
                        WEB3AdminFPTForceLogoutUnitService l_service = (WEB3AdminFPTForceLogoutUnitService) Services
                                .getService(WEB3AdminFPTForceLogoutUnitService.class);
        
                        //���O�A�E�g���Map�̒lSet�̃T�C�Y��Loop
                        for (Iterator iter = l_logoutMap.values().iterator(); iter.hasNext();)
                        {
                            List l_valueList = (List) iter.next();
                            //���O�C���Z�b�V����Row��List�̃T�C�Y��Loop
                            for (Iterator iterator = l_valueList.iterator(); iterator.hasNext();)
                            {
                                //���O�A�E�g���{�Z�b�V����List.add(�Z�b�V����ID)
                                LoginSessionRow l_row = (LoginSessionRow) iterator.next();
                                l_logoutSessionList.add(new Long(l_row.getSessionId()));
                                try
                                {
                                    //���O�A�E�g����������
                                    //[����]
                                    //���O�C���Z�b�V����Row�F ���ݏ������̃��O�C���Z�b�V����Row
                                    l_service.logout(l_row);
                                    //��������++
                                    l_count++;
                                }
                                catch (WEB3BaseException e1)
                                {
                                    log.error("�ꌏ�������O�A�E�g�G���[�B", e1);
                                    //���g���C������0�̏ꍇ�Ais�G���[=true
                                    if (l_retryCount == 0)
                                    {
                                        l_isError = true;
                                    }
                                }
                            }
        
                        }
        
                        if(log.ison())
                        {
                            log.debug("���O�A�E�g���{�Z�b�V������=" + l_logoutSessionList.size());
                            log.debug("���O�A�E�g���{�����Z�b�V������=" + l_count);
                        }
                        //���g���C����Loop
                        for (int i = 0; i < l_retryCount; i++)
                        {
        
                            if(log.ison())
                            {
                                log.debug("���g���C ���{ index=" + i);
                            }
        
                            //���g���C���O�A�E�g���{List
                            List l_retryList = new ArrayList();
                            //�����J�E���g 0�ŏ�����
                            l_execCount = 0;
                            //�V�X�e���������擾
                            // ���O�C���Z�b�V�����e�[�u���ւ̌����Ȃ̂ŁAGtlUtils.getSystemTimestamp()�ł͂Ȃ��ASystem.currentTimeMillis()���g�p����
                            l_now = new Timestamp(System.currentTimeMillis());
                            l_strNow = WEB3DateUtility.formatDate(l_now, DATE_FORMAT);
                            //���[�v
                            while (true)
                            {
                                //���O�C���Z�b�V�����e�[�u���̌����̏���
                                //[����]
                                //�T�u�N�G�������s����
                                //session_id in
                                //(select session_id from
                                //     (select session_id
                                //      from login_session
                                //      where �L���t���O = �L��
                                //      and �������t > �V�X�e������
                                //      and ���O�C���^�C�vID = getTypeId()
                                //      and ���O�C��ID => this.���N�G�X�g�f�[�^.From����ID
                                //      and ���O�C��ID <= this.���N�G�X�g�f�[�^.To����ID
                                //      order by session_id
                                //      )
                                //where rownum > �����P��*���[�v��[0..n]
                                //and rownum <= �����P��*(���[�v��[0..n]+1)
                                //)
                                Long l_rownumFrom = new Long(l_searchUnits * l_execCount);
                                Long l_rownumTo = new Long(l_searchUnits * (++l_execCount));
                                l_sbWhere = new StringBuffer();
                                l_sbWhere.append("session_id in (select session_id from (select rownum as rowno,session_id from login_session where");
                                l_sbWhere.append(" validity = ?");
                                l_sbWhere.append(" and to_char(expiration_date,'YYYYMMDDHH24MISS') > ?");
                                l_sbWhere.append(" and type_id in (" +l_typeConditionWhere.toString()+ ")");
                                l_sbWhere.append(" and login_id >= ?");
                                l_sbWhere.append(" and login_id <= ?");
                                l_sbWhere.append(" order by session_id)");
                                l_sbWhere.append(" where rowno > ?");
                                l_sbWhere.append(" and rowno <= ? )");
        
                                l_objList = new ArrayList();
                                l_objList.add(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_VALID_USER_LOGOUT);
                                l_objList.add(l_strNow);
                                l_objList.addAll(l_typeConditionList);
                                l_objList.add(requestData.accountIdFrom);
                                l_objList.add(requestData.accountIdTo);
                                l_objList.add(l_rownumFrom);
                                l_objList.add(l_rownumTo);
                                
                                l_vars = l_objList.toArray();
        
                                //���O�C���Z�b�V�����e�[�u��������
                                List l_loginSessionList = l_qp.doFindAllQuery(LoginSessionRow.TYPE,l_sbWhere.toString(), null,null,l_vars,l_rowType);
        
                                //���O�C���Z�b�V�����e�[�u���̌������ʐ���Loop
                                l_listCnt = l_loginSessionList.size();
                                for (int j = 0; j < l_listCnt; j++)
                                {
                                    LoginSessionRow l_row = (LoginSessionRow) l_loginSessionList.get(j);
                                    Long l_sessionId = new Long(l_row.getSessionId());
                                    //���O�A�E�g���{�Z�b�V����List�Ɍ��݂̃Z�b�V����ID�����݂���ꍇ
                                    if (l_logoutSessionList.contains(l_sessionId))
                                    {
                                        //���g���C���O�A�E�g���{List�Ɍ��݂̃��O�C���Z�b�V����Row��ǉ�����B
                                        l_retryList.add(l_row);
                                    }
                                }
                                //���O�C���Z�b�V�����e�[�u���̌�������.size()<�����P�ʂ̏ꍇ
                                if (l_listCnt < l_searchUnits)
                                {
                                    break;
                                }
                            }
        
                            //���g���C���O�A�E�g���{List�̐���Loop
                            l_listCnt = l_retryList.size();
                            for (int j = 0; j < l_listCnt; j++)
                            {
                                LoginSessionRow l_row = (LoginSessionRow) l_retryList.get(j);
                                try
                                {
                                    l_service.logout(l_row);
                                }
                                catch (WEB3BaseException e1)
                                {
                                    //�G���[�������́A���O�o�͂��đ��s
                                    log.error("�ꌏ�������O�A�E�g�G���[�B", e1);
                                    //�Ō��Loop�̏ꍇ�Ais�G���[=true
                                    if (i == l_retryCount - 1)
                                    {
                                        l_isError = true;
                                    }
                                }
                            }
        
                            if(log.ison())
                            {
                                log.debug("���g���C ���O�A�E�g���{��=" + l_retryList.size());
                                log.debug("���g���C ���O�A�E�g���{�Ώۏڍ�=" + l_retryList);
                            }
        
                        }
                    }
                    else
                    {
                        if(log.ison())
                        {
                            log.debug("���O�A�E�g���Ȃ� �����I��");
                        }
                    }
                }
                else
                {
                    if(log.ison())
                    {
                        log.debug("���O�C���^�C�v�������ʂȂ��@@�����I��");
                    }
                }
            }
            catch(Exception l_ex)
            {
                l_isError = true;
                log.error("�������O�A�E�g�������ɗ�O���������܂����B" + l_ex.getMessage(), l_ex);
            }

            //ThreadLocal��TIMESTAMP_TAG��null�Ń��Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

            //�e�[�u�����X�V����B
            //[����]
            //arg0�F �Ǘ��� ���ʖ����� �������O�A�E�g���s����TransactionCallback�̖߂�l�̃R�s�[�ɒl���Z�b�g��������
            //���ʖ����� �������O�A�E�g���s���ʃe�[�u��
            //�I������ ��GtlUtils.getSystemTimestamp()
            //�������� ����������
            //���s�X�e�[�^�X�敪��is�G���[ ? �G���[ : ������
            //�X�V���t ���I������
            DocForceLogoutRunStatusParams l_execParam = new DocForceLogoutRunStatusParams(l_exeResultRow);
            l_execParam.setEndTimestamp(GtlUtils.getSystemTimestamp());
            l_execParam.setProcessCount(l_count);
            l_execParam.setStatus(l_isError ? WEB3DocForceLogoutRunStatusStatusDef.PROCESS_ERROR : WEB3DocForceLogoutRunStatusStatusDef.PROCESSED);
            l_execParam.setLastUpdatedTimestamp(l_execParam.getEndTimestamp());

            l_qp.doUpdateQuery(l_execParam);

            //(*)�r�����b�N�����f�[�����g���K�[�e�[�u���̃��R�[�h��"���ғ�"��update����B
            //�f�[�����g���K�[�e�[�u��
            //�@@�@@������� �����ғ�
            //�@@�@@�ŏI�������� ��GtlUtils.getSystemTimestamp()
            DaemonTriggerParams l_daemonParam = new DaemonTriggerParams(l_daemonRow);
            l_daemonParam.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            l_daemonParam.setTriggerDate(GtlUtils.getSystemTimestamp());

            l_qp.doUpdateQuery(l_daemonParam);

        }
        catch (DataNetworkException l_dataException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dataException.getMessage(), l_dataException);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataCallbackException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dataCallbackException.getMessage(), l_dataCallbackException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dataQueryException.getMessage(), l_dataQueryException);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        finally
        {
            //clearAccountIdCtx( )
            if (l_racService != null)
            {
                l_racService.clearAccountIdCtx();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get�v���p�e�B
     * 
     * �P�j�����@@����.get�V�X�e���v���t�@@�����X(����.�v���p�e�B�E�L�[)
     * 
     * �Q�j�l�̔��� �P�j�̒l != null�̏ꍇ�A�P�j�̒l��long�ɂ��ĕԂ��B ����ȊO�̏ꍇ�A�f�t�H���g�l��Ԃ��B
     * 
     * @@param �v���p�e�B�E�L�[ -
     *            �v���p�e�B�E�L�[
     * @@param �f�t�H���g�l -
     *            �f�t�H���g�l
     * @@return long
     * @@roseuid 47D758C00306
     */
    public long getProperties(String l_proKey, long l_defaultValue)
    {
        String l_value = null;
        try
        {
            l_value = WEB3AdminFPTCommon.getSystemPreferences(l_proKey);
            if (l_value != null)
            {
                return Long.valueOf(l_value).longValue();
            }
            else
            {
                return l_defaultValue;
            }
        }
        catch (WEB3BaseException e)
        {
            log.warn("�v���p�e�B���擾����Ƃ��A�G���[�������B�f�t�H�[���g�l���g�p�B", e);
            return l_defaultValue;
        }
        catch (NumberFormatException e)
        {
            log.warn("�v���p�e�B��long�ɕύX����Ƃ��A�G���[�������B�f�t�H�[���g�l���g�p�B", e);
            return l_defaultValue;
        }
    }

    /**
     * �Ǘ��� ���ʖ����� �������O�A�E�g�f�[�����g���K�[TransactionCallback
     * �i�g�����U�N�V���������FTX_JOIN_EXISTING�j
     * 
     * @@author ��
     * @@version 1.0
     */
    public class WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback implements TransactionCallback
    {

        /**
         * �X���b�hNo
         */
        private long threadNo;

        /**
         * @@roseuid 47DF4FE4020C
         */
        public WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback()
        {

        }

        /**
         * �R���X�g���N�^�B
         * 
         * ���������g�̓������ڂɃZ�b�g����B
         * 
         * @@param �X���b�hNo -
         *            �X���b�hNo
         * @@roseuid 47D656F0010C
         */
        public WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback(long l_threadNo)
        {
            this.threadNo = l_threadNo;
        }

        /**
         * this.�X���b�hNo�ɊY������f�[�����g���K�[�e�[�u���� ���R�[�h�����b�N����B
         * 
         * �P�j DB���� �ȉ��̏����ɊY������f�[�����g���K�[�e�[�u���� ���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB
         * 
         * [����] �X���b�h�ԍ� = this.�X���b�hNo
         * 
         * �Q�j �������ʂ�ԋp����B ���������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��
         * ��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B
         * 
         * @@return java.lang.Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 47D657BB009B
         */
        public java.lang.Object process() throws com.fitechlabs.xtrade.kernel.data.DataNetworkException,
                com.fitechlabs.xtrade.kernel.data.DataQueryException, com.fitechlabs.xtrade.kernel.data.DataCallbackException
        {
            //�P�j DB����
            //�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����
            //���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            String l_strWhere = " thread_no = ? ";
            String l_strCondition = " for update nowait ";
            Object l_bindVars[] = { new Long(threadNo) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(DaemonTriggerRow.TYPE, l_strWhere, l_strCondition, l_bindVars);

            //�Q�j �������ʂ�ԋp����B
            //�������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��<BR>
            // ��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B
            if (l_lisRows.isEmpty())
            {
                log.error("�����X���b�h�̐�L���b�N�Ɏ��s����");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_lisRows.get(0);
            }
        }
    }

    /**
     * �Ǘ��� ���ʖ����� �������O�A�E�g���s����TransactionCallback
     * 
     * @@author ��
     * @@version 1.0
     */
    public class WEB3AdminFPTForceLogoutExecuteResultTransactionCallback implements TransactionCallback
    {

        /**
         * �،���ЃR�[�h
         */
        private String institutionCode;

        /**
         * From����ID
         */
        private long accountIdFrom;

        /**
         * To����ID
         */
        private long accountIdTo;

        /**
         * ���ʎ�ރR�[�h�ꗗ
         */
        private String[] documentCategoryArr;

        /**
         * �Ǘ���
         */
        private WEB3Administrator admin;

        /**
         * @@roseuid 47DF4FE4024A
         */
        public WEB3AdminFPTForceLogoutExecuteResultTransactionCallback()
        {

        }

        /**
         * �R���X�g���N�^�B
         * 
         * ���������g�̓������ڂɃZ�b�g����B
         * 
         * @@param l_institutionCode -
         *            �،���ЃR�[�h
         * @@param l_fromAccountID -
         *            From����ID
         * @@param l_toAccountID -
         *            To����ID
         * @@param l_docCatDivArr -
         *            ���ʎ�ރR�[�h�ꗗ
         * @@param l_admin -
         *            �Ǘ���
         * @@roseuid 47D658D00103
         */
        public WEB3AdminFPTForceLogoutExecuteResultTransactionCallback(String l_institutionCode, long l_fromAccountID, long l_toAccountID,
                String[] l_docCatDivArr, WEB3Administrator l_admin)
        {
            institutionCode = l_institutionCode;
            accountIdFrom = l_fromAccountID;
            accountIdTo = l_toAccountID;
            documentCategoryArr = l_docCatDivArr;
            admin = l_admin;
        }

        /**
         * �w����e�ŏ��ʖ����� �������O�A�E�g���s���ʃe�[�u�����������A �P���R�[�h�̓o�^�i�f�[�^�Ȃ����j�^�X�V�i�f�[�^���莞�j���s���B
         * 
         * �P�j DB���� �w����e�ŁA���ʖ����� �������O�A�E�g���s���ʃe�[�u������������B
         * 
         * ----------------------------------------------------- ������������ �،���ЃR�[�h =
         * this.�،���ЃR�[�h ���� From����ID = this.From����ID
         * -----------------------------------------------------
         * 
         * �Q�j �Y���f�[�^�̗L���ɂ��A�ȉ��̒ʂ蕪�򂷂�B
         * 
         * �Q�|�P�j �Y���f�[�^�Ȃ��̏ꍇ
         * 
         * �Ethis.���ʖ����� �������O�A�E�g���s����Row�𐶐�����B �،���ЃR�[�h ��this.�،���ЃR�[�h From����ID
         * ��this.From����ID To����ID ��this.To����ID �J�n����
         * ��GtlUtils.getSystemTimestamp() �I������ ��null
         * ���ʎ�ރR�[�h�ꗗ��this.���ʎ�ރR�[�h�ꗗ�̃R���}��؂� �������� ��null ���s�X�e�[�^�X�敪�������� �X�V�҃R�[�h
         * ���Ǘ���.getAdministratorCode() �쐬���t ���J�n���� �X�V���t ���J�n����
         * 
         * �E�C���T�[�g����B
         * 
         * �Q�|�Q�j �Y���f�[�^����̏ꍇ
         * 
         * �E�擾�������R�[�h.���s�X�e�[�^�X�敪 = "������"�̏ꍇ�́A �u�w��AP�N�����i��d�N���G���[�j�v�̗�O��throw����B
         * 
         * �E�擾�������R�[�h.���s�X�e�[�^�X�敪 != "������"�̏ꍇ�́A�ȉ��̏������s���B ���ʖ�����
         * �������O�A�E�g���s����Row�𐶐�����B�R���X�g���N�^(�擾�������R�[�h) To����ID ��this.To����ID �J�n����
         * ��GtlUtils.getSystemTimestamp() �I������ ��null
         * ���ʎ�ރR�[�h�ꗗ��this.���ʎ�ރR�[�h�ꗗ�̃R���}��؂� �������� ��null ���s�X�e�[�^�X�敪�������� �X�V�҃R�[�h
         * ���Ǘ���.getAdministratorCode() �쐬���t ���J�n���� �X�V���t ���J�n����
         * 
         * �E�A�b�v�f�[�g����B
         * 
         * �R�j���ʖ����� �������O�A�E�g���s����Row��Ԃ��B
         * 
         * @@return java.lang.Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 47D65DA901AC
         */
        public java.lang.Object process() throws com.fitechlabs.xtrade.kernel.data.DataNetworkException,
                com.fitechlabs.xtrade.kernel.data.DataQueryException, com.fitechlabs.xtrade.kernel.data.DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            DocForceLogoutRunStatusRow l_resultRow = null;

            StringBuffer l_docSb = new StringBuffer();
            for (int i = 0; i < documentCategoryArr.length; i++)
            {
                if (i != 0)
                {
                    l_docSb.append(",");
                }
                l_docSb.append(documentCategoryArr[i]);
            }

            // �P�j DB����
            // �@@�w����e�ŁA���ʖ����� �������O�A�E�g���s���ʃe�[�u������������B
            // �@@-----------------------------------------------------
            // �@@������������
            // �@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h
            // �@@���� From����ID = this.From����ID
            // �@@-----------------------------------------------------
            String l_strWhere = " institution_code = ? and account_id_from = ?";
            Object l_bindVars[] = { institutionCode, new Long(accountIdFrom) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(DocForceLogoutRunStatusRow.TYPE, l_strWhere, l_bindVars);

            // �Q�|�P�j �Y���f�[�^�Ȃ��̏ꍇ
            if (l_lisRows.size() < 1)
            {
                //�Ethis.���ʖ����� �������O�A�E�g���s����Row�𐶐�����B
                // �@@�@@�،���ЃR�[�h ��this.�،���ЃR�[�h
                // �@@�@@From����ID ��this.From����ID
                // �@@�@@To����ID ��this.To����ID
                // �@@�@@�J�n���� ��GtlUtils.getSystemTimestamp()
                // �@@�@@�I������ ��null
                // �@@�@@���ʎ�ރR�[�h�ꗗ��this.���ʎ�ރR�[�h�ꗗ�̃R���}��؂�
                // �@@�@@�������� ��null
                // �@@�@@���s�X�e�[�^�X�敪��������
                // �@@�@@�X�V�҃R�[�h ���Ǘ���.getAdministratorCode()
                // �@@�@@�쐬���t ���J�n����
                // �@@�@@�X�V���t ���J�n����
                // �@@�E�C���T�[�g����B
                DocForceLogoutRunStatusParams l_param = new DocForceLogoutRunStatusParams();
                l_param.setInstitutionCode(institutionCode);
                l_param.setAccountIdFrom(accountIdFrom);
                l_param.setAccountIdTo(accountIdTo);
                l_param.setStartTimestamp(GtlUtils.getSystemTimestamp());
                l_param.setEndTimestamp(null);
                l_param.setDocumentCategoryList(l_docSb.toString());
                l_param.setProcessCount(null);
                l_param.setStatus(WEB3DocForceLogoutRunStatusStatusDef.PROCESSING);
                l_param.setLastUpdater(admin.getAdministratorCode());
                l_param.setCreatedTimestamp(l_param.getStartTimestamp());
                l_param.setLastUpdatedTimestamp(l_param.getStartTimestamp());

                l_queryProcesser.doInsertQuery(l_param);
                l_resultRow = l_param;
            }
            // �Q�|�Q�j �Y���f�[�^����̏ꍇ
            else
            {
                DocForceLogoutRunStatusRow l_row = (DocForceLogoutRunStatusRow) l_lisRows.get(0);
                //�@@�E�擾�������R�[�h.���s�X�e�[�^�X�敪 = "������"�̏ꍇ�́A
                //�@@�u�w��AP�N�����i��d�N���G���[�j�v�̗�O��throw����B
                if (WEB3DocForceLogoutRunStatusStatusDef.PROCESSING.equals(l_row.getStatus()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_01992, this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���ʖ����� �������O�A�E�g���s���� �������B");
                }
                //�@@�E�擾�������R�[�h.���s�X�e�[�^�X�敪 != "������"�̏ꍇ�́A�ȉ��̏������s���B
                // �@@�@@���ʖ�����
                //     �������O�A�E�g���s����Row�𐶐�����B�R���X�g���N�^(�擾�������R�[�h)
                // �@@�@@�@@To����ID ��this.To����ID
                // �@@�@@�@@�J�n���� ��GtlUtils.getSystemTimestamp()
                // �@@�@@�@@�I������ ��null
                // �@@�@@�@@���ʎ�ރR�[�h�ꗗ��this.���ʎ�ރR�[�h�ꗗ�̃R���}��؂�
                // �@@�@@�@@�������� ��null
                // �@@�@@�@@���s�X�e�[�^�X�敪��������
                // �@@�@@�@@�X�V�҃R�[�h ���Ǘ���.getAdministratorCode()
                // �@@�@@�@@�쐬���t ���J�n����
                // �@@�@@�@@�X�V���t ���J�n����
                else
                {
                    DocForceLogoutRunStatusParams l_param = new DocForceLogoutRunStatusParams(l_row);
                    l_param.setAccountIdTo(accountIdTo);
                    l_param.setStartTimestamp(GtlUtils.getSystemTimestamp());
                    l_param.setEndTimestamp(null);
                    l_param.setDocumentCategoryList(l_docSb.toString());
                    l_param.setProcessCount(null);
                    l_param.setStatus(WEB3DocForceLogoutRunStatusStatusDef.PROCESSING);
                    l_param.setLastUpdater(admin.getAdministratorCode());
                    l_param.setCreatedTimestamp(l_param.getStartTimestamp());
                    l_param.setLastUpdatedTimestamp(l_param.getLastUpdatedTimestamp());

                    l_queryProcesser.doUpdateQuery(l_param);
                    l_resultRow = l_param;
                }

            }

            //�R�j���ʖ����� �������O�A�E�g���s����Row��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_resultRow;
        }
    }
}@
