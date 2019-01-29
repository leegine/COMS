head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecuteDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Admin Order Execute Data Manager
                 : (WEB3AdminOrderExecuteDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 ���@@�_�O(���u) ���f���ύX�_087�E090
                 : 2005/10/20 ���@@�_�O(���u) ���f���ύX�_092
                 : 2006/08/21 �юu��(���u) ���f���ύX�_062
                 : 2006/10/06 ������(���u) ���f���ύX�_078
                 : 2007/01/30 ������(���u) ���f���ύX�_088
Revesion History : 2007/07/01 �����F(���u) ���f���ύX�_102 104
Revesion History : 2008/10/02 ���V(SRA) �y�Ǘ��Ғ������Ɖ�z�d�l�ύX�Ǘ��䒠�i���f���jNo.128�A129
*/

package webbroker3.adminorderexecinquiry;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.adminorderexecinquiry.define.WEB3AdminExecTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminTradingTypeDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORDetailDispInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommon;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.gentrade.WEB3GentradeTrader;

/**
 * (�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��)<BR>
 * �Ǘ��Ғ������Ɖ��DB I/O�A�f�[�^�ϊ��Ȃǂ��Ǘ�����N���X�B<BR>
 * <BR>
 * -----<English>---------------<BR>
 * <BR>
 * WEB3AdminOrderExecuteDataManager<BR>
 * It is a class to manage DB I/O and data conversion in
 * web3-adminorderexecinquiry<BR>
 * <BR>
 * @@author Anil
 * @@version 1.0
 */
public class WEB3AdminOrderExecuteDataManager
{

    /** Log Variable.<BR>*/
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOrderExecuteDataManager.class);

    /**
     * @@roseuid 4213049F0046
     */
    public WEB3AdminOrderExecuteDataManager()
    {

    }

    /**
     * (create���ʌ�������������)<BR>
     * <BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j���X��������������������ɒǉ�����B<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B <BR>
     * <BR>
     * �@@�������������� += "branch_id in (?, ?,,,) " <BR>
     * <BR>
     * �R�j�p�����[�^.���N�G�X�g�f�[�^.����ID != null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and order_id = ? "<BR>
     * <BR>
     * �S�j����������<BR>
     * �@@�S�|�P�j�p�����[�^.���N�G�X�g�f�[�^.������ != null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and biz_date = ? "<BR>
     * <BR>
     * �@@�S�|�Q�j�p�����[�^.���N�G�X�g�f�[�^.������ == null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and biz_date >= ? "<BR>
     * <BR>
     * �T�j�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get�ڋq()�̖߂�l��<BR>
     * �@@�v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and account_id in (?, ?,,,) "<BR>
     * <BR>
     * �@@[get�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �U�j�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j != null�̏ꍇ�A<BR>
     * �@@���҃R�[�h�iSONAR�j����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and sonar_trader_code = ? "<BR>
     * <BR>
     * �V�j�p�����[�^.���N�G�X�g�f�[�^.���i�敪 != null�̏ꍇ�A<BR>
     * �@@���i��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "��������"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_categ = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type in (?, ?) "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "�M�p���"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_categ in (?, ?, ?) "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "�����~�j����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_categ = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type in (?, ?) "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == ("�I�v�V����" or "�敨")�̏ꍇ]<BR>
     * �@@�@@�������������� += "and future_option_div = ? "<BR>
     * <BR>
     * �W�j�p�����[�^.���N�G�X�g�f�[�^.����敪 != null�̏ꍇ�A<BR>
     * �@@����𔻕ʂ����������������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����敪 == "����O����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and sonar_traded_code = ? "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����敪 == "�������t����"�̏ꍇ] <BR>
     * �@@�@@�������������� += "and order_type = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and sonar_traded_code != ? " <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_type = ? "<BR>
     * <BR>
     * �X�j�p�����[�^.���N�G�X�g�f�[�^.���������敪 != null�̏ꍇ�A<BR>
     * �@@�o����܂Œ������ǂ����𔻕ʂ��������<BR>
     * �@@��������������ɒǉ�����B �i���j <BR>
     * <BR>
     * �@@�@@�y���p�����[�^.���N�G�X�g�f�[�^��instanceof�Ŕ��ʂ��A�����𕪊򂷂�z <BR>
     * �@@�@@�@@�@@�p�����[�^.���N�G�X�g��"�Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@���������敪�͌�������������ɒǉ����Ȃ��B <BR>
     * �@@�@@�@@�@@�@@�i�Ǝ����\�b�h�Œǉ����s���j <BR>
     * <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���������敪 == "��������"�̏ꍇ]  <BR>
     * �@@�@@�������������� += "and first_order_unit_id is null "  <BR>
     * �@@[��L�ȊO�̏ꍇ]  <BR>
     * �@@�@@�������������� += "and first_order_unit_id is not null " <BR>
     * <BR>
     * �P�O�j�p�����[�^.���N�G�X�g�f�[�^.�����o�H�敪 != null�̏ꍇ�A<BR>
     * �@@�����o�H�敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and order_root_div = ? "<BR>
     * <BR>
     * �P�P�j�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 != null�̏ꍇ�A<BR>
     * �@@������Ԃ𔻕ʂ����������������������ɒǉ�����B �i���j <BR>
     * <BR>
     * �@@�@@�y���p�����[�^.���N�G�X�g�f�[�^��instanceof�Ŕ��ʂ��A�����𕪊򂷂�z <BR>
     * �@@�@@�@@�@@�p�����[�^.���N�G�X�g��"�Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@������ԋ敪�͌�������������ɒǉ����Ȃ��B <BR>
     * �@@�@@�@@�@@�@@�i�Ǝ����\�b�h�Œǉ����s���j <BR>
     * <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�ꕔ����"�̏ꍇ] <BR>
     * �@@�@@�������������� += "and executed_quantity is not null"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and executed_quantity != ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_open_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�S������"�̏ꍇ]<BR>
     * �@@�@@�������������� += " and (executed_quantity is null " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "or executed_quantity = ?) " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_open_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_open_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�蓮����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_open_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and error_reason_code in (?, ?) "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z��"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and first_order_unit_id > ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_status = ? "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z���s"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and expiration_date >= ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_open_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and expiration_status = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and error_reason_code not in (?, ?) "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and first_order_unit_id is not null "<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_status = ? "<BR>
     * <BR>
     * �P�Q�j�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 != null�̏ꍇ�A<BR>
     * �@@����Ԃ𔻕ʂ����������������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 == "�����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and executed_quantity is null "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "or executed_quantity == ? "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 == "�ꕔ����"�܂���"��菈����(�ꕔ����)"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and executed_quantity is not null "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and executed_quantity != ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and executed_quantity < confirmed_quantity "<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 == "�S������"�܂���"��菈����(�S������)"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and executed_quantity = confirmed_quantity "<BR>
     * <BR>
     * �P�R�j�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 != null�̏ꍇ�A<BR>
     * �@@������Ԃ𔻕ʂ����������������������ɒǉ�����B �i���j<BR>
     * <BR>
     * �@@�@@�y���p�����[�^.���N�G�X�g�f�[�^��instanceof�Ŕ��ʂ��A�����𕪊򂷂�z<BR>
     * �@@�@@�@@�@@�p�����[�^.���N�G�X�g���ȉ������ꂩ�ɊY������ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�E"�Ǘ��ҁE�O�������o�����͈ꗗ���N�G�X�g"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�Ǘ��ҁE�O�������������Ɖ�N�G�X�g"<BR>
     * �@@�@@�@@�@@�����t���O����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += " and temporary_execution_flag = ?"<BR>
     * <BR> 
     * �P�S�j�p�����[�^.���N�G�X�g�f�[�^.��������敪 != null�̏ꍇ�A<BR>
     * �@@��������敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and modify_cancel_type = ? "<BR>
     * <BR>
     * �P�T�j�p�����[�^.���N�G�X�g�f�[�^.��������From != null�̏ꍇ�A<BR>
     * �@@��������From��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and received_date_time >= to_date(?,'yyyyMMddhh24mi') "<BR>
     * <BR>
     * �P�U�j�p�����[�^.���N�G�X�g�f�[�^.��������To != null�̏ꍇ�A<BR>
     * �@@��������To��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and received_date_time <= to_date(?,'yyyyMMddhh24mi') "<BR>
     * <BR>
     * �P�V�j�p�����[�^.���N�G�X�g�f�[�^.��n�� != null�̏ꍇ�A<BR>
     * �@@��n������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and delivery_date = ? "<BR>
     * <BR>
     * �P�W�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41B3B57B00FC
     */
    public String createCommonQueryString(
        String l_strInstitutionCode,
        WEB3AdminOROrderExecutionRefCommonRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createCommonQueryString(String l_strInstitutionCode, "
                + "WEB3AdminOROrderExecutionRefCommonRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMainAccount[] l_gntradeMainAccounts = null;

        //Step1Create an instance of l_strQueryCond
        StringBuffer l_strQueryCond = new StringBuffer();

        int l_intlength = 0;
        int l_intcount = 0;

        //Step2 Add the condition about branch to l_strQueryCond
        l_intlength = l_request.branchCode.length;
        l_strQueryCond.append("");
        if (l_intlength == 1)
        {
            l_strQueryCond.append("branch_id in (?) ");
        } else
        {
            for (l_intcount = 0; l_intcount < l_intlength; l_intcount++)
            {
                if (l_intcount == 0)
                {
                    l_strQueryCond.append("branch_id in (?");
                } else if (l_intcount == (l_intlength - 1))
                {
                    l_strQueryCond.append(",?)");
                } else
                {
                    l_strQueryCond.append(",?");
                }
            }
        }

        /*
          * Step3 If l_request.orderId != null
          * Add orderId to l_strQueryCond
          */
        if (l_request.orderId != null)
        {
            l_strQueryCond.append(" and order_id = ?");
        }

        /*
          * Step4.1 l_request.orderBizDate != null
          * Add the following condition about orderBizDay to l_strQueryCond
          */
        if (l_request.orderBizDate != null)
        {
            l_strQueryCond.append(" and biz_date = ?");
        } else
        {
            l_strQueryCond.append(" and biz_date >= ? ");
        }

        /*
         *Step5 l_request.accountCode != null
         *Add "?" as many as the number of the elements of
         *return value of getAccountList()
         */
        if (l_request.accountCode != null)
        {
            l_gntradeMainAccounts =
                getAccountList(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            l_intlength = l_gntradeMainAccounts.length;
            if (l_intlength == 1)
            {
                l_strQueryCond.append(" and account_id in (?) ");
            } else
            {
                for (l_intcount = 0; l_intcount < l_intlength; l_intcount++)
                {
                    if (l_intcount == 0)
                    {
                        l_strQueryCond.append(" and account_id in (?");
                    } else if (l_intcount == (l_intlength - 1))
                    {
                        l_strQueryCond.append(",?)");
                    } else
                    {
                        l_strQueryCond.append(",?");
                    }
                }
            }
        }

        /*
          * Step6 If l_request.sonarTraderCode != null
          * Add sonarTraderCode to l_strQueryCond
          */
        if (l_request.sonarTraderCode != null)
        {
            l_strQueryCond.append(" and sonar_trader_code = ?");
        }

        //Step 7 Add the conditions about a product to l_strQueryCond
        if (l_request.productDiv != null)
        {
            if (l_request.productDiv.equals(WEB3AdminProductDivDef.EQUITY))
            {
                l_strQueryCond.append(" and order_categ = ? and order_type in (?,?) ");
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MARGIN))
            {
                l_strQueryCond.append(" and order_categ in (?,?,?) ");
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
                l_strQueryCond.append(" and order_categ = ? and order_type in (?,?) ");
            } else if (
                l_request.productDiv.equals(WEB3AdminProductDivDef.OPTION)
                    || l_request.productDiv.equals(WEB3AdminProductDivDef.FUTURE))
            {
                l_strQueryCond.append(" and future_option_div = ? ");
            }
        }

        //Step 8 )If l_request.tradingType != null
        if (l_request.tradingType != null)
        {
            if (l_request.tradingType.equals(WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET))
            {
                l_strQueryCond.append(" and sonar_traded_code = ?");
            }
            else if (l_request.tradingType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
            {
                l_strQueryCond.append(" and order_type = ? and sonar_traded_code != ?");
            }
            else
            {
                l_strQueryCond.append(" and order_type = ?");
            }
        }

        //Step 9 If l_request.expirationDateType != null
        if (!(l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest))
        {
            if (l_request.expirationDateType != null)
            {
                if (l_request.expirationDateType.equals(WEB3OrderExpirationDateTypeDef.DAY_LIMIT))
                {
                    l_strQueryCond.append(" and first_order_unit_id is null");
                } else
                {
                    l_strQueryCond.append(" and first_order_unit_id is not null");
                }
            }
        }

        //Step 10 If l_request.orderRootDiv != null
        if (l_request.orderRootDiv != null)
        {
            l_strQueryCond.append(" and order_root_div = ?");
        }

        //Step 11 If l_request.orderState != null
        if (!(l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest))
        {
            if (l_request.orderState != null)
            {
                if (l_request.orderState.equals(WEB3OrderStatusDef.PART_INAFFECTED))
                {
                    l_strQueryCond.append(" and executed_quantity is not null");
                    l_strQueryCond.append(" and executed_quantity != ?");
                    l_strQueryCond.append(" and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.FULL_INAFFECTED))
                {
                    l_strQueryCond.append(" and (executed_quantity is null");
                    l_strQueryCond.append(" or executed_quantity = ?)");
                    l_strQueryCond.append(" and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
    
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.CLOSED))
                {
                    l_strQueryCond.append(" and order_open_status = ? and expiration_status = ?");
                }else if (l_request.orderState.equals(WEB3OrderStatusDef.MANUAL_EXPIRED))
                {
                    l_strQueryCond.append(" and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
                    l_strQueryCond.append(" and error_reason_code in (?, ?)");
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.TRANSFERED))
                {
                    l_strQueryCond.append(" and first_order_unit_id > ? and order_status = ?");
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.NOT_TRANSFERED))
                {
                    l_strQueryCond.append(" and expiration_date >= ? and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
                    l_strQueryCond.append(" and error_reason_code not in (?, ?) ");
                    l_strQueryCond.append(" and first_order_unit_id is not null ");
                } else
                {
                    l_strQueryCond.append(" and order_status = ?");
                }
            }
        }

        //Step12 If l_request.execType != null
        if (l_request.execType != null)
        {
            if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE))
            {
                l_strQueryCond.append(" and (executed_quantity is null");
                l_strQueryCond.append(" or executed_quantity = ?)");
            } else if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE) ||
                    l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE))
            {
                l_strQueryCond.append(" and executed_quantity is not null");
                l_strQueryCond.append(" and executed_quantity != ?");
                l_strQueryCond.append(" and executed_quantity < confirmed_quantity");
            } else if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_ALL_COMPLETE) ||
                    l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ALL_COMPLETE))
            {
                l_strQueryCond.append(" and executed_quantity = confirmed_quantity");
            }
            
            if (l_request instanceof WEB3AdminORFeqExecuteListRequest ||
                l_request instanceof WEB3AdminORFeqOrderExecutionRefReferenceRequest)
            {
                l_strQueryCond.append(" and temporary_execution_flag = ?");
            }
        }

        //Step 13 If l_request.changeCancelDiv != null
        if (l_request.changeCancelDiv != null)
        {
            l_strQueryCond.append(" and modify_cancel_type = ?");
        }

        //Step 14 If l_request.orderStartDate != null
        if (l_request.orderStartDate != null)
        {
            l_strQueryCond.append(" and received_date_time >= to_date(?,'yyyyMMddhh24mi')");
        }

        //Step 15 If l_request.orderEndDate != null
        if (l_request.orderEndDate != null)
        {
            l_strQueryCond.append(" and received_date_time <= to_date(?,'yyyyMMddhh24mi')");
        }

        //Step 16 If l_request.deliveryDate != null
        if (l_request.deliveryDate != null)
        {
            l_strQueryCond.append(" and delivery_date = ? ");
        }

        log.exiting(STR_METHOD_NAME);

        //Step 17 Return the created l_strQueryCond
        return l_strQueryCond.toString();
    }

    /**
     * (create���ʌ��������f�[�^�R���e�i)<BR>
     * <BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j���X�����𐶐�����ArrayList�ɒǉ�����B<BR> 
     * �@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��<BR> 
     * �@@�@@�Y�����镔�XID��S��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����[�^.���N�G�X�g�f�[�^.����ID != null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j����������<BR>
     * �@@�S�|�P�j�p�����[�^.���N�G�X�g�f�[�^.������ != null�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.�������𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�S�|�Q�j�p�����[�^.���N�G�X�g�f�[�^.������ == null�̏ꍇ�A<BR>
     * �@@�@@�V�X�e�����t(*1)�̑O�c�Ɠ��𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@������ID�́A�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get�ڋq()���\�b�h�ɂ�<BR>
     * �@@�@@�擾�����S�Ă̌ڋq.����ID���Z�b�g<BR>
     * <BR>
     * �@@[get�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �U�j�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j���ȉ��̏����ŕҏW���A<BR>
     * �@@��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j��5�������̏ꍇ<BR>
     * �@@�i�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j.length < 5�j]<BR>
     * �@@�@@�E�O�h0�h�l�ɕҏW�����p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h�iSONAR�j<BR>
     * <BR>
     * �V�j�p�����[�^.���N�G�X�g�f�[�^.���i�敪 != null�̏ꍇ�A<BR>
     * �@@���i�����𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "��������"�̏ꍇ]<BR>
     * �@@�@@�EOrderCategEnum.��������<BR>
     * �@@�@@�EOrderTypeEnum.����������<BR>
     * �@@�@@�EOrderTypeEnum.����������<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "�M�p���"�̏ꍇ]<BR>
     * �@@�@@�EOrderCategEnum.�V�K������<BR>
     * �@@�@@�EOrderCategEnum.�ԍϒ���<BR>
     * �@@�@@�EOrderCategEnum.�������n����<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "�����~�j����"�̏ꍇ]<BR>
     * �@@�@@�EOrderCategEnum.��������<BR>
     * �@@�@@�EOrderTypeEnum.�����~�j��������<BR>
     * �@@�@@�EOrderTypeEnum.�����~�j��������<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�E"�I�v�V����"(�敨�^�I�v�V�����敪)<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.���i�敪 == "�敨"�̏ꍇ]<BR>
     * �@@�@@�E"�敨"(�敨�^�I�v�V�����敪)<BR>
     * <BR>
     * �W�j�p�����[�^.���N�G�X�g�f�[�^.����敪 != null�̏ꍇ�A<BR>
     * �@@����𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����敪 == "����O����"�̏ꍇ]<BR>
     * �@@�@@"����O����"(����R�[�h(SONAR))<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����敪 == "�������t����"�̏ꍇ]<BR>
     * �@@�@@�E�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get�������()�̖߂�l<BR>
     * �@@�@@�E"����O����"(����R�[�h(SONAR))<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get�������()�̖߂�l<BR>
     * <BR>
     * �@@�@@[get�������()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@����敪�F�@@�p�����[�^.���N�G�X�g�f�[�^.����敪<BR>
     * <BR>
     * �X�j�p�����[�^.���N�G�X�g�f�[�^.�����o�H�敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.�����o�H�敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�O�j�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 != null�̏ꍇ�A <BR>
     * �@@������Ԃ𔻕ʂ���������ォ�珇�ɐ�������ArrayList�ɒǉ�����B �i���j <BR>
     * <BR>
     * �@@�@@�y���p�����[�^.���N�G�X�g�f�[�^��instanceof�Ŕ��ʂ��A�����𕪊򂷂�z <BR>
     * �@@�@@�@@�@@�p�����[�^.���N�G�X�g��"�Ǘ��ҁE�敨OP�������Ɖ�N�G�X�g"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@������Ԃ𔻕ʂ��������ArrayList�ɒǉ����Ȃ��B <BR>
     * �@@�@@�@@�@@�@@�i�Ǝ����\�b�h�Œǉ����s���j <BR>
     * <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�ꕔ����"�̏ꍇ]<BR>
     * �@@�@@�E0<BR>
     * �@@�@@�E"�N���[�Y"(�����L�����)<BR>
     * �@@�@@�E"�}�[�P�b�g����"(�����敪)<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�S������"�̏ꍇ]<BR>
     * �@@�@@�E0<BR>
     * �@@�@@�E"�N���[�Y"(�����L�����)<BR>
     * �@@�@@�E"�}�[�P�b�g����"(�����敪)<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "����"�̏ꍇ]<BR>
     * �@@�@@�E"�N���[�Y"(�����L�����)<BR>
     * �@@�@@�E"�I��"(�����敪)<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�蓮����"�̏ꍇ]<BR>
     * �@@�@@�E"�N���[�Y"(�����L�����)<BR>
     * �@@�@@�E"�}�[�P�b�g����"(�����敪)<BR>
     * �@@�@@�E"�����Ǘ��Ҏ蓮������"�i�����G���[���R�R�[�h�j<BR>
     * �@@�@@�E"�敨OP�Ǘ��Ҏ蓮������"�i�����G���[���R�R�[�h�j<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z��"�̏ꍇ]<BR>
     * �@@�@@�E0<BR>
     * �@@�@@�E"��t�ρi�V�K�����j"(�������)<BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪 == "�J�z���s"�̏ꍇ] <BR>
     * �@@�@@�E�Ɩ����t(*1)<BR>
     * �@@�@@�E"�N���[�Y"(�����L�����)<BR>
     * �@@�@@�E"�I��"(�����敪)<BR>
     * �@@�@@�E"����"(�����G���[���R�R�[�h)<BR>
     * �@@�@@�E"�g���K�[�����Ǘ��Ҏ蓮������"(�����G���[���R�R�[�h)<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.������ԋ敪��ǉ�����B<BR>
     * <BR>
     * �P�P�j�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 != null�̏ꍇ�A<BR>
     * �@@����Ԃ𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 == ("�����" 
     * or "�ꕔ����" or "��菈����(�ꕔ����)")�̏ꍇ]<BR>
     * �@@�@@�E0<BR>
     * <BR>
     * �P�Q�j�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 != null�̏ꍇ�A<BR>
     * �@@������Ԃ𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B�i���j<BR>
     * <BR>
     * �@@�@@�y���p�����[�^.���N�G�X�g�f�[�^��instanceof�Ŕ��ʂ��A�����𕪊򂷂�z<BR>
     * �@@�@@�@@�@@�p�����[�^.���N�G�X�g���ȉ������ꂩ�ɊY������ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�E"�Ǘ��ҁE�O�������o�����͈ꗗ���N�G�X�g"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�Ǘ��ҁE�O�������������Ɖ�N�G�X�g"<BR>
     * �@@�@@�@@�@@�����t���O�𔻕ʂ��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.����ԋ敪 == "��菈����(�ꕔ����)"�܂���"��菈����(�S������)"�̏ꍇ]<BR>
     * �@@�@@�E"�����"<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�EDEFAULT<BR>
     * <BR>
     * �P�R�j�p�����[�^.���N�G�X�g�f�[�^.��������敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.��������敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�S�j�p�����[�^.���N�G�X�g�f�[�^.��������From != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.��������From�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�T�j�p�����[�^.���N�G�X�g�f�[�^.��������To != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.��������To�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�U�j�p�����[�^.���N�G�X�g�f�[�^.��n�� != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.��n���𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�V�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate() <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws NotFoundException NotFoundException
     * @@return String[]
     * @@roseuid 41B3B57B0112
     */
    public String[] createCommonQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminOROrderExecutionRefCommonRequest l_request)
        throws
            WEB3BaseException,
            DataFindException,
            DataQueryException,
            DataNetworkException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "createCommonQueryDataContainer(String l_strInstitutionCode, "
                + "WEB3AdminOROrderExecutionRefCommonRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOrderExecuteDataManager l_web3AdminOrderExecuteDataManager = null;
        l_web3AdminOrderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();
        WEB3GentradeMainAccount[] l_gntradeMainAccounts = null;
        String[] l_arrBranchCodes = null;
        String[] l_branchCodes = null;
        String l_accountCode = null;
        String l_orderState = null;
        Date l_datPrevBizDate = null;
        String l_straccountId = null;
        int l_intAccountLength = 0;
        int l_intCount = 0;
        int l_intbranchCodeLength = 0;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3Administrator l_administrator = null;
        Institution l_institution = null;
        Branch l_branch = null;
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strdateFormat = "yyyyMMdd";

        //Step1 Create ArrayList
        ArrayList l_arrdataList = new ArrayList();

        /*
          * Step 2 Add branch conditions to created ArrayList
          * Add all elements of l_request.branchCode to ArrayList
          */
        l_intbranchCodeLength = l_request.branchCode.length;
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_institution = l_administrator.getInstitution();
        l_accManager = new WEB3GentradeAccountManager();
        for (l_intCount = 0; l_intCount < l_intbranchCodeLength; l_intCount++)
        {
            l_branch = l_accManager.getBranch(l_institution, l_request.branchCode[l_intCount]);
            l_arrdataList.add(WEB3StringTypeUtility.formatNumber(l_branch.getBranchId()));
        }

        // Step3 If l_request.orderId != null
        if (l_request.orderId != null)
        {
            l_arrdataList.add(l_request.orderId);
        }

        //Step 4-1 l_request.orderBizDate != null
        if (l_request.orderBizDate != null)
        {
            String l_strDate = WEB3DateUtility.formatDate(l_request.orderBizDate, l_strdateFormat);
            l_arrdataList.add(l_strDate);
        } else
        {
            Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            WEB3GentradeBizDate l_dateCalc = new WEB3GentradeBizDate(l_tsBizDate);
            l_datPrevBizDate = l_dateCalc.roll(-1);
            l_arrdataList.add(WEB3DateUtility.formatDate(l_datPrevBizDate, l_strdateFormat));
        }

        // Step 5 l_request.accountCode != null
        if (l_request.accountCode != null)
        {
            l_branchCodes = l_request.branchCode;
            l_accountCode = l_request.accountCode;
            l_gntradeMainAccounts =
                this.getAccountList(l_strInstitutionCode, l_branchCodes, l_accountCode);
            l_intAccountLength = l_gntradeMainAccounts.length;

            //Set all accountId acquired at getAccountList()
            for (l_intCount = 0; l_intCount < l_intAccountLength; l_intCount++)
            {
                l_straccountId =
                    WEB3StringTypeUtility.formatNumber(
                        l_gntradeMainAccounts[l_intCount].getAccountId());
                l_arrdataList.add(l_straccountId);
            }
        }

        // Step6 If l_request.sonarTraderCode != null
        if (l_request.sonarTraderCode != null)
        {
            int l_intSonarTraderCodeLen = 5;
            if (l_request.sonarTraderCode.length() < l_intSonarTraderCodeLen)
            {
                StringBuffer l_strSonarTraderCodeFillZero = new StringBuffer();
                for (int i = l_request.sonarTraderCode.length(); i < l_intSonarTraderCodeLen; i++)
                {
                    l_strSonarTraderCodeFillZero.append("0");
                }
                l_arrdataList.add(l_strSonarTraderCodeFillZero + l_request.sonarTraderCode);
            }
            else
            {
                l_arrdataList.add(l_request.sonarTraderCode);
            }
        }

        /*
         * Step 7 If l_request.productDiv != null,
         * Add product conditions to created ArrayList
         */
        if (l_request.productDiv != null)
        {
            if (l_request.productDiv.equals(WEB3AdminProductDivDef.EQUITY))
            {
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.ASSET.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_BUY.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_SELL.intValue()));
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MARGIN))
            {
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.OPEN_MARGIN.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.CLOSE_MARGIN.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.SWAP_MARGIN.intValue()));
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.ASSET.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_BUY.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_SELL.intValue()));
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.OPTION))
            {
                l_arrdataList.add(WEB3FuturesOptionDivDef.OPTION);
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.FUTURE))
            {
                l_arrdataList.add(WEB3FuturesOptionDivDef.FUTURES);
            }
        }

        /*
          * Step8 If l_request.productDiv != null,
          * Add trading conditions to created ArrayList
          */
        if (l_request.tradingType != null)
        {
            if (l_request.tradingType.equals(WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET))
            {
                l_arrdataList.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            }
            else if (l_request.tradingType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
            {
                l_arrdataList.add(l_request.tradingType);
                l_arrdataList.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            }
            else
            {
                l_orderTypeEnum =
                    l_web3AdminOrderExecuteDataManager.getOrderType(l_request.tradingType);
                if (l_orderTypeEnum != null)
                {
                    l_arrdataList.add(String.valueOf(l_orderTypeEnum.intValue()));
                }
            }
        }

        //Step 9 Check orderRootDiv != null
        if (l_request.orderRootDiv != null)
        {
            l_arrdataList.add(l_request.orderRootDiv);
        }

        // Step10 Add Conditions judging orderState to created ArrayList sqquentially
        if (!(l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest))
        {
            l_orderState = l_request.orderState;
            if (l_request.orderState != null)
            {
                if (l_orderState.equals(WEB3OrderStatusDef.PART_INAFFECTED))
                {
                    l_arrdataList.add(String.valueOf(0));
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.FULL_INAFFECTED))
                {
                    l_arrdataList.add(String.valueOf(0));
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.CLOSED))
                {
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.EXPIRED.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.MANUAL_EXPIRED))
                {
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                    l_arrdataList.add(String.valueOf(WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED));
                    l_arrdataList.add(String.valueOf(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED));
                } else if (l_orderState.equals(WEB3OrderStatusDef.TRANSFERED))
                {
                    l_arrdataList.add(String.valueOf(0));
                    l_arrdataList.add(String.valueOf(OrderStatusEnum.ACCEPTED.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.NOT_TRANSFERED))
                {
                    Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getBizDate();
                    l_arrdataList.add(WEB3DateUtility.formatDate(l_datSystemTimestamp, l_strdateFormat));
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.EXPIRED.intValue()));
                    l_arrdataList.add(WEB3ErrorReasonCodeDef.NORMAL);
                    l_arrdataList.add(WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);
                } else
                {
                    l_arrdataList.add(l_orderState);
                }
            }
        }

        // Step11 Add Conditions judging execType to created ArrayList
        if (l_request.execType != null)
        {
            if ((l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE))
                || (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE))
                || (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE)))
            {
                l_arrdataList.add(WEB3OrderStatusDef.OTHER);
            }
            
            if (l_request instanceof WEB3AdminORFeqExecuteListRequest ||
                l_request instanceof WEB3AdminORFeqOrderExecutionRefReferenceRequest)
            {
                if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE) ||
                    l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ALL_COMPLETE))
                {
                    l_arrdataList.add(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
                }
                else
                {
                    l_arrdataList.add(WEB3TemporaryExecutionFlagDef.DEFAULT);
                }
            }
        }

        // Step12 Check l_request.changeCancelDiv != null and add it.
        if (l_request.changeCancelDiv != null)
        {
            l_arrdataList.add(l_request.changeCancelDiv);
        }

        // Step13 Check l_request.orderStartDate != null and add it.
        if (l_request.orderStartDate != null)
        {
            l_arrdataList.add(l_request.orderStartDate);
        }

        // Step14 Check l_request.orderEndDate != null and add it.
        if (l_request.orderEndDate != null)
        {
            l_arrdataList.add(l_request.orderEndDate);
        }

        // Step15 Check l_request.deliveryDate != null and add it.
        if (l_request.deliveryDate != null)
        {
            l_arrdataList.add(l_request.deliveryDate);
        }
        l_arrBranchCodes = new String[l_arrdataList.size()];
        l_arrBranchCodes = (String[]) l_arrdataList.toArray(l_arrBranchCodes);

        log.exiting(STR_METHOD_NAME);

        // Step16 return List
        return l_arrBranchCodes;
    }

    /**
     * (get�������)<BR>
     * <BR>
     * �����̎���敪���AAP�w�Ŏg�p���钍����ʁiOrderTypeEnum�j��ԋp����B <BR>
     * <BR>
     * �P�j�p�����[�^.����敪�ɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.����敪���A<BR>
     * �@@["�������t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�������t������ԋp����B<BR>
     * �@@["�������t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�������t������ԋp����B<BR>
     * �@@["�V�K��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�V�K����������ԋp����B<BR>
     * �@@["�V�K��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�V�K����������ԋp����B<BR>
     * �@@["�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�����ԍϒ�����ԋp����B<BR>
     * �@@["�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�����ԍϒ�����ԋp����B<BR>
     * �@@["��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.����������ԋp����B<BR>
     * �@@["���n����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.���n������ԋp����B<BR>
     * �@@["�~�j�����t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�~�j�����t������ԋp����B<BR>
     * �@@["�~�j�����t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�~�j�����t������ԋp����B<BR>
     * �@@["�����M�����t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�����M�����t������ԋp����B<BR>
     * �@@["�����M�����t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�����M�����t������ԋp����B<BR>
     * �@@["���M��W����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.���M��W������ԋp����B<BR>
     * �@@["���M�抷����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.���M�抷������ԋp����B<BR>
     * �@@["�ݓ����t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�ݓ����t������ԋp����B<BR>
     * �@@["�ݓ����t����"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�ݓ����t������ԋp����B<BR>
     * �@@["�w���敨�V�K��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���敨�V�K����������ԋp����B<BR>
     * �@@["�w���敨�V�K��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���敨�V�K����������ԋp����B<BR>
     * �@@["�w���敨�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���敨�����ԍϒ�����ԋp����B<BR>
     * �@@["�w���敨�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���敨�����ԍϒ�����ԋp����B<BR>
     * �@@["�w���I�v�V�����V�K��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���I�v�V�����V�K����������ԋp����B<BR>
     * �@@["�w���I�v�V�����V�K��������"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���I�v�V�����V�K����������ԋp����B<BR>
     * �@@["�w���I�v�V���������ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���I�v�V���������ԍϒ�����ԋp����B<BR>
     * �@@["�w���I�v�V���������ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�w���I�v�V���������ԍϒ�����ԋp����B<BR>
     * �@@["�O���������t"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�O���������t������ԋp����B<BR>
     * �@@["�O���������t"�̏ꍇ]<BR>
     * �@@�@@OrderTypeEnum.�O���������t������ԋp����B<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * ------<English>-----------------------<BR>
     * <BR>
     * getOrderType<BR>
     * <BR>
     * Return orderType�iOrderTypeEnum�j to use in AP layer from the argument,
     * l_tradingType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_strtradingType, and<BR>
     * return the corresponding value<BR>
     * <BR>
     * �@@If [l_tradingType is Def.EQUITY_BUY]<BR>
     * �@@�@@OrderTypeEnum.Def.EQUITY_BUY<BR>
     * �@@If [l_tradingType is Def.EQUITY_SELL]<BR>
     * �@@�@@OrderTypeEnum.Def.EQUITY_SELL<BR>
     * �@@If [l_tradingType is Def.MARGIN_LONG]<BR>
     * �@@�@@OrderTypeEnum.Def.MARGIN_LONG<BR>
     * �@@If [l_tradingType is Def.MARGIN_SHORT]<BR>
     * �@@�@@OrderTypeEnum.Def.MARGIN_SHORT<BR>
     * �@@If [l_tradingType is Def.CLOSE_MARGIN_LONG]<BR>
     * �@@�@@OrderTypeEnum.Def.CLOSE_MARGIN_LONG<BR>
     * �@@If [l_tradingType is Def.CLOSE_MARGIN_SHORT]<BR>
     * �@@�@@OrderTypeEnum.Def.CLOSE_MARGIN_SHORT<BR>
     * �@@If [l_tradingType is Def.SWAP_MARGIN_LONG]<BR>
     * �@@�@@OrderTypeEnum.Def.SWAP_MARGIN_LONG<BR>
     * �@@If [l_tradingType is Def.SWAP_MARGIN_SHORT]<BR>
     * �@@�@@OrderTypeEnum.Def.SWAP_MARGIN_SHORT<BR>
     * �@@If [l_tradingType is Def.MINI_STOCK_BUY]<BR>
     * �@@�@@OrderTypeEnum.Def.MINI_STOCK_BUY<BR>
     * �@@If [l_tradingType is Def.MINI_STOCK_SELL]<BR>
     * �@@�@@OrderTypeEnum.Def.MINI_STOCK_SELL<BR>
     * �@@If [l_tradingType is Def.MF_BUY]<BR>
     * �@@�@@OrderTypeEnum.Def.MF_BUY<BR>
     * �@@If [l_tradingType is Def.MF_SELL]<BR>
     * �@@�@@OrderTypeEnum.Def.MF_SELL<BR>
     * �@@If [l_tradingType is Def.MF_RECRUIT]<BR>
     * �@@�@@OrderTypeEnum.Def.MF_RECRUIT<BR>
     * �@@If [l_tradingType is Def.MF_SWITCHING]<BR>
     * �@@�@@OrderTypeEnum.Def.MF_SWITCHING<BR>
     *   If [l_tradingType is Def.RUITO_BUY]<BR>
     * �@@�@@OrderTypeEnum.Def.RUITO_BUY<BR>
     * �@@If [l_tradingType is Def.RUITO_SELL]<BR>
     * �@@�@@OrderTypeEnum.Def.RUITO_SELL<BR>
     * �@@If [l_tradingType is Def.IDX_FUTURES_BUY_TO_OPEN]<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * �@@If [l_tradingType is Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * �@@If [l_tradingType is Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * �@@If [l_tradingType is Def.IDX_FUTURES_SELL_TO_CLOSE]<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * �@@If [l_tradingType is Def.IDX_OPTIONS_BUY_TO_OPEN]<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * �@@If [l_tradingType is Def.IDX_OPTIONS_SELL_TO_OPEN<]<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_OPTIONS_SELL_TO_OPEN<<BR>
     * �@@If [l_tradingType is Def.IDX_OPTIONS_BUY_TO_CLOSE]<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * �@@If [l_tradingType is Def.IDX_OPTIONS_SELL_TO_CLOSE]<BR>
     * �@@�@@OrderTypeEnum.Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * �@@If [l_tradingType is Def.FEQ_BUY]<BR>
     * �@@�@@OrderTypeEnum.Def.FEQ_BUY<BR>
     * �@@If [l_tradingType is Def.FEQ_SELL]<BR>
     * �@@�@@OrderTypeEnum.Def.FEQ_SELL<BR>
     * �@@[For other cases]<BR>
     * �@@�@@Return null<BR>
     * <BR>
     * @@param l_strtradingType - (����敪)<BR>
     * <BR>
     * ����敪<BR>
     * <BR>
     * 1�F�@@�������t����<BR>
     * 2�F�@@�������t����<BR>
     * 3�F�@@�V�K��������<BR>
     * 4�F�@@�V�K��������<BR>
     * 5�F�@@�����ԍϒ���<BR>
     * 6�F�@@�����ԍϒ���<BR>
     * 7�F�@@��������<BR>
     * 8�F�@@���n����<BR>
     * 99�F�@@����O����<BR>
     * 101�F�@@�~�j�����t����<BR>
     * 102�F�@@�~�j�����t����<BR>
     * 201�F�@@�����M�����t����<BR>
     * 202�F�@@�����M�����t����<BR>
     * 203�F�@@���M��W����<BR>
     * 204�F�@@���M�抷����<BR>
     * 501�F�@@�ݓ����t����<BR>
     * 502�F�@@�ݓ����t����<BR>
     * 601�F�@@�w���敨�V�K��������<BR>
     * 602�F�@@�w���敨�V�K��������<BR>
     * 603�F�@@�w���敨�����ԍϒ���<BR>
     * 604�F�@@�w���敨�����ԍϒ���<BR>
     * 605�F�@@�w���I�v�V�����V�K��������<BR>
     * 606�F�@@�w���I�v�V�����V�K��������<BR>
     * 607�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 608�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 701�F�@@�O���������t<BR>
     * 702�F�@@�O���������t<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * tradingType<BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.SWAP_MARGIN_LONG<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 99: Def.SALES_OUTSIDE_MARKET <BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * 201: Def.MF_BUY<BR>
     * 202: Def.MF_SELL<BR>
     * 203: Def.MF_RECRUIT<BR>
     * 204: Def.MF_SWITCHING<BR>
     * 501: Def.RUITO_BUY<BR>
     * 502: Def.RUITO_SELL<BR>
     * 601: Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 602: Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 603: Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 604: Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 605: Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 606: Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 607: Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 608: Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 701: Def.FEQ_BUY <BR>
     * 702: Def.FEQ_SELL <BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum
     * @@roseuid 41B3B90402C1
     */
    public OrderTypeEnum getOrderType(String l_strtradingType)
    {

        final String STR_METHOD_NAME = "getOrderType(String tradingType)";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderTypeEnum = null;

        /*
          *  Step1 Do one of following processes according to l_tradingType, and
          * return the corresponding value
          */
        if (l_strtradingType
            .equals(WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.EQUITY_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MARGIN_LONG.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MARGIN_SHORT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.SWAP_MARGIN_LONG.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_RECRUIT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_RECRUIT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_SWITCHING.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SWITCHING;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.RUITO_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.RUITO_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.RUITO_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.RUITO_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.FEQ_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.FEQ_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.FEQ_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.FEQ_SELL;
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderTypeEnum;

    }

    /**
     * (get����敪)<BR>
     * <BR>
     * �����̒�����ʂ��APR�w�Ŏg�p�������敪��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.������ʂɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.������ʂ��A<BR>
     * �@@[OrderTypeEnum.�������t�����̏ꍇ]<BR>
     * �@@�@@"�������t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�������t�����̏ꍇ]<BR>
     * �@@�@@"�������t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�V�K���������̏ꍇ]<BR>
     * �@@�@@"�V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�V�K���������̏ꍇ]<BR>
     * �@@�@@"�V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�����ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�����ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�����ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�����ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.���������̏ꍇ]<BR>
     * �@@�@@"��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.���n�����̏ꍇ]<BR>
     * �@@�@@"���n����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�~�j�����t�����̏ꍇ]<BR>
     * �@@�@@"�~�j�����t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�~�j�����t�����̏ꍇ]<BR>
     * �@@�@@"�~�j�����t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�����M�����t�����̏ꍇ]<BR>
     * �@@�@@"�����M�����t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�����M�����t�����̏ꍇ]<BR>
     * �@@�@@"�����M�����t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�����M����W�����̏ꍇ]<BR>
     * �@@�@@"�����M����W����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�����M���抷�����̏ꍇ]<BR>
     * �@@�@@"�����M���抷����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�ݓ����t�����̏ꍇ]<BR>
     * �@@�@@"�ݓ����t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�ݓ����t�����̏ꍇ]<BR>
     * �@@�@@"�ݓ����t����"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���敨�V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���敨�V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�����ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���敨�����ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�����ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���敨�����ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V�����V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V�����V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V�����V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V�����V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V���������ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V���������ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V���������ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V���������ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�O���������t�̏ꍇ]<BR>
     * �@@�@@"�O���������t"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�O���������t�̏ꍇ]<BR>
     * �@@�@@"�O���������t"��ԋp����B<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * ----<English>---------------------<BR>
     * <BR>
     * getTradingType<BR>
     * <BR>
     * Return tradingType to use in PR layer from the argument, l_orderType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_orderType, and<BR>
     * return the corresponding value<BR>
     * <BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.EQUITY_BUY]<BR>
     * �@@�@@Return Def.EQUITY_BUY<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.EQUITY_SELL]<BR>
     * �@@�@@Return Def.EQUITY_SELL<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MARGIN_LONG]<BR>
     * �@@�@@Return Def.MARGIN_LONG<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MARGIN_SHORT]<BR>
     * �@@�@@Return Def.MARGIN_SHORT<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.CLOSE_MARGIN_LONG]<BR>
     * �@@�@@Return Def.CLOSE_MARGIN_LONG<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.CLOSE_MARGIN_SHORT]<BR>
     * �@@�@@Return Def.CLOSE_MARGIN_SHORT<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.SWAP_MARGIN_LONG]<BR>
     * �@@�@@Return Def.SWAP_MARGIN_LONG<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.SWAP_MARGIN_SHORT]<BR>
     * �@@�@@Return Def.SWAP_MARGIN_SHORT<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MINI_STOCK_BUY]<BR>
     * �@@�@@Return Def.MINI_STOCK_BUY<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MINI_STOCK_SELL]<BR>
     * �@@�@@Return Def.MINI_STOCK_SELL<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MF_BUY]<BR>
     * �@@�@@Return Def.MF_BUY<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MF_SELL]<BR>
     * �@@�@@Return Def.MF_SELL<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MF_RECRUIT]<BR>
     * �@@�@@Return Def.MF_RECRUIT<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.MF_SWITCHING]<BR>
     * �@@�@@Return Def.MF_SWITCHING<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.RUITO_BUY]<BR>
     * �@@�@@Return Def.RUITO_BUY<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.RUITO_SELL]<BR>
     * �@@�@@Return Def.RUITO_SELL<BR>
     * �@@If [l_orderType is OrderTypeEnum.DefIDX_FUTURES_BUY_TO_OPEN]<BR>
     * �@@�@@Return Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.IDX_FUTURES_SELL_TO_OPEN]<BR>
     * �@@�@@Return Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.IDX_FUTURES_BUY_TO_CLOSE]<BR>
     * �@@�@@Return Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def..IDX_FUTURES_SELL_TO_CLOSE]<BR>
     * �@@�@@Return Def..IDX_FUTURES_SELL_TO_CLOSE<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_OPEN]<BR>
     * �@@�@@Return Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.IDX_OPTIONS_SELL_TO_OPEN]<BR>
     * �@@�@@Return DefIDX_OPTIONS_SELL_TO_OPEN<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_CLOSE]<BR>
     * �@@�@@Return Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def..IDX_OPTIONS_SELL_TO_CLOSE]<BR>
     * �@@�@@Return Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.FEQ_BUY]<BR>
     * �@@�@@Return Def.FEQ_BUY<BR>
     * �@@If [l_orderType is OrderTypeEnum.Def.FEQ_SELL]<BR>
     * �@@�@@Return Def.FEQ_SELL<BR>
     * �@@[For other cases]<BR>
     * �@@�@@Return null<BR>
     * <BR>
     * @@param l_orderType - (�������)<BR>
     * <BR>
     * �������<BR>
     * (OrderTypeEnum�ɂĒ�`)<BR>
     * <BR>
     * -----<English>-----------<BR>
     * <BR>
     * l_orderType<BR>
     * (Defined at OrderTypeEnum)<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41B3BC38033E
     */
    public String getTradingType(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME = "getTradingType(OrderTypeEnum l_orderType)";
        log.entering(STR_METHOD_NAME);

        String l_strReturnValue = null;

        /*
         * Step1 Go to one of the following processes according to l_orderType, and
         * return the corresponding value
         */
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_BUY);
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_SELL);
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_LONG);
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_SHORT);
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG);
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT);
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_LONG);
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT);
        } else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MINI_STOCK_BUY);
        } else if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MINI_STOCK_SELL);
        } else if (OrderTypeEnum.MF_BUY.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_BUY);
        } else if (OrderTypeEnum.MF_SELL.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_SELL);
        } else if (OrderTypeEnum.MF_RECRUIT.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_RECRUIT);
        } else if (OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_SWITCHING);
        } else if (OrderTypeEnum.RUITO_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.RUITO_BUY);
        } else if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.RUITO_SELL);
        } else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_OPEN);
        } else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_OPEN);
        } else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_CLOSE);
        } else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_CLOSE);
        } else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_OPEN);
        } else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_OPEN);
        } else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_CLOSE);
        } else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_CLOSE);
        } else if (OrderTypeEnum.FEQ_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.FEQ_BUY);
        } else if (OrderTypeEnum.FEQ_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.FEQ_SELL);
        } else
        {
            l_strReturnValue = null;
        }

        log.exiting(STR_METHOD_NAME);

        return l_strReturnValue;
    }

    /**
     * (get������ԋ敪�iPR�w�j)<BR>
     * <BR>
     * �p�����[�^.�����P�ʂ��APR�w�Ŏg�p���钍����ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����P�ʂ̕ێ�����f�[�^�ɂ�蕪�򂵁A<BR>
     * �@@�Ή����钍����ԋ敪��ԋp����B<BR>
     * <BR>
     * �@@�P�|�P�j�蓮�����̔���<BR>
     * �@@�@@�@@�����P��.�����L����� == CLOSED�i�N���[�Y�j ����<BR>
     * �@@�@@�@@�����P��.�����敪 == INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj����<BR>
     * �@@�@@�@@�����P��.�����G���[���R�R�[�h == ("W001:�����Ǘ��Ҏ蓮������", <BR>
     * "W004:�敨OP�Ǘ��Ҏ蓮������")�̏ꍇ�A"�蓮����"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�Q�j��L�ȊO�̏ꍇ�A�p�����[�^.�����P�ʂ̌^�ɂ��A<BR>
     * �@@�@@�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * �@@�@@[�p�����[�^.�����P�ʂ̌^ == "���������P��"�̏ꍇ]<BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get������ԋ敪(�p�����[�^.�����P��)���\�b�h��<BR>
     * �@@�@@�@@�R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.�����P�ʂ̌^ == "�敨OP�����P��"�̏ꍇ]<BR>
     * �@@�@@�@@�敨OP�f�[�^�A�_�v�^.get������ԋ敪(�p�����[�^.�����P��)���\�b�h��<BR>
     * �@@�@@�@@�R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[��L�ȊO�̌^�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��.�������.intValue�𕶎���ŕԂ��B <BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * <BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * l_orderUnit<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException
     * @@roseuid 41B3C47401D7
     */
    public String getOrderStateDivPR(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderStateDivPR(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        String l_strreturnValue = null;
        String l_strReasonCode = null;

        if ((l_orderUnit instanceof EqTypeOrderUnit) || (l_orderUnit instanceof IfoOrderUnit))
        {
            //Check if l_orderUnit is type of EqTypeOrderUnit
            if (l_orderUnit instanceof EqTypeOrderUnit)
            {
                EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit) l_orderUnit;
                EqtypeOrderUnitParams l_equityParams =
                    new EqtypeOrderUnitParams(
                        (EqtypeOrderUnitRow) l_eqtypeOrderUnit.getDataSourceObject());
                l_strReasonCode = l_equityParams.error_reason_code;
            } else
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit) l_orderUnit;
                IfoOrderUnitParams l_ifoParams =
                    new IfoOrderUnitParams((IfoOrderUnitRow) l_ifoOrderUnit.getDataSourceObject());
                l_strReasonCode = l_ifoParams.error_reason_code;
            }
        }
        
        // �蓮�������ǂ����̔���
        if ((l_orderUnit.getOrderOpenStatus().equals(OrderOpenStatusEnum.CLOSED))
            && (l_orderUnit.getExpirationStatus().equals(
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT))
            && ((WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED.equals(l_strReasonCode))
            || (WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED.equals(l_strReasonCode))))
        {
            l_strreturnValue = WEB3OrderStatusDef.MANUAL_EXPIRED;
            log.exiting(STR_METHOD_NAME);
            return l_strreturnValue;
        }
        
        // ��L�ȊO�̏ꍇ�A�p�����[�^.�����P�ʂ̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            try
            {
	            l_strreturnValue = WEB3EquityDataAdapter.getOrderState((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getErrorMessage(), l_wbe);
            }
        }
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            l_strreturnValue = WEB3IfoDataAdapter.getOrderStatusType((IfoOrderUnit)l_orderUnit);
        }
        else
        {
            l_strreturnValue = Integer.toString(l_orderUnit.getOrderStatus().intValue());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strreturnValue;
    }

    /**
     * (get����ԋ敪�iPR�w�j)<BR>
     * <BR>
     * �p�����[�^.�����P�ʂ��APR�w�Ŏg�p�������ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����P�ʂ̕ێ�����f�[�^�ɂ�蕪�򂵁A<BR>
     * �@@�Ή��������ԋ敪��ԋp����B<BR>
     * <BR>
     * �@@�P�|�P�j�p�����[�^.�����P��.isPartiallyExecuted( ) == true�̏ꍇ�́A<BR>
     * �@@�@@"�ꕔ����"��Ԃ��B <BR>
     * <BR>
     * �@@�P�|�Q�j�p�����[�^.�����P��.isFullyExecuted( ) == true�̏ꍇ�́A<BR>
     * �@@�@@"�S������"��Ԃ��B <BR>
     * <BR>
     * �@@�P�|�R�j��L�ȊO�̏ꍇ�́A"�����"��Ԃ��B <BR>
     * <BR>
     * ------<English>---------------<BR>
     * <BR>
     * getExecTypeDivPR<BR>
     * <BR>
     * Return execType to use in PR layer from l_orderUnit<BR>
     * <BR>
     * 1)Go to one of the following processes according to the data in l_orderUnit,
     * and<BR>
     * �@@return the corresponding execType<BR>
     * <BR>
     * �@@1-1)If l_orderUnit.isPartiallyExecuted( ) == true,<BR>
     * �@@�@@Return Def.EXEC_TYPE_ONE_COMPLETE<BR>
     * <BR>
     * �@@1-2)If l_orderUnit.isFullyExecuted( ) == true,<BR>
     * �@@�@@Return Def.EXEC_TYPE_ALL_COMPLETE<BR>
     * <BR>
     * �@@1-3)Return Def.EXEC_TYPE_NOT_PROMISE for other cases<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * <BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * l_orderUnit<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41B3DD74026B
     */
    public String getExecTypeDivPR(OrderUnit l_orderUnit)
    {

        final String STR_METHOD_NAME = "getExecTypeDivPR(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        String l_strreturnValue = null;

        /*
         * Step1 Go to one of the following processes according to the data in l_orderUnit,
         * return the corresponding execType
         */
        if (l_orderUnit.isPartiallyExecuted())
        {
            l_strreturnValue = WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE;
        } else if (l_orderUnit.isFullyExecuted())
        {
            l_strreturnValue = WEB3AdminExecTypeDef.EXEC_TYPE_ALL_COMPLETE;
        } else
        {
            l_strreturnValue = WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strreturnValue;
    }

    /**
     * (get�ڋq�ꗗ)<BR>
     * <BR>
     * �����̏����ɊY������ڋq�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�jDB����<BR>
     * �@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�j�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A<BR>
     * �@@�@@��������ArrayList�ɒǉ�����B<BR>
     * �@@<BR>
     * �R�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * �@@��toArray()�̖߂�l.length == 0�̏ꍇ�A<BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00398<BR>
     * <BR>
     * -------<English>------------------<BR>
     * <BR>
     * getAccountList<BR>
     * <BR>
     * Return a list of account corresponding to conditions of the arguments<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Serach DB<BR>
     * �@@Loop the following process for as many times as the number of the elements of
     * l_branchCode<BR>
     * �@@2-1)Call WEB3GentradeAccountManager.getMainAccount() method<BR>
     * <BR>
     * �@@�@@[Parameter set into getMainAccount()]<BR>
     * �@@�@@�@@l_strInstitutionCode:�@@parameter.l_strInstitutionCode<BR>
     * �@@�@@�@@l_branchCode: l_branchCode to be processed<BR>
     * �@@�@@�@@l_accountCode: parameter.l_accountCodeBR>
     * <BR>
     * �@@2-2)If it is able to acquire the result of 2-1),<BR>
     * �@@�@@ add it to created ArrayList<BR>
     * �@@<BR>
     * 3)Return return values of ArrayList.toArray()<BR>
     * �@@��If return value of toArray().length == 0,<BR>
     * �@@�@@Throw the exception "No corresponding data"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00398<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCode - (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_accountCode - (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * l_accountCode<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@return WEB3GentradeMainAccount[]
     * @@roseuid 41B3EAEE03C3
     */
    public WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode,
        String[] l_branchCode,
        String l_accountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getAccountList(String l_strInstitutionCode, "
                + "String[] l_branchCode, String l_accountCode)";
        log.entering(STR_METHOD_NAME);
        int l_intCount = 0;
        int l_intlbranchCodeLength = 0;

        //Step1 Create ArrayList
        ArrayList l_arrAccountMain = new ArrayList();
        WEB3GentradeAccountManager l_web3GentradeAccountManager = null;
        WEB3GentradeMainAccount l_web3GentradeMainAccount = null;
        l_intlbranchCodeLength = l_branchCode.length;

        /* Step 2 Loop the following process for as many times as the number of the
         * elements of l_branchCode
         */
        l_web3GentradeAccountManager = new WEB3GentradeAccountManager();
        for (l_intCount = 0; l_intCount < l_intlbranchCodeLength; l_intCount++)
        {
        	try
        	{
	            l_web3GentradeMainAccount =
	                l_web3GentradeAccountManager.getMainAccount(
	                    l_strInstitutionCode,
	                    l_branchCode[l_intCount],
	                    l_accountCode);
	
	            //Step 2.2 Add Object to List
	            l_arrAccountMain.add(l_web3GentradeMainAccount);
        	}
			catch (WEB3BaseException l_exp)
			{
				continue;
			}
        }

        /*
          * Step3 If return value of toArray().length == 0,<BR>
          * Throw the exception "No corresponding data"<BR>
          */
        if (l_arrAccountMain.size() == 0)
        {
            String l_strMsg = "No corresponding data ";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        WEB3GentradeMainAccount[] l_gntradeMainAccounts =
            new WEB3GentradeMainAccount[l_arrAccountMain.size()];
        l_arrAccountMain.toArray(l_gntradeMainAccounts);

        log.exiting(STR_METHOD_NAME);

        //Step3 Return return values of ArrayList.toArray()<BR>
        return l_gntradeMainAccounts;
    }

    /**
     * (create�ڍ׉�ʏ��ꗗ)<BR>
     * <BR>
     * �����̒������Ɖ��Unit�ꗗ���A�ڍ׉�ʏ��̈ꗗ��<BR>
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�������Ɖ��Unit�ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�������Ɖ��Unit.���i�敪 == "�����~�j����"�̏ꍇ�A<BR>
     * �@@�@@���̗v�f�֏������ڍs����B(continue)<BR>
     * <BR>
     * �@@�Q�|�Q�j�ڍ׉�ʏ��C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�R�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@����ID = �������Ɖ��Unit.ID<BR>
     * �@@�@@���i�敪 = �������Ɖ��Unit.���i�敪<BR>
     *     ����ID = �������Ɖ��Unit.ID�ɊY�����钍��.����ID<BR>
     * <BR>
     * �@@�Q�|�S�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��<BR>
     * �@@�@@�ǉ�����B<BR>
     * <BR>
     * �R�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * -----<English>------------------------<BR>
     * <BR>
     * createExecuteDetailsInfoList<BR>
     * <BR>
     * Create executeDetailsInfoList from the argument,
     * l_orderExecutionRefReferenceUnitList, and<BR>
     * return it<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Loop the following process for as many times as the number of<BR>
     * the elements of l_orderExecutionRefReferenceUnitList<BR>
     * <BR>
     * �@@2-1)
     *  If l_orderExecutionRefReferenceUnitList.productDiv == Def.MINI_STOCK<BR>
     *    continue processing to the next element(continue)<BR>
     * <BR>
     * �@@2-2)Create WEB3AdminORDetailDispInfoUnit<BR>
     * <BR>
     * �@@2-3)Set the following properties into the created instance<BR>
     * �@@�@@orderId = l_orderExecutionRefReferenceUnitList.Id<BR>
     * �@@�@@productDiv = l_orderExecutionRefReferenceUnitList.productDiv<BR>
     * <BR>
     * �@@2-4)Add the instance set in 'Property Set' to the created ArrayList<BR>
     * <BR>
     * 3)Return the return value of created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_orderExecutionRefReferenceUnitList - (�������Ɖ��Unit�ꗗ)<BR>
     * <BR>
     * �Ǘ��Ғ������Ɖ��Unit�̔z��<BR>
     * <BR>
     * l_orderExecutionRefReferenceUnitList<BR>
     * <BR>
     * @@return WEB3AdminDetailDispInfoUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 41B55AD500D7
     */
    public WEB3AdminORDetailDispInfoUnit[]
         createExecuteDetailsInfoList(
         WEB3AdminOROrderExecutionRefCommon[] l_orderExecutionRefReferenceUnitList)
         throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createExecuteDetailsInfoList(WEB3AdminOROrderExecutionRefCommon[] "
                + "l_orderExecutionRefReferenceUnitList)";
        log.entering(STR_METHOD_NAME);

        //Step1 Create ArrayList l_orderExecutionRefReferenceUnitList
        ArrayList l_arrorderExecutionRefReferenceUnitList = new ArrayList();
        WEB3AdminORDetailDispInfoUnit[] l_web3AdminORDetailDispInfoUnit = null;
        WEB3AdminORDetailDispInfoUnit l_adminORDetailDispInfoUnit = null;
        int l_intorderLength = 0;
        int l_intCount = 0;

        /*Step2 Loop the following process for as many times as elements
         * of l_orderExecutionRefReferenceUnitList
         */
        l_intorderLength = l_orderExecutionRefReferenceUnitList.length;
        for (l_intCount = 0; l_intCount < l_intorderLength; l_intCount++)
        {
            //Step2.1 Check with MINI_STOCK
            if (l_orderExecutionRefReferenceUnitList[l_intCount].productDiv
                == WEB3AdminProductDivDef.MINI_STOCK)
            {
                continue;
            }

            //Step2.2 Create WEB3AdminORDetailDispInfoUnit
            l_adminORDetailDispInfoUnit = new WEB3AdminORDetailDispInfoUnit();

            /*
             * Step2.3 Set the following properties into the created instance
             * orderId = l_orderExecutionRefReferenceUnitList.id
             * productDiv = l_orderExecutionRefReferenceUnitList.productDiv
             */
            l_adminORDetailDispInfoUnit.orderId =
                l_orderExecutionRefReferenceUnitList[l_intCount].id;
            l_adminORDetailDispInfoUnit.productDiv =
                l_orderExecutionRefReferenceUnitList[l_intCount].productDiv;
                
            long l_lngOrderId = Long.parseLong(l_adminORDetailDispInfoUnit.orderId);
            long l_lngAccountId = 0;
            try
            {
                // ���E�M�p�����̏ꍇ
	            if (l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.EQUITY)
	            || l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.MARGIN))
	            {
	                EqtypeOrderRow l_eqtypeOrderRow = EqtypeOrderDao.findRowByOrderId(l_lngOrderId);
	                if (l_eqtypeOrderRow == null)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����ID:" + l_lngOrderId + "�ɊY�����钍�����擾�ł��܂���ł����B"
                        + "���i�敪:" + l_adminORDetailDispInfoUnit.productDiv);
                    }
	                l_lngAccountId = l_eqtypeOrderRow.getAccountId();
	            }
	            // �敨�E�I�v�V���������̏ꍇ
	            else if (l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.OPTION)
	            || l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.FUTURE))
	            {
	                IfoOrderRow l_ifoOrderRow = IfoOrderDao.findRowByOrderId(l_lngOrderId);
                    if (l_ifoOrderRow == null)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����ID:" + l_lngOrderId + "�ɊY�����钍�����擾�ł��܂���ł����B"
                        + "���i�敪:" + l_adminORDetailDispInfoUnit.productDiv);
                    }
	                l_lngAccountId = l_ifoOrderRow.getAccountId();
	            }
                //�O�����������̏ꍇ
                else if (l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.FEQ))
                {
                    FeqOrderRow l_feqOrderRow = FeqOrderDao.findRowByOrderId(l_lngOrderId);
                    if (l_feqOrderRow == null)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����ID:" + l_lngOrderId + "�ɊY�����钍�����擾�ł��܂���ł����B"
                        + "���i�敪:" + l_adminORDetailDispInfoUnit.productDiv);
                    }
                    l_lngAccountId = l_feqOrderRow.getAccountId();
                }
            }
            catch (DataFindException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID:" + l_lngOrderId + "�ɊY�����钍�����擾�ł��܂���ł����B"
                    + "���i�敪:" + l_adminORDetailDispInfoUnit.productDiv,
                    l_ex);
            }
            catch (DataException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
            l_adminORDetailDispInfoUnit.accountID = String.valueOf(l_lngAccountId);

            //Step2.4 Add the instance set in 'Property Set' to the created ArrayList
            l_arrorderExecutionRefReferenceUnitList.add(l_adminORDetailDispInfoUnit);
        }

        l_web3AdminORDetailDispInfoUnit =
            new WEB3AdminORDetailDispInfoUnit[l_arrorderExecutionRefReferenceUnitList.size()];
        l_arrorderExecutionRefReferenceUnitList.toArray(l_web3AdminORDetailDispInfoUnit);

        log.exiting(STR_METHOD_NAME);

        //Step3 Return the return value of created ArrayList.toArray()<BR>
        return l_web3AdminORDetailDispInfoUnit;
    }

    /**
     * (get���i���{���)<BR>
     * <BR>
     * �����̕��X�R�[�h�ꗗ�ɊY�����镔�X��<BR>
     * ���i���{�����쐬���A�ԋp����B<BR>
     * �������̕��X�R�[�h�ꗗ�̂����A<BR>
     * �@@�ꕔ�X�ł����{���Ă���΁A���{�ƂȂ�B<BR>
     * <BR>
     * �P�j���X�ꗗ�̎擾<BR>
     * �@@�P�|�P�jArrayList�𐶐�����B<BR>
     * �@@�P�|�Q�j�p�����[�^.���X�R�[�h�ꗗ�̗v�f�����A<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.get���X()���\�b�h���R�[�����A<BR>
     * �@@�@@���ʂ�ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[get���X()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h<BR>
     * �@@�P�|�R�jArrayList.toArray()���\�b�h���R�[�����A<BR>
     * �@@�@@���X�ꗗ(���X�I�u�W�F�N�g�̔z��)���擾����B<BR>
     * <BR>
     * �Q�j���i���{���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�P�j�ɂĎ擾�������X�ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�����Ώۂ̕��X�̊e���{�敪�̒l�ɂ��A<BR>
     * �@@�@@���i���{���C���X�^���X�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@[�����Ώۂ̕��X.���x�M�p���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.���x�M�p���{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.��ʐM�p���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.��ʐM�p���{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.�~�j�����{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.�~�j�����{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.�I�v�V�������{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.�I�v�V�������{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.�敨���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.�敨���{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.���M���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.���M���{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.�ݓ����{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.�ݓ����{�t���O = true<BR>
     * �@@�@@[�����Ώۂ̕��X.�O�����{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@���i���{���.�O���������{�t���O = true<BR>
     * <BR>
     * �S�j�v���p�e�B�Z�b�g�������i���{���C���X�^���X��ԋp����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getProductExecutionInfo<BR>
     * <BR>
     * Create WEB3ProductExecutionInfo of the branch corresponding to the argument,<BR>
     * l_branchCodeList, and return it<BR>
     * ��Let it Def.ENFORCEMENT if even one of branches executes in the argument,
     * l_branchCodeList<BR>
     * <BR>
     * 1)Acquire branch list<BR>
     * �@@1-1)Create ArrayList<BR>
     * �@@1-2)Call WEB3GentradeAccountManager.getBranch() method for as many times
     * as<BR>
     * the number of the elements of l_branchCodeList, and<BR>
     * �@@�@@ add the result to ArrayList<BR>
     * <BR>
     * �@@�@@[Parameter set into getBranch()]<BR>
     * �@@�@@�@@l_strInstitutionCode: parameter.institutionCode<BR>
     * �@@�@@�@@l_strBranchCode: branchCode to be processed<BR>
     * �@@1-3)Call ArrayList.toArray() method, and<BR>
     * �@@�@@ acquire branch list(an array of l_branch)<BR>
     * <BR>
     * 2)Create WEB3ProductExecutionInfo instance<BR>
     * <BR>
     * 3)Loop the following process for as many times as the number of the elements
     * of<BR>
     * branchCodeList acquired at 1)<BR>
     * �@@3-1)Set properties into WEB3ProductExecutionInfo instance<BR>
     * �@@�@@ based on the values of each execution division in branch to be
     * processed<BR>
     * <BR>
     * �@@�@@If [l_branch to be processed.margin_sys_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.marginSysFlag = true<BR>
     * �@@�@@If [l_branch to be processed.margin_gen_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.marginGenFlag = true<BR>
     * �@@�@@If [l_branch to be processed.mstk_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.miniFlag = true<BR>
     * �@@�@@If [l_branch to be processed.option_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.optionFlag = true<BR>
     * �@@�@@If [l_branch to be processed.future_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.futureFlag = true<BR>
     * �@@�@@If [l_branch to be processed.mf_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.mutalFlag = true<BR>
     * �@@�@@If [l_branch to be processed.ruito_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.ruitoFlag = true<BR>
     * �@@�@@If [l_branch to be processed.fstk_div == Def.ENFORCEMENET]<BR>
     * �@@�@@�@@WEB3ProductExecutionInfo.fstkFlag = true<BR>
     * <BR>
     * 4)Return WEB3ProductExecutionInfo instance set into 'Property Set'<BR>
     * <BR>
     * @@param l_strinstitutionCode - (�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCodeList - (���X�R�[�h�ꗗ)<BR>
     * <BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * l_branchCodeList<BR>
     * <BR>
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@throws DataFindException DataFindException
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@return webbroker3.adminorderexecinquiry.WEB3ProductExecutionInfo
     * @@roseuid 41B6D4860113
     */
    public WEB3AdminProductExecutionInfo getProductExecutionInfo(
        String l_strinstitutionCode,
        String[] l_branchCodeList)
        throws
            WEB3SystemLayerException,
            NotFoundException,
            DataFindException,
            DataQueryException,
            DataNetworkException
    {
        final String STR_METHOD_NAME =
            "getProductExecutionInfo(String l_strInstitutionCode, String[] l_branchCodeList)";
        log.entering(STR_METHOD_NAME);

        //Step1.1 Create ArrayList
        ArrayList l_arrbranchCodeList = new ArrayList();
        int l_intBranchCodeSize = 0;
        int l_count = 0;
        int l_intBranchCount = 0;
        WEB3AdminProductExecutionInfo l_web3ProductExecution = null;

        Branch[] l_arrbranches = new WEB3GentradeBranch[1];
        WEB3GentradeBranch l_gentradeBranch = null;
        BranchParams l_branchParams = null;
        l_intBranchCodeSize = l_branchCodeList.length;
        WEB3GentradeAccountManager l_gentradeAccountManager = null;

        //Step 1.2
        FinApp l_finApp = null;
        l_finApp = (FinApp) Services.getService(FinApp.class);
        l_gentradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeInstitution l_institution = null;
        l_institution = new WEB3GentradeInstitution(l_strinstitutionCode);

        //Call WEB3GentradeAccountManager.getBranch() method for as many times of l_branchCodeList
        for (l_count = 0; l_count < l_intBranchCodeSize; l_count++)
        {
            l_gentradeBranch =
                (WEB3GentradeBranch) l_gentradeAccountManager.getBranch(
                    l_institution,
                    l_branchCodeList[l_count]);
            l_arrbranchCodeList.add(l_gentradeBranch);
        }

        //Step 1.3
        l_arrbranches = (WEB3GentradeBranch[]) l_arrbranchCodeList.toArray(l_arrbranches);

        //Step 2 Create WEB3ProductExecutionInfo instance
        l_web3ProductExecution = new WEB3AdminProductExecutionInfo();

        //Step 3 Loop the following process for as many elements of branchCodeList
        for (l_intBranchCount = 0; l_intBranchCount < l_arrbranches.length; l_intBranchCount++)
        {
            l_branchParams = (BranchParams) l_arrbranches[l_intBranchCount].getDataSourceObject();

            //Step3.1 Set properties into WEB3ProductExecutionInfo instance
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
            {
                l_web3ProductExecution.marginSysFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
                l_web3ProductExecution.marginGenFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mstk_div))
            {
                l_web3ProductExecution.miniFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.option_div))
            {
                l_web3ProductExecution.optionFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.future_div))
            {
                l_web3ProductExecution.futureFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mf_div))
            {
                l_web3ProductExecution.mutualFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.ruito_div))
            {
                l_web3ProductExecution.ruitoFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.fstk_div))
            {
                l_web3ProductExecution.fstkFlag = true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_web3ProductExecution;
    }
    
    /**
     * (get���҈ꗗ)<BR>
     * �����̏����ɊY�����鈵�҂̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�jDB����<BR>
     *�@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     *�@@�Q�|�P�j�g�����Z�I�u�W�F�N�g�}�l�[�W��.get����()���\�b�h���R�[������B<BR>
     * <BR>
     *�@@�@@[get����()�ɃZ�b�g����p�����[�^]<BR>
     *�@@�@@�@@�،���ЁF�@@�p�����[�^.�،����<BR>
     *�@@�@@�@@���҃R�[�h�F�@@�p�����[�^.���҃R�[�h<BR>
     *�@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h<BR>
     * <BR>
     *�@@�Q�|�Q�j�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A<BR>
     *�@@�@@��������ArrayList�ɒǉ�����B<BR>
     *�@@<BR>
     * �R�jArrayList.toArray()�̖߂�l��ԋp����B<BR>
     *�@@��toArray()�̖߂�l.length == 0�̏ꍇ�A<BR>
     *�@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00398<BR>
     * @@param l_strInstitution - (�،����)<BR>
     * <BR>
     * �،����<BR>
     * <BR>
     * l_strInstitution<BR>
     * <BR>
     * @@param l_branchCode - (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_traderCode - (���҃R�[�h)<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * l_accountCode<BR>
     * @@throws WEB3BaseException
     * @@return WEB3GentradeTrader[]
     */
    public WEB3GentradeTrader[] getTraderList(
            Institution l_institution,
            String[] l_branchCodes,
            String l_strTraderCode)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "getTraderList(Institution l_institution, "
                    + "String[] l_branchCodes, String l_strTraderCode)";
            log.entering(STR_METHOD_NAME);

            //�P�jArrayList�𐶐�����B
            List l_lisTraders = new ArrayList();
            
            //�Q�jDB����
            //�@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B
            //�@@�Q�|�P�j�g�����Z�I�u�W�F�N�g�}�l�[�W��.get����()���\�b�h���R�[������B
            //�@@�@@[get����()�ɃZ�b�g����p�����[�^]
            //�@@�@@�@@�،���ЁF�@@�p�����[�^.�،����
            //�@@�@@�@@���҃R�[�h�F�@@�p�����[�^.���҃R�[�h
            //�@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            int l_intLength = 0;
            if (l_branchCodes != null || l_branchCodes.length != 0)
            {
                l_intLength = l_branchCodes.length;
            }
            for (int i = 0; i < l_intLength; i++)
            {
                try
                {
                    Trader l_trader = l_finObjManager.getTrader(
                        l_institution,
                        l_strTraderCode,
                        l_branchCodes[i]);
                    
                    //�@@�Q�|�Q�j�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A
                    //�@@�@@��������ArrayList�ɒǉ�����B
                    l_lisTraders.add(l_trader);
                } 
                catch (NotFoundException l_ex)
                {
                    continue;
                }
            }

            //�R�jArrayList.toArray()�̖߂�l��ԋp����B
            //�@@��toArray()�̖߂�l.length == 0�̏ꍇ�A
            //�@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
            if (l_lisTraders.size() == 0)
            {
                String l_strMsg = "No corresponding data ";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);
            }
            WEB3GentradeTrader[] l_traders = new WEB3GentradeTrader[l_lisTraders.size()];            
            l_lisTraders.toArray(l_traders);
            
            return l_traders;
        }
}
@
