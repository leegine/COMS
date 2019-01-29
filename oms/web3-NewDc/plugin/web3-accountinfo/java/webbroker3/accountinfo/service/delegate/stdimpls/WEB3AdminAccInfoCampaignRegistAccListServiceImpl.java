head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[���o�^�ڋq�Ɖ�T�[�r�X�����N���X
                       (WEB3AdminAccInfoCampaignRegistAccListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �Ј��� (���u) �V�K�쐬
Revision History : 2007/02/01 �Ј��� (���u) ���f��No.166
Revision History : 2007/02/01 �Ј��� (���u) ���f��No.176
Revision History : 2007/03/05 �R�c (SCS) ���f��No.198
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignRegistAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �萔�������L�����y�[���o�^�ڋq�Ɖ�T�[�r�X�����N���X<BR>
 * @@author �Ј��� 
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListServiceImpl implements WEB3AdminAccInfoCampaignRegistAccListService
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignRegistAccListServiceImpl.class);

    /**
     * @@roseuid 45C08B5501C5
     */
    public WEB3AdminAccInfoCampaignRegistAccListServiceImpl()
    {

    }

    /**
     * �萔�������L�����y�[���o�^�ڋq�Ɖ�������{����B<BR>
     * <BR>
     * �P�j���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     *  �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �ǊǗ��҂��q�l���萔�������L�����y�[���o�^�ڋq�Ɖ�ظ��Ă̏ꍇ<BR> 
     * �E get�Ɖ���()���R�[������B<BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45ADF02103A4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminAccInfoCampaignRegistAccListRequest)
        {
            //get�Ɖ���
            l_response = 
                this.getListScreen((WEB3AdminAccInfoCampaignRegistAccListRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR>
     * <BR>
     * �P�j �߂�l���� <BR>
     * �E�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐�����B<BR>
     * <BR>
     * �Q�j �،���ЃR�[�h�����ǉ�<BR>
     * �E�،���ЃR�[�h������ǉ�����B <BR>
     * �@@�@@ " institution_code = ? " <BR>
     * <BR>
     * �R�j ���i�R�[�h�����ǉ�<BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.���i�R�[�h != null�̏ꍇ�A<BR>
     * ���i�R�[�h������ǉ�����B <BR>
     * <BR>
     * "and campaign_id in <BR>
     * ( select campaign_id from<BR>
     * comm_campaign_product_mst where comm_product_code = ? )" <BR>
     * <BR>
     * �S�j �L�����y�[�����̏����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�L�����y�[������ != null�̏ꍇ�A<BR>
     * �L�����y�[�����̏�����ǉ�����B <BR>
     * �@@�@@" and comm_campaign_name like ? " <BR>
     * <BR>
     * <BR>
     * �T�j ���X�R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h != null�̏ꍇ�A<BR>
     * ���X�R�[�h������ǉ�����B <BR>
     * �@@�@@" and branch_code = ? " <BR>
     * <BR>
     * �U�j �ڋq�R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �ڋq�R�[�h������ǉ�����B <BR>
     * �@@�@@" and account_code like ? " <BR>
     * <BR>
     * �V�j ���҃R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h != null�̏ꍇ�A<BR>
     * ���҃R�[�h������ǉ�����B <BR>
     * �@@�@@" and sonar_trader_code = ? " <BR>
     * <BR>
     * �W�j �����J�݋敪�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�����J�݋敪 != null�̏ꍇ�A<BR>
     * �����J�݋敪������ǉ�����B <BR>
     * �@@�@@" and acc_open_kind_div = ? " <BR>
     * <BR>
     * �X�j �����������ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.������ != null �̏ꍇ�A<BR>
     * ������������ǉ�����B <BR>
     * �@@�@@" and account_charge_ratio = ? " <BR>
     * <BR>
     * �P�O�j�@@�Ώۊ���From�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A<BR>
     * �Ώۊ���From������ǉ�����B <BR>
     * �@@�@@" and ( appli_start_date <= ? or appli_start_date is null )" <BR>
     * <BR>
     * �P�P�j �Ώۊ���To�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A<BR>
     * �Ώۊ���To������ǉ�����B <BR>
     *     " and ( appli_end_date >= ? or appli_end_date is null )" <BR>
     * <BR>
     * �P�Q�j �o�^�^�C�v�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�o�^�^�C�v != null�̏ꍇ�A<BR>
     * �o�^�^�C�v������ǉ�����B <BR>
     * �@@�@@ " and regist_type = ? " <BR>
     * <BR>
     * �P�R�j �L���敪�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�L���敪 != null�̏ꍇ�A<BR>
     * �L���敪������ǉ�����B <BR>
     * �@@�@@" and valid_div = ? " <BR>
     * <BR>
     * �P�S�j��������������C���X�^���X.toString() ��ԋp���� <BR>
     * <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^)<BR>
     *            �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     *            �o�^�ڋq�Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 45ADF2F003E3
     */
    protected String createSearchCondition(WEB3AdminAccInfoCampaignRegistAccListRequest l_request)
    {
        final String STR_METHOD_NAME = ".createSearchCondition(WEB3AdminAccInfoCampaignRegistAccListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j �߂�l���� 
        //�E�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐�����B
        StringBuffer l_sbSearchCondition = new StringBuffer();
        
        //�Q�j �،���ЃR�[�h�����ǉ�
        //�E�،���ЃR�[�h������ǉ�����B 
        //" institution_code = ? " 
        l_sbSearchCondition.append(" institution_code = ? ");
        
        //�R�j ���i�R�[�h�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.���i�R�[�h != null�̏ꍇ
        //���i�R�[�h������ǉ�����B
        if (l_request.itemCode != null)
        {
            //"and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )"
            l_sbSearchCondition.append("and campaign_id in " +
                    "( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )");
        }
        
        //�S�j �L�����y�[�����̏����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.�L�����y�[������ != null�̏ꍇ�A
        //�L�����y�[�����̏�����ǉ�����B 
        if (l_request.campaignName != null)
        {
            //" and comm_campaign_name like ? "
            l_sbSearchCondition.append(" and comm_campaign_name like ? ");
        }
        
        //�T�j ���X�R�[�h�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h != null�̏ꍇ�A
        //���X�R�[�h������ǉ�����B 
        if (l_request.branchCode != null)
        {
            //" and branch_code = ? "
            l_sbSearchCondition.append(" and branch_code = ? ");
        }
        
        //�U�j �ڋq�R�[�h�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A
        //�ڋq�R�[�h������ǉ�����B 
        if (l_request.accountCode != null)
        {
            //" and account_code like ? "
            l_sbSearchCondition.append(" and account_code like ? ");
        }
        
        //�V�j ���҃R�[�h�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h != null�̏ꍇ�A
        //���҃R�[�h������ǉ�����B
        if (l_request.traderCode != null)
        {
            //" and sonar_trader_code = ? "
            l_sbSearchCondition.append(" and sonar_trader_code = ? ");
        }
        
        //�W�j �����J�݋敪�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�����J�݋敪 != null�̏ꍇ�A
        //�����J�݋敪������ǉ�����B
        if (l_request.accountOpenDiv != null)
        {
            //" and acc_open_kind_div = ? " 
            l_sbSearchCondition.append(" and acc_open_kind_div = ? ");
        }
        
        //�X�j �����������ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.������ != null �̏ꍇ�A
        //������������ǉ�����B 
        if (l_request.collectRate != null)
        {
            //" and account_charge_ratio = ? "
            l_sbSearchCondition.append(" and account_charge_ratio = ? ");
        }
        
        //�P�O�j�@@�Ώۊ���From�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A
        //�Ώۊ���From������ǉ�����B
        if (l_request.targetDate != null)
        {
            //" and ( appli_start_date <= ? or appli_start_date is null ) "
            l_sbSearchCondition.append(" and ( appli_start_date <= ? or appli_start_date is null ) ");
        }
        
        //�P�P�j �Ώۊ���To�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ
        //�Ώۊ���To������ǉ�����B
        if (l_request.targetDate != null)
        {
            //" and ( appli_end_date >= ? or appli_end_date is null ) "
            l_sbSearchCondition.append(" and ( appli_end_date >= ? or appli_end_date is null ) ");
        }
        
        //�P�Q�j �o�^�^�C�v�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�o�^�^�C�v != null�̏ꍇ�A
        //�o�^�^�C�v������ǉ�����B 
        if (l_request.registType != null)
        {
            //" and regist_type = ? "
            l_sbSearchCondition.append(" and regist_type = ? ");
        }
        
        //�P�R�j �L���敪�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.�L���敪 != null�̏ꍇ�A
        //�L���敪������ǉ�����B 
        if (l_request.activeDiv != null)
        {
            //" and valid_div = ? "
            l_sbSearchCondition.append(" and valid_div = ? ");
        }
        
        //�P�S�j��������������C���X�^���X.toString() ��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCondition.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j �߂�l���� <BR>
     * �E�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �Q�j �،���ЃR�[�h�����ǉ� <BR>
     * �E���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.�،���ЃR�[�h <BR>
     * <BR>
     * �R�j ���i�R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.���i�R�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɏ��i�R�[�h������ǉ�����B<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.���i�R�[�h<BR>
     * <BR>
     * �S�j �L�����y�[�����̏����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�L�����y�[������ != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɃL�����y�[�����̏�����ǉ�����B<BR>
     * �������܂�(�O�����v)����<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * "%" + �p�����[�^.���N�G�X�g�f�[�^.�L�����y�[������ + "%"<BR>
     * <BR>
     * �T�j ���X�R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɕ��X�R�[�h������ǉ�����B<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�L�����y�[����������.���X�R�[�h<BR>
     * <BR>
     * �U�j �ڋq�R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɍڋq�R�[�h������ǉ�����B<BR>
     * ���O����v����<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h + "%"<BR>
     * <BR>
     * �V�j ���҃R�[�h�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɉ��҃R�[�h������ǉ�����B<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h <BR>
     * <BR>
     * �W�j �����J�݋敪�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�����J�݋敪 != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɍ����J�݋敪������ǉ�����B<BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�����J�݋敪<BR>
     * <BR>
     * �X�j �����������ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.������ != null �̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɒ�����������ǉ�����B <BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.������<BR>
     * <BR>
     * �P�O�j �Ώۊ���From�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɑΏۊ���From������ǉ�����B <BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�Ώۓ�<BR>
     * <BR>
     * �P�P�j �Ώۊ���To�����ǉ�  <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɑΏۊ���To������ǉ�����B <BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�Ώۓ�<BR>
     * <BR>
     * �P�Q�j �o�^�^�C�v�����ǉ� <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�o�^�^�C�v != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɓo�^�^�C�v������ǉ�����B <BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�o�^�^�C�v<BR>
     * <BR>
     * �P�R�j�@@�L���敪�����ǉ�  <BR>
     * �E�p�����[�^.���N�G�X�g�f�[�^.�L���敪 != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɗL���敪������ǉ�����B <BR>
     * <BR>
     * [add()�Ɏw�肷�����] <BR>
     * �p�����[�^.���N�G�X�g�f�[�^.�L���敪<BR>
     * <BR>
     * �P�S�j�@@�z���ԋp  <BR>
     * �E�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR>
     * <BR>
     * @@param l_request -�@@<BR>
     *            (���N�G�X�g�f�[�^)<BR>
     *            �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     *            �o�^�ڋq�Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@param l_strInstitutionCode - <BR>
     *            �،���ЃR�[�h<BR>
     * @@return String[]
     * @@roseuid 45ADF3080191
     */
    protected Object[] createSearchConditionContainers(WEB3AdminAccInfoCampaignRegistAccListRequest l_request,
            String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME =
            ".createSearchConditionContainers(WEB3AdminAccInfoCampaignRegistAccListRequest, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j �߂�l���� 
        //�E�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�����B
        List l_lisArrSearchConditionList = new ArrayList();
        
        //�Q�j �،���ЃR�[�h�����ǉ� 
        //�E���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B
        //[add()�Ɏw�肷�����]
        //�p�����[�^.�،���ЃR�[�h 
        l_lisArrSearchConditionList.add(l_strInstitutionCode);
        
        //�R�j ���i�R�[�h�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.���i�R�[�h != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɏ��i�R�[�h������ǉ�����B
        if (l_request.itemCode != null)
        {
            //[add()�Ɏw�肷�����]
            //�p�����[�^.���N�G�X�g�f�[�^.���i�R�[�h
            l_lisArrSearchConditionList.add(l_request.itemCode);
        }
        
        //�S�j �L�����y�[�����̏����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.�L�����y�[������ != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɃL�����y�[�����̏�����ǉ�����B
        //�������܂�(�O�����v)����
        if (l_request.campaignName != null)
        {
            //[add()�Ɏw�肷�����] 
            //"%" + �p�����[�^.���N�G�X�g�f�[�^.�L�����y�[������ + "%"
            l_lisArrSearchConditionList.add("%" + l_request.campaignName + "%");
        }
        
        //�T�j ���X�R�[�h�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɕ��X�R�[�h������ǉ�����B
        if (l_request.branchCode != null)
        {
            //[add()�Ɏw�肷�����] 
            //�p�����[�^.���N�G�X�g�f�[�^.�L�����y�[����������.���X�R�[�h
            l_lisArrSearchConditionList.add(l_request.branchCode);
        }
        
        //�U�j �ڋq�R�[�h�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�Ɍڋq�R�[�h������ǉ�����B
        //���O����v����
        if (l_request.accountCode != null)
        {
            //[add()�Ɏw�肷�����]
            //�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h + "%"
            l_lisArrSearchConditionList.add(l_request.accountCode + "%");
        }
        
        //�V�j ���҃R�[�h�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�Ɉ��҃R�[�h������ǉ�����B
        if (l_request.traderCode != null)
        {
            //[add()�Ɏw�肷�����] 
            //�p�����[�^.���N�G�X�g�f�[�^.���҃R�[�h 
            l_lisArrSearchConditionList.add(l_request.traderCode);
        }
        
        //�W�j �����J�݋敪�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�����J�݋敪 != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�Ɍ����J�݋敪������ǉ�����B
        if (l_request.accountOpenDiv != null)
        {
            //[add()�Ɏw�肷�����]
            //�p�����[�^.���N�G�X�g�f�[�^.�����J�݋敪
            l_lisArrSearchConditionList.add(l_request.accountOpenDiv);
        }
        
        //�X�j �����������ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.������ != null �̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɒ�����������ǉ�����B
        if (l_request.collectRate != null)
        {
            //[add()�Ɏw�肷�����] 
            //�p�����[�^.���N�G�X�g�f�[�^.������
            l_lisArrSearchConditionList.add(l_request.collectRate);
        }
        
        //�P�O�j �Ώۊ���From�����ǉ�
        //�E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɑΏۊ���From������ǉ�����B
        if (l_request.targetDate != null)
        {
            //[add()�Ɏw�肷�����] 
            //�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ�
            l_lisArrSearchConditionList.add(l_request.targetDate);
        }
        
        //�P�P�j �Ώۊ���To�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ� != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɑΏۊ���To������ǉ�����B
        if (l_request.targetDate != null)
        {
            //[add()�Ɏw�肷�����]
            //�p�����[�^.���N�G�X�g�f�[�^.�Ώۓ�
            l_lisArrSearchConditionList.add(l_request.targetDate);
        }
        
        //�P�Q�j �o�^�^�C�v�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�o�^�^�C�v != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɓo�^�^�C�v������ǉ�����B 
        if (l_request.registType != null)
        {
            //[add()�Ɏw�肷�����]
            //�p�����[�^.���N�G�X�g�f�[�^.�o�^�^�C�v
            l_lisArrSearchConditionList.add(l_request.registType);
        }
        
        //�P�R�j�@@�L���敪�����ǉ� 
        //�E�p�����[�^.���N�G�X�g�f�[�^.�L���敪 != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�ɗL���敪������ǉ�����B
        if (l_request.activeDiv != null)
        {
            //[add()�Ɏw�肷�����]
            //�p�����[�^.���N�G�X�g�f�[�^.�L���敪
            l_lisArrSearchConditionList.add(l_request.activeDiv);
        }
        
        //�P�S�j�@@�z���ԋp  
        //�E�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B
        Object[] l_strArrSearchCondition = new Object[l_lisArrSearchConditionList.size()];
        l_lisArrSearchConditionList.toArray(l_strArrSearchCondition);
        
        log.exiting(STR_METHOD_NAME);
        return l_strArrSearchCondition;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * �E�e�[�u���񕨗������g�p���A�Ή�����\�[�g����������iorder by��j��ҏW����B<BR>
     * <BR>
     * �P�j �߂�l���� <BR>
     * �E�߂�l�̃\�[�g����������C���X�^���X�i�FStringBuffer�j�𐶐�����B <BR>
     * <BR>
     * �Q�j �p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B <BR>
     * <BR>
     * �Q�|�P�j�\�[�g�L�[.�L�[���ڂɑΉ�����e�[�u���񕨗������\�[�g����������ɒǉ�����B <BR>
     * �@@�@@ - �\�[�g�L�[.�L�[���� = ���X�R�[�h�̏ꍇ�A<BR>
     * �萔�������L�����y�[���ڋq����.���X�R�[�h<BR>
     * �@@�@@ - �\�[�g�L�[.�L�[���� = �ڋq�R�[�h�̏ꍇ�A<BR>
     * �萔�������L�����y�[���ڋq����.�ڋq�R�[�h<BR>
     * �@@�@@ - �\�[�g�L�[.�L�[���� = ���҃R�[�h�̏ꍇ�A<BR>
     * �萔�������L�����y�[���ڋq����.���҃R�[�h<BR>
     * �@@�@@ - �\�[�g�L�[.�L�[���� = �������̏ꍇ�A<BR>
     * �萔�������L�����y�[���ڋq����.������<BR>
     * �@@�@@ - �\�[�g�L�[.�L�[���� = �Ώۊ���From�̏ꍇ�A<BR>
     * �萔�������L�����y�[���ڋq����.�Ώۊ���From<BR>
     * �@@�@@ - �\�[�g�L�[.�L�[���� = �Ώۊ���To�̏ꍇ�A<BR>
     * �萔�������L�����y�[���ڋq����.�Ώۊ���To<BR>
     * <BR>
     * �Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����\�[�g����<BR>
     * (asc or desc)���\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �R�j �쐬�����\�[�g����������C���X�^���X.toString() ��ԋp����B <BR>
     * @@param l_sortKey - <BR>
     *            (�\�[�g�L�[)<BR>
     *            �\�[�g�����̔z��<BR>
     * @@return String
     * @@roseuid 45ADF317002A
     */
    protected String createSortSearchCondition(WEB3AccInfoSortKey l_sortKey[])
    {
        final String STR_METHOD_NAME =
            ".createSortSearchCondition(WEB3AccInfoSortKey)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j �߂�l����
        //�E�߂�l�̃\�[�g����������C���X�^���X�i�FStringBuffer�j�𐶐�����B 
        StringBuffer l_sbSortSearchCondition = new StringBuffer();
        
        Map l_map = new HashMap();
        //�\�[�g�L�[.�L�[���� = ���X�R�[�h
        l_map.put(WEB3AccInfoKeyItemDef.BRANCH_CODE, CommCampaignAccHistoryParams.PTYPE + ".branch_code");
        //�\�[�g�L�[.�L�[���� = �ڋq�R�[�h
        l_map.put(WEB3AccInfoKeyItemDef.ACCOUNT_CODE, CommCampaignAccHistoryParams.PTYPE + ".account_code");
        //�\�[�g�L�[.�L�[���� = ���҃R�[�h
        l_map.put(WEB3AccInfoKeyItemDef.TRADER_CODE, CommCampaignAccHistoryParams.PTYPE + ".sonar_trader_code");
        //�\�[�g�L�[.�L�[���� = ������
        l_map.put(WEB3AccInfoKeyItemDef.COLLECT_RATE, CommCampaignAccHistoryParams.PTYPE + ".account_charge_ratio");
        //�\�[�g�L�[.�L�[���� = �Ώۊ���From
        l_map.put(WEB3AccInfoKeyItemDef.TARGETPERIOD_FROM, CommCampaignAccHistoryParams.PTYPE + ".appli_start_date");
        //�\�[�g�L�[.�L�[���� = �Ώۊ���To
        l_map.put(WEB3AccInfoKeyItemDef.TARGETPERIOD_TO, CommCampaignAccHistoryParams.PTYPE + ".appli_end_date");

        String l_strAscDescDef = null;
        
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strAscDescDef = WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc) ? " ASC " : " DESC ";

            if (l_map.containsKey(l_sortKey[i].keyItem))
            {
                l_sbSortSearchCondition.append(l_map.get(l_sortKey[i].keyItem));
                l_sbSortSearchCondition.append(l_strAscDescDef);
            }
            
            if (i < (l_sortKey.length - 1))
            {
                l_sbSortSearchCondition.append(", ");
            }

        }
        
        //�R�j �쐬�����\�[�g����������C���X�^���X.toString() ��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSortSearchCondition.toString();
    }

    /**
     * (get�Ɖ���)<BR>
     * �萔�������L�����y�[���ڋq�Ɖ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} �uget�Ɖ��ʁv �Q��<BR>
     * @@param l_request -�@@<BR>
     *            (���N�G�X�g�f�[�^)<BR>
     *            �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     *            �o�^�ڋq�Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccInfoCampaignRegistAccListResponse
     * @@throws WEB3BaseException 
     * @@throws DataException 
     * @@roseuid 45B5A5D80149
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse getListScreen(
            WEB3AdminAccInfoCampaignRegistAccListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(WEB3AdminAccInfoCampaignRegistAccListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate( )
        l_request.validate();
        
        //���O�C�����C���X�^���X
        WEB3Administrator l_administrator = null;
        
        //���O�C�����擾
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate�����`�F�b�N()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN,
            false);
        
        //���N�G�X�g�f�[�^.���X�R�[�h != null�@@�̏ꍇ�A
        if (l_request.branchCode != null)
        {
            //Validate���X����()���s���B
            l_administrator.validateBranchPermission(l_request.branchCode);
        }
        
        //�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //create��������������
        String l_strSearchCondition = this.createSearchCondition(l_request);
        
        //�����������f�[�^�R���e�i
        Object[] l_strSearchConditionContainers = this.createSearchConditionContainers(l_request,l_strInstitutionCode);
        
        //create�\�[�g����������
        String l_strSortSearchCondition = this.createSortSearchCondition(l_request.sortKeys);
        
        //get�萔�������L�����y�[���ڋq�������
        List l_lisAccInfoCampaignActionInfo = this.getAccInfoCampaignActionInfo(
                l_strSearchCondition,l_strSearchConditionContainers,l_strSortSearchCondition,l_request.itemCode);
        
        //�萔�������L�����y�[���o�^�ڋq���List
        List l_lisAccInfoCampaignRegistAccountInfo = new ArrayList();
        
        //createResponse( )
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = 
           (WEB3AdminAccInfoCampaignRegistAccListResponse)l_request.createResponse();
        
        //get�萔�������L�����y�[���ڋq�������()�̖߂�l���A�ȉ��̎��ɏ������s���B
        if (l_lisAccInfoCampaignActionInfo == null
                || l_lisAccInfoCampaignActionInfo.size() == 0)
        {
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";
            l_response.registAccountInfo = null;
        }
        else
        {
            //get�萔�������L�����y�[���ڋq�������()�̖߂�l�̂����A
            //�\���Ώۍs�ifromIndex �` toIndex�j�̊�Loop���������{����B
            //[�\���Ώۍs�ifromIndex�CtoIndex�j�̌v�Z]
            //�y�[�W���\���s��
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            //�v���y�[�W�ԍ�
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            
            WEB3PageIndexInfo l_pageIndexInfo = new 
                WEB3PageIndexInfo(l_lisAccInfoCampaignActionInfo,l_intPageIndex,l_intPageSize);
            
            CommCampaignAccHistoryRow[] l_commCampaignAccHistoryRows = 
                (CommCampaignAccHistoryRow[])l_pageIndexInfo.getArrayReturned(CommCampaignAccHistoryRow.class);
            
            for (int i = 0; i < l_commCampaignAccHistoryRows.length; i++)
            {
                //�萔�������L�����y�[���ڋq�������
                CommCampaignAccHistoryRow l_commAccHistoryRow = 
                    l_commCampaignAccHistoryRows[i];
                
                //���i�R�[�h���擾����B
                String[] l_strItemCode;
                
                l_strItemCode = this.getItemCode(l_commAccHistoryRow.getCampaignId());

                //�萔�������L�����y�[���o�^�ڋq���
                WEB3AccInfoCampaignRegistAccountInfo l_registAccountInfo = new WEB3AccInfoCampaignRegistAccountInfo();
                
                //�萔�������L�����y�[���o�^�ڋq���.�萔���L�����y�[������ID�@@=�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�萔�������L�����y�[������ID
                l_registAccountInfo.campaignId = "" + l_commAccHistoryRow.getCampaignId();
                
                //�萔�������L�����y�[���o�^�ڋq���.���i�R�[�h = get���i�R�[�h()�̖߂�l
                l_registAccountInfo.itemCode = l_strItemCode;
                
                //�萔�������L�����y�[���o�^�ڋq���.�L�����y�[������ =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�萔�������L�����y�[������
                l_registAccountInfo.campaignName = l_commAccHistoryRow.getCommCampaignName();
                
                //�萔�������L�����y�[���o�^�ڋq���.���X�R�[�h  =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].���X�R�[�h
                l_registAccountInfo.branchCode = l_commAccHistoryRow.getBranchCode();
                
                //�萔�������L�����y�[���o�^�ڋq���.�ڋq�R�[�h =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�ڋq�R�[�h(��1)
                //(��1)�\���p�Ɂuget�萔�������L�����y�[���ڋq�������()[index].�ڋq�R�[�h(7��)�v��1���`6���ڂ��Z�b�g����B
                l_registAccountInfo.accountCode = l_commAccHistoryRow.getAccountCode().substring(0,6);
                
                //�萔�������L�����y�[���o�^�ڋq���.�ڋq�� =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�ڋq����
                l_registAccountInfo.accountName = l_commAccHistoryRow.getFamilyName();
                
                //�萔�������L�����y�[���o�^�ڋq���.���҃R�[�h =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].���҃R�[�h
                l_registAccountInfo.traderCode = l_commAccHistoryRow.getSonarTraderCode();
                
                //�萔�������L�����y�[���o�^�ڋq���.�����J�݋敪 =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�����J�݋敪
                l_registAccountInfo.accountOpenDiv = l_commAccHistoryRow.getAccOpenKindDiv();
                
                //�萔�������L�����y�[���o�^�ڋq���.������ =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].������
                l_registAccountInfo.collectRate = WEB3StringTypeUtility.formatNumber(
                    l_commAccHistoryRow.getAccountChargeRatio());
                
                //�萔�������L�����y�[���o�^�ڋq���.�Ώۊ���From =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�Ώۊ���From
                l_registAccountInfo.targetPeriodFrom = l_commAccHistoryRow.getAppliStartDate();
                
                //�萔�������L�����y�[���o�^�ڋq���.�Ώۊ���To =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�Ώۊ���To
                l_registAccountInfo.targetPeriodTo = l_commAccHistoryRow.getAppliEndDate();
                
                //�萔�������L�����y�[���o�^�ڋq���.�o�^�^�C�v =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�o�^�^�C�v
                l_registAccountInfo.registType = l_commAccHistoryRow.getRegistType();
                
                //�萔�������L�����y�[���o�^�ڋq���.�L���敪 =�@@
                //      get�萔�������L�����y�[���ڋq�������()[index].�L���敪
                l_registAccountInfo.activeDiv = l_commAccHistoryRow.getValidDiv();
                
                //ArrayList�Ɂu�萔�������L�����y�[���o�^�ڋq���v��ǉ�����B 
                l_lisAccInfoCampaignRegistAccountInfo.add(l_registAccountInfo);
            }
            
            //���X�|���X�f�[�^.���y�[�W�� = �����R�[�h�� / ���N�G�X�g�f�[�^.�y�[�W���\���s�� (��1)
            //(��1)�v�Z���ʂ͏����_�ȉ�1�ʂ�؂�グ�������l�Ƃ���B
            l_response.totalPages = Integer.toString(l_pageIndexInfo.getTotalPages());

            //���X�|���X�f�[�^.�����R�[�h�� = get�萔�������L�����y�[���o�^�ڋq���().size()
            l_response.totalRecords = Integer.toString(l_pageIndexInfo.getTotalRecords());
            
            //���X�|���X�f�[�^.�\���y�[�W�ԍ� = toIndex / ���N�G�X�g�f�[�^.�y�[�W���\���s�� (��1)
            //(��1)�v�Z���ʂ͏����_�ȉ�1�ʂ�؂�グ�������l�Ƃ���B
            l_response.pageIndex = Integer.toString(l_pageIndexInfo.getPageIndex());
            
            
            WEB3AccInfoCampaignRegistAccountInfo[] l_accInfoCampaignRegistAccountInfo = 
                new WEB3AccInfoCampaignRegistAccountInfo[l_lisAccInfoCampaignRegistAccountInfo.size()]; 
            
            l_lisAccInfoCampaignRegistAccountInfo.toArray(l_accInfoCampaignRegistAccountInfo);
            
            //���X�|���X�f�[�^.�萔�������L�����y�[���o�^�ڋq��� = toArray()�̖߂�l
            l_response.registAccountInfo = l_accInfoCampaignRegistAccountInfo;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�萔�������L�����y�[���ڋq�������)<BR>
     * �����ɊY������u�萔�������L�����y�[���ڋq�����I�u�W�F�N�g�v��List���擾����B <BR>
     * <BR>
     * �P�j QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �u�萔�������L�����y�[���ڋq�����I�u�W�F�N�g�v��List���擾����B <BR>
     * <BR>
     * [doFindAllQuery()�Ɏw�肷�����] <BR>
     * rowType�F�@@�萔�������L�����y�[���ڋq����RowType <BR>
     * where�F�@@�p�����[�^.��������������<BR>
     * orderBy�F�@@�p�����[�^.�\�[�g����������<BR>
     * conditions�F null <BR>
     * bindVars�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * subQueryRowTypes�F�@@�萔���������i�}�X�^RowType(*1) <BR>
     * <BR>
     * (*1)<BR>
     * �p�����[�^.���i�R�[�h�̗L���ɂ��A�g�p����QueryProcessor.doFindAllQuery( )��ύX���� <BR>
     *  �E �p�����[�^.���i�R�[�h != null �̏ꍇ�ɂ́A���⍇�����g�p���Ă���ׁA"subQueryRowTypes"��ݒ肷��B<BR>
     *   �@@�@@�@@�� doFindAllQuery( RowType rowType, String where, String orderBy, <BR>
     *    �@@                                String conditions, Object[] bindVars, RowType[] subQueryRowTypes ) <BR>
     *  �E �p�����[�^.���i�R�[�h = null �̏ꍇ�ɂ́A�ݒ肵�Ȃ��B<BR>
     *  �@@     �� doFindAllQuery( RowType rowType, String where, String orderBy, <BR>
     *                                      String conditions, Object[] bindVars ) <BR>
     * �Q�j �������ʂ̍s�I�u�W�F�N�g��<BR>
     * �u�萔�������L�����y�[���ڋq�����I�u�W�F�N�g�v�𐶐����AList�ŕԋp����B <BR>
     * @@param l_strSearchCondition -�@@<BR>
     *            ��������������<BR>
     * @@param l_strSearchConditionContainers -�@@<BR>
     *            ���������f�[�^�R���e�i<BR>
     * @@param l_sortCondition -�@@<BR>
     *            �\�[�g����������<BR>
     * @@param l_strItemCode -�@@<BR>
     *            ���i�R�[�h <BR>
     * @@return List
     * @@roseuid 45B5B270038B
     */
    protected List getAccInfoCampaignActionInfo(String l_strSearchCondition, Object[] l_strSearchConditionContainers,
        String l_sortCondition, String l_strItemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getAccInfoCampaignActionInfo(String,String[],String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisAccInfoCampaignActionInfoList = new ArrayList();  
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            if (l_strItemCode != null)
            {
                //�p�����[�^.���i�R�[�h != null �̏ꍇ�ɂ́A���⍇�����g�p���Ă���ׁA"subQueryRowTypes"��ݒ肷��
                l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                    l_strSearchCondition,
                    l_sortCondition,
                    null,
                    l_strSearchConditionContainers,
                    l_rowType);
            }
            else
            {
                //�E �p�����[�^.���i�R�[�h = null �̏ꍇ�ɂ́A�ݒ肵�Ȃ��B
                l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                    l_strSearchCondition,
                    l_sortCondition,
                    null,
                    l_strSearchConditionContainers);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAccInfoCampaignActionInfoList;
    }

    /**
     * (get���i�R�[�h)<BR>
     * �萔�������L�����y�[������ID�ɕR�t���A���i�R�[�h��S�Ď擾����B<BR>
     * <BR>
     * �P�j �w�萔�������L�����y�[�����i�}�X�^�x���ȉ��̏����Ō���<BR>
     * �i�����R�[�h���擾�o���Ȃ��ꍇ�͗�O�Ƃ���j <BR>
     * <BR>
     * [��������] <BR> 
     * �E �萔�������L�����y�[������ID = �p�����[�^.�萔�������L�����y�[������ID<BR>
     * <BR>
     * [�\�[�g����] <BR> 
     * �E ���i�R�[�h�i�����j<BR>
     * <BR>
     * �Q�j �߂�l�i���i�R�[�h�̔z��j���쐬<BR>
     * <BR> �E �u���i�R�[�h�z��v�𐶐�����B<BR> 
     * �E �P�j�̌������ʂ��u���i�R�[�h�z��v�ɒǉ�����B<BR>
     * �i���������擾�o�����ꍇ�́A�������������s���j<BR>
     * <BR>
     * ���i�R�[�h�z�� = �P�j�̌�������.���i�R�[�h<BR>
     * <BR>
     * �R�j �u���i�R�[�h�z��v��ԋp����B<BR>
     * @@param l_lngCampaignId -�@@<BR>
     *            �萔�������L�����y�[������ID<BR>
     * @@return String[]
     * @@throws WEB3BaseException 
     * @@throws DataNetworkException 
     * @@throws DataQueryException 
     * @@roseuid 45B6DC8703D0
     */
    protected String[] getItemCode(long l_lngCampaignId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getItemCode(long)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisCommCampaignProduct = new ArrayList();  
        
        // ���o����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        
        l_sbWhere.append(" campaign_id = ? ");
        
        // ���i�R�[�h�����w��
        String l_strSort = "comm_product_code asc";
        
        // ���o�����R���e�i�̐���
        Object[] l_objWhere =
            {
                new Long(l_lngCampaignId)
            };
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            // �L���[�e�[�u������������B 
            l_lisCommCampaignProduct = l_qp.doFindAllQuery(CommCampaignProductMstRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisCommCampaignProduct == null 
            || l_lisCommCampaignProduct.size() == 0)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,  
                this.getClass().getName()  + STR_METHOD_NAME);
        }

        //�߂�l�i���i�R�[�h�̔z��j���쐬
        List l_lisCommProductCode = new ArrayList();  
        
        for (int i = 0;i < l_lisCommCampaignProduct.size(); i++)
        {
            CommCampaignProductMstParams l_commCampaignProductRow = 
                (CommCampaignProductMstParams)l_lisCommCampaignProduct.get(i);
            
            l_lisCommProductCode.add(l_commCampaignProductRow.getCommProductCode());
        }
        
        String[] l_strProductCode = new String[l_lisCommProductCode.size()];
        l_lisCommProductCode.toArray(l_strProductCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductCode;
    }

}
@
