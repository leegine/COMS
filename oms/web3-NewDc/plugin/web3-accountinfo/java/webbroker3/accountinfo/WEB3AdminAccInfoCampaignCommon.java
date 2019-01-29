head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[������(WEB3AdminAccInfoCampaignCommon.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �h�C (���u) �V�K�쐬
Revision History : 2007/02/07 �g��i (���u) �d�l�ύX���f��189�A192
Revision History : 2007/02/16 �g��i (���u) �d�l�ύX���f��194�`197
Revision History : 2007/02/28 Inomata(SCS)���f��No.202
Revision History : 2007/03/07 Inomata(SCS)���f��No.209
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.accountinfo.data.CommCampaignCondMstDao;
import webbroker3.accountinfo.data.CommCampaignCondMstPK;
import webbroker3.accountinfo.data.CommCampaignCondMstParams;
import webbroker3.accountinfo.data.CommCampaignCondMstRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.accountinfo.define.WEB3AccInfoTargetPeriodCheckDef;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
//import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
//import webbroker3.gentrade.WEB3GentradeBizDate;
//import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �萔�������L�����y�[������<BR>
 * �萔�������L�����y�[�����ʃT�[�r�X<BR>
 *
 * @@author �h�C (���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignCommon
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminAccInfoCampaignCommon.class);

    /**
     * ���c�Ɠ�
     */
    private static final int BIZDATE_T1 = 1;

    /**
     * ���X�c�Ɠ�
     */
    private static final int BIZDATE_T2 = 2;

    /**
     * ���t�t�H�[�}�b�g������
     */
    private static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";

    private static final String BLANK = "";

    private static WEB3AdminAccInfoCampaignCommon accInfoCampaignCommon = new WEB3AdminAccInfoCampaignCommon();


    /**
     * @@roseuid 45C08B5301F4
     */
    protected WEB3AdminAccInfoCampaignCommon()
    {

    }

    /**
     * �萔�������L�����y�[�����ʂ̃C���X�^���X���擾
     */
    public static WEB3AdminAccInfoCampaignCommon getInstance()
    {
        return accInfoCampaignCommon;
    }

    /**
     * �萔�������L�����y�[�����ʂ̃C���X�^���X��ݒ�
     */
    public static void setInstance(WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon)
    {
        accInfoCampaignCommon = l_accInfoCampaignCommon;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g����������𐶐�����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�Ή�����\�[�g������iorder by��j��ҏW����B<BR>
     * <BR>
     * �P�j �߂�l����<BR>
     *     �߂�l�̃\�[�g����������C���X�^���X�i�FStringBuffer�j�𐶐�����B<BR>
     * <BR>
     * �Q�j �i�����j�\�[�g�L�[�̗v�f�����A�ȉ��������s���A�\�[�g��������쐬����B<BR>
     *   �Q-�P�j �i�����j�\�[�g�L�[�������L�[���ڂ��A<BR>
     *          �萔�������L�����y�[�������}�X�^�񕨗����ɕϊ����A�\�[�g������ɒǉ�����B<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.���X�R�[�h�̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.���X�R�[�h<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.�ڋq�R�[�h�̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.�ڋq�R�[�h<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.�������̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.������<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.���҃R�[�h�̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.���҃R�[�h<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.�����J�ݓ�From�̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.�����J�ݓ�From<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.�����J�ݓ�To�̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.�����J�ݓ�To<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.�����J�ݓ��o�^���̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.�o�^��<BR>
     * <BR>
     * �@@       -�\�[�g�L�[.�L�[����.�����J�ݓ��X�V���̏ꍇ�A<BR>
     *            �萔�������L�����y�[�������}�X�^.�X�V��<BR>
     * <BR>
     *   �Q-�Q�j �\�[�g�L�[.�����^�~���ɑΉ�����\�[�g�����iasc or desc�j���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j �쐬�����\�[�g����������C���X�^���X.toString()��ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�����̔z��<BR>
     * @@return String
     * @@roseuid 45AC9AC303DA
     */
    protected String createSortCondition(WEB3AccInfoSortKey[] l_sortKeys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCondition(WEB3AccInfoSortKey)";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �߂�l����
        //    �߂�l�̃\�[�g����������C���X�^���X�i�FStringBuffer�j�𐶐�����B
        StringBuffer l_sbSortCondition = new StringBuffer();

        //�Q�j �i�����j�\�[�g�L�[�̗v�f�����A�ȉ��������s���A�\�[�g��������쐬����B
        //�Q-�P�j �i�����j�\�[�g�L�[�������L�[���ڂ��A
        //       �萔�������L�����y�[�������}�X�^�񕨗����ɕϊ����A�\�[�g������ɒǉ�����B
        //-�\�[�g�L�[.�L�[����.���X�R�[�h�̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.���X�R�[�h
        //-�\�[�g�L�[.�L�[����.�ڋq�R�[�h�̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.�ڋq�R�[�h
        //-�\�[�g�L�[.�L�[����.�������̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.������
        //-�\�[�g�L�[.�L�[����.���҃R�[�h�̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.���҃R�[�h
        //-�\�[�g�L�[.�L�[����.�����J�ݓ�From�̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.�����J�ݓ�From
        //-�\�[�g�L�[.�L�[����.�����J�ݓ�To�̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.�����J�ݓ�To
        //-�\�[�g�L�[.�L�[����.�����J�ݓ��o�^���̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.�o�^��
        //-�\�[�g�L�[.�L�[����.�����J�ݓ��X�V���̏ꍇ�A
        //�萔�������L�����y�[�������}�X�^.�X�V��
        //�Q-�Q�j �\�[�g�L�[.�����^�~���ɑΉ�����\�[�g�����iasc or desc�j���\�[�g����������ɒǉ�����B
        Map l_mapTransform = new HashMap();
        l_mapTransform.put(WEB3AccInfoKeyItemDef.BRANCH_CODE, " branch_code ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.ACCOUNT_CODE, " account_code ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.COLLECT_RATE, " account_charge_ratio ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.TRADER_CODE, " sonar_trader_code ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_FROM, " acc_open_date_from ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_TO, " acc_open_date_to ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.REGIST_DATE, " created_timestamp ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.UPDATED_DATE, " last_updated_timestamp ");
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            l_sbSortCondition.append(l_mapTransform.get(l_sortKeys[i].keyItem));
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCondition.append(" asc ");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCondition.append(" desc ");
            }
            if ((i + 1) != l_sortKeys.length)
            {
                l_sbSortCondition.append(", ");
            }
            else
            {
                l_sbSortCondition.append(" ");
            }
        }

        //�R�j �쐬�����\�[�g����������C���X�^���X.toString()��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCondition.toString();
    }

    /**
     * (create�����f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�萔�������L�����y�[������ID�����ǉ�<BR>
     * �@@�萔�������L�����y�[������ID  != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɏ萔�������L�����y�[������ID������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.�萔�������L�����y�[������ID <BR>
     * <BR>
     * �R�j�@@�،���ЃR�[�h�����ǉ� <BR>
     * �@@�،���ЃR�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.�،���ЃR�[�h <BR>
     * <BR>
     * �S�j�@@���X�R�[�h�����ǉ� <BR>
     * �@@���X�R�[�h != null && ���X�R�[�h != ""�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɕ��X�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.���X�R�[�h<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�����ǉ� <BR>
     * �@@�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɍڋq�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.�ڋq�R�[�h + "%"<BR>
     * <BR>
     * �U�j�@@���i�R�[�h�����ǉ� <BR>
     * �@@���i�R�[�h != null�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɏ��i�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.���i�R�[�h<BR>
     * <BR>
     * �V�j�@@�����������ǉ� <BR>
     * �@@������ != null �̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�ɒ�����������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.������<BR>
     * <BR>
     * �W�j�@@���҃R�[�h�����ǉ� <BR>
     * �@@���҃R�[�h != null && ���҃R�[�h != ""�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɉ��҃R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.���҃R�[�h <BR>
     * <BR>
     * �X�j�@@�����J�݋敪�����ǉ� <BR>
     * �@@�����J�݋敪 != null && �����J�݋敪 != ""�̏ꍇ�A<BR>
     * ���������f�[�^�R���e�i�C���X�^���X�Ɍ����J�݋敪������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�i�����j��������.�����J�݋敪<BR>
     * <BR>
     * �P�O�j�@@�Ώۓ������ǉ�  <BR>
     * �@@�Ώۓ� != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɑΏۓ�������ǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�Ώۓ� <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�Ώۓ� <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�Ώۓ� <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�Ώۓ� <BR>
     * <BR>
     * �P�P�j�@@�Ώۊ���From�����ǉ�  <BR>
     * �@@�Ώۊ���From != null && �Ώۊ���From != ""�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɑΏۊ���From������ǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�Ώۊ���From <BR>
     * <BR>
     * �P�Q�j�@@�����J�ݓ�From�����ǉ�  <BR>
     * �@@�����J�ݓ�From != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�Ɍ����J�ݓ�From������ǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�����J�ݓ�From <BR>
     * <BR>
     * �P�R�j�@@�폜�t���O�����ǉ�  <BR>
     * �@@�폜�t���O != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɍ폜�t���O������ǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�폜�t���O <BR>
     * <BR>
     * �P�S�j�@@�L�����y�[�����̏����ǉ�  <BR>
     * �@@�L�����y�[������ != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɃL�����y�[�����̏�����ǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@"%" + �i�����j��������.�L�����y�[������ + "%" <BR>
     * <BR>
     * �P�T�j�@@�o�^�^�C�v�����ǉ�  <BR>
     * �@@�o�^�^�C�v != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɓo�^�^�C�v������ǉ�����B  <BR>
     *       <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�i�����j��������.�o�^�^�C�v <BR>
     * <BR>
     * �P�U�j�@@�z���ԋp  <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * @@param l_searchCondition - (��������)<BR>
     * �L�����y�[����������<BR>
     * @@return String[]
     * @@roseuid 45AC585D02CE
     */
    protected Object[] createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition l_searchCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)";
        log.entering(STR_METHOD_NAME);

        if (l_searchCondition == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j�@@�߂�l����
        //�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisSearchContainers = new ArrayList();

        //�Q�j�@@�萔�������L�����y�[������ID�����ǉ�
        //�萔�������L�����y�[������ID  != null�̏ꍇ�A
        //���������f�[�^�R���e�i�C���X�^���X�Ɏ萔�������L�����y�[������ID������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�萔�������L�����y�[������ID
        if (l_searchCondition.commissionCampaignConditionId != null)
        {
            l_lisSearchContainers.add(l_searchCondition.commissionCampaignConditionId);
        }

        //�R�j�@@�،���ЃR�[�h�����ǉ�
        //�،���ЃR�[�h != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�،���ЃR�[�h
        if (l_searchCondition.institutionCode != null)
        {
            l_lisSearchContainers.add(l_searchCondition.institutionCode);
        }

        //�S�j�@@���X�R�[�h�����ǉ�
        //���X�R�[�h != null && l_searchCondition.branchCode != ""�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɕ��X�R�[�h������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.���X�R�[�h
        if (l_searchCondition.branchCode != null && !BLANK.equals(l_searchCondition.branchCode))
        {
            l_lisSearchContainers.add(l_searchCondition.branchCode);
        }

        //�T�j�@@�ڋq�R�[�h�����ǉ�
        //�ڋq�R�[�h != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�Ɍڋq�R�[�h������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�ڋq�R�[�h + "%"
        if (l_searchCondition.accountCode != null)
        {
            l_lisSearchContainers.add(l_searchCondition.accountCode + "%");
        }

        //�U�j�@@���i�R�[�h�����ǉ�
        //���i�R�[�h != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɏ��i�R�[�h������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.���i�R�[�h
        if (l_searchCondition.itemCodes != null)
        {
            for (int i = 0; i < l_searchCondition.itemCodes.length; i++)
            {
                l_lisSearchContainers.add(l_searchCondition.itemCodes[i]);
            }
        }

        //�V�j�@@�����������ǉ�
        //������ != null �̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɒ�����������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.������
        if (l_searchCondition.collectRate != null)
        {
            l_lisSearchContainers.add(l_searchCondition.collectRate);
        }

        //�W�j�@@���҃R�[�h�����ǉ�
        //���҃R�[�h != null && ���҃R�[�h != ""�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�Ɉ��҃R�[�h������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.���҃R�[�h
        if (l_searchCondition.traderCode != null && !BLANK.equals(l_searchCondition.traderCode))
        {
            l_lisSearchContainers.add(l_searchCondition.traderCode);
        }

        //�X�j�@@�����J�݋敪�����ǉ�
        //�����J�݋敪 != null && �����J�݋敪 != ""�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�Ɍ����J�݋敪������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�����J�݋敪
        if (l_searchCondition.accountOpenDiv != null && !BLANK.equals(l_searchCondition.accountOpenDiv))
        {
            l_lisSearchContainers.add(l_searchCondition.accountOpenDiv);
        }

        //�P�O�j�@@�Ώۓ������ǉ�
        //�Ώۓ� != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɑΏۓ�������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�Ώۓ�
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�Ώۓ�
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�Ώۓ�
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�Ώۓ�
        if (l_searchCondition.targetDate != null)
        {
            l_lisSearchContainers.add(l_searchCondition.targetDate);
            l_lisSearchContainers.add(l_searchCondition.targetDate);
            l_lisSearchContainers.add(l_searchCondition.targetDate);
            l_lisSearchContainers.add(l_searchCondition.targetDate);
        }

        //�P�P�j�@@�Ώۊ���From�����ǉ�
        //�Ώۊ���From != null && �Ώۊ���From != ""�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɑΏۊ���From������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�Ώۊ���From
        if (l_searchCondition.targetPeriodFrom != null && !BLANK.equals(l_searchCondition.targetPeriodFrom))
        {
            l_lisSearchContainers.add(
                WEB3DateUtility.getDate(l_searchCondition.targetPeriodFrom,YYYYMMDD_DATE_FORMAT));
        }

        //�P�Q�j�@@�����J�ݓ�From�����ǉ�
        //�����J�ݓ�From != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�Ɍ����J�ݓ�From������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�����J�ݓ�From
        if (l_searchCondition.accountOpenDateFrom != null)
        {
            l_lisSearchContainers.add(l_searchCondition.accountOpenDateFrom);
        }

        //�P�R�j�@@�폜�t���O�����ǉ�
        //�폜�t���O != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɍ폜�t���O������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�폜�t���O
        if (l_searchCondition.deleteFlag != null)
        {
            l_lisSearchContainers.add(l_searchCondition.deleteFlag);
        }

        //�P�S�j�@@�L�����y�[�����̏����ǉ�
        //�L�����y�[������ != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɃL�����y�[�����̏�����ǉ�����B
        //[add()�Ɏw�肷�����]
        //"%" + �i�����j��������.�L�����y�[������ + "%"
        if (l_searchCondition.campaignName != null)
        {
            l_lisSearchContainers.add("%" + l_searchCondition.campaignName + "%");
        }

        //�P�T�j�@@�o�^�^�C�v�����ǉ�
        //�o�^�^�C�v != null�̏ꍇ�A���������f�[�^�R���e�i�C���X�^���X�ɓo�^�^�C�v������ǉ�����B
        //[add()�Ɏw�肷�����]
        //�i�����j��������.�o�^�^�C�v
        if (l_searchCondition.registerTypes != null)
        {
            for (int i = 0; i < l_searchCondition.registerTypes.length; i++)
            {
                l_lisSearchContainers.add(l_searchCondition.registerTypes[i]);
            }
        }

        //�P�U�j�@@�z���ԋp
        //�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B
        Object[] l_queryContainers = new Object[l_lisSearchContainers.size()];
        l_queryContainers = l_lisSearchContainers.toArray();
        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�萔�������L�����y�[������ID�����ǉ�<BR>
     * �@@�萔�������L�����y�[������ID  != null�̏ꍇ�A<BR>
     * �萔�������L�����y�[������ID������ǉ�����B <BR>
     * <BR>
     *   " campaign_id = ?"<BR>
     * <BR>
     * �R�j�@@�،���ЃR�[�h�����ǉ� <BR>
     * �@@�،���ЃR�[�h������ǉ�����B <BR>
     * <BR>
     *   �R-�P�j StringBuffer�� > 0�̏ꍇ<BR>
     * �@@           " and institution_code = ? "<BR>
     * <BR>
     *   �R-�P�j StringBuffer�� == 0 �̏ꍇ<BR>
     * �@@           " institution_code = ? "<BR>
     * <BR>
     * �S�j�@@���X�R�[�h�����ǉ� <BR>
     * �@@�S-�P�j ���X�R�[�h != null && ���X�R�[�h != ""�̏ꍇ <BR>
     * <BR>
     * �@@" and branch_code = ? " <BR>
     * <BR>
     * �@@�S-�Q�j ���X�R�[�h == ""�̏ꍇ <BR>
     * <BR>
     * �@@" and branch_code is null " <BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�����ǉ� <BR>
     * �@@�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and account_code like ? " <BR>
     * <BR>
     * �U�j�@@���i�R�[�h�����ǉ� <BR>
     * �@@���i�R�[�h != null or ���i�R�[�h�� > 0 �̏ꍇ�A���i�R�[�h������ǉ�����B <BR>
     *   �U-�P�j ���i�R�[�h�������P�̏ꍇ<BR>
     * �@@  "and campaign_id in <BR>
     * ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )" <BR>
     *   �U-�Q�j ���i�R�[�h���� > 1 �̏ꍇ<BR>
     * �@@  "and campaign_id in <BR>
     * ( select campaign_id from comm_campaign_product_mst where comm_product_code in (?,?,?,�c))" <BR>
     *      �����i�R�[�h������"?"��t������B<BR>
     * <BR>
     * �V�j�@@�����������ǉ� <BR>
     * �@@������ != null �̏ꍇ�A������������ǉ�����B <BR>
     * <BR>
     * �@@"and account_charge_ratio = ? " <BR>
     * <BR>
     * �W�j�@@���҃R�[�h�����ǉ� <BR>
     * �@@�W-�P�j ���҃R�[�h != null && ���҃R�[�h != ""�̏ꍇ <BR>
     * <BR>
     * �@@" and sonar_trader_code = ? " <BR>
     * <BR>
     * �@@�W-�Q�j ���҃R�[�h == ""�̏ꍇ <BR>
     * <BR>
     * �@@" and sonar_trader_code is null " <BR>
     * <BR>
     * �X�j�@@�����J�݋敪�����ǉ� <BR>
     * �@@�X-�P�j �����J�݋敪 != null && �����J�݋敪 != ""�̏ꍇ <BR>
     * <BR>
     * �@@" and acc_open_kind_div = ? " <BR>
     * <BR>
     * �@@�X-�Q�j �����J�݋敪 == ""�̏ꍇ <BR>
     * <BR>
     * �@@" and acc_open_kind_div is null " <BR>
     * <BR>
     * �P�O�j�@@�Ώۓ������ǉ�  <BR>
     * �@@�Ώۓ� != null�̏ꍇ�A�Ώۓ�������ǉ�����B  <BR>
     * <BR>
     *   " and (((appli_start_date <= ? OR appli_start_date IS NULL) AND <BR>
     *  (appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL)<BR>
     *  OR (acc_open_date_from <= ?<BR>
     *  AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ?<BR>
     *  AND acc_open_pass_month IS NOT NULL)<BR>
     *  OR (appli_start_date IS NULL AND appli_end_date IS NULL<BR>
     *  AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL))" <BR>
     * <BR>
     * �P�P�j�@@�Ώۊ���From�����ǉ�  <BR>
     *   �P�P-�P�j �Ώۊ���From != null && �Ώۊ���From != ""�̏ꍇ<BR>
     * <BR>
     * �@@" and appli_start_date = ? "<BR>
     * <BR>
     *   �P�P-�Q�j �Ώۊ���From == ""�̏ꍇ<BR>
     * <BR>
     * �@@" and appli_start_date is null "  <BR>
     * <BR>
     * �P�Q�j�@@�����J�ݓ�From�����ǉ�   <BR>
     * �@@�����J�ݓ�From != null�̏ꍇ�A�����J�ݓ�From������ǉ�����B <BR>
     * <BR>
     * �@@" and acc_open_date_from = ? "   <BR>
     * <BR>
     * �P�R�j�@@�폜�t���O�����ǉ�  <BR>
     * �@@�폜�t���O != null�̏ꍇ�A�폜�t���O������ǉ�����B  <BR>
     * <BR>
     * �@@" and delete_flag = ? "  <BR>
     * <BR>
     * �P�S�j�@@�L�����y�[�����̏����ǉ�  <BR>
     * �@@�L�����y�[������ != null�̏ꍇ�A�L�����y�[�����̏�����ǉ�����B  <BR>
     * <BR>
     * �@@" and comm_campaign_name like ? "  <BR>
     * <BR>
     * �P�T�j�@@�o�^�^�C�v�����ǉ�  <BR>
     * �@@�o�^�^�C�v != null or �o�^�^�C�v�� > 0 �̏ꍇ�A�o�^�^�C�v������ǉ�����B  <BR>
     *   �P�T-�P�j �o�^�^�C�v�������P�̏ꍇ <BR>
     * �@@  "and regist_type  = ? "  <BR>
     *   �P�T-�Q�j �o�^�^�C�v���� > 1 �̏ꍇ <BR>
     * �@@  "and regist_type in ( ?,?,?,�c)"  <BR>
     *      ���o�^�^�C�v������"?"��t������B <BR>
     * <BR>
     * �P�U�j��������������C���X�^���X.toString() ��ԋp���� <BR>
     * @@param l_searchCondition - (��������)<BR>
     * �L�����y�[�����������I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 45AC56D00296
     */
    protected String createSearchCondition(WEB3AdminAccInfoCampaignSearchCondition l_searchCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchCondition(WEB3AdminAccInfoCampaignSearchCondition)";
        log.entering(STR_METHOD_NAME);

        if (l_searchCondition == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j�@@�߂�l����
        //�߂�l�̌�������������C���X�^���X�i�FStringBuffer�j�𐶐�
        StringBuffer l_sbSearchCondition = new StringBuffer();

        //�Q�j�@@�萔�������L�����y�[������ID�����ǉ�
        //�萔�������L�����y�[������ID  != null�̏ꍇ�A�萔�������L�����y�[������ID������ǉ�����B
        //" campaign_id = ?"
        if (l_searchCondition.commissionCampaignConditionId != null)
        {
            l_sbSearchCondition.append(" campaign_id = ?");
        }

        //�R�j�@@�،���ЃR�[�h�����ǉ�
        //�،���ЃR�[�h != null�̏ꍇ�A������ǉ�����B
        if (l_searchCondition.institutionCode != null)
        {
            //�R-�P�j StringBuffer�� > 0�̏ꍇ
            //" and institution_code = ? "
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and institution_code = ? ");
            }
            //�R-�P�j StringBuffer�� == 0 �̏ꍇ
            //" institution_code = ? "
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" institution_code = ? ");
            }
        }

        //�S�j�@@���X�R�[�h�����ǉ�
        //���X�R�[�h != null && ���X�R�[�h != ""�̏ꍇ
        //" and branch_code = ? "
        //���X�R�[�h == ""�̏ꍇ
        //" and branch_code is null "
        if (l_searchCondition.branchCode != null && !BLANK.equals(l_searchCondition.branchCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and branch_code = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" branch_code = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.branchCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and branch_code is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" branch_code is null ");
            }

        }

        //�T�j�@@�ڋq�R�[�h�����ǉ�
        //�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����B
        //" and account_code like ? "
        if (l_searchCondition.accountCode != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and account_code like ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" account_code like ? ");
            }
         }

        //�U�j�@@���i�R�[�h�����ǉ�
        //���i�R�[�h != null or ���i�R�[�h�� > 0 �̏ꍇ�A���i�R�[�h������ǉ�����B
        //�U-�P�j ���i�R�[�h�������P�̏ꍇ
        //"and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )"
        if ((l_searchCondition.itemCodes != null) && (l_searchCondition.itemCodes.length == 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(
                    "and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? ) ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(
                    "campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? ) ");
            }
        }
        //�U-�Q�j ���i�R�[�h���� > 1 �̏ꍇ
        //"and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code in (?,?,?,�c))"
        //�����i�R�[�h������"?"��t������B
        else if ((l_searchCondition.itemCodes != null) && (l_searchCondition.itemCodes.length > 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(
                    " and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code in (");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(
                    " campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code in (");
            }
            for (int i = 0; i < l_searchCondition.itemCodes.length; i++)
            {
                l_sbSearchCondition.append("?");
                if (i != l_searchCondition.itemCodes.length - 1)
                {
                    l_sbSearchCondition.append(", ");
                }
            }
            l_sbSearchCondition.append("))");
        }

        //�V�j�@@�����������ǉ�
        //������ != null �̏ꍇ�A������������ǉ�����B
        //"and account_charge_ratio = ? "
        if (l_searchCondition.collectRate != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and account_charge_ratio = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" account_charge_ratio = ? ");
            }
        }

        //�W�j�@@���҃R�[�h�����ǉ�
        //���҃R�[�h != null && ���҃R�[�h != ""�̏ꍇ
        //" and sonar_trader_code = ? "
        //���҃R�[�h == ""�̏ꍇ
        //" and sonar_trader_code is null "
        if (l_searchCondition.traderCode != null && !BLANK.equals(l_searchCondition.traderCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and sonar_trader_code = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" sonar_trader_code = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.traderCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and sonar_trader_code is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" sonar_trader_code is null ");
            }
        }
        //�X�j�@@�����J�݋敪�����ǉ�
        //�����J�݋敪 != null && �����J�݋敪 != ""�̏ꍇ
        //" and acc_open_kind_div = ? "
        //�����J�݋敪 == ""�̏ꍇ
        //" and acc_open_kind_div is null "
        if (l_searchCondition.accountOpenDiv != null && !BLANK.equals(l_searchCondition.accountOpenDiv))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and acc_open_kind_div = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" acc_open_kind_div = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.accountOpenDiv))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and acc_open_kind_div is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" acc_open_kind_div is null ");
            }
        }

        //�P�O�j�@@�Ώۓ������ǉ�
        //�Ώۓ� != null�̏ꍇ�A�Ώۓ�������ǉ�����B
        // " AND (((appli_start_date <= ? OR appli_start_date IS NULL) AND
        //(appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL)
        //OR (acc_open_date_from <= ?
        //AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ?
        //AND acc_open_pass_month IS NOT NULL)
        //OR (appli_start_date IS NULL AND appli_end_date IS NULL
        //AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL))"
        if (l_searchCondition.targetDate != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" AND (((appli_start_date <= ? OR appli_start_date IS NULL) AND ");
                l_sbSearchCondition.append("(appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL) ");
                l_sbSearchCondition.append("OR (acc_open_date_from <= ? ");
                l_sbSearchCondition.append("AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ? ");
                l_sbSearchCondition.append("AND acc_open_pass_month IS NOT NULL) ");
                l_sbSearchCondition.append("OR (appli_start_date IS NULL AND appli_end_date IS NULL ");
                l_sbSearchCondition.append("AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL)) ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" (((appli_start_date <= ? OR appli_start_date IS NULL) AND ");
                l_sbSearchCondition.append("(appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL) ");
                l_sbSearchCondition.append("OR (acc_open_date_from <= ? ");
                l_sbSearchCondition.append("AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ? ");
                l_sbSearchCondition.append("AND acc_open_pass_month IS NOT NULL) ");
                l_sbSearchCondition.append("OR (appli_start_date IS NULL AND appli_end_date IS NULL ");
                l_sbSearchCondition.append("AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL)) ");
            }
        }

        //�P�P�j�@@�Ώۊ���From�����ǉ�
        //�P�P-�P�j �Ώۊ���From != null && �Ώۊ���From != ""�̏ꍇ
        //" and appli_start_date = ? "
        //�P�P-�Q�j �Ώۊ���From == ""�̏ꍇ
        //" and appli_start_date is null "
        if (l_searchCondition.targetPeriodFrom != null && !BLANK.equals(l_searchCondition.targetPeriodFrom))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and appli_start_date = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" appli_start_date = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.targetPeriodFrom))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and appli_start_date is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" appli_start_date is nul ");
            }
        }

        //�P�Q�j�@@�����J�ݓ�From�����ǉ�
        //�����J�ݓ�From != null�̏ꍇ�A�����J�ݓ�From������ǉ�����B
        //" and acc_open_date_from = ? "

        if (l_searchCondition.accountOpenDateFrom != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and acc_open_date_from = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" acc_open_date_from = ? ");
            }
        }

        //�P�R�j�@@�폜�t���O�����ǉ�
        //�폜�t���O != null�̏ꍇ�A�폜�t���O������ǉ�����B
        //" and delete_flag = ? "
        if (l_searchCondition.deleteFlag != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and delete_flag = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" delete_flag = ? ");
            }
        }

        //�P�S�j�@@�L�����y�[�����̏����ǉ�
        //�L�����y�[������ != null�̏ꍇ�A�L�����y�[�����̏�����ǉ�����B
        //" and comm_campaign_name like ? "
        if (l_searchCondition.campaignName != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and comm_campaign_name like ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" comm_campaign_name like ? ");
            }
        }

        //�P�T�j�@@�o�^�^�C�v�����ǉ�
        //�o�^�^�C�v != null or �o�^�^�C�v�� > 0 �̏ꍇ�A�o�^�^�C�v������ǉ�����B
        //�P�T-�P�j �o�^�^�C�v�������P�̏ꍇ
        //"and regist_type  = ? "
        if ((l_searchCondition.registerTypes != null) && (l_searchCondition.registerTypes.length == 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and regist_type  = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" regist_type  = ? ");
            }
        }
        //�P�T-�Q�j �o�^�^�C�v���� > 1 �̏ꍇ
        //"and regist_type in ( ?,?,?,�c)"
        //���o�^�^�C�v������"?"��t������B
        else if ((l_searchCondition.registerTypes != null) && (l_searchCondition.registerTypes.length > 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and regist_type in ( ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" regist_type in ( ");
            }
            for (int i = 0; i < l_searchCondition.registerTypes.length; i++)
            {
                l_sbSearchCondition.append("?");
                if (i != l_searchCondition.registerTypes.length - 1)
                {
                    l_sbSearchCondition.append(", ");
                }
            }
            l_sbSearchCondition.append(") ");
        }

        //�P�U�j��������������C���X�^���X.toString() ��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCondition.toString();
    }

    /**
     * (create�萔�������L�����y�[���������)<BR>
     * �萔�������L�����y�[�������}�X�^�̎萔�������L�����y�[������ID��<BR>
     * �萔�������L�����y�[�����i�}�X�^�̃L�����y�[���萔������ID�����ɁA<BR>
     * �i�����j�萔�������L�����y�[�������}�X�^List�Ɓi�����j�萔�������L�����y�[�����i�}�X�^List��<BR>
     * �}�[�W���s���B<BR>
     * <BR>
     * �P�j �ԋp�l�p�̎萔�������L�����y�[���������z��𐶐�����B�i�����F�L�����y�[�������}�X�^�sList.size()�j<BR>
     * <BR>
     * �Q�j �L�����y�[�������}�X�^�sList.size() �񐔈ȉ����J��Ԃ��B<BR>
     * <BR>
     *   �Q-�P�j �萔�������L�����y�[������ID���擾����B<BR>
     *             �L�����y�[�������}�X�^�sList.get(index).getColumn(�萔�������L�����y�[������ID)<BR>
     * <BR>
     *   �Q-�Q�j �L�����y�[�����i�}�X�^�sList��� �Q-�P�j �Ŏ擾����ID�������i�R�[�h���擾����B<BR>
     * <BR>
     *   �Q-�R�j �Q-�Q�j �ɉ����Ď擾�������i�R�[�h���̒���������String�z��𐶐����A�擾�������i�R�[�h��v�f�ɐݒ肷��B<BR>
     * <BR>
     *   �Q-�S�j �萔�������L�����y�[���������v���p�e�B�ֈȉ��ݒ���s���B<BR>
     * <BR>
     *    �萔�������L�����y�[���������[index].�萔�������L�����y�[������ID<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�萔�������L�����y�[������ID)<BR>
     *    �萔�������L�����y�[���������[index].�L�����y�[������<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�L�����y�[������)<BR>
     *    �萔�������L�����y�[���������[index].�،���ЃR�[�h<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�،���ЃR�[�h)<BR>
     *    �萔�������L�����y�[���������[index].���X�R�[�h<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(���X�R�[�h)<BR>
     *    �萔�������L�����y�[���������[index].�ڋq�R�[�h<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�ڋq�R�[�h)<BR>
     *    �萔�������L�����y�[���������[index].�ڋq��<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�ڋq����)<BR>
     *    �萔�������L�����y�[���������[index].���i�R�[�h[]<BR>
     *  = �Q-�R�j �Ő�������String�z��<BR>
     *    �萔�������L�����y�[���������[index].�Ώۊ���From<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�Ώۊ���From)<BR>
     *    �萔�������L�����y�[���������[index].�Ώۊ���To<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�Ώۊ���To)<BR>
     *    �萔�������L�����y�[���������[index].������<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�ڋq������)<BR>
     *    �萔�������L�����y�[���������[index].�����J�݌o�ߊ��ԁi���j<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�݌o�ߊ��ԁi���j)<BR>
     *    �萔�������L�����y�[���������[index].�����J�݌o�ߊ��ԁi���j    <BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�݌o�ߊ��ԁi���j)<BR>
     *    �萔�������L�����y�[���������[index].���҃R�[�h<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(���҃R�[�h)<BR>
     *    �萔�������L�����y�[���������[index].�����J�݋敪<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�݋敪)<BR>
     *    �萔�������L�����y�[���������[index].�����J�ݓ�From<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�ݓ�From)<BR>
     *    �萔�������L�����y�[���������[index].�����J�ݓ�To<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�ݓ�To)<BR>
     *    �萔�������L�����y�[���������[index].�o�^�^�C�v<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�o�^�^�C�v)<BR>
     *    �萔�������L�����y�[���������[index].�폜�t���O<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�폜�t���O)<BR>
     *    �萔�������L�����y�[���������[index].�����敪<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�����敪)<BR>
     *    �萔�������L�����y�[���������[index].�o�^��<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�X�V�҃R�[�h)<BR>
     *    �萔�������L�����y�[���������[index].�o�^��<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�쐬����)<BR>
     *    �萔�������L�����y�[���������[index].�X�V��<BR>
     *  = �L�����y�[�������}�X�^�sList.get(index).getColumn(�X�V����)<BR>
     * <BR>
     * �R�j �Q�j�ō쐬�����萔�������L�����y�[���������z���ԋp����B<BR>
     * <BR>
     * @@param l_lisCampaignConditionMasterList - (�L�����y�[�������}�X�^�sList)<BR>
     * �萔�������L�����y�[�������}�X�^�s���X�g<BR>
     * @@param l_lisCampaignItemMasterList - (�L�����y�[�����i�}�X�^�sList)<BR>
     * �萔�������L�����y�[�����i�}�X�^�s���X�g<BR>
     * @@return WEB3AccInfoCampaignInfo[]
     * @@roseuid 45B6C0DC02BC
     */
    protected WEB3AccInfoCampaignInfo[] createAccInfoCampaignInfo(
        List l_lisCampaignConditionMasterList,
        List l_lisCampaignItemMasterList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAccInfoCampaignInfo(List, List)";
        log.entering(STR_METHOD_NAME);

        if ((l_lisCampaignConditionMasterList == null) || (l_lisCampaignItemMasterList == null))
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �ԋp�l�p�̎萔�������L�����y�[���������z��𐶐�����B
        //�i�����F�L�����y�[�������}�X�^�sList.size()�j
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos
            = new WEB3AccInfoCampaignInfo[l_lisCampaignConditionMasterList.size()];

        Map l_map = new HashMap();

        //�L�����y�[�����i�}�X�^�sList��� �Q-�P�j �Ŏ擾����ID�������i�R�[�h���擾
        for (int i = 0; i < l_lisCampaignItemMasterList.size(); i++)
        {
            Long l_longCampaignId =
                new Long(((CommCampaignProductMstRow)l_lisCampaignItemMasterList.get(i)).getCampaignId());
            List l_lisCommProductCodes = new ArrayList();

            if (!l_map.containsKey(l_longCampaignId))
            {
                l_lisCommProductCodes.add(((CommCampaignProductMstRow)l_lisCampaignItemMasterList.get(i)).getCommProductCode());
                l_map.put(l_longCampaignId, l_lisCommProductCodes);
            }
            else
            {
                ((List)l_map.get(l_longCampaignId)).add(
                        ((CommCampaignProductMstRow)l_lisCampaignItemMasterList.get(i)).getCommProductCode());
            }
        }

        //�Q�j �L�����y�[�������}�X�^�sList.size() �񐔈ȉ����J��Ԃ��B
        for (int i = 0; i < l_lisCampaignConditionMasterList.size(); i++)
        {
            l_accInfoCampaignInfos[i] = new WEB3AccInfoCampaignInfo();

            String[] l_strCommProductCodes = null;

            //�Q-�P�j �萔�������L�����y�[������ID���擾����B
            //�L�����y�[�������}�X�^�sList.get(index).getColumn(�萔�������L�����y�[������ID)
            Long l_longCampaignId = new Long(((CommCampaignCondMstRow)
                l_lisCampaignConditionMasterList.get(i)).getCampaignId());

            //�Q-�Q�j �L�����y�[�����i�}�X�^�sList��� �Q-�P�j �Ŏ擾����ID�������i�R�[�h���擾����B
            //�Q-�R�j �Q-�Q�j �ɉ����Ď擾�������i�R�[�h���̒���������String�z��𐶐����A
            //�擾�������i�R�[�h��v�f�ɐݒ肷��B
            if ((!l_map.isEmpty()) && (l_map.containsKey(l_longCampaignId)))
            {
                List l_lisForGetCommProductCodes = (List)l_map.get(l_longCampaignId);
                l_strCommProductCodes =
                    (String[])(l_lisForGetCommProductCodes).toArray(new String[l_lisForGetCommProductCodes.size()]);
            }

            //�Q-�S�j �萔�������L�����y�[���������v���p�e�B�ֈȉ��ݒ���s���B
            //�萔�������L�����y�[���������[index].�萔�������L�����y�[������ID
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�萔�������L�����y�[������ID)
            l_accInfoCampaignInfos[i].campaignId =
                Long.toString(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getCampaignId());
            //�萔�������L�����y�[���������[index].�L�����y�[������
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�L�����y�[������)
            l_accInfoCampaignInfos[i].campaignName =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getCommCampaignName();
            //�萔�������L�����y�[���������[index].�،���ЃR�[�h
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�،���ЃR�[�h)
            l_accInfoCampaignInfos[i].institutionCode =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getInstitutionCode();
            //�萔�������L�����y�[���������[index].���X�R�[�h
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(���X�R�[�h)
            l_accInfoCampaignInfos[i].branchCode =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getBranchCode();
            //�萔�������L�����y�[���������[index].�ڋq�R�[�h
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�ڋq�R�[�h)
            String l_strAccountCode = ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccountCode();
            if (l_strAccountCode != null)
            {
                l_accInfoCampaignInfos[i].accountCode =
                    ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccountCode().substring(0, 6);
            }
            //�萔�������L�����y�[���������[index].�ڋq��
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�ڋq����)
            l_accInfoCampaignInfos[i].accountName =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getFamilyName();
            //�萔�������L�����y�[���������[index].���i�R�[�h[]
            //= �Q-�R�j �Ő�������String�z��
            l_accInfoCampaignInfos[i].itemCode = l_strCommProductCodes;
            //�萔�������L�����y�[���������[index].�Ώۊ���From
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�Ώۊ���From)
            l_accInfoCampaignInfos[i].targetPeriodFrom =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAppliStartDate();
            //�萔�������L�����y�[���������[index].�Ώۊ���To
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�Ώۊ���To)
            l_accInfoCampaignInfos[i].targetPeriodTo =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAppliEndDate();
            //�萔�������L�����y�[���������[index].������
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�ڋq������)
            l_accInfoCampaignInfos[i].collectRate =
                WEB3StringTypeUtility.formatNumber(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccountChargeRatio());
            //�萔�������L�����y�[���������[index].�����J�݌o�ߊ��ԁi���j
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�݌o�ߊ��ԁi���j)
            l_accInfoCampaignInfos[i].accopenPassPeriodMonth =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenPassMonth();
            //�萔�������L�����y�[���������[index].�����J�݌o�ߊ��ԁi���j
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�݌o�ߊ��ԁi���j)
            l_accInfoCampaignInfos[i].accopenPassPeriodDay =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenPassDate());
            //�萔�������L�����y�[���������[index].���҃R�[�h
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(���҃R�[�h)
            l_accInfoCampaignInfos[i].traderCode =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getSonarTraderCode());
            //�萔�������L�����y�[���������[index].�����J�݋敪
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�݋敪)
            l_accInfoCampaignInfos[i].accountOpenDiv =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenKindDiv());
            //�萔�������L�����y�[���������[index].�����J�ݓ�From
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�ݓ�From)
            l_accInfoCampaignInfos[i].accountOpenDateFrom =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenDateFrom());
            //�萔�������L�����y�[���������[index].�����J�ݓ�To
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�����J�ݓ�To)
            l_accInfoCampaignInfos[i].accountOpenDateTo =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenDateTo());
            //�萔�������L�����y�[���������[index].�o�^�^�C�v
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�o�^�^�C�v)
            l_accInfoCampaignInfos[i].registType =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getRegistType());
            //�萔�������L�����y�[���������[index].�폜�t���O
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�폜�t���O)
            l_accInfoCampaignInfos[i].deleteFlag =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getDeleteFlag());
            //�萔�������L�����y�[���������[index].�����敪
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�����敪)
            l_accInfoCampaignInfos[i].transactionDiv =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getStatus());
            //�萔�������L�����y�[���������[index].�o�^��
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�X�V�҃R�[�h)
            l_accInfoCampaignInfos[i].registrant =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getLastUpdater());
            //�萔�������L�����y�[���������[index].�o�^��
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�쐬����)
            l_accInfoCampaignInfos[i].registDate =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getCreatedTimestamp());
            //�萔�������L�����y�[���������[index].�X�V��
            //= �L�����y�[�������}�X�^�sList.get(index).getColumn(�X�V����)
            l_accInfoCampaignInfos[i].updateDate =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getLastUpdatedTimestamp());
        }

        //�R�j �Q�j�ō쐬�����萔�������L�����y�[���������z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCampaignInfos;
    }

    /**
     * (delete�L�����y�[������)<BR>
     * �萔�������L�����y�[�������}�X�^���R�[�h�̍폜���s���B<BR>
     * <BR>
     * �P�j �폜�Ώ����R�[�h�̍폜�t���O�`�F�b�N���s���B<BR>
     * <BR>
     *      [this.validate�폜�t���O()�Ɏw�肷�����]<BR>
     *        �萔�������L�����y�[������ID�F �i�����j�萔�������L�����y�[������ID<BR>
     *  <BR>
     * �Q�j Map�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B<BR>
     * <BR>
     *     �R�������F "delete_flag"      / �l�F "1"<BR>
     *     �R�������F "status"           / �l�F "0"<BR>
     *     �R�������F "last_updater"     / �l�F �i�����j�X�V�҃R�[�h<BR>
     *     �R�������F "last_updated_timestamp"   / �l�F ���ݓ���<BR>
     * <BR>
     * �R�j QueryProcessor#doUpdateQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *       [doUpdateQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *    �@@�@@ arg0�F�@@�i�����j�萔�������L�����y�[������ID��<BR>
     * ��ɂ����萔�������L�����y�[�������}�X�^��PrimaryKey�I�u�W�F�N�g<BR>
     *         arg1�F �Q�j�ō쐬����Map�I�u�W�F�N�g<BR>
     * @@param l_strCampaignConditionId - �萔�������L�����y�[������ID<BR>
     * <BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 45AD7DEF0382
     */
    public void deleteCampaignCondition(String l_strCampaignConditionId, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteCampaignCondition(String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strCampaignConditionId == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j �폜�Ώ����R�[�h�̍폜�t���O�`�F�b�N���s���B
        //[this.validate�폜�t���O()�Ɏw�肷�����]
        //�萔�������L�����y�[������ID�F �i�����j�萔�������L�����y�[������ID
        this.validateDeleteFlag(l_strCampaignConditionId);

        //�Q�j Map�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B
        //�R�������F "delete_flag" / �l�F "1"
        //�R�������F "status" / �l�F "0"
        //�R�������F "last_updater" / �l�F �i�����j�X�V�҃R�[�h
        //�R�������F "last_updated_timestamp" / �l�F ���ݓ���
        Map l_mapSearchCondition = new HashMap();
        l_mapSearchCondition.put("delete_flag", WEB3PvInfoDeleteFlagDef.DELETE_YES);
        l_mapSearchCondition.put("status", WEB3StatusDef.NOT_DEAL);
        l_mapSearchCondition.put("last_updater", l_strUpdaterCode);
        l_mapSearchCondition.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //�R�j QueryProcessor#doUpdateQuery()���\�b�h���R�[������B
        //[doUpdateQuery()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@�i�����j�萔�������L�����y�[������ID����ɂ����萔�������L�����y�[�������}�X�^��PrimaryKey�I�u�W�F�N�g
        //arg1�F �Q�j�ō쐬����Map�I�u�W�F�N�g
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                new CommCampaignCondMstPK(Long.parseLong(l_strCampaignConditionId)),
                l_mapSearchCondition);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�L�����y�[���ꗗ)<BR>
     * �萔�������L�����y�[�����R�[�h�̎擾���s���B<BR>
     * <BR>
     * �萔�������L�����y�[�������}�X�^�y�ю萔�������L�����y�[�����i�}�X�^���<BR>
     * �萔�������L�����y�[�����R�[�h���擾����B<BR>
     * <BR>
     * �P�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     *     [set�L�����y�[����������_all()�Ɏw�肷�����]<BR>
     *       �萔�������L�����y�[�����������F �i�����jl_request.�萔�������L�����y�[����������<BR>
     *       �萔�������L�����y�[������ID�F null<BR>
     *       �،���ЃR�[�h�F �i�����j�،���ЃR�[�h<BR>
     *       �o�^�^�C�v�F �i�����j�o�^�^�C�v<BR>
     * <BR>
     * <BR>
     * �Q�j �萔�������L�����y�[�������}�X�^���f�[�^���擾����B<BR>
     * <BR>
     *   �Q-�P�j ����������������쐬����B<BR>
     * <BR>
     *      [this.create��������������()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     *   �Q-�Q�j �����f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     *      [this.create�����f�[�^�R���e�i()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     *   �Q-�R�j �\�[�g�������쐬����B<BR>
     * <BR>
     *      [this.create�\�[�g����()�Ɏw�肷�����]<BR>
     *        �\�[�g�L�[[]�F�i�����jl_request.���q�l���\�[�g�L�[[]<BR>
     * <BR>
     *   �Q-�S�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B  <BR>
     *      [doFindAllQuery()�Ɏw�肷�����]  <BR>
     *    �@@  arg0�F�@@�萔�������L�����y�[�������}�X�^RowType <BR>
     *   �@@�@@ arg1�F�@@this.create��������������() �̖߂�l<BR>
     * �@@  �@@ arg2�F�@@this.create�\�[�g����()�̖߂�l<BR>
     * �@@�@@   arg3�F�@@null <BR>
     * �@@�@@   arg4�F�@@this.create�����f�[�^�R���e�i()�̖߂�l <BR>
     * �@@ �@@  arg5�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��<BR>
     * �@@�@@   arg6�F  ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�<BR>
     *        arg7�F  �萔�������L�����y�[�����i�}�X�^RowType�� <BR>
     * <BR>
     *           �i���萔�������L�����y�[�����i�}�X�^�ւ̕��⍇���������ꍇ��arg7�͕s�v�j <BR>
     * <BR>
     * �Q-�T�j �Q-�S�j�̖߂�l�� 0�� �̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j �Q�j�Ŏ擾�����f�[�^�����ɁA�萔�������L�����y�[�����i�}�X�^�̃f�[�^�������s���B<BR>
     *     �擾�o���Ȃ��ꍇ�́A��O���X���[����B<BR>
     * <BR>
     *      [this.get�L�����y�[�����i�}�X�^�s���X�g()�Ɏw�肷�����]<BR>
     *        �L�����y�[�������}�X�^�sList�F �Q�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList<BR>
     * <BR>
     * <BR>
     * �S�j �Q�j �Ŏ擾����List�� �R�j�Ŏ擾�������i�R�[�h���}�[�W����B<BR>
     * <BR>
     *      [this.create�萔�������L�����[�y�[���������()�Ɏw�肷�����]<BR>
     *        �L�����y�[�������}�X�^�sList�F �Q�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList<BR>
     *        �L�����y�[�����i�}�X�^�sList�F �R�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList<BR>
     * <BR>
     * �T�j �S�j�̖߂�l��ԋp����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strRegistTypes - (�o�^�^�C�v)<BR>
     * �o�^�^�C�v�̔z��<BR>
     * <BR>
     * 0�F �����J�ݏ����w��<BR>
     * 1�F�ʌڋq�w��<BR>
     * 2�F�����ʌڋq�w��<BR>
     * @@return WEB3AccInfoCampaignInfo[]
     * @@roseuid 45AB508903A1
     */
    public WEB3AccInfoCampaignInfo[] getCampaignList(WEB3GenRequest l_request, String l_strInstitutionCode,
            String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignList(WEB3GenRequest, String, String[])";
        log.entering(STR_METHOD_NAME);

        //�P�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B
        //[set�L�����y�[����������_all()�Ɏw�肷�����]
        //�萔�������L�����y�[�����������F �i�����jl_request.�萔�������L�����y�[����������
        //�萔�������L�����y�[������ID�F null
        //�،���ЃR�[�h�F �i�����j�،���ЃR�[�h
        //�o�^�^�C�v�F �i�����j�o�^�^�C�v
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();
        if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
        {
            l_campaignSearchCondition.setCampaignCondition(
                ((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).campaignSearchItem,
                null,
                l_strInstitutionCode,
                l_strRegistTypes);
        }
        else if (l_request instanceof WEB3AdminAccInfoCampaignIndiviListRequest)
        {
            l_campaignSearchCondition.setCampaignCondition(
                ((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).campaignSearchItem,
                null,
                l_strInstitutionCode,
                l_strRegistTypes);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "l_request = " + l_request
                );
        }

        //�Q�j �萔�������L�����y�[�������}�X�^���f�[�^���擾����B
        //�Q-�P�j ����������������쐬����B
        //[this.create��������������()�Ɏw�肷�����]
        //�L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        String l_strSearchCondition = this.createSearchCondition(l_campaignSearchCondition);

        //�Q-�Q�j �����f�[�^�R���e�i���쐬����B
        //[this.create�����f�[�^�R���e�i()�Ɏw�肷�����]
        //�L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        Object[] l_strSearchContainers = this.createSearchContainers(l_campaignSearchCondition);

        //�Q-�R�j �\�[�g�������쐬����B
        //[this.create�\�[�g����()�Ɏw�肷�����]
        //�\�[�g�L�[[]�F�i�����jl_request.���q�l���\�[�g�L�[[]
        String l_strSortCondition = null;
        if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
        {
            l_strSortCondition =
                this.createSortCondition(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).sortKeys);
        }
        else
        {
            l_strSortCondition =
                this.createSortCondition(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).sortKeys);
        }

        //�Q-�S�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //[doFindAllQuery()�Ɏw�肷�����]
        //arg0�F�@@�萔�������L�����y�[�������}�X�^RowType
        //arg1�F�@@this.create��������������() �̖߂�l
        //arg2�F�@@this.create�\�[�g����()�̖߂�l
        //arg3�F�@@null
        //arg4�F�@@this.create�����f�[�^�R���e�i()�̖߂�l
        //arg5�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        //arg6�F  ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        //arg7�F  �萔���������i�}�X�^RowType
        //�i���萔�������L�����y�[�����i�}�X�^�ւ̕��⍇���������ꍇ��arg7�͕s�v�j
        List l_lisCampaignConditionMasterList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            RowType[] l_commCampaignProductMstRowTypes = new RowType[1];
            l_commCampaignProductMstRowTypes[0] = CommCampaignProductMstRow.TYPE;
            if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
            {
                int l_intRequestPageIndex =
                    Integer.parseInt(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).pageIndex);

                if (l_intRequestPageIndex < 1)
                {
                    l_intRequestPageIndex = 1;
                }
                l_intRequestPageIndex = l_intRequestPageIndex - 1;

                if (l_campaignSearchCondition.itemCodes != null)
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).pageSize),
                        l_intRequestPageIndex,
                        l_commCampaignProductMstRowTypes);
                }
                else
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).pageSize),
                        l_intRequestPageIndex);
                }
            }
            else
            {
                int l_intRequestPageIndex =
                    Integer.parseInt(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).pageIndex);

                if (l_intRequestPageIndex < 1)
                {
                    l_intRequestPageIndex = 1;
                }
                l_intRequestPageIndex = l_intRequestPageIndex - 1;

                if (l_campaignSearchCondition.itemCodes != null)
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).pageSize),
                        l_intRequestPageIndex,
                        l_commCampaignProductMstRowTypes);
                }
                else
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).pageSize),
                        l_intRequestPageIndex);
                }
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�Q-�T�j �Q-�S�j�̖߂�l�� 0�� �̏ꍇ�Anull��ԋp����B
        if ((l_lisCampaignConditionMasterList != null) && (l_lisCampaignConditionMasterList.size() == 0))
        {
            log.debug("�萔�������L�����y�[�������}�X�^�̃f�[�^�擾���� 0���Anull��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�R�j �Q�j�Ŏ擾�����f�[�^�����ɁA�萔�������L�����y�[�����i�}�X�^�̃f�[�^�������s���B
        //�擾�o���Ȃ��ꍇ�́A��O���X���[����B
        //[this.get�L�����y�[�����i�}�X�^�s���X�g()�Ɏw�肷�����]
        //�L�����y�[�������}�X�^�sList�F �Q�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList
        List l_lisCampaignItemMasterList
            = this.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
        if ((l_lisCampaignItemMasterList == null) || (l_lisCampaignItemMasterList.isEmpty()))
        {
            log.debug("�萔�������L�����y�[�����i�}�X�^�̃f�[�^�擾�o���Ȃ��A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�S�j �Q�j �Ŏ擾����List�� �R�j�Ŏ擾�������i�R�[�h���}�[�W����B
        //[this.create�萔�������L�����[�y�[���������()�Ɏw�肷�����]
        //�L�����y�[�������}�X�^�sList�F �Q�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList
        //�L�����y�[�����i�}�X�^�sList�F �R�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos = this.createAccInfoCampaignInfo(
            l_lisCampaignConditionMasterList,
            l_lisCampaignItemMasterList);

        //�T�j �S�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCampaignInfos;
    }

    /**
     * (get�L�����y�[�����i�}�X�^�s���X�g)<BR>
     * �i�����j�萔�������L�����y�[������List�ɑΉ�����<BR>
     * �萔�������L�����y�[�����i�}�X�^�s���������A<BR>
     * �萔�������L�����y�[�����i�}�X�^�s���X�g��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j �i�����j�萔�������L�����y�[������List�������ɁA<BR>
     *      �萔�������L�����y�[�������}�X�^�����p��������쐬����B<BR>
     * <BR>
     *      "campaign_id in (?, ?, ?, �c)"     ��List�̒�����"?"��t���B<BR>
     * <BR>
     * <BR>
     * �Q�j �i�����j�萔�������L�����y�[������List���萔�������L�����y�[������ID�𒊏o���A<BR>
     *      �萔�������L�����y�[�������}�X�^�����p�f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     *   �Q-�P�j ArrayList�𐶐�<BR>
     * <BR>
     *   �Q-�Q�j List���̉񐔈ȉ����J��Ԃ��B<BR>
     * <BR>
     *           [add()�Ɏw�肷�����]<BR>
     *           �i�����j�萔�������L�����y�[������List.get(0�c).<BR>
     * getColumn(�萔�������L�����y�[������ID) <BR>
     * <BR>
     * <BR>
     * �R�j �萔�������L�����y�[�����i�}�X�^��背�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����] <BR>
     * �@@�@@  arg0�F �萔�������L�����y�[�����i�}�X�^Row.TYPE <BR>
     * �@@�@@  arg1�F �P�j�ō쐬���������p������<BR>
     * �@@�@@  arg2�F "campaign_id, comm_product_code"<BR>
     *       arg3�F null<BR>
     *       arg4�F �Q�j�ō쐬����ArrayList#toArray()<BR>
     * <BR>
     * �S�j �R�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList��ԋp����B<BR>
     * <BR>
     * @@param l_lisCampaignConditionMasterList - (�L�����y�[�������}�X�^�sList)<BR>
     * �萔�������L�����y�[�������̃��X�g<BR>
     * @@return List
     * @@roseuid 45B5E7260076
     */
    protected List getCampaignItemMasterList(List l_lisCampaignConditionMasterList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignItemMasterList(List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisCampaignConditionMasterList == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �i�����j�萔�������L�����y�[������List�������ɁA
        //�萔�������L�����y�[�������}�X�^�����p��������쐬����B
        //"campaign_id in (?, ?, ?, �c)"     ��List�̒�����"?"��t���B
        StringBuffer l_sbSearchCondition = new StringBuffer();
        if (l_lisCampaignConditionMasterList.size() != 0)
        {
            l_sbSearchCondition.append("campaign_id in (");
            for (int i = 0; i < l_lisCampaignConditionMasterList.size(); i++)
            {
                l_sbSearchCondition.append("?");
                if (i != l_lisCampaignConditionMasterList.size() - 1)
                {
                    l_sbSearchCondition.append(", ");
                }
            }
            l_sbSearchCondition.append(")");
        }

        //�Q�j �i�����j�萔�������L�����y�[������List���萔�������L�����y�[������ID�𒊏o���A
        //�萔�������L�����y�[�������}�X�^�����p�f�[�^�R���e�i���쐬����B
        //�Q-�P�j ArrayList�𐶐�
        List l_lisCampaignId = new ArrayList();
        //�Q-�Q�j List���̉񐔈ȉ����J��Ԃ��B
        for (int i = 0; i < l_lisCampaignConditionMasterList.size(); i++)
        {
            //[add()�Ɏw�肷�����]
            //�i�����j�萔�������L�����y�[������List.get(0�c).getColumn(�萔�������L�����y�[������ID)
            l_lisCampaignId.add(((CommCampaignCondMstRow)
                l_lisCampaignConditionMasterList.get(i)).getColumn("campaign_id"));
        }

        //�R�j �萔�������L�����y�[�����i�}�X�^��背�R�[�h���擾����B
        //[doFindAllQuery()�Ɏw�肷�����]
        //arg0�F �萔�������L�����y�[�����i�}�X�^Row.TYPE
        //arg1�F �P�j�ō쐬���������p������
        //arg2�F "campaign_id, comm_product_code"
        //arg3�F null
        //arg4�F �Q�j�ō쐬����ArrayList#toArray()
        List l_lisCampaignItemMasterList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCampaignItemMasterList = l_queryProcessor.doFindAllQuery(
                CommCampaignProductMstRow.TYPE,
                l_sbSearchCondition.toString(),
                "campaign_id, comm_product_code",
                null,
                l_lisCampaignId.toArray());
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�S�j �R�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisCampaignItemMasterList;
    }

    /**
     * (get�L�����y�[������)<BR>
     * �萔�������L�����y�[������ID���L�[�Ƃ���<BR>
     * �萔�������L�����y�[�������}�X�^�y�ю萔�������L�����y�[�����i���}�X�^���f�[�^���擾����B<BR>
     * �f�[�^���擾�o���Ȃ��ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * �P�j �����Ώ����R�[�h�̍폜�t���O�`�F�b�N���s���B<BR>
     * <BR>
     *      [this.validate�폜�t���O()�Ɏw�肷�����]<BR>
     *        �萔�������L�����y�[������ID�F �i�����j�ύX����.�萔�������L�����y�[������ID<BR>
     * <BR>
     * �Q�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     *     [set�L�����y�[����������_all()�Ɏw�肷�����]<BR>
     *       �萔�������L�����y�[�����������F null<BR>
     *       �萔�������L�����y�[������ID�F �i�����j�萔�������L�����y�[������ID<BR>
     *       �،���ЃR�[�h�F null<BR>
     *       �o�^�^�C�v�F null <BR>
     * <BR>
     * �R�j �萔�������L�����y�[�������}�X�^���f�[�^���擾����B<BR>
     *      �擾�o���Ȃ��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01037<BR>
     * <BR>
     *   �R-�P�j ����������������쐬����B<BR>
     * <BR>
     *      [this.create��������������()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�Q�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     *   �R-�Q�j �����f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     *      [this.create�����f�[�^�R���e�i()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�Q�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     *   �R-�R�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *      [doFindAllQuery()�Ɏw�肷�����]  <BR>
     *    �@@  arg0�F �萔�������L�����y�[�������}�X�^RowType<BR>
     *   �@@�@@ arg1�F�@@this.create��������������() �̖߂�l<BR>
     * �@@  �@@ arg2�F�@@this.create�����f�[�^�R���e�i()�̖߂�l <BR>
     * <BR>
     * �S�j �萔�������L�����y�[�����i�}�X�^��菤�i�R�[�h���擾����B<BR>
     *      �擾�o���Ȃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01037<BR>
     * <BR>
     *      [this.get�L�����y�[�����i�}�X�^�s���X�g()�Ɏw�肷�����]<BR>
     *        �L�����y�[�������}�X�^�sList�F �R�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList<BR>
     * <BR>
     * �T�j   �R�j �Ŏ擾����List�� �S�j�Ŏ擾�������i�R�[�h���}�[�W����B<BR>
     * <BR>
     *      [this.create�萔�������L�����[�y�[���������()�Ɏw�肷�����]<BR>
     *        �L�����y�[�������}�X�^�sList�F �R�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList<BR>
     *        �L�����y�[�����i�}�X�^�sList�F �S�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList<BR>
     * <BR>
     * �U�j �T�j �̖߂�l��ԋp����B <BR>
     * @@param l_strCampaignId - �萔�������L�����y�[������ID<BR>
     * <BR>
     * @@return WEB3AccInfoCampaignInfo
     * @@roseuid 45AC33210298
     */
    public WEB3AccInfoCampaignInfo getCampaignCondition(String l_strCampaignId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignItemMasterList(List)";
        log.entering(STR_METHOD_NAME);

        //�P�j �����Ώ����R�[�h�̍폜�t���O�`�F�b�N���s���B
        //[this.validate�폜�t���O()�Ɏw�肷�����]
        //�萔�������L�����y�[������ID�F �i�����j�ύX����.�萔�������L�����y�[������ID
        this.validateDeleteFlag(l_strCampaignId);

        //�Q�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B
        //[set�L�����y�[����������_all()�Ɏw�肷�����]
        //�萔�������L�����y�[�����������F null
        //�萔�������L�����y�[������ID�F �i�����j�萔�������L�����y�[������ID
        //�،���ЃR�[�h�F null
        //�o�^�^�C�v�F null
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();
        l_campaignSearchCondition.setCampaignCondition(
            null,
            l_strCampaignId,
            null,
            null);

        //�R�j �萔�������L�����y�[�������}�X�^���f�[�^���擾����B
        //�擾�o���Ȃ��ꍇ�́A��O���X���[����B
        //�R-�P�j ����������������쐬����B
        //[this.create��������������()�Ɏw�肷�����]
        //�L�����y�[���������ځF�Q�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        String l_strSearchCondition = this.createSearchCondition(l_campaignSearchCondition);

        //�R-�Q�j �����f�[�^�R���e�i���쐬����B
        //[this.create�����f�[�^�R���e�i()�Ɏw�肷�����]
        //�L�����y�[���������ځF�Q�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        Object[] l_strSearchContainers = this.createSearchContainers(l_campaignSearchCondition);

        //�R-�R�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
        //[doFindAllQuery()�Ɏw�肷�����]
        //arg0�F �萔�������L�����y�[�������}�X�^RowType
        //arg1�F�@@this.create��������������() �̖߂�l
        //arg2�F�@@this.create�����f�[�^�R���e�i()�̖߂�l
        List l_lisCampaignConditionMasterList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                CommCampaignCondMstRow.TYPE,
                l_strSearchCondition,
                l_strSearchContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        if ((l_lisCampaignConditionMasterList == null) || (l_lisCampaignConditionMasterList.size() == 0))
        {
            log.debug("�萔�������L�����y�[�������}�X�^�̃f�[�^�擾�o���Ȃ��A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�S�j �萔�������L�����y�[�����i�}�X�^��菤�i�R�[�h���擾����B
        //�擾�o���Ȃ��ꍇ�͗�O���X���[����B
        //[this.get�L�����y�[�����i�}�X�^�s���X�g()�Ɏw�肷�����]
        //�L�����y�[�������}�X�^�sList�F �R�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList
        List l_lisCampaignItemMasterList = this.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
        if ((l_lisCampaignItemMasterList == null) || (l_lisCampaignItemMasterList.isEmpty()))
        {
            log.debug("�萔�������L�����y�[�����i�}�X�^�̃f�[�^�擾�o���Ȃ��A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�T�j   �R�j �Ŏ擾����List�� �S�j�Ŏ擾�������i�R�[�h���}�[�W����B
        //[this.create�萔�������L�����[�y�[���������()�Ɏw�肷�����]
        //�L�����y�[�������}�X�^�sList�F �R�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList
        //�L�����y�[�����i�}�X�^�sList�F �S�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos
            = this.createAccInfoCampaignInfo(
            l_lisCampaignConditionMasterList,
            l_lisCampaignItemMasterList);

        //�U�j �T�j �̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCampaignInfos[0];
    }

    /**
     * (get�d���L�����y�[������)<BR>
     * �萔�������L�����y�[�����R�[�h�̎擾���s���B<BR>
     * �d���L�����y�[�������p���\�b�h<BR>
     * <BR>
     * �萔�������L�����y�[�������}�X�^�y�ю萔�������L�����y�[�����i�}�X�^���<BR>
     * �萔�������L�����y�[�����R�[�h���擾����B<BR>
     * ���R�[�h���擾�o���Ȃ��ꍇ��null�l��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j �萔�������L�����y�[�������}�X�^���f�[�^���擾����B<BR>
     * <BR>
     *   �P-�P�j ����������������쐬����B<BR>
     * <BR>
     *      [this.create��������������()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�i�����j�d����������<BR>
     * <BR>
     *   �P-�Q�j �����f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     *      [this.create�����f�[�^�R���e�i()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�d����������<BR>
     * <BR>
     *   �P-�R�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B  <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����] <BR>
     *    �@@ arg0�F�@@�萔�������L�����y�[�������}�X�^RowType <BR>
     *       arg1�F�@@�P-�P�j�ō쐬�������������� + " and delete_flag != '1' or delete_flag is null"<BR>
     * �@@�@@  arg2�F�@@"campaign_id" <BR>
     * �@@    arg3�F�@@null<BR>
     * �@@�@@  arg4�F�@@�P-�Q�j�ō쐬���������f�[�^�R���e�i<BR>
     *       arg5�F  �萔���������i�}�X�^RowType<BR>
     * <BR>
     * �Q�j �P�j �̖߂�lList�̒��� > 0 �̏ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     *   �Q-�P�j �萔�������L�����y�[�����i�}�X�^��菤�i�}�X�^�s���擾����B<BR>
     *           �擾�o���Ȃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01037<BR>
     * <BR>
     *           [this.get�L�����y�[�����i�}�X�^�s���X�g()�Ɏw�肷�����]<BR>
     *             �L�����y�[�������}�X�^�sList�F �P�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList<BR>
     * <BR>
     *   �Q-�Q�j �P�j �Ŏ擾����List�� �Q-�P�j�Ŏ擾�������i�R�[�h���}�[�W����B<BR>
     * <BR>
     *      [this.create�萔�������L�����[�y�[���������()�Ɏw�肷�����]<BR>
     *        �L�����y�[�������}�X�^�sList�F �P�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList<BR>
     *        �L�����y�[�����i�}�X�^�sList�F �Q-�P�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList<BR>
     * <BR>
     *   �Q-�R�j �Q-�Q�j�̖߂�l��ԋp����B <BR>
     * <BR>
     * �R�j �P�j�̖߂�lList�̒��� = 0 �̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_sameSearchCondition - �d����������<BR>
     * @@return WEB3AccInfoCampaignInfo[]
     * @@roseuid 45B07FF101D8
     */
    public WEB3AccInfoCampaignInfo[] getSameCampaignCondition(
            WEB3AdminAccInfoCampaignSearchCondition l_sameSearchCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)";
        log.entering(STR_METHOD_NAME);

        //�P�j �萔�������L�����y�[�������}�X�^���f�[�^���擾����B
        //�P-�P�j ����������������쐬����B
        //[this.create��������������()�Ɏw�肷�����]
        //�L�����y�[���������ځF�i�����j�d����������
        String l_strSearchCondition
            = this.createSearchCondition(l_sameSearchCondition);

        //�P-�Q�j �����f�[�^�R���e�i���쐬����B
        //[this.create�����f�[�^�R���e�i()�Ɏw�肷�����]
        //�L�����y�[���������ځF�d����������
        Object[] l_strSearchContainers
            = this.createSearchContainers(l_sameSearchCondition);

        //�P-�R�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //[doFindAllQuery()�Ɏw�肷�����]
        //arg0�F�@@�萔�������L�����y�[�������}�X�^RowType
        //arg1�F�@@�P-�P�j�ō쐬�������������� + " and delete_flag != '1' or delete_flag is null"
        //arg2�F�@@"campaign_id"
        //arg3�F�@@null
        //arg4�F�@@�P-�Q�j�ō쐬���������f�[�^�R���e�i
        //arg5�F  �萔���������i�}�X�^RowType
        List l_lisCampaignConditionMasters = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            RowType[] l_commCampaignProductMstRowTypes = new RowType[1];
            l_commCampaignProductMstRowTypes[0] = CommCampaignProductMstRow.TYPE;
            l_lisCampaignConditionMasters = l_queryProcessor.doFindAllQuery(
                CommCampaignCondMstRow.TYPE,
                l_strSearchCondition + " and (delete_flag != '1' or delete_flag is null)",
                "campaign_id",
                null,
                l_strSearchContainers,
                l_commCampaignProductMstRowTypes);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�Q�j �P�j �̖߂�lList�̒��� > 0 �̏ꍇ�A�ȉ��������s���B
        //�Q-�P�j �萔�������L�����y�[�����i�}�X�^��菤�i�}�X�^�s���擾����B
        //�擾�o���Ȃ��ꍇ�͗�O���X���[����B
        //[this.get�L�����y�[�����i�}�X�^�s���X�g()�Ɏw�肷�����]
        //�L�����y�[�������}�X�^�sList�F �P�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList
        List l_lisCampaignItemMasters = new ArrayList();
        if ((l_lisCampaignConditionMasters != null) && (l_lisCampaignConditionMasters.size() > 0))
        {
            l_lisCampaignItemMasters = this.getCampaignItemMasterList(l_lisCampaignConditionMasters);
            if ((l_lisCampaignItemMasters == null) || (l_lisCampaignItemMasters.size() == 0))
            {
                log.debug("�萔�������L�����y�[�����i�}�X�^�̃f�[�^�擾�o���Ȃ��A��O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }

            //�Q-�Q�j �P�j �Ŏ擾����List�� �Q-�P�j�Ŏ擾�������i�R�[�h���}�[�W����B
            //[this.create�萔�������L�����[�y�[���������()�Ɏw�肷�����]
            //�L�����y�[�������}�X�^�sList�F �P�j�Ŏ擾�����萔�������L�����y�[�������}�X�^�sList
            //�L�����y�[�����i�}�X�^�sList�F �Q-�P�j�Ŏ擾�����萔�������L�����y�[�����i�}�X�^�sList
            WEB3AccInfoCampaignInfo[] l_campaignInfos =
                this.createAccInfoCampaignInfo(l_lisCampaignConditionMasters, l_lisCampaignItemMasters);

            //�Q-�R�j �Q-�Q�j�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_campaignInfos;
        }

        //�R�j �P�j�̖߂�lList�̒��� = 0 �̏ꍇ�Anull��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get�����R�[�h����)<BR>
     * �����R�[�h�����l��Ԃ��B<BR>
     * <BR>
     * <BR>
     * �P�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     *     [set�L�����y�[����������_all()�Ɏw�肷�����]<BR>
     *       �萔�������L�����y�[�����������F �i�����j�L�����y�[����������<BR>
     *       �萔�������L�����y�[������ID�F null<BR>
     *       �،���ЃR�[�h�F �i�����j�،���ЃR�[�h<BR>
     *       �o�^�^�C�v�F �i�����j�o�^�^�C�v <BR>
     * <BR>
     * �Q�j ����������������쐬����B<BR>
     *     [create��������������()�Ɏw�肷�����]<BR>
     *     �L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     * �R�j �����f�[�^�R���e�i���쐬����B<BR>
     *     [create�����f�[�^�R���e�i()�Ɏw�肷�����]<BR>
     *     �L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     * �R�j �����R�[�h�������擾����B<BR>
     * <BR>
     *   �R-�P�j ���������Ɂu���i�R�[�h�v���܂܂�Ă��Ȃ��ꍇ<BR>
     * <BR>
     *     �R-�P-�P�j QueryProcessor#doGetCountQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *                  [doGetCountQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *    �@@  �@@           arg0�F�@@�萔�������L�����y�[�������}�X�^RowType <BR>
     *   �@@�@@              arg1�F�@@this.create��������������() �̖߂�l<BR>
     * �@@  �@@              arg2�F�@@this.create�����f�[�^�R���e�i()�̖߂�l <BR>
     * <BR>
     *     �R-�P-�Q�j QueryProcessor#doGetCountQuery()�̖߂�l��ԋp����B<BR>
     * <BR>
     * <BR>
     *   �R-�Q�j ���������Ɂu���i�R�[�h�v���܂܂�Ă���ꍇ<BR>
     * <BR>
     *     �R-�Q-�P�j QueryProcessor#oFindAllQuery()���\�b�h���R�[������B  <BR>
     * <BR>
     *                  [doFindAllQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *    �@@�@@            arg0�F�@@�萔�������L�����y�[�������}�X�^RowType <BR>
     *   �@@�@@             arg1�F�@@this.create��������������() �̖߂�l<BR>
     * �@@  �@@             arg2�F�@@null<BR>
     * �@@�@@               arg3�F�@@null <BR>
     * �@@�@@               arg4�F�@@this.create�����f�[�^�R���e�i()�̖߂�l <BR>
     *                    arg5�F  �萔���������i�}�X�^RowType<BR>
     * <BR>
     *     �R-�Q-�Q�j QueryProcessor#doFindAllQuery()���\�b�h�̖߂�l�̒�����ԋp����B<BR>
     * @@param l_campaignSearchCondition - (�L�����y�[����������)<BR>
     * �萔�������L�����y�[�����������I�u�W�F�N�g<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strRegistTypes - �o�^�^�C�v<BR>
     * @@return int
     * @@roseuid 45AC95920184
     */
    public int getAllRecordCount(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strInstitutionCode, String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getAllRecordCount(WEB3AccInfoCampaignSearchCondition, String, String[])";
        log.entering(STR_METHOD_NAME);

        //�P�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B
        //[set�L�����y�[����������_all()�Ɏw�肷�����]
        //�萔�������L�����y�[�����������F �i�����j�L�����y�[����������
        //�萔�������L�����y�[������ID�F null
        //�،���ЃR�[�h�F �i�����j�،���ЃR�[�h
        //�o�^�^�C�v�F �i�����j�o�^�^�C�v
        WEB3AdminAccInfoCampaignSearchCondition l_adminCampaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();
        l_adminCampaignSearchCondition.setCampaignCondition(
            l_campaignSearchCondition,
            null,
            l_strInstitutionCode,
            l_strRegistTypes);

        //�Q�j ����������������쐬����B
        //[create��������������()�Ɏw�肷�����]
        //�L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        String l_strSearchCondition
            = this.createSearchCondition(l_adminCampaignSearchCondition);

        //�R�j �����f�[�^�R���e�i���쐬����B
        //[create�����f�[�^�R���e�i()�Ɏw�肷�����]
        //�L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        Object[] l_strSearchContainers
            = this.createSearchContainers(l_adminCampaignSearchCondition);

        //�R�j �����R�[�h�������擾����B
        //�R-�P�j ���������Ɂu���i�R�[�h�v���܂܂�Ă��Ȃ��ꍇ
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if ((l_adminCampaignSearchCondition.itemCodes == null)
                || (l_adminCampaignSearchCondition.itemCodes.length <= 0))
            {
                //�R-�P-�P�j QueryProcessor#doGetCountQuery()���\�b�h���R�[������B
                //[doGetCountQuery()�ɃZ�b�g����p�����[�^]
                //arg0�F�@@�萔�������L�����y�[�������}�X�^RowType
                //arg1�F�@@this.create��������������() �̖߂�l
                //arg2�F�@@this.create�����f�[�^�R���e�i()�̖߂�l
                int l_intResultCnt;
                l_intResultCnt = l_queryProcessor.doGetCountQuery(
                    CommCampaignCondMstRow.TYPE,
                    l_strSearchCondition,
                    l_strSearchContainers);
                //�R-�P-�Q�j QueryProcessor#doGetCountQuery()�̖߂�l��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_intResultCnt;
            }

            //�R-�Q�j ���������Ɂu���i�R�[�h�v���܂܂�Ă���ꍇ
            else
            {
                //�R-�Q-�P�j QueryProcessor#oFindAllQuery()���\�b�h���R�[������B
                //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
                //arg0�F�@@�萔�������L�����y�[�������}�X�^RowType
                //arg1�F�@@this.create��������������() �̖߂�l
                //arg2�F�@@null
                //arg3�F�@@null
                //arg4�F�@@this.create�����f�[�^�R���e�i()�̖߂�l
                //arg5�F  �萔���������i�}�X�^RowType
                RowType[] l_commCampaignProductMstRowTypes = new RowType[1];
                l_commCampaignProductMstRowTypes[0] = CommCampaignProductMstRow.TYPE;
                List l_lisCampaignConditionMasters = l_queryProcessor.doFindAllQuery(
                    CommCampaignCondMstRow.TYPE,
                    l_strSearchCondition,
                    null,
                    null,
                    l_strSearchContainers,
                    l_commCampaignProductMstRowTypes);

                //�R-�Q-�Q�j QueryProcessor#doFindAllQuery()���\�b�h�̖߂�l�̒�����ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_lisCampaignConditionMasters.size();
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
    }

    /**
     * (insert�L�����y�[������)<BR>
     * �萔�������L�����y�[�������}�X�^���R�[�h�y��<BR>
     * �萔�������L�����y�[�����i�}�X�^���R�[�h�̓o�^���s���B<BR>
     * <BR>
     * <BR>
     * �P�j �萔�������L�����y�[�������}�X�^�̓o�^���s���B<BR>
     * <BR>
     * �P-�P�j �i�����j�o�^���.�o�^�^�C�v != 0 �̏ꍇ
     *   �P-�P-�P�j �g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B<BR>
     *   [get�ڋq()�ɃZ�b�g����p�����[�^] <BR>
     *    �@@arg0�F�@@�i�����j�o�^���.�،���ЃR�[�h<BR>
     *  �@@  arg1�F�@@�i�����j�o�^���.���X�R�[�h<BR>
     *  �@@  arg2�F�@@�i�����j�o�^���.�ڋq�R�[�h<BR>
     *    �P-�P-�Q�j �P�|�P-�P�j�̖߂�l����7���̌ڋq�R�[�h���擾����B<BR>
     *  �P-�Q�j �萔�������L�����y�[�������}�X�^�s�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B<BR>
     * <BR>
     *     �萔�������L�����y�[�������}�X�^�s.�萔�������L�����y�[������ID<BR>
     *  = �萔�������L�����y�[�������}�X�^Dao.newPkValue()�̖߂�l<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�萔�������L�����y�[������<BR>
     *  = �i�����j�o�^���.�萔�������L�����y�[������<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�،���ЃR�[�h<BR>
     *  = �i�����j�o�^���.�،���ЃR�[�h<BR>
     *     �萔�������L�����y�[�������}�X�^�s.���X�R�[�h<BR>
     *  = �i�����j�o�^���.���X�R�[�h<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�ڋq�R�[�h<BR>
     *  = �P-�Q�j�Ŏ擾�����ڋq�R�[�h<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�ڋq����<BR>
     *  = �i�����j�o�^���.�ڋq����<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j<BR>
     *  = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j<BR>
     *  = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�Ώۊ���From<BR>
     *  = �i�����j�o�^���.�Ώۊ���From<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�Ώۊ���To<BR>
     *  = �i�����j�o�^���.�Ώۊ���To<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�ڋq������<BR>
     *  = �i�����j�o�^���.������<BR>
     *     �萔�������L�����y�[�������}�X�^�s.���҃R�[�h<BR>
     *  = �i�����j�o�^���.���҃R�[�h<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�����J�݋敪<BR>
     *  = �i�����j�o�^���.�����J�݋敪<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�From<BR>
     *  = �i�����j�o�^���.�����J�ݓ�From<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To<BR>
     *  = �i�����j�o�^���.�����J�ݓ�To<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�o�^�^�C�v<BR>
     *  = �i�����j�o�^���.�o�^�^�C�v<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�����敪<BR>
     *  = "0"<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�X�V�҃R�[�h<BR>
     *  = �i�����j�X�V�҃R�[�h<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�쐬����<BR>
     *  = ���ݓ���<BR>
     *     �萔�������L�����y�[�������}�X�^�s.�X�V����<BR>
     *  = ���ݓ���<BR>
     * <BR>
     *       �i���ʌڋq�w��ύX�A�����J�ݏ����w��ύX�A���ꂼ��̓o�^���e�ɂ��Ă�DB�X�V�d�l���Q�Ɗ肢�܂��B�j<BR>
     * <BR>
     * <BR>
     *   �P-�R�j QueryProcessor#doInsertQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *       [doInsertQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *    �@@�@@ arg0�F�@@�P-�P�j�ō쐬�����s�I�u�W�F�N�g<BR>
     * <BR>
     *   �P-�S�j QueryProcessor#doInsertQuery()�̖߂�l���擾����B<BR>
     * <BR>
     * <BR>
     * �Q�j �萔�������L�����y�[�����i�}�X�^�̓o�^���s���B<BR>
     * <BR>
     *   �Q-�P�j �i�����j�o�^���.���i�R�[�h�̔z�񒷉񐔈ȉ����J��Ԃ��B<BR>
     * <BR>
     *     �Q-�P-�P�j �萔�������L�����y�[�����i�}�X�^�s�I�u�W�F�N�g���쐬���ݒ���s���B <BR>
     * <BR>
     *      �萔�������L�����y�[�����i�}�X�^�s.�萔�������L�����y�[������ID<BR>
     *  = �i�P-�R�j�̖߂�l�j�萔�������L�����y�[��<BR>
     *  �����}�X�^��L�[�I�u�W�F�N�g.�萔���L�����y�[������ID <BR>
     *      �萔�������L�����y�[�����i�}�X�^�s.���i�R�[�h<BR>
     *  = �i�����j�o�^���.���i�R�[�h[0�c]<BR>
     *      �萔�������L�����y�[�����i�}�X�^�s.�X�V�҃R�[�h<BR>
     *  = �i�����j�X�V�҃R�[�h<BR>
     *      �萔�������L�����y�[�����i�}�X�^�s.�쐬����<BR>
     *  = ���ݓ���<BR>
     *      �萔�������L�����y�[�����i�}�X�^�s.�X�V����<BR>
     *  = ���ݓ���<BR>
     * <BR>
     *     �Q-�P-�Q�j QueryProcessor#doInsertQuery()���\�b�h���R�[������B <BR>
     * <BR>
     *       [doInsertQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *    �@@�@@ arg0�F�@@�Q-�P-�P�j�ō쐬�����s�I�u�W�F�N�g<BR>
     * @@param l_registInfo - (�o�^���)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h<BR>
     * @@roseuid 45AF08C700A2
     */
    public void insertCampaignCondition(WEB3AccInfoCampaignInfo l_registInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "insertCampaignCondition(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_registInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �萔�������L�����y�[�������}�X�^�̓o�^���s���B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�P-�P�j �i�����j�o�^���.�o�^�^�C�v != 0 �̏ꍇ
        //  �P-�P-�P�j �g���A�J�E���g�}�l�[�W���𗘗p���Čڋq�s���擾����B<BR>
        //    [get�ڋq()�ɃZ�b�g����p�����[�^]
        //      arg0�F�@@�i�����j�o�^���.�،���ЃR�[�h
        //      arg1�F�@@�i�����j�o�^���.���X�R�[�h
        //      arg2�F�@@�i�����j�o�^���.�ڋq�R�[�h

        String l_strAccountCode = null;
        if (!WEB3AccInfoRegistTypeDef.ACCOPEN_CONDITION.equals(l_registInfo.registType))
        {
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            if (l_registInfo.accountCode != null)
            {
                l_mainAccount =
                    l_accMgr.getMainAccount(
                        l_registInfo.institutionCode,
                        l_registInfo.branchCode,
                        l_registInfo.accountCode.substring(0, 6));
            }
        //�P-�P-�Q�j �P�|�P-�P�j�̖߂�l����7���̌ڋq�R�[�h���擾����B
            l_strAccountCode = l_mainAccount.getAccountCode();
        }

        //�P-�Q�j �萔�������L�����y�[�������}�X�^�s�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B
        CommCampaignCondMstParams l_commCampaignCondMstParams
            = new CommCampaignCondMstParams();
        //�萔�������L�����y�[�������}�X�^�s.�萔�������L�����y�[������ID
        //= �萔�������L�����y�[�������}�X�^Dao.newPkValue()�̖߂�l
        try
        {
            l_commCampaignCondMstParams.setCampaignId(CommCampaignCondMstDao.newPkValue());
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //�萔�������L�����y�[�������}�X�^�s.�萔�������L�����y�[������
        //= �i�����j�o�^���.�萔�������L�����y�[������
        l_commCampaignCondMstParams.setCommCampaignName(l_registInfo.campaignName);
        //�萔�������L�����y�[�������}�X�^�s.�،���ЃR�[�h = �i�����j�o�^���.�،���ЃR�[�h
        l_commCampaignCondMstParams.setInstitutionCode(l_registInfo.institutionCode);
        //�萔�������L�����y�[�������}�X�^�s.���X�R�[�h = �i�����j�o�^���.���X�R�[�h
        l_commCampaignCondMstParams.setBranchCode(l_registInfo.branchCode);
        //�萔�������L�����y�[�������}�X�^�s.�ڋq�R�[�h = �P-�P-�Q�j�Ŏ擾�����ڋq�R�[�h
        l_commCampaignCondMstParams.setAccountCode(l_strAccountCode);
        //�萔�������L�����y�[�������}�X�^�s.�ڋq���� = �i�����j�o�^���.�ڋq����
        l_commCampaignCondMstParams.setFamilyName(l_registInfo.accountName);
        //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j
        l_commCampaignCondMstParams.setAccOpenPassMonth(l_registInfo.accopenPassPeriodMonth);
        //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j = �i�����j�o�^���.�����J�݌o�ߊ��ԁi���j
        l_commCampaignCondMstParams.setAccOpenPassDate(l_registInfo.accopenPassPeriodDay);
        //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���From = �i�����j�o�^���.�Ώۊ���From
        l_commCampaignCondMstParams.setAppliStartDate(l_registInfo.targetPeriodFrom);
        //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To = �i�����j�o�^���.�Ώۊ���To
        l_commCampaignCondMstParams.setAppliEndDate(l_registInfo.targetPeriodTo);
        //�萔�������L�����y�[�������}�X�^�s.�ڋq������ = �i�����j�o�^���.������
        l_commCampaignCondMstParams.setAccountChargeRatio(Double.parseDouble(l_registInfo.collectRate));
        //�萔�������L�����y�[�������}�X�^�s.���҃R�[�h = �i�����j�o�^���.���҃R�[�h
        l_commCampaignCondMstParams.setSonarTraderCode(l_registInfo.traderCode);
        //�萔�������L�����y�[�������}�X�^�s.�����J�݋敪 = �i�����j�o�^���.�����J�݋敪
        l_commCampaignCondMstParams.setAccOpenKindDiv(l_registInfo.accountOpenDiv);
        //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�From = �i�����j�o�^���.�����J�ݓ�From
        l_commCampaignCondMstParams.setAccOpenDateFrom(l_registInfo.accountOpenDateFrom);
        //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To = �i�����j�o�^���.�����J�ݓ�To
        l_commCampaignCondMstParams.setAccOpenDateTo(l_registInfo.accountOpenDateTo);
        //�萔�������L�����y�[�������}�X�^�s.�o�^�^�C�v = �i�����j�o�^���.�o�^�^�C�v
        l_commCampaignCondMstParams.setRegistType(l_registInfo.registType);
        //�萔�������L�����y�[�������}�X�^�s.�����敪 = "0"
        l_commCampaignCondMstParams.setStatus(WEB3StatusDef.NOT_DEAL);
        //�萔�������L�����y�[�������}�X�^�s.�X�V�҃R�[�h = �i�����j�X�V�҃R�[�h
        l_commCampaignCondMstParams.setLastUpdater(l_strUpdaterCode);
        //�萔�������L�����y�[�������}�X�^�s.�쐬���� = ���ݓ���
        l_commCampaignCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�萔�������L�����y�[�������}�X�^�s.�X�V���� = ���ݓ���
        l_commCampaignCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //�i���ʌڋq�w��ύX�A�����J�ݏ����w��ύX�A���ꂼ��̓o�^���e�ɂ��Ă�DB�X�V�d�l���Q�Ɗ肢�܂��B�j

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //�P-�R�j QueryProcessor#doInsertQuery()���\�b�h���R�[������B
            //[doInsertQuery()�ɃZ�b�g����p�����[�^]
            //arg0�F�@@�P-�P�j�ō쐬�����s�I�u�W�F�N�g
            //�P-�S�j QueryProcessor#doInsertQuery()�̖߂�l���擾����B
            Object l_objInsertQuery
                = l_queryProcessor.doInsertQuery(l_commCampaignCondMstParams);

            //�Q�j �萔�������L�����y�[�����i�}�X�^�̓o�^���s���B
            //�Q-�P�j �i�����j�o�^���.���i�R�[�h�̔z�񒷉񐔈ȉ����J��Ԃ��B
            for (int i = 0; i < l_registInfo.itemCode.length; i++)
            {
                //�Q-�P-�P�j  �萔�������L�����y�[�����i�}�X�^�s�I�u�W�F�N�g���쐬���ݒ���s���B
                CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
                //�萔�������L�����y�[�����i�}�X�^�s.�萔�������L�����y�[������ID
                //= �i�P-�R�j�̖߂�l�j�萔�������L�����y�[�������}�X�^��L�[�I�u�W�F�N�g.�萔���L�����y�[������ID
                l_commCampaignProductMstParams.setCampaignId(((CommCampaignCondMstPK)l_objInsertQuery).campaign_id);
                //�萔�������L�����y�[�����i�}�X�^�s.���i�R�[�h = �i�����j�o�^���.���i�R�[�h[0�c]
                l_commCampaignProductMstParams.setCommProductCode(l_registInfo.itemCode[i]);
                //�萔�������L�����y�[�����i�}�X�^�s.�X�V�҃R�[�h = �i�����j�X�V�҃R�[�h
                l_commCampaignProductMstParams.setLastUpdater(l_strUpdaterCode);
                //�萔�������L�����y�[�����i�}�X�^�s.�쐬���� = ���ݓ���
                l_commCampaignProductMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //�萔�������L�����y�[�����i�}�X�^�s.�X�V���� = ���ݓ���
                l_commCampaignProductMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //�Q-�P-�Q�j  QueryProcessor#doInsertQuery()���\�b�h���R�[������B
                //[doInsertQuery()�ɃZ�b�g����p�����[�^]
                //arg0�F�@@�Q-�P-�P�j�ō쐬�����s�I�u�W�F�N�g
                l_queryProcessor.doInsertQuery(l_commCampaignProductMstParams);
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (is�ύX���)<BR>
     * �ύX����ƕύX�O�̃f�[�^���r���A<BR>
     * <BR>
     * �@@ �ύX�s��񂪕ύX����Ă���ꍇ <BR>
     *      OR �ύX�\���ɕύX���Ȃ��ꍇ��FALSE��ԋp����B<BR>
     * �A �ύX�s���ɕύX�������ꍇ <BR>
     *      AND �ύX�\���ɕύX������ꍇ��TRUE��ԋp����B<BR>
     * <BR>
     * �P�j �萔�������L�����y�[�������}�X�^���ύX�O�f�[�^���擾����B<BR>
     *     this.get�L�����y�[������()���R�[������B<BR>
     * <BR>
     *     [get�L�����y�[������()�Ɏw�肷�����]<BR>
     *     �萔�������L�����y�[������ID�F �i�����j�X�V���.�萔�������L�����y�[������ID<BR>
     * <BR>
     * �Q�j �P�j �Ŏ擾�����ύX�O�f�[�^�ƁA�i�����j�ύX����̓��e���r����B<BR>
     * <BR>
     *   �Q-�P�j �ύX�s���̃`�F�b�N���s���B<BR>
     *           �ȉ����ڂ� �ύX�O�f�[�^ �� �ύX���� �ňꍀ�ڂł����ق�����ꍇ�́A<BR>
     * FALSE��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�萔�������L�����y�[������ID<BR>
     * �@@�@@�@@�@@�E�萔�������L�����y�[������<BR>
     * �@@�@@�@@�@@�E�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�E���X�R�[�h<BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@�E�ڋq����<BR>
     * �@@�@@�@@�@@�E���҃R�[�h<BR>
     * �@@�@@�@@�@@�E�����J�݋敪<BR>
     * �@@�@@�@@�@@�E�o�^�^�C�v<BR>
     * �@@�@@�@@�@@�E�폜�t���O<BR>
     * �@@�@@�@@�@@�E�����敪<BR>
     * �@@�@@�@@�@@�E���i�R�[�h[]  ���v�f�P�ʂŔ�r�i���s���j<BR>
     * <BR>
     *   �Q-�Q�j �ύX���̃`�F�b�N���s���B<BR>
     *           �ȉ����ڂ� �ύX�O�f�[�^ �� �ύX���� �őS���ڍ��ق��Ȃ��ꍇ�́A<BR>
     * FALSE��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����J�݌o�ߊ��ԁi���j<BR>
     * �@@�@@�@@�@@�E�����J�݌o�ߊ��ԁi���j<BR>
     * �@@�@@�@@�@@�E�Ώۊ���From<BR>
     * �@@�@@�@@�@@�E�Ώۊ���To<BR>
     * �@@�@@�@@�@@�E�ڋq������<BR>
     * �@@�@@�@@�@@�E�����J�ݓ�From<BR>
     * �@@�@@�@@�@@�E�����J�ݓ�To<BR>
     * <BR>
     * �R�j �Q�j�̃`�F�b�N�ł������FALSE�ɊY�����Ȃ������ꍇ�A<BR>
     * TRUE��ԋp����B<BR>
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 45AD82380367
     */
    public boolean isChangeInfo(WEB3AccInfoCampaignInfo l_changeAfterInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeInfo(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �萔�������L�����y�[�������}�X�^���ύX�O�f�[�^���擾����B
        //this.get�L�����y�[������()���R�[������B
        //[get�L�����y�[������()�Ɏw�肷�����]
        //�萔�������L�����y�[������ID�F �i�����j�X�V���.�萔�������L�����y�[������ID
        WEB3AccInfoCampaignInfo l_changeBeforeInfo
            = this.getCampaignCondition(l_changeAfterInfo.campaignId);

        String l_strAccountCode = l_changeBeforeInfo.accountCode;
        if (l_strAccountCode != null)
        {
            l_strAccountCode = l_changeBeforeInfo.accountCode.substring(0, 6);
        }
        //�Q�j �P�j �Ŏ擾�����ύX�O�f�[�^�ƁA�i�����j�ύX����̓��e���r����B
        //�Q-�P�j �ύX�s���̃`�F�b�N���s���B
        //�ȉ����ڂ� �ύX�O�f�[�^ �� �ύX���� �ňꍀ�ڂł����ق�����ꍇ�́AFALSE��ԋp����B
        //�E�萔�������L�����y�[������ID
        //�E�萔�������L�����y�[������
        //�E�،���ЃR�[�h
        //�E���X�R�[�h
        //�E�ڋq�R�[�h
        //�E�ڋq����
        //�E���҃R�[�h
        //�E�����J�݋敪
        //�E�o�^�^�C�v
        //�E�폜�t���O
        //�E�����敪
        //�E���i�R�[�h[]  ���v�f�P�ʂŔ�r�i���s���j
        if ((!this.isEquals(l_changeAfterInfo.campaignId, l_changeBeforeInfo.campaignId)) ||
            (!this.isEquals(l_changeAfterInfo.campaignName, l_changeBeforeInfo.campaignName)) ||
            (!this.isEquals(l_changeAfterInfo.institutionCode, l_changeBeforeInfo.institutionCode)) ||
            (!this.isEquals(l_changeAfterInfo.branchCode, l_changeBeforeInfo.branchCode)) ||
            (!this.isEquals(l_changeAfterInfo.accountCode, l_strAccountCode)) ||
            (!this.isEquals(l_changeAfterInfo.accountName, l_changeBeforeInfo.accountName)) ||
            (!this.isEquals(l_changeAfterInfo.traderCode, l_changeBeforeInfo.traderCode)) ||
            (!this.isEquals(l_changeAfterInfo.accountOpenDiv, l_changeBeforeInfo.accountOpenDiv)) ||
            (!this.isEquals(l_changeAfterInfo.registType, l_changeBeforeInfo.registType)) ||
            (!this.isEquals(l_changeAfterInfo.deleteFlag, l_changeBeforeInfo.deleteFlag)) ||
            (!this.isEquals(l_changeAfterInfo.transactionDiv, l_changeBeforeInfo.transactionDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        if ((l_changeAfterInfo.itemCode != null) && (l_changeBeforeInfo.itemCode == null) ||
            (l_changeAfterInfo.itemCode == null) && (l_changeBeforeInfo.itemCode != null) ||
            ((l_changeAfterInfo.itemCode != null && (l_changeBeforeInfo.itemCode != null) &&
            (l_changeAfterInfo.itemCode.length != l_changeBeforeInfo.itemCode.length))))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q-�Q�j �ύX���̃`�F�b�N���s���B
        //�ȉ����ڂ� �ύX�O�f�[�^ �� �ύX���� �őS���ڍ��ق��Ȃ��ꍇ�́AFALSE��ԋp����B
        //�E�����J�݌o�ߊ��ԁi���j
        //�E�����J�݌o�ߊ��ԁi���j
        //�E�Ώۊ���From
        //�E�Ώۊ���To
        //�E�ڋq������
        //�E�����J�ݓ�From
        //�E�����J�ݓ�To
        if (this.isEquals(l_changeAfterInfo.accopenPassPeriodMonth, l_changeBeforeInfo.accopenPassPeriodMonth) &&
            this.isEquals(l_changeAfterInfo.accopenPassPeriodDay, l_changeBeforeInfo.accopenPassPeriodDay) &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodFrom, l_changeBeforeInfo.targetPeriodFrom) == 0 &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodTo, l_changeBeforeInfo.targetPeriodTo) == 0 &&
            this.isEquals(l_changeAfterInfo.collectRate, l_changeBeforeInfo.collectRate) &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_changeBeforeInfo.accountOpenDateFrom) == 0 &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateTo, l_changeBeforeInfo.accountOpenDateTo) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�j �Q�j�̃`�F�b�N�ł������FALSE�ɊY�����Ȃ������ꍇ�ATRUE��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (update�L�����y�[������)<BR>
     * �萔�������L�����y�[�������}�X�^���R�[�h�̍X�V���s���B<BR>
     * <BR>
     * �P�j �X�V�Ώ����R�[�h�̍폜�t���O�`�F�b�N���s���B<BR>
     * <BR>
     *      [this.validate�폜�t���O()�Ɏw�肷�����]<BR>
     *        �萔�������L�����y�[������ID�F �i�����j�ύX����.�萔�������L�����y�[������ID<BR>
     *  <BR>
     * �Q�j Map�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B<BR>
     * <BR>
     *     �R�������F "acc_open_pass_month"  / �l�F �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j<BR>
     *     �R�������F "acc_open_pass_date"   / �l�F �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j<BR>
     *     �R�������F "appli_start_date"     / �l�F �i�����j�ύX����.�Ώۊ���From<BR>
     *     �R�������F "appli_end_date"       / �l�F �i�����j�ύX����.�Ώۊ���To<BR>
     *     �R�������F "account_charge_ratio" / �l�F �i�����j�ύX����.������<BR>
     *     �R�������F "acc_open_date_from "  / �l�F �i�����j�ύX����.�����J�ݓ�From<BR>
     *     �R�������F "lacc_open_date_to"        / �l�F �i�����j�ύX����.�����J�ݓ�To<BR>
     *     �R�������F "status"           / �l�F "0"<BR>
     *     �R�������F "last_updater"     / �l�F �i�����j�X�V�҃R�[�h<BR>
     *     �R�������F "last_updated_timestamp"   / �l�F ���ݓ���<BR>
     * <BR>
     * �R�j QueryProcessor#doUpdateQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *       [doUpdateQuery()�ɃZ�b�g����p�����[�^]  <BR>
     *    �@@�@@ arg0�F�@@�i�����j�萔�������L�����y�[������ID<BR>
     * ����ɂ����萔�������L�����y�[�������}�X�^��PrimaryKey�I�u�W�F�N�g<BR>
     *         arg1�F �Q�j�ō쐬����Map�I�u�W�F�N�g<BR>
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h<BR>
     * @@roseuid 45AD7DD500B3
     */
    public void updateCampaignCondition(WEB3AccInfoCampaignInfo l_changeAfterInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCampaignCondition(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �X�V�Ώ����R�[�h�̍폜�t���O�`�F�b�N���s���B
        //[this.validate�폜�t���O()�Ɏw�肷�����]
        //�萔�������L�����y�[������ID�F �i�����j�ύX����.�萔�������L�����y�[������ID
        this.validateDeleteFlag(l_changeAfterInfo.campaignId);

        //�Q�j Map�I�u�W�F�N�g���쐬���A�ȉ���ݒ肷��B
        //�R�������F "acc_open_pass_month" / �l�F �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j
        //�R�������F "acc_open_pass_date" / �l�F �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j
        //�R�������F "appli_start_date" / �l�F �i�����j�ύX����.�Ώۊ���From
        //�R�������F "appli_end_date" / �l�F �i�����j�ύX����.�Ώۊ���To
        //�R�������F "account_charge_ratio" / �l�F �i�����j�ύX����.������
        //�R�������F "acc_open_date_from " / �l�F �i�����j�ύX����.�����J�ݓ�From
        //�R�������F "acc_open_date_to" / �l�F �i�����j�ύX����.�����J�ݓ�To
        //�R�������F "status" / �l�F "0"
        //�R�������F "last_updater" / �l�F �i�����j�X�V�҃R�[�h
        //�R�������F "last_updated_timestamp" / �l�F ���ݓ���
        Map l_mapSearchCondition = new HashMap();
        l_mapSearchCondition.put("acc_open_pass_month", l_changeAfterInfo.accopenPassPeriodMonth);
        l_mapSearchCondition.put("acc_open_pass_date", l_changeAfterInfo.accopenPassPeriodDay);
        l_mapSearchCondition.put("appli_start_date", l_changeAfterInfo.targetPeriodFrom);
        l_mapSearchCondition.put("appli_end_date", l_changeAfterInfo.targetPeriodTo);
        l_mapSearchCondition.put("account_charge_ratio", l_changeAfterInfo.collectRate);
        l_mapSearchCondition.put("acc_open_date_from", l_changeAfterInfo.accountOpenDateFrom);
        l_mapSearchCondition.put("acc_open_date_to", l_changeAfterInfo.accountOpenDateTo);
        l_mapSearchCondition.put("status", WEB3StatusDef.NOT_DEAL);
        l_mapSearchCondition.put("last_updater", l_strUpdaterCode);
        l_mapSearchCondition.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //�R�j QueryProcessor#doUpdateQuery()���\�b�h���R�[������B
        //[doUpdateQuery()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@�i�����j�萔�������L�����y�[������ID����ɂ����萔�������L�����y�[�������}�X�^��PrimaryKey�I�u�W�F�N�g
        //arg1�F �Q�j�ō쐬����Map�I�u�W�F�N�g
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                new CommCampaignCondMstPK(Long.parseLong(l_changeAfterInfo.campaignId)),
                l_mapSearchCondition);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�Ώۊ���)<BR>
     * �C���Ώۂ̃L�����y�[�������݁A�L�����y�[�����Ԓ����ǂ����`�F�b�N���s���B<BR>
     * �C���Ώۂ̑Ώۊ��ԁA�܂��͏C����̑Ώۊ��Ԃ��ߋ��̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�ύX�O�̑Ώۊ���From�ƕύX�O�̑Ώۊ���To�̊ԂɌ��ݓ��t���܂܂�Ă���ꍇ<BR>
     *  �i�L�����y�[�������K�p���̏ꍇ�j�́A<BR>
     *   1�F�x���L���A�܂܂�Ă��Ȃ��ꍇ��0�F�x���Ȃ���ԋp����B<BR>
     * �A�ύX�O�̑Ώۊ���To�A�����J�ݓ�To�{�����J�݌o�ߊ���(��)�{�����J�݌o�ߊ��ԁi���j���ߋ��̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �B �����J�ݓ�From�y�ь����J�ݓ�To���ύX����Ă���ꍇ�A
     *   �ύX��̌����J�ݓ�From < ���ݓ��t �y�ѕύX��̌����J�ݓ�To < ���ݓ��t�̏ꍇ��O�𐶐�����B
     * �C �L�����y�[�����{���̏��őΏۊ���From�A�y�ь����J�ݓ�From���ύX����Ă���ꍇ�́A <BR>
     * ��O���X���[����B<BR>
     * �D�ύX��̑Ώۊ���To�A�����J�ݓ�To�`�F�b�N�ivalidate���͑Ώۊ���()���\�b�h�R�[���j<BR>
     * <BR>
     * <BR>
     * �P�j �萔�������L�����y�[�������}�X�^���ύX�O�f�[�^���擾����B<BR>
     *      �߂�l��"0"���Z�b�g����B
     *     this.get�L�����y�[������()���R�[������B<BR>
     * <BR>
     *     [get�L�����y�[������()�Ɏw�肷�����]<BR>
     *     �萔�������L�����y�[������ID�F �i�����j�ύX����.�萔�������L�����y�[������ID<BR>
     * <BR>
     * <BR>
     * �Q�j �ύX�\�������̎擾���s���B<BR>
     *   �Q-�P�j ���ݎ��� < �����c�Ɠ�17���̏ꍇ�A�ύX�\������ = ���X�c�Ɠ�<BR>
     * <BR>
     *   �Q-�Q�j ���ݎ��� >= �����c�Ɠ�17���̏ꍇ�A�ύX�\������ = �����X�c�Ɠ�<BR>
     * <BR>
     * �R�j �P�j�Ŏ擾�����A�萔�������L�����y�[�������}�X�^�s�ɉ����Ĉȉ��̏������s���B <BR>
     * <BR>
     *   �R-�P�j �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j == null &&  <BR>
     *           �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j == null �̏ꍇ�A <BR>
     * <BR>
     *     �R-�P-�P�j �Ώۊ���To != null �̎��A�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To < �ύX�\�������̏ꍇ�A <BR>
     *                �w�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���x��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02735<BR>
     * <BR>
     *     �R-�P-�Q�j �Ώۊ���From != null && �Ώۊ���To != null �̎��A <BR>
     *                 �萔�������L�����y�[�������}�X�^�s.�Ώۊ���From <= ���ݓ��t && <BR>
     *                 �萔�������L�����y�[�������}�X�^�s.�Ώۊ���To >= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B <BR>
     * <BR>
     *     �R-�P-�R�j �Ώۊ���From == null  && �Ώۊ���To != null �̎��A <BR>
     *                 �萔�������L�����y�[�������}�X�^�s.�Ώۊ���To >= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B <BR>
     * <BR>
     *     �R-�P-�S�j �Ώۊ���From != null && �Ώۊ���To == null �̎��A <BR>
     *                 �萔�������L�����y�[�������}�X�^�s.�Ώۊ���From <= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B <BR>
     * <BR>
     *     �R-�P-�T�j �Ώۊ���From == null && �Ώۊ���To == null �̏ꍇ�A�߂�l��"1"���Z�b�g����B <BR>
     * <BR>
     * <BR>
     *   �R-�Q�j �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j != null &&<BR>
     *           �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j != null �̏ꍇ�A<BR>
     * <BR>
     *     �R-�Q-�P�j �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j��<BR>
     *                 ���Z�������t < �ύX�\�������̏ꍇ�A�w�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02735<BR>
     * <BR>
     *     �R-�Q-�Q�j �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�From <= ���ݓ��t && <BR>
     *                 �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j��<BR>
     *                 ���Z�������t >= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �S�j �i�����j�X�V�����t���O ==  "1"�i�X�V�����j �̎��A�ȉ��̏������s���B<BR>
     *   �S-�P�j  �ύX����.�����J�ݓ�To != null && <BR>
     *            �ύX�O���.�����J�ݓ�To != �ύX����.�����J�ݓ�To && <BR>
     *            �ύX����.�����J�ݓ�To < ���ݓ��t�̏ꍇ�A�w�����J�ݓ�To���ߋ����t�ł��B�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02736<BR>
     * <BR>
     *   �S-�Q�j  �ύX�O���.�����J�ݓ�From != null && <BR>
     * 	          �ύX�O���.�����J�ݓ�From != �ύX����.�����J�ݓ�From && <BR>
     * 	          �ύX�O���.�����J�ݓ�From < ���ݓ��t�̏ꍇ�A <BR>
     * 	         �w�����J�ݓ�From���ߋ����t�̃f�[�^�͕ύX�ł��܂���B�x��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02736<BR>
     * <BR>
     *   �S-�R�j  �ύX����.�����J�ݓ�From != null &&<BR>
     *     �ύX�O���.�����J�ݓ�From != �ύX����.�����J�ݓ�From�̏ꍇ�A<BR>
     * 
     *     �S-�R-�P�j �ύX������J�ݓ�From < ���ݓ��t�̏ꍇ<BR>
     *     �u�����J�ݓ�From �͌��݂��ߋ����t�ɂ͐ݒ�ł��܂���v��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02742<BR>
     * <BR>
     *    �S-�S�j �߂�l == "1" �̏ꍇ�A�Ώۊ���From�A�y�ь����J�ݓ�From�̃`�F�b�N���s���B<BR>
     * <BR>
     *      �S-�S-�P�j �ύX�O�Ώۊ���From != �ύX��Ώۊ���From�̏ꍇ�A<BR>
     *    �u�L�����y�[�����{���̑Ώۊ���From �y�� �����J�ݓ�From�̕ύX�͂ł��܂���v<BR>
     *     ��O���X���[����B<BR>
     * <BR>
     *      �S-�S-�Q�j �ύX�O�����J�ݓ�From != �ύX������J�ݓ�From�̏ꍇ�A<BR>
     *    �u�L�����y�[�����{���̑Ώۊ���From �y�� �����J�ݓ�From�̕ύX�͂ł��܂���v<BR>
     *     ��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02734<BR>
     * <BR>
     *    �S-�T�j  ���͒l�̑Ώۊ��ԃ`�F�b�N���s���B<BR>
     *            [this.validate���͑Ώۊ��ԃ`�F�b�N()�Ɏw�肷�����] <BR>
     *              �ύX����F�i�����j�ύX���� <BR>
     *              �X�V�����t���O�F�i�����j�X�V�����t���O <BR>
     * <BR>
     * <BR>
     * �T�j �R�j�ɓ��Ă͂܂炸�A�S�j������I�������ꍇ�A�߂�l��ԋp����B <BR>
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * @@param updateFlag - �X�V�����t���O
     * @@return String
     * @@roseuid 45B4779E01E5
     */
    public String validateTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo, String updateFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTargetPeriod(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j �萔�������L�����y�[�������}�X�^���ύX�O�f�[�^���擾����B
        //this.get�L�����y�[������()���R�[������B
        //[get�L�����y�[������()�Ɏw�肷�����]
        //�萔�������L�����y�[������ID�F �i�����j�ύX����.�萔�������L�����y�[������ID
        WEB3AccInfoCampaignInfo l_campaignInfo
            = this.getCampaignCondition(l_changeAfterInfo.campaignId);

        //�Q�j �ύX�\�������̎擾���s���B
        //  �Q-�P�j ���ݎ��� < �����c�Ɠ�17���̏ꍇ�A�ύX�\������ = ���X�c�Ɠ�
        //  �Q-�Q�j ���ݎ��� >= �����c�Ɠ�17���̏ꍇ�A�ύX�\������ = �����X�c�Ɠ�

        Date l_judgeBaseDate = null;
        l_judgeBaseDate = this.getJudgeBaseDate(BIZDATE_T2);

        //�R�j �P�j�Ŏ擾�����A�萔�������L�����y�[�������}�X�^�s�ɉ����Ĉȉ��̏������s���B
        //�߂�l��"0"���Z�b�g����B
        String l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.NO_WARNING;
        Date l_currentTimestamp = GtlUtils.getSystemTimestamp();
        //�R-�P�j �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j == null &&
        //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j == null �̏ꍇ�A
        if ((l_campaignInfo.accopenPassPeriodMonth == null)
            && (l_campaignInfo.accopenPassPeriodDay == null))
        {
            //�R-�P-�P�j �Ώۊ���To != null �̎��A�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To < �ύX�\�������̏ꍇ�A
            //�w�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���x��O���X���[����B
            if ((l_campaignInfo.targetPeriodTo != null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodTo, l_judgeBaseDate) < 0))
            {
                log.debug("�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02735,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ߋ����ԃf�[�^�ύX�E�폜�s��");
            }

            //�R-�P-�Q�j �Ώۊ���From != null && �Ώۊ���To != null �̎��A
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���From <= ���ݓ��t &&
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To >= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B
            if ((l_campaignInfo.targetPeriodFrom != null) && (l_campaignInfo.targetPeriodTo != null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodFrom, l_currentTimestamp) <= 0)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodTo, l_currentTimestamp) >= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }

            //�R-�P-�R�j �Ώۊ���From == null  && �Ώۊ���To != null �̎��A
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���To >= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B
            if ((l_campaignInfo.targetPeriodFrom == null) && (l_campaignInfo.targetPeriodTo != null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodTo, l_currentTimestamp) >= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }

            //�R-�P-�S�j �Ώۊ���From != null && �Ώۊ���To == null �̎��A
            //�萔�������L�����y�[�������}�X�^�s.�Ώۊ���From <= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B
            if ((l_campaignInfo.targetPeriodFrom != null) && (l_campaignInfo.targetPeriodTo == null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodFrom, l_currentTimestamp) <= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }

            //�R-�P-�T�j �Ώۊ���From == null && �Ώۊ���To == null �̏ꍇ�A�߂�l��"1"���Z�b�g����B
            if ((l_campaignInfo.targetPeriodFrom == null) && (l_campaignInfo.targetPeriodTo == null))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }
        }

        //  �R-�Q�j �萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j != null &&
        //�萔�������L�����y�[�������}�X�^�s.�����J�݌o�ߊ��ԁi���j != null �̏ꍇ�A
        if ((l_campaignInfo.accopenPassPeriodMonth != null) && (l_campaignInfo.accopenPassPeriodDay != null))
        {
            //    �R-�Q-�P�j �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j��
            //���Z�������t < �ύX�\�������̏ꍇ�A�w�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���x��O���X���[����B
            if (WEB3DateUtility.compareToDay(
                WEB3DateUtility.addDay(
                WEB3DateUtility.addMonth(
                l_campaignInfo.accountOpenDateTo,
                Integer.parseInt(l_campaignInfo.accopenPassPeriodMonth)),
                Integer.parseInt(l_campaignInfo.accopenPassPeriodDay)),
                l_judgeBaseDate) < 0)
            {
                log.debug("�ߋ����Ԃ̃f�[�^�͕ύX�E�폜�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02735,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ߋ����ԃf�[�^�ύX�E�폜�s��");
            }

            //    �R-�Q-�Q�j �萔�������L�����y�[�������}�X�^�s.�����J�ݓ�From <= ���ݓ��t &&
            //�萔�������L�����y�[�������}�X�^�s.�����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j��
            //���Z�������t >= ���ݓ��t�̏ꍇ�A�߂�l��"1"���Z�b�g����B
            if ((WEB3DateUtility.compareToDay(l_campaignInfo.accountOpenDateFrom, l_currentTimestamp) <= 0)
                && (WEB3DateUtility.compareToDay(
                WEB3DateUtility.addDay(
                WEB3DateUtility.addMonth(
                l_campaignInfo.accountOpenDateTo,
                Integer.parseInt(l_campaignInfo.accopenPassPeriodMonth)),
                Integer.parseInt(l_campaignInfo.accopenPassPeriodDay)),
                l_currentTimestamp) >= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }
        }

        //�S�j �i�����j�X�V�����t���O ==  "1"�i�X�V�����j �̎��A�ȉ��̏������s���B
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(updateFlag))
        {
	        //�S-�P�j  �ύX����.�����J�ݓ�To != null &&
	        //�ύX�O���.�����J�ݓ�To != �ύX����.�����J�ݓ�To &&
	        //�ύX����.�����J�ݓ�To < ���ݓ��t�̏ꍇ�A�w�����J�ݓ�To���ߋ����t�ł��B�x��O���X���[����B
	        if (l_changeAfterInfo.accountOpenDateTo != null
	            && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateTo, l_campaignInfo.accountOpenDateTo) != 0
	            && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateTo, l_currentTimestamp) < 0)
	        {
	            log.debug("�����J�ݓ�To�ݒ�l�G���[");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02736,
	                this.getClass().getName() + STR_METHOD_NAME,
	                "�����J�ݓ�To���ߋ����t�ł��B");
	        }
 
	        //�S-�Q�j  �ύX�O���.�����J�ݓ�From != null &&
	        //�ύX�O���.�����J�ݓ�From != �ύX����.�����J�ݓ�From &&
	        //�ύX�O���.�����J�ݓ�From < ���ݓ��t�̏ꍇ�A
	        //�w�����J�ݓ�From���ߋ����t�̃f�[�^�͕ύX�ł��܂���B�x��O���X���[����B
	        if (l_campaignInfo.accountOpenDateFrom != null
		        && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_campaignInfo.accountOpenDateFrom) != 0
		        && WEB3DateUtility.compareToDay(l_campaignInfo.accountOpenDateFrom, l_currentTimestamp) < 0)
	        {
	        	log.debug("�����J�ݓ�From�ߋ����G���[");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02744,
	                this.getClass().getName() + STR_METHOD_NAME,
	                "�����J�ݓ�From���ߋ����t�̃f�[�^�͕ύX�ł��܂���B");
	        }
	        
	        //�S-�R�j  �ύX����.�����J�ݓ�From != null &&
	        //�ύX�O���.�����J�ݓ�From != �ύX����.�����J�ݓ�From�̏ꍇ
	        if (l_changeAfterInfo.accountOpenDateFrom != null
			    && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_campaignInfo.accountOpenDateFrom) != 0)
	        {
	        	//�S-�R-�P�j �ύX������J�ݓ�From < ���ݓ��t�̏ꍇ
	        	//�u�����J�ݓ�From �͌��݂��ߋ����t�ɂ͐ݒ�ł��܂���v��O���X���[����B
	        	if (WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_currentTimestamp) < 0)
	        	{
		            log.debug("�����J�ݓ�From�ݒ�l�G���[");
		            log.exiting(STR_METHOD_NAME);
		            throw new WEB3BusinessLayerException(
		                WEB3ErrorCatalog.BUSINESS_ERROR_02742,
		                this.getClass().getName() + STR_METHOD_NAME,
		                "�����J�ݓ�From �͌��݂��ߋ����t�ɂ͐ݒ�ł��܂���B");	        		
	        	}
	        }
	        
			//�S-�S�j�߂�l == "1" �̏ꍇ�A�Ώۊ���From�A�y�ь����J�ݓ�From�̃`�F�b�N���s���B
			//  �S-�S-�P�j �ύX�O�Ώۊ���From != �ύX��Ώۊ���From�̏ꍇ�A
			//      �u�L�����y�[�����{���̑Ώۊ���From �y�� �����J�ݓ�From�̕ύX�͂ł��܂���v��O���X���[����B
			//  �S-�S-�Q�j �ύX�O�����J�ݓ�From != �ύX������J�ݓ�From�̏ꍇ�A
			//      �u�L�����y�[�����{���̑Ώۊ���From �y�� �����J�ݓ�From�̕ύX�͂ł��܂���v��O���X���[����B
			if (WEB3AccInfoTargetPeriodCheckDef.WARNING.equals(l_returnFlag))
			{
				if (WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodFrom, l_campaignInfo.targetPeriodFrom) != 0
					|| WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_campaignInfo.accountOpenDateFrom) != 0)
				{
					log.debug("�L�����y�[�����{���̑Ώۊ���From �y�� �����J�ݓ�From�̕ύX�͂ł��܂���B");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_02734,
						this.getClass().getName() + STR_METHOD_NAME,
						"�L�����y�[�����{���J�n���t�ύX�s�B");
				}
			}
	
	        //�S-�T�j  ���͒l�̑Ώۊ��ԃ`�F�b�N���s���B
	        //   [this.validate���͑Ώۊ��ԃ`�F�b�N()�Ɏw�肷�����]
	        //      �ύX����F�i�����j�ύX����
	        //      �X�V�����t���O�F�i�����j�X�V�����t���O
	        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(updateFlag))
	        {
	            this.validateInputTargetPeriod(l_changeAfterInfo, updateFlag);
	        }
		}

        //�T�j �R�j�ɓ��Ă͂܂炸�A�S�j������I�������ꍇ�A�߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_returnFlag;
    }

    /**
     * (validate���͑Ώۊ��ԃ`�F�b�N)<BR>
     * ���͒l�̑Ώۊ��Ԃ��K�p�J�n�����ߋ��̏ꍇ�A��O���X���[����B<BR>
     * ���͒l�̊��Ԏw��To <= �����J�ݓ�To�̏ꍇ�A��O���X���[����B
     * �P�j �K�p�J�n���̎擾���s���B<BR>
     *   �P-�P�j ���ݎ��� < �����c�Ɠ�17�F00�F00�̏ꍇ�A�K�p�J�n�� = ���X�c�Ɠ�<BR>
     *   �P-�Q�j ���ݎ��� >= �����c�Ɠ�17�F00�F00�̏ꍇ�A�K�p�J�n�� = �����X�c�Ɠ�<BR>
     * <BR>
     * �Q�j �i�����j�ύX���� �ɉ����Ĉȉ��̏������s���B<BR>
     * <BR>
     *   �Q-�P�j �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j != null && <BR>
     *           �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j != null �̏ꍇ<BR>
     * <BR>
     *     �Q-�P-�P�j �i�����j�X�V�����t���O == "1"�i�X�V�����j�̏ꍇ�A<BR>
     *                 �i�����j�ύX����.�����J�ݓ�To�Ɂi�����j�ύX����.�����J�݌o�ߊ���(��)��<BR>
     *                 �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j�����Z�������t < �K�p�J�n�� �̎��A<BR>
     *                 �w�����J�ݓ�To�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n�����ߋ��̓��t�ł��B�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02737<BR>
     *     �Q-�P-�Q�j �i�����j�X�V�����t���O == "0"�i�o�^�����j�̏ꍇ�A<BR>
     *        �Q-�P-�Q-�P�j         �i�����j�ύX����.�����J�ݓ�To < �K�p�J�n�� �̎��A<BR>
     *                 �w�����J�ݓ�To���K�p�J�n�����ߋ��̓��t�ł��B�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02738<BR>
     *        �Q-�P-�Q-�Q�j �i�����j�ύX����. �����J�ݓ�From != null �̏ꍇ�A<BR>
     *          �i�����j�ύX����.�����J�ݓ�From�Ɂi�����j�ύX����.�����J�݌o�ߊ���(��)��<BR>
     *          �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j�����Z�������t < �K�p�J�n�� �̎��A<BR>
     *          �w�����J�ݓ�From�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n���ȑO�̓��t�ł��B�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02739<BR>
     * <BR>
     *   �Q-�Q�j �i�����j�ύX����.�Ώۊ���To != null �̏ꍇ<BR>
     * <BR>
     *     �Q-�Q-�P�j  �i�����j�ύX����.�Ώۊ���To < �K�p�J�n���̏ꍇ�A
     *                �w���Ԏw��To���K�p�J�n�����ߋ��̓��t�ł��B�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02740<BR>     * <BR>
     *     �Q-�Q-�Q�j �i�����j�ύX����. �����J�ݓ�To != null �̎��A<BR>
     *          �i�����j�ύX����.�Ώۊ���To <= �i�����j�ύX����.�����J�ݓ�To �̏ꍇ�A<BR>
     *          �w���Ԏw��To�������J�ݓ�To�ȑO�̓��t�ł��B�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02741<BR>
     *
     * @@param l_changeAfterInfo - (�ύX����)<BR>
     * �萔�������L�����y�[���������I�u�W�F�N�g<BR>
     * @@param updateFlag - �X�V�����t���O
     * @@throws WEB3BaseException
     */
    public void validateInputTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo, String updateFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputTargetPeriod(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j �K�p�J�n���̎擾���s���B
        //  �P-�P�j ���ݎ��� < �����c�Ɠ�17�F00�F00�̏ꍇ�A�K�p�J�n�� = ���X�c�Ɠ�
        //          ���ݎ��� >= �����c�Ɠ�17�F00�F00�̏ꍇ�A�K�p�J�n�� = �����X�c�Ɠ�
        Date l_judgeBaseDate = this.getJudgeBaseDate(BIZDATE_T2);

        // �Q�j �i�����j�ύX���� �ɉ����Ĉȉ��̏������s���B
        //  �Q-�P�j �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j != null &&
        //     �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j != null �̏ꍇ

        if (l_changeAfterInfo.accopenPassPeriodMonth != null
             && l_changeAfterInfo.accopenPassPeriodDay != null)
        {
            //�Q-�P-�P�j�i�����j�X�V�����t���O == "1"�i�X�V�����j�̏ꍇ
            //�i�����j�ύX����.�����J�ݓ�To�Ɂi�����j�ύX����.�����J�݌o�ߊ���(��)��
            //�i�����j�ύX����.�����J�݌o�ߊ��ԁi���j�����Z�������t < �K�p�J�n�� �̎��A
            //�w�����J�ݓ�To�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n�����ߋ��̓��t�ł��B�x��O���X���[����B
            if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(updateFlag))
            {
                if(WEB3DateUtility.compareToDay(WEB3DateUtility.addDay(
                    WEB3DateUtility.addMonth(l_changeAfterInfo.accountOpenDateTo,
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodMonth)),
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodDay)),
                    l_judgeBaseDate) < 0)
                {
                    log.debug("�����J�ݓ�To�ݒ�l�G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02737,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�����J�ݓ�To�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n�����ߋ��̓��t�ł��B");
                }
            }
            //�Q-�P-�Q�j�i�����j�X�V�����t���O == "0"�i�o�^�����j�̏ꍇ�A
            if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(updateFlag))
            {
                //�Q-�P-�Q-�P�j�i�����j�ύX����.�����J�ݓ�To < �K�p�J�n�� �̎��A
                //�w�����J�ݓ�To���K�p�J�n�����ߋ��̓��t�ł��B�x��O���X���[����B            	
                if(WEB3DateUtility.compareToDay(
                     l_changeAfterInfo.accountOpenDateTo,l_judgeBaseDate) < 0)
                {
                    log.debug("�����J�ݓ�To�ݒ�l�G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02738,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�����J�ݓ�To���K�p�J�n�����ߋ��̓��t�ł��B");
                }
                //�Q-�P-�Q-�Q�j �i�����j�ύX����. �����J�ݓ�From != null �̏ꍇ�A
                //�i�����j�ύX����.�����J�ݓ�From�Ɂi�����j�ύX����.�����J�݌o�ߊ���(��)��
                //�i�����j�ύX����.�����J�݌o�ߊ��ԁi���j�����Z�������t < �K�p�J�n�� �̎��A
                //�w�����J�ݓ�From�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n���ȑO�̓��t�ł��B�x��O���X���[����B
                if(WEB3DateUtility.compareToDay(WEB3DateUtility.addDay(
                    WEB3DateUtility.addMonth(l_changeAfterInfo.accountOpenDateFrom,
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodMonth)),
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodDay)),
                    l_judgeBaseDate) < 0)
                {
                    log.debug("�����J�ݓ�From�ݒ�l�G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02739,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�����J�ݓ�From�ƌ����J�݌o�ߊ��Ԃ𑫂������t���K�p�J�n���ȑO�̓��t�ł��B");
                }
            }
        }
        //�Q-�Q�j �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j == null &&
        //     �i�����j�ύX����.�����J�݌o�ߊ��ԁi���j == null �̏ꍇ
        //     �i�����j�ύX����.�Ώۊ���To != null �̏ꍇ
        else if (l_changeAfterInfo.accopenPassPeriodMonth == null
            && l_changeAfterInfo.accopenPassPeriodDay == null
            && l_changeAfterInfo.targetPeriodTo != null)
        {
            //�Q-�Q-�P�j�i�����j�ύX����.�Ώۊ���To < �K�p�J�n���̏ꍇ�A�w���Ԏw��To���K�p�J�n�����ߋ��̓��t�ł��B�x��O���X���[����B
            if (WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodTo, l_judgeBaseDate) < 0)
            {
                log.debug("���Ԏw��To�ݒ�l�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02740,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "���Ԏw��To���K�p�J�n�����ߋ��̓��t�ł��B");
            }
            //�Q-�Q-�Q�j �i�����j�ύX����. �����J�ݓ�To != null �̎��A
            //�i�����j�ύX����.�Ώۊ���To <= �i�����j�ύX����.�����J�ݓ�To �̏ꍇ�A
            //�w���Ԏw��To�������J�ݓ�To�ȑO�̓��t�ł��B�x��O���X���[����B
            if (WEB3DateUtility.compareToDay(
                 l_changeAfterInfo.targetPeriodTo, l_changeAfterInfo.accountOpenDateTo) <= 0)
            {
                log.debug("���Ԏw��To�ݒ�l�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02741,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "���Ԏw��To�������J�ݓ�To�ȑO�̓��t�ł��B");
            }
        }
    }

    /**
     * (validate�폜�t���O)<BR>
     * �i�����j�萔�������L�����y�[������ID �� �폜�t���O=�P�i�폜�ς݁j�������Ƃ���<BR>
     * �萔���L�����y�[�������}�X�^��背�R�[�h�������擾����B<BR>
     * �擾���ꂽ���� > 0 �̏ꍇ�A�w�L�����y�[���͊��ɍ폜����Ă��܂��x��O���X���[����B<BR>
     * <BR>
     * �P�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     *     �P-�P�j �L�����y�[�����������R���X�g���N�^���g�p���A�L�����y�[�����������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     *     �P-�Q�j ���������̐ݒ���s���B<BR>
     *   <BR>
     *       [set�폜�t���O()�Ɏw�肷�����]<BR>
     *         �폜�t���O�F "1"<BR>
     *       [set�萔�������L�����y�[������ID()�Ɏw�肷�����]<BR>
     *         �萔�������L�����y�[������ID�F �i�����j�萔�������L�����y�[������ID<BR>
     * <BR>
     * �Q�j �萔�������L�����y�[�������}�X�^��背�R�[�h�������擾����B<BR>
     * <BR>
     *   �Q-�P�j ����������������쐬����B<BR>
     * <BR>
     *      [this.create��������������()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     *   �Q-�Q�j �����f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     *      [this.create�����f�[�^�R���e�i()�Ɏw�肷�����]<BR>
     *        �L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g<BR>
     * <BR>
     *   �Q-�R�j QueryProcessor#doGetCountQuery)���\�b�h���R�[������B<BR>
     * <BR>
     *      [doGetCountQuery()�Ɏw�肷�����]  <BR>
     *    �@@  arg0�F �萔�������L�����y�[�������}�X�^RowType<BR>
     *   �@@�@@ arg1�F�@@this.create��������������() �̖߂�l<BR>
     * �@@  �@@ arg2�F�@@this.create�����f�[�^�R���e�i()�̖߂�l <BR>
     * <BR>
     * <BR>
     * �R�j �Q�j�̖߂�l > 0 �̏ꍇ�A�w�L�����y�[���͊��ɍ폜����Ă��܂��x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00989<BR>
     * @@param l_strCampaignId - �萔�������L�����y�[������ID<BR>
     * @@roseuid 45B99D7D0245
     */
    protected void validateDeleteFlag(String l_strCampaignId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDeleteFlag(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �L�����y�[�����������I�u�W�F�N�g�𐶐�����B
        //�P-�P�j �L�����y�[�����������R���X�g���N�^���g�p���A�L�����y�[�����������I�u�W�F�N�g�𐶐�����B
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();

        //�P-�Q�j ���������̐ݒ���s���B
        //[set�폜�t���O()�Ɏw�肷�����]
        //�폜�t���O�F "1"
        //[set�萔�������L�����y�[������ID()�Ɏw�肷�����]
        //�萔�������L�����y�[������ID�F �i�����j�萔�������L�����y�[������ID
        l_campaignSearchCondition.setDeleteFlag(WEB3PvInfoDeleteFlagDef.DELETE_YES);
        l_campaignSearchCondition.setCommissionCampaignConditionId(l_strCampaignId);

        //�Q�j �萔�������L�����y�[�������}�X�^��背�R�[�h�������擾����B
        //�Q-�P�j ����������������쐬����B
        //[this.create��������������()�Ɏw�肷�����]
        //�L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        String l_strSearchCondition
            = this.createSearchCondition(l_campaignSearchCondition);

        //�Q-�Q�j �����f�[�^�R���e�i���쐬����B
        //[this.create�����f�[�^�R���e�i()�Ɏw�肷�����]
        //�L�����y�[���������ځF�P�j �ō쐬�����L�����y�[�����������I�u�W�F�N�g
        Object[] l_strSearchContainers
            = this.createSearchContainers(l_campaignSearchCondition);

        //�Q-�R�j QueryProcessor#doGetCountQuery)���\�b�h���R�[������B
        //[doGetCountQuery()�Ɏw�肷�����]
        //arg0�F �萔�������L�����y�[�������}�X�^RowType
        //arg1�F�@@this.create��������������() �̖߂�l
        //arg2�F�@@this.create�����f�[�^�R���e�i()�̖߂�l
        int l_intResultCnt;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_intResultCnt = l_queryProcessor.doGetCountQuery(
                CommCampaignCondMstRow.TYPE,
                l_strSearchCondition,
                l_strSearchContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //�R�j �Q�j�̖߂�l > 0 �̏ꍇ�A�w�L�����y�[���͊��ɍ폜����Ă��܂��x��O���X���[����B
        if (l_intResultCnt > 0)
        {
            log.debug("�L�����y�[���͊��ɍ폜����Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02727,
                this.getClass().getName() + STR_METHOD_NAME,
                "�L�����y�[���͊��ɍ폜����Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45C08B5303A9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        return null;
    }

    /**
     * ���t�^�f�[�^��"YYYYMMDD"�t�H�[�}�b�g�ŕ�����Ɍ^�ϊ�����B
     * @@param l_dat ���t�^�f�[�^
     * @@return �ϊ���̕������Ԃ��B
     */
    public String formatDate(Date l_dat)
    {
        return WEB3DateUtility.formatDate(l_dat, YYYYMMDD_DATE_FORMAT);
    }

    /**
     * @@param l_strFirst
     * @@param l_strSecond
     * @@return boolean
     */
    private boolean isEquals(String l_strFirst, String l_strSecond)
    {
        if (l_strFirst == null)
        {
            if (l_strSecond == null)
            {
                return true;
            }
            return false;
        }
        else if (l_strSecond == null)
        {
            return false;
        }
        return l_strFirst.equals(l_strSecond);
    }

    /**
     * �K�p�J�n���擾
     * ���ݓ����𓖓��c�Ɠ�17�F00�Ɣ�r���A�ȑO�ł���Έ�����Ԃ��A
     * �ȍ~�ł���΁A�����̗��c�Ɠ��i�������c�Ɠ��̏ꍇ�A���X�c�Ɠ��j��Ԃ��B
     * @@param l_nextBizDate - �c�Ɠ�����17:00�ȑO�̏ꍇ�̉c�Ɗ��
     * @@return ����p�c�Ɠ�
     */
    private Date getJudgeBaseDate(int l_nextBizDate) throws WEB3BaseException
    {
        // ���ݓ����擾
        Timestamp l_currentTimestamp = GtlUtils.getSystemTimestamp();
        Calendar l_currentCalendar =Calendar.getInstance();
        l_currentCalendar.setTime(l_currentTimestamp);
        // �����c�Ɠ��擾�i���ݓ�������c�Ɠ��̏ꍇ���̉c�Ɠ����擾����B�j
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(WEB3GentradeUtils.getBizDate(l_currentTimestamp));

        // �����c�Ɠ�17:00�쐬
        l_bizDateCalendar.clear(Calendar.HOUR_OF_DAY);
        l_bizDateCalendar.clear(Calendar.HOUR);
        l_bizDateCalendar.clear(Calendar.MINUTE);
        l_bizDateCalendar.clear(Calendar.SECOND);
        l_bizDateCalendar.clear(Calendar.MILLISECOND);
        l_bizDateCalendar.add(Calendar.HOUR_OF_DAY, 17);
        l_bizDateCalendar.add(Calendar.MINUTE, 0);
        l_bizDateCalendar.add(Calendar.SECOND, 0);
        l_bizDateCalendar.add(Calendar.MILLISECOND, 0);
        // ���ݎ��� < �����c�Ɠ�17�F00�F00�̏ꍇ�A�K�p�J�n�� = l_nextBizDate
        //���ݎ��� >= �����c�Ɠ�17�F00�F00�̏ꍇ�A�K�p�J�n�� = l_nextBizDate + 1
        Date l_judgeDate = null;
        if (l_currentCalendar.before(l_bizDateCalendar))
        {
            l_judgeDate = WEB3GentradeUtils.getBizDate(l_currentTimestamp, l_nextBizDate);
        }
        else
        {
            l_judgeDate = WEB3GentradeUtils.getBizDate(l_currentTimestamp, ++l_nextBizDate);
        }
        return l_judgeDate;
    }
}
@
