head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountCodeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݌ڋq�R�[�h�̔ԃT�[�r�XImpl(WEB3AccOpenAccountCodeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 ���G�� (���u) �V�K�쐬
                   2006/10/18 ���G�� (���u) �i�c�a�X�V�d�l�j��008�C��
                   2006/12/25 ��� (SCS) �i���f���j119�C��
                   2007/01/12 ���� (SCS) �i���f���j120�C��
                   2007/01/26 ���� (SCS) �i���f���j121�C��
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import webbroker3.gentrade.WEB3GentradeCheckDigitalUtility;
import webbroker3.accountopen.data.AccOpenAccountCodeDao;
import webbroker3.accountopen.data.AccOpenAccountCodeParams;
import webbroker3.accountopen.data.AccOpenAccountCodeRow;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.service.delegate.WEB3AccOpenAccountCodeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�݌ڋq�R�[�h�̔ԃT�[�r�XImpl)<BR>
 * �����J�݌ڋq�R�[�h�̔ԃT�[�r�X�����N���X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AccOpenAccountCodeServiceImpl implements WEB3AccOpenAccountCodeService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAccountCodeServiceImpl.class);


    /**
     * (get�V�K�ڋq�R�[�h)<BR>
     * �����J�݌����q�̌ڋq�R�[�h�������̔Ԃ���B<BR>
     * <BR>
     * �P�j�ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �P�|�P�j�@@�����J�݌ڋq�R�[�h�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����J�݌ڋq�R�[�h.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�����J�݌ڋq�R�[�h.���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�����J�݌ڋq�R�[�h.�ڋq�敪 = ����.�ڋq�敪<BR>
     * <BR>
     * �@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�u�����̔Ԃ̃f�[�^���擾�ł��܂���ł����B�v<BR>
     * <BR>
     * �P�|�Q�j�@@�ڋq�R�[�h���ݒl���擾����B<BR>
     * <BR>
     * �@@�ڋq�R�[�h���ݒl = �����J�݌ڋq�R�[�h.�ڋq�R�[�h���ݒl<BR>
     * <BR>
     * �P�|�R�j�@@�ڋq�R�[�h�ő�l���擾����B<BR>
     * <BR>
     * �@@�ڋq�R�[�h�ő�l = �����J�݌ڋq�R�[�h.�ڋq�R�[�h�ő�l<BR>
     * <BR>
     * �P�|�S�j�@@�V�K�ڋq�R�[�h���̔Ԃ���B<BR>
     * <BR>
     * �@@�V�K�ڋq�R�[�h = �ڋq�R�[�h���ݒl + 1<BR>
     * <BR>
     * �P�|�T�j�@@�V�K�ڋq�R�[�h���ő�l�𒴂��Ă��Ȃ����`�F�b�N����B<BR>
     * �@@�V�K�ڋq�R�[�h > �����J�݌ڋq�R�[�h.�ڋq�R�[�h�ő�l�@@�̏ꍇ�A�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�u�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B<BR>
     * <BR>
     * �Q�j�����J�݌����q�f�[�^���擾����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����J�݌����q���ȉ��̏����Ō�������<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����J�݌����q.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�����J�݌����q.���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�����J�݌����q.�����R�[�h => �V�K�ڋq�R�[�h<BR>
     * �@@�@@�����J�݌����q.�����R�[�h <= �����J�݌ڋq�R�[�h.�ڋq�R�[�h�ő�l<BR>
     * <BR>
     * �@@�@@[���בւ�]<BR>
     * �@@�@@�����J�݌����q�D�����R�[�h�̏���<BR>
     * <BR>
     * �@@�@@�擾���ʂ��A�����J�݌����q���X�g�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�����J�݌����q�f�[�^�������擾����B<BR>
     * <BR>
     * �R�j�ڋq�}�X�^�f�[�^���擾����<BR>
     * <BR>
     * �R�|�P�j�@@�V�K�ڋq�R�[�h�̃`�F�b�N�f�W�b�g���擾��(*1)�A7���̐V�K�ڋq�R�[�h��ݒ肷��B<BR>
     * <BR>
     * �R�|�Q�j�@@�ڋq�R�[�h�ő�l�̃`�F�b�N�f�W�b�g���擾��(*1)�A7���̌ڋq�R�[�h�ő�l��ݒ肷��B<BR>
     * <BR>
     * �R�|�R�j�@@�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�ڋq�}�X�^.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�ڋq�}�X�^.���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�@@�ڋq�}�X�^.�����R�[�h => 7���̐V�K�ڋq�R�[�h<BR>
     * �@@�@@�@@�ڋq�}�X�^.�����R�[�h <= 7���̌ڋq�R�[�h�ő�l<BR>
     * <BR>
     * �@@�@@�@@[���בւ�]<BR>
     * �@@�@@�@@�ڋq�}�X�^�D�����R�[�h�̏���<BR>
     * <BR>
     * �@@�@@�擾���ʂ��A�ڋq�}�X�^���X�g�ɃZ�b�g����B<BR>
     * <BR>
     * �R�|�S�j�@@�ڋq�}�X�^�f�[�^�������擾����<BR>
     * <BR>
     * �S�j�V�K�ڋq�R�[�h����������<BR>
     * <BR>
     * �S�|�P�j�@@�V�K�ڋq�R�[�h���ő�l�𒴂��Ă��Ȃ����`�F�b�N����B<BR>
     * �|�V�K�ڋq�R�[�h > �ڋq�R�[�h�ő�l�@@�̏ꍇ�A�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�u�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B<BR>
     * <BR>
     * �S�|�Q�j�@@�V�K�ڋq�R�[�h�������J�݌����q�f�[�^�ɑ��݂��邩���`�F�b�N����B<BR>
     * <BR>
     * �S�|�Q�|�P�j�@@�����J�݌����q�f�[�^������0���̏ꍇ<BR>
     * <BR>
     * �@@�@@�����J�݌����q�f�[�^ = "0"<BR>
     * <BR>
     * �S�|�Q�|�Q�j�@@�����J�݌����q�f�[�^���������݂���ꍇ<BR>
     * <BR>
     * �@@�@@�����J�݌����q�f�[�^ = �����J�݌����q���X�g m �Ԗ�.get�ڋq�R�[�h<BR>
     * <BR>
     * �S�|�Q�|�R�j�@@�V�K�ڋq�R�[�h = �����J�݌����q�f�[�^�̏ꍇ�A�ȉ��̒l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�V�K�ڋq�R�[�h = �V�K�ڋq�R�[�h + 1<BR>
     * �@@�@@�����J�݌����q���X�g m �Ԗ� = �����J�݌����q���X�g m + 1 �Ԗ�<BR>
     * �@@�@@�����J�݌����q�d���`�F�b�N�t���O = false<BR>
     * <BR>
     * �S�|�R�j�@@�����J�݌����q�d���`�F�b�N�t���O = true ���邢�́A<BR>
     * �����J�݌����q���X�g m �Ԗڂ������J�݌����q�f�[�^�������傫���ꍇ�A<BR>
     * �V�K�ڋq�R�[�h���ڋq�}�X�^�ɑ��݂��邩�`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�ȉ��̏����ɊY������ꍇ�A�S�|�R�j�̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�ڋq�}�X�^���X�g n �Ԗڂ��ڋq�}�X�^�f�[�^���������������@@���A<BR>
     * �@@�@@�@@�ڋq�}�X�^�d���`�F�b�N�t���O = true�@@���A<BR>
     * �@@�@@�@@�ڋq�}�X�^�ڋq�R�[�h�召�`�F�b�N�t���O = true<BR>
     * <BR>
     * �S�|�R�|�P�j�@@�V�K�ڋq�R�[�h�̃`�F�b�N�f�W�b�g���擾��(*1)�A7���̐V�K�ڋq�R�[�h��ݒ肷��B<BR>
     * <BR>
     * �S�|�R�|�Q�j�@@�ڋq�}�X�^�f�[�^������0���̏ꍇ<BR>
     * <BR>
     * �@@�@@�ڋq�}�X�^�f�[�^ = "0"<BR>
     * <BR>
     * �S�|�R�|�R�j�@@�ڋq�}�X�^�f�[�^���������݂���ꍇ<BR>
     * <BR>
     * �@@�@@�ڋq�}�X�^�f�[�^ = �ڋq�}�X�^���X�g n �Ԗ�.get�ڋq�R�[�h<BR>
     * �S�|�R�|�S�j�@@�V�K�ڋq�R�[�h < �ڋq�}�X�^�f�[�^ �̏ꍇ�A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@�@@�ڋq�}�X�^�ڋq�R�[�h�召�`�F�b�N�t���O = false<BR>
     * <BR>
     * �S�|�R�|�T�j�@@�V�K�ڋq�R�[�h = �ڋq�}�X�^�f�[�^ �̏ꍇ�A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@�@@�V�K�ڋq�R�[�h = �V�K�ڋq�R�[�h + 1<BR>
     * �@@�@@�@@�ڋq�}�X�^�d���`�F�b�N�t���O = false<BR>
     * <BR>
     * �S�|�R�|�U�j�@@�ڋq�}�X�^���X�g n �Ԗڂ̐ݒ�<BR>
     * �@@�@@�@@�ڋq�}�X�^���X�g n �Ԗ� = �ڋq�}�X�^���X�g n �Ԗ� + 1<BR>
     * <BR>
     * �@@�@@�ȉ��̏����ɊY������ꍇ�A�S�j�̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@(�����J�݌����q�d���`�F�b�N�t���O = false ���邢�́A�ڋq�}�X�^�d���`�F�b�N�t���O = false)�@@���A<BR>
     * �@@�@@�@@�ڋq�}�X�^�ڋq�R�[�h�召�`�F�b�N�t���O = true<BR>
     * <BR>
     * �T�j�@@�����J�݌ڋq�R�[�h�e�[�u�����X�V����<BR>
     * <BR>
     * �T�|�P�j�@@�S�j�Ŏ擾�����f�[�^���X�V�ΏۂƂ���<BR>
     * �@@�����J�݌ڋq�R�[�h.�ڋq�R�[�h���ݒl�ɂ́A�V�K�ڋq�R�[�h���Z�b�g����<BR>
     * �@@�X�V���e�́ADB�X�V�d�l�u�����J�݌ڋq�R�[�h�e�[�u��_�X�V�d�l.xls�v���Q��<BR>
     * <BR>
     * �U�j�V�K�ڋq�R�[�h��ԋp����B<BR>
     * <BR>
     * (*1)�`�F�b�N�f�W�b�g�擾���\�b�h<BR>
     * <BR>
     * get�`�F�b�N�f�W�b�g�i�����R�[�h�FString�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountDiv - (�ڋq�敪)<BR>
     * �l�́ADB���C�A�E�g�u�����J�݌ڋq�R�[�h�v���Q�ƁB<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getNewAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewAccountCode(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            int l_no1 = 0;
            int l_no2 = 0;
            int l_lstDataSize1 = 0;
            int l_lstDataSize2 = 0;
            boolean l_blnNoLength1 = true;
            boolean l_blnNoLength2 = true;
            boolean l_blnNoLength3 = true;
            String l_strNewAccountCode = null;
            String l_strMaxAccountCode = null;
            String l_lstData1 = null;
            String l_lstData2 = null;
            String l_strMainCheckDigital = null;
            String l_strMainAccountCodeAddCheckDigital = null;

            QueryProcessor l_processer = Processors.getDefaultProcessor();

            // �P�j�����J�݌����q�̌ڋq�R�[�h�������̔Ԃ���B
            // �P�|�P�j�@@�ڋq�R�[�h���R�[�h���擾����B
            // �@@�����J�݌ڋq�R�[�h�e�[�u�����ȉ��̏����Ō�������B
            // �@@�@@[����]
            // �@@�@@�����J�݌ڋq�R�[�h.�،���ЃR�[�h = ����.�،���ЃR�[�h
            // �@@�@@�����J�݌ڋq�R�[�h.���X�R�[�h = ����.���X�R�[�h
            // �@@�@@�����J�݌ڋq�R�[�h.�ڋq�敪 = ����.�ڋq�敪
            StringBuffer l_sbWhereNewAccountCode = new StringBuffer();
            l_sbWhereNewAccountCode.append(" institution_code = ? ");
            l_sbWhereNewAccountCode.append(" and branch_code = ? ");
            l_sbWhereNewAccountCode.append(" and account_div = ? ");

            Object[] l_objWhereValuesNewAccountCode = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountDiv};

            List l_lisAccOpenAccountCodeRows = new ArrayList();
            l_lisAccOpenAccountCodeRows = l_processer.doFindAllQuery(
                AccOpenAccountCodeRow.TYPE,
                l_sbWhereNewAccountCode.toString(),
                "for update",
                l_objWhereValuesNewAccountCode);

            // �@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̗�O���X���[����B
            // �@@�@@�u�����̔Ԃ̃f�[�^���擾�ł��܂���ł����B�v
            if (l_lisAccOpenAccountCodeRows == null || l_lisAccOpenAccountCodeRows.size() == 0)
            {
                log.debug("�����̔Ԃ̃f�[�^���擾�ł��܂���ł����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����̔Ԃ̃f�[�^���擾�ł��܂���ł����B");
            }

            AccOpenAccountCodeRow l_row = (AccOpenAccountCodeRow) l_lisAccOpenAccountCodeRows.get(0);

            // �P�|�Q�j�@@�ڋq�R�[�h���ݒl���擾����B
            // �@@�ڋq�R�[�h���ݒl = �����J�݌ڋq�R�[�h.�ڋq�R�[�h���ݒl
            AccOpenAccountCodeParams l_params = new AccOpenAccountCodeParams(l_row);
            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
                Double.parseDouble(l_row.getLastAccountCode()));
            // �P�|�R�j�@@�ڋq�R�[�h�ő�l���擾����B
            // �@@�ڋq�R�[�h�ő�l = �����J�݌ڋq�R�[�h.�ڋq�R�[�h�ő�l
            l_strMaxAccountCode = l_row.getMaxAccountCode();
            // �P�|�S�j�@@�V�K�ڋq�R�[�h���̔Ԃ���B
            // �@@�V�K�ڋq�R�[�h = �ڋq�R�[�h���ݒl + 1
            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
                Double.parseDouble(l_strNewAccountCode) + 1);
            // �P�|�T�j�@@�V�K�ڋq�R�[�h���ő�l�𒴂��Ă��Ȃ����`�F�b�N����B
            // �@@�V�K�ڋq�R�[�h > �����J�݌ڋq�R�[�h.�ڋq�R�[�h�ő�l�@@�̏ꍇ�A�ȉ��̗�O���X���[����B
            // �@@�u�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B
            if (Double.parseDouble(l_strNewAccountCode) > Double.parseDouble(l_strMaxAccountCode))
            {
                log.debug("�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02609,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B");
            }

            // �Q�j�����J�݌����q�f�[�^���擾����B
            // �Q�|�P�j�@@�����J�݌����q���ȉ��̏����Ō�������B
            // �@@[����]
            // �@@�����J�݌����q.�،���ЃR�[�h = ����.�،���ЃR�[�h
            // �@@�����J�݌����q.���X�R�[�h = ����.���X�R�[�h
            // �@@�����J�݌����q.�����R�[�h => �V�K�ڋq�R�[�h
            // �@@�����J�݌����q.�����R�[�h <= �����J�݌ڋq�R�[�h.�ڋq�R�[�h�ő�l
            StringBuffer l_sbWhere2 = new StringBuffer();
            l_sbWhere2.append(" institution_code = ? ");
            l_sbWhere2.append(" and branch_code = ? ");
            l_sbWhere2.append(" and account_code between ? ");
            l_sbWhere2.append(" and ? ");
            // �@@[���בւ�]
            // �@@�����J�݌����q�D�����R�[�h�̏���
            StringBuffer l_strSortCond2 = new StringBuffer();
            l_strSortCond2.append("account_code asc");

            Object[] l_objWhereValue2s = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strNewAccountCode,
                l_strMaxAccountCode
                };
            // �@@�擾���ʂ��A�����J�݌����q���X�g�ɃZ�b�g����B
            List l_lisExpAccountOpenRows = new ArrayList();
            l_lisExpAccountOpenRows = l_processer.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_sbWhere2.toString(),
                l_strSortCond2.toString(),
                null,
                l_objWhereValue2s);
            // �Q�|�Q�j�@@�����J�݌����q�f�[�^�������擾����B
            l_lstDataSize2 = l_lisExpAccountOpenRows.size();

            // �R�j�ڋq�}�X�^�f�[�^���擾����
            // �R�|�P�j�@@�V�K�ڋq�R�[�h�̃`�F�b�N�f�W�b�g���擾��(*1)�A7���̐V�K�ڋq�R�[�h��ݒ肷��B
            String l_strNewCheckDigital =
                WEB3GentradeCheckDigitalUtility.getCheckDigital(l_strNewAccountCode);
            String l_strNewAccountCodeAddCheckDigital = l_strNewAccountCode + l_strNewCheckDigital;
            // �R�|�Q�j�@@�ڋq�R�[�h�ő�l�̃`�F�b�N�f�W�b�g���擾��(*1)�A7���̌ڋq�R�[�h�ő�l��ݒ肷��B
            String l_strMaxCheckDigital =
                WEB3GentradeCheckDigitalUtility.getCheckDigital(l_strMaxAccountCode);
            String l_strMaxAccountCodeAddCheckDigital = l_strMaxAccountCode + l_strMaxCheckDigital;
            // �R�|�R�j�@@�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������
            // �@@[����]
            // �@@�ڋq�}�X�^.�،���ЃR�[�h = ����.�،���ЃR�[�h
            // �@@�ڋq�}�X�^.���X�R�[�h = ����.���X�R�[�h
            // �@@�ڋq�}�X�^.�����R�[�h => 7���̐V�K�ڋq�R�[�h
            // �@@�ڋq�}�X�^.�����R�[�h <= 7���̌ڋq�R�[�h�ő�l
            StringBuffer l_sbWhere1 = new StringBuffer();
            l_sbWhere1.append(" institution_code = ? ");
            l_sbWhere1.append(" and branch_code = ? ");
            l_sbWhere1.append(" and account_code between ? ");
            l_sbWhere1.append(" and ? ");
            // �@@[���בւ�]
            // �@@�ڋq�}�X�^�D�����R�[�h�̏���
            StringBuffer l_strSortCond1 = new StringBuffer();
            l_strSortCond1.append("account_code asc");

            Object[] l_objWhereValue1s = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strNewAccountCodeAddCheckDigital,
                l_strMaxAccountCodeAddCheckDigital};
            // �擾���ʂ��A�ڋq�}�X�^���X�g�ɃZ�b�g����B
            List l_lisMainAccountRows = new ArrayList();
            l_lisMainAccountRows = l_processer.doFindAllQuery(
                MainAccountRow.TYPE,
                l_sbWhere1.toString(),
                l_strSortCond1.toString(),
                null,
                l_objWhereValue1s);
            // �R�|�S�j�@@�ڋq�}�X�^�f�[�^�������擾����
            l_lstDataSize1 = l_lisMainAccountRows.size();

            // �S�j�@@�V�K�ڋq�R�[�h����������
            do
            {
                l_blnNoLength1 = true;
                l_blnNoLength2 = true;
                // �S�|�P�j�@@�V�K�ڋq�R�[�h���ő�l�𒴂��Ă��Ȃ����`�F�b�N����B
                // �|�V�K�ڋq�R�[�h > �ڋq�R�[�h�ő�l�@@�̏ꍇ�A�ȉ��̗�O���X���[����B
                //  �ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B
                if (Double.parseDouble(l_strNewAccountCode) > Double.parseDouble(l_strMaxAccountCode))
                {
                    log.debug("�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02609,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ő�l�𒴂������߁A�����̔Ԃł��܂���ł����B");
                }
                // �S�|�Q�j�@@�V�K�ڋq�R�[�h�������J�݌����q�f�[�^�ɑ��݂��邩���`�F�b�N����B
                // �S�|�Q�|�P�j�@@�����J�݌����q�f�[�^������0���̏ꍇ
				if (l_lisExpAccountOpenRows == null || l_lstDataSize2 == 0)
				{
					l_lstData2 = "0";
				}
                // �S�|�Q�|�Q�j�@@�����J�݌����q�f�[�^���������݂���ꍇ
				if (l_no2 < l_lstDataSize2)
				{
					// �����J�݌����q�f�[�^ = �����J�݌����q���X�g m �Ԗ�.get�ڋq�R�[�h
					ExpAccountOpenRow exp_row = (ExpAccountOpenRow) l_lisExpAccountOpenRows.get(l_no2);
					l_lstData2 = exp_row.getAccountCode();
				}
                // �S�|�Q�|�R�j�@@�V�K�ڋq�R�[�h = �����J�݌����q�f�[�^�̏ꍇ�A�ȉ��̒l���Z�b�g����B
				if (Double.parseDouble(l_strNewAccountCode) == Double.parseDouble(l_lstData2))
				{
                    // �V�K�ڋq�R�[�h = �V�K�ڋq�R�[�h + 1
                    // �����J�݌����q���X�g m �Ԗ� = �����J�݌����q���X�g m + 1 �Ԗ�
                    // �����J�݌����q�d���`�F�b�N�t���O = false
		            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
		                Double.parseDouble(l_strNewAccountCode) + 1);
					l_no2 = l_no2 + 1;
					l_blnNoLength2 = false;
				}
                // �S�|�R�j�@@�����J�݌����q�d���`�F�b�N�t���O = true ���邢�́A
                // �����J�݌����q���X�g m �Ԗڂ������J�݌����q�f�[�^�������傫���ꍇ�A
                // �V�K�ڋq�R�[�h���ڋq�}�X�^�ɑ��݂��邩�`�F�b�N����B
				if (l_blnNoLength2 || l_no2 >= l_lstDataSize2)
				{
                    // �ȉ��̏����ɊY������ꍇ�A�S�|�R�j�̏������J��Ԃ��B
                    // [����]
                    // �ڋq�}�X�^���X�g n �Ԗڂ��ڋq�}�X�^�f�[�^���������������@@���A
                    // �ڋq�}�X�^�d���`�F�b�N�t���O = true�@@���A
                    // �ڋq�}�X�^�ڋq�R�[�h�召�`�F�b�N�t���O = true
					while (l_no1 < l_lstDataSize1 && l_blnNoLength1 && l_blnNoLength3)	
					{
                        // �S�|�R�|�P�j�@@�V�K�ڋq�R�[�h�̃`�F�b�N�f�W�b�g���擾��(*1)�A7���̐V�K�ڋq�R�[�h��ݒ肷��B
						l_strMainCheckDigital = WEB3GentradeCheckDigitalUtility.getCheckDigital(l_strNewAccountCode);
						l_strMainAccountCodeAddCheckDigital = l_strNewAccountCode + l_strMainCheckDigital;

                        // �S�|�R�|�Q�j�@@�ڋq�}�X�^�f�[�^������0���̏ꍇ
						if (l_lisMainAccountRows == null || l_lstDataSize1 == 0)
						{
							l_lstData1 = "0";
                        // �S�|�R�|�R�j�@@�ڋq�}�X�^�f�[�^���������݂���ꍇ
						} else {
                            // �ڋq�}�X�^�f�[�^ = �ڋq�}�X�^���X�g n �Ԗ�.get�ڋq�R�[�h
							MainAccountRow main_row = (MainAccountRow) l_lisMainAccountRows.get(l_no1);
							l_lstData1 = main_row.getAccountCode();
						}
                        // �S�|�R�|�S�j�@@�V�K�ڋq�R�[�h < �ڋq�}�X�^�f�[�^ �̏ꍇ�A�ȉ��̒l���Z�b�g����B
						if (Double.parseDouble(l_lstData1) > Double.parseDouble(l_strMainAccountCodeAddCheckDigital)) {
                            // �ڋq�}�X�^�ڋq�R�[�h�召�`�F�b�N�t���O = false
							l_blnNoLength3 = false;
						}
                        // �S�|�R�|�T�j�@@�V�K�ڋq�R�[�h = �ڋq�}�X�^�f�[�^ �̏ꍇ�A�ȉ��̒l���Z�b�g����B
						if (Double.parseDouble(l_lstData1) == Double.parseDouble(l_strMainAccountCodeAddCheckDigital))
						{
                            // �V�K�ڋq�R�[�h = �V�K�ڋq�R�[�h + 1
                            // �ڋq�}�X�^�d���`�F�b�N�t���O = false
							l_blnNoLength1 = false;
				            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
				                Double.parseDouble(l_strNewAccountCode) + 1);
						}
                        // �S�|�R�|�U�j�@@�ڋq�}�X�^���X�g n �Ԗڂ̐ݒ�
                        // �ڋq�}�X�^���X�g n �Ԗ� = �ڋq�}�X�^���X�g n �Ԗ� + 1
						l_no1 = l_no1 + 1;
					}
				}
			}
            // �ȉ��̏����ɊY������ꍇ�A�S�j�̏������J��Ԃ��B
            // [����]
            // (�����J�݌����q�d���`�F�b�N�t���O = false ���邢�́A�ڋq�}�X�^�d���`�F�b�N�t���O = false)�@@���A
            // �ڋq�}�X�^�ڋq�R�[�h�召�`�F�b�N�t���O = true
			while ((!l_blnNoLength2 || !l_blnNoLength1) && l_blnNoLength3);

            // �T�j�@@�����J�݌ڋq�R�[�h�e�[�u�����X�V����
            // �T�|�P�j�@@�S�j�Ŏ擾�����f�[�^���X�V�ΏۂƂ���
            // �@@�����J�݌ڋq�R�[�h.�ڋq�R�[�h���ݒl�ɂ́A�V�K�ڋq�R�[�h���Z�b�g����
            // �@@�X�V���e�́ADB�X�V�d�l�u�����J�݌ڋq�R�[�h�e�[�u��_�X�V�d�l.xls�v���Q��
            l_params.setLastAccountCode(l_strNewAccountCode);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_processer.doUpdateQuery(l_params);//DataNetworkException, DataQueryException

            // �U�j�V�K�ڋq�R�[�h��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_strNewAccountCode;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
