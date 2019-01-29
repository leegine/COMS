head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOtherOrgServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�O���A�g�T�[�r�XImpl(WEB3SrvRegiOtherOrgServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 ���g �V�K�쐬 ���f��310,313,314,315,317,318,319,320
Revision History : 2008/02/26 ���g �d�l�ύX ���f��321
Revision History : 2008/03/03 ���g �d�l�ύX ���f��330,343
Revision History : 2008/03/03 ���g �d�l�ύX ���f��340
Revision History : 2008/03/19 ���g �d�l�ύX ���f��354,356,358
Revision History : 2008/03/26 ���g �����̖��002
Revision History : 2008/03/28 ���g �d�l�ύX ���f��364
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.OtherOrgInfoAdminRow;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�O���A�g�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�O���A�g�T�[�r�X�����N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3SrvRegiOtherOrgServiceImpl
    implements WEB3SrvRegiOtherOrgService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiOtherOrgServiceImpl.class);

    /**
     * �T�[�r�X���p�O���A�g�T�[�r�XImpl<BR>
     */
    public WEB3SrvRegiOtherOrgServiceImpl()
    {

    }

    /**
     * (get�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u�����A�f�[�^���擾����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�O���A�g���Ǘ��e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �T�[�r�X�敪 = ����.�T�[�r�X�敪( )<BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * ���X�R�[�h = ����.���X�R�[�h<BR>
     * �����R�[�h = ����.�����R�[�h<BR>
     * �X�e�[�^�X = 0�F�g�p��<BR>
     * <BR>
     * 2) �������ʂ�2���ȏ�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * 3) �߂�l�̐���<BR>
     * �@@3-1) �������ʂ�0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@3-2) �������ʂ�1���̏ꍇ�A
     * �@@�@@��L�������ʂł���O���A�g���Params�I�u�W�F�N�g�������ɁA<BR>
     * �@@�@@�O���A�g���Ǘ��N���X�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     * 4) ����.is�s���b�N=true�̏ꍇ<BR>
     * �@@�@@���������O���A�g���Ǘ��I�u�W�F�N�g.createForUpdateParams( ) ���R�[������B<BR>
     * <BR>
     * 5) ���������O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * is�s���b�N<BR>
     * true�F�s���b�N������B�@@false�F�s���b�N�����Ȃ��B<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        String l_strServiceDiv,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgInfo(String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) �ȉ��̏����Łu�O���A�g���Ǘ��e�[�u���v����������B
        //�i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)
        //�T�[�r�X�敪 = ����.�T�[�r�X�敪( )
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h = ����.���X�R�[�h
        //�����R�[�h = ����.�����R�[�h
        //�X�e�[�^�X = 0�F�g�p��
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" srv_div = ? and ");

        l_sbQueryString.append(" institution_code = ? and ");

        l_sbQueryString.append(" branch_code = ? and ");

        l_sbQueryString.append(" account_code = ? and ");

        l_sbQueryString.append(" status = ? ");

        Object[] l_queryContainers = {l_strServiceDiv, l_strInstitutionCode,
            l_strBranchCode, l_strAccountCode, WEB3OtherOrgStatusDef.USING};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (l_blnIsRowLock)
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    " for update ",
                    l_queryContainers);
            }
            else
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    null,
                    l_queryContainers);
            }
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2) �������ʂ�2���ȏ�̏ꍇ�A��O���X���[����B
        if (l_lisOtherOrgInfoAdminRows.size() > 1)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //3) �߂�l�̐���
        //3-1) �������ʂ�0���̏ꍇ�Anull��ԋp����B
        if (l_lisOtherOrgInfoAdminRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //3-2) �������ʂ�1���̏ꍇ�A
        //��L�������ʂł���O���A�g���Params�I�u�W�F�N�g�������ɁA
        //�O���A�g���Ǘ��N���X�̃R���X�g���N�^���R�[������B
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
            (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(0);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);
        //4) ����.is�s���b�N=true�̏ꍇ
        //���������O���A�g���Ǘ��I�u�W�F�N�g.createForUpdateParams( ) ���R�[������B
        if (l_blnIsRowLock)
        {
            l_srvRegiOtherOrgInfoAdmin.createForUpdateParams();
        }

        //5) ���������O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (get�O���A�g���)<BR>
     * �����Ŏw�肳�ꂽ�ʔԁA�T�[�r�X�敪�ɊY������<BR>
     * �O���A�g���Ǘ��I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * 1)�@@�O���A�g���Ǘ��e�[�u�����������A�O���A�g���Ǘ�Params���擾����B<BR>
     * �@@�@@�i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@�ʔ�=����.�ʔ� and<BR>
     * �@@�@@�@@�@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * <BR>
     * �@@1-1)�@@��������=0���̏ꍇ<BR>
     * �@@�@@�@@�@@null��ԋp����B<BR>
     * �@@1-2)�@@��������=1���̏ꍇ<BR>
     * �@@�@@�@@�@@�O���A�g���Ǘ��N���X�̃R���X�g���N�^���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�O���A�g���Ǘ��I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�O���A�g���Ǘ�Row: �擾�����O���A�g���Ǘ�Params<BR>
     * <BR>
     * 2)�@@����.is�s���b�N=true�̏ꍇ<BR>
     * �@@�@@�O���A�g���Ǘ��I�u�W�F�N�g.createForUpdateParams( )���R�[������B<BR>
     * <BR>
     * 3)�@@�O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * is�s���b�N<BR>
     * true�F�s���b�N������B�@@false�F�s���b�N�����Ȃ��B<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgInfo(long, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) �O���A�g���Ǘ��e�[�u�����������A�O���A�g���Ǘ�Params���擾����B
        // �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)
        // [��������]
        //�ʔ�=����.�ʔ� and
        //�T�[�r�X�敪=����.�T�[�r�X�敪
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" sequence_number = ? and ");

        l_sbQueryString.append(" srv_div = ? ");

        Object[] l_queryContainers = {new Long(l_lngSequenceNumber), l_strServiceDiv};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (l_blnIsRowLock)
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    " for update ",
                    l_queryContainers);
            }
            else
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    null,
                    null,
                    l_queryContainers);
            }
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1-1) ��������=0���̏ꍇ
        //null��ԋp����B
        if (l_lisOtherOrgInfoAdminRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //1-2) ��������=1���̏ꍇ
        //�O���A�g���Ǘ��N���X�̃R���X�g���N�^���R�[�����A�O���A�g���Ǘ��I�u�W�F�N�g�𐶐�����B
        //[����]
        //�O���A�g���Ǘ�Row: �擾�����O���A�g���Ǘ�Params
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
            (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(0);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);
        //2) ����.is�s���b�N=true�̏ꍇ
        //�O���A�g���Ǘ��I�u�W�F�N�g.createForUpdateParams( )���R�[������B
        if (l_blnIsRowLock)
        {
            l_srvRegiOtherOrgInfoAdmin.createForUpdateParams();
        }

        //3) �O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (get�O���A�g���g�p����)<BR>
     * �O���A�g���Ǘ��e�[�u���̃X�e�[�^�X�����g�p�̃��R�[�h�̌�����ԋp<BR>
     * <BR>
     * 1) �ȉ��̏����́u�O���A�g���Ǘ��e�[�u���v�̃��R�[�h�������擾����B<BR>
     * [��������]<BR>
     * �T�[�r�X�敪 = ����.�T�[�r�X�敪( )<BR>
     * �X�e�[�^�X = 9�i���g�p�j<BR>
     * <BR>
     * 2�j�������ʂ̌�����ԋp����B<BR>
     * �������� = 0 �̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@return Long
     * @@throws WEB3BaseException
     */
    public Long getOtherOrgUnUsedCount(
        String l_strServiceDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgUnUsedCount(String)";
        log.entering(STR_METHOD_NAME);

        //1) �ȉ��̏����́u�O���A�g���Ǘ��e�[�u���v�̃��R�[�h�������擾����B
        //�T�[�r�X�敪 = ����.�T�[�r�X�敪( )
        //�X�e�[�^�X = 9�i���g�p�j
        String l_strQueryString = " srv_div = ? and status = ? ";

        Object[] l_queryContainers = {l_strServiceDiv, WEB3OtherOrgStatusDef.DEFAULT};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                OtherOrgInfoAdminRow.TYPE,
                l_strQueryString,
                l_queryContainers);
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2�j�������ʂ̌�����ԋp����B
        //�������� = 0 �̏ꍇ�A0��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return new Long(l_lisOtherOrgInfoAdminRows.size());
    }

    /**
     * (get�O���A�g���ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v����O���A�g���Ǘ�ð��ق��������A<BR>
     * ���̌��ʂ��O���A�g���Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR>
     * <BR>
     * 1) �\�[�g�����̍쐬<BR>
     * �@@����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ�A<BR>
     * �@@����.�\�[�g�����̌������A�ȉ����J��Ԃ��B<BR>
     * �@@1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�@@�E�ʔԁ@@�@@�@@�@@�@@�@@�@@�@@=�O���A�g���Ǘ��e�[�u��.�ʔ�<BR>
     * �@@�@@�@@�@@�EID �@@�@@�@@�@@�@@�@@�@@�@@�@@=�O���A�g���Ǘ��e�[�u��.ID<BR>
     * �@@�@@�@@�@@�E�X�e�[�^�X�@@�@@�@@�@@�@@=�O���A�g���Ǘ��e�[�u��.�X�e�[�^�X<BR>
     * �@@�@@�@@�@@�E���X�R�[�h�@@�@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.���X�R�[�h<BR>
     * �@@�@@�@@�@@�E�����R�[�h�@@�@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�����R�[�h<BR>
     * �@@�@@�@@�@@�E�K�p����From�@@�@@=�O���A�g���Ǘ��e�[�u��.�K�p����From<BR>
     * �@@�@@�@@�@@�E�K�p����To�@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�K�p����To<BR>
     * �@@�@@�@@�@@�E�ŏI�X�V���@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�X�V���t<BR>
     * �@@�@@�@@�@@�E�ŏI�X�V�ҁ@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�@@�������^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B<BR>
     * <BR>
     * 2) �ȉ��̌��������ŁA�u�O���A�g���Ǘ��e�[�u���v����������B<BR>
     * �@@[��������] <BR>
     * �@@�@@���T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�@@���،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�@@�����X�R�[�h=null�@@���́@@���X�R�[�h=����.���X�R�[�h(*1)<BR>
     * <BR>
     * �@@�@@���ʔԁ@@=�@@����.�ʔ� ---------�i�������A����.�ʔԂ�null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@��ID�@@=�@@����.ID ---------�i�������A����.ID��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���X�e�[�^�X�@@=�@@����.�X�e�[�^�X ---------�i�������A����.�X�e�[�^�X��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@�������R�[�h�@@like�@@����.�����R�[�h% ---------�i�������A����.�����R�[�h��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���K�p����From�@@���@@����.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j��null�ł͖����ꍇ�Ɍ���j<BR>
     * �@@�@@���K�p����From�@@���@@����.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j��null�ł͖����ꍇ�Ɍ���j<BR>
     * <BR>
     * �@@[���я�]<BR>
     * �@@�@@1)�Ő��������\�[�g����(*3)<BR>
     * <BR>
     * (*1)����.���X�ɂ���<BR>
     * �@@�E����.���X�R�[�h�̗v�f�����P�����Ȃ��ꍇ�A�ȉ��̂悤�ȏ����Ō������s���B<BR>
     * �@@�@@�@@"���X�R�[�h=����.���X�R�[�h�@@and ..."<BR>
     * �@@�E����.���X�R�[�h�̗v�f���������������ꍇ�A�ȉ��̂悤�ȏ����Ō������s���B<BR>
     * �@@�@@�@@"���X�R�[�h in (����.���X�R�[�h[n]�A����.���X�R�[�h[n+1]...)"<BR>
     * <BR>
     * (*2)����.�\�[�g����=null�̏ꍇ�A�K�p����From�̍~�����w�肷��B<BR>
     * <BR>
     * <BR>
     * 3) 2)�̌������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strID - (ID)<BR>
     * ID<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�ꗗ<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n���i���j)<BR>
     * �K�p�J�n���i���j<BR>
     * @@param l_tsAppliEndDate - (�K�p�J�n���i���j)<BR>
     * �K�p�J�n���i���j<BR>
     * @@param l_sortKeys - (�\�[�g����)<BR>
     * �Ώۍ���<BR>
     * ��Ɖ�̏ꍇ��<BR>
     * �@@�ʔԁ^ID�^�X�e�[�^�X�^���X�^�ڋq�^�K�p�J�n���^�K�p�I�����^�ŏI�X�V���^�ŏI�X�V��<BR>
     * ���޳�۰�ނ̏ꍇ��<BR>
     * �@@�ʔ�<BR>
     * @@return OtherOrgInfoAdminParams[]
     * @@throws WEB3BaseException
     */
    public OtherOrgInfoAdminParams[] getOtherOrgInfoList(
        String l_strSequenceNumber,
        String l_strServiceDiv,
        String l_strID,
        String l_strStatus,
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        WEB3SrvRegiSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOtherOrgInfoList(String, String, String, String, String,"
            + " String[], String, Timestamp, Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        //1) �\�[�g�����̍쐬
        //����.�\�[�g������null�ȊO�ł���A���v�f����0�̏ꍇ�A
        //����.�\�[�g�����̌������A�ȉ����J��Ԃ��B
        String l_strOrderBy = "";
        if (l_sortKeys != null && l_sortKeys.length > 0)
        {
            //1-1) �Ή�����e�[�u���̗񕨗����������^�~���w���t�����Z�b�g����B
            //���L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B
            //���L�[���ڕ�����(�V���{����)�́A���b�Z�[�W��`�����Q�ƁB
            //���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB
            int l_intLength = l_sortKeys.length;
            for (int i = 0; i < l_intLength; i++)
            {
                if (WEB3SrvRegiKeyItemDef.SEQUENCE_NUMBER.equals(l_sortKeys[i].keyItem))
                {
                    //�E�ʔԁ@@�@@�@@�@@�@@�@@�@@�@@=�O���A�g���Ǘ��e�[�u��.�ʔ�
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("sequence_number asc");
                        l_strOrderBy = l_strOrderBy + " sequence_number asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("sequence_number desc");
                        l_strOrderBy = l_strOrderBy + " sequence_number desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ID.equals(l_sortKeys[i].keyItem))
                {
                    //�EID �@@�@@�@@�@@�@@�@@�@@�@@�@@=�O���A�g���Ǘ��e�[�u��.ID
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("id asc");
                        l_strOrderBy = l_strOrderBy + " id asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("id desc");
                        l_strOrderBy = l_strOrderBy + " id desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.STATUS.equals(l_sortKeys[i].keyItem))
                {
                    //�E�X�e�[�^�X�@@�@@�@@�@@�@@=�O���A�g���Ǘ��e�[�u��.�X�e�[�^�X
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("status asc");
                        l_strOrderBy = l_strOrderBy + " status asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("status desc");
                        l_strOrderBy = l_strOrderBy + " status desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    //�E���X�R�[�h�@@�@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.���X�R�[�h
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("branch_code asc");
                        l_strOrderBy = l_strOrderBy + " branch_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("branch_code desc");
                        l_strOrderBy = l_strOrderBy + " branch_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    //�E�����R�[�h�@@�@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�����R�[�h
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("account_code asc");
                        l_strOrderBy = l_strOrderBy + " account_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("account_code desc");
                        l_strOrderBy = l_strOrderBy + " account_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_START_DATE_FROM.equals(l_sortKeys[i].keyItem))
                {
                    //�E�K�p����From�@@�@@=�O���A�g���Ǘ��e�[�u��.�K�p����From
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_start_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_start_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_END_DATE_TO.equals(l_sortKeys[i].keyItem))
                {
                    //�E�K�p����To�@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�K�p����To
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_end_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("appli_end_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
                {
                    //�E�ŏI�X�V���@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�X�V���t
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  asc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  desc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATER.equals(l_sortKeys[i].keyItem))
                {
                    //�E�ŏI�X�V�ҁ@@�@@�@@ =�O���A�g���Ǘ��e�[�u��.�X�V�҃R�[�h
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updater  asc");
                        l_strOrderBy = l_strOrderBy + " last_updater  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                    {
                        log.debug("last_updater  desc");
                        l_strOrderBy = l_strOrderBy + " last_updater  desc";
                    }
                }

                if (i != l_intLength - 1)
                {
                    l_strOrderBy = l_strOrderBy + ", ";
                }
            }
        }
        else
        {
            log.debug("appli_start_date desc");
            l_strOrderBy = " appli_start_date desc ";
        }

        //2) �ȉ��̌��������ŁA�u�O���A�g���Ǘ��e�[�u���v����������B
        //[��������]
        //���T�[�r�X�敪=����.�T�[�r�X�敪
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryStrings = new ArrayList();
        l_sbQueryString.append(" srv_div = ? and ");
        l_lisQueryStrings.add(l_strServiceDiv);

        //���،���ЃR�[�h=����.�،���ЃR�[�h
        l_sbQueryString.append(" institution_code = ? and ");
        l_lisQueryStrings.add(l_strInstitutionCode);

        //�����X�R�[�h=null�@@���́@@���X�R�[�h=����.���X�R�[�h(*1)
        l_sbQueryString.append(" (branch_code is null ");

        //(*1)����.���X�ɂ���
        //�E����.���X�R�[�h�̗v�f�����P�����Ȃ��ꍇ�A�ȉ��̂悤�ȏ����Ō������s���B
        //"���X�R�[�h=����.���X�R�[�h�@@and ..."
        //�E����.���X�R�[�h�̗v�f���������������ꍇ�A�ȉ��̂悤�ȏ����Ō������s���B
        //"���X�R�[�h in (����.���X�R�[�h[n]�A����.���X�R�[�h[n+1]...)"
        if (l_strBranchCodes.length == 1)
        {
            l_sbQueryString.append(" or branch_code = ? ");
            l_lisQueryStrings.add(l_strBranchCodes[0]);
        }
        else if (l_strBranchCodes.length > 1)
        {
            String l_strBranchCode = "";
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                l_lisQueryStrings.add(l_strBranchCodes[i]);
                if (i == l_strBranchCodes.length - 1)
                {
                    l_strBranchCode = l_strBranchCode + " ?";
                }
                else
                {
                    l_strBranchCode = l_strBranchCode + " ?,";
                }
            }
            l_sbQueryString.append(" or branch_code in (" + l_strBranchCode + ") ");
        }

        l_sbQueryString.append(" ) ");
        //���ʔԁ@@=�@@����.�ʔ� ---------�i�������A����.�ʔԂ�null�ł͖����ꍇ�Ɍ���j
        if (l_strSequenceNumber != null)
        {
            l_sbQueryString.append(" and sequence_number = ? ");
            l_lisQueryStrings.add(l_strSequenceNumber);
        }

        //��ID�@@=�@@����.ID ---------�i�������A����.ID��null�ł͖����ꍇ�Ɍ���j
        if (l_strID != null)
        {
            l_sbQueryString.append(" and id = ? ");
            l_lisQueryStrings.add(l_strID);
        }

        //���X�e�[�^�X�@@=�@@����.�X�e�[�^�X ---------�i�������A����.�X�e�[�^�X��null�ł͖����ꍇ�Ɍ���j
        if (l_strStatus != null)
        {
            l_sbQueryString.append(" and status = ? ");
            l_lisQueryStrings.add(l_strStatus);
        }

        //�������R�[�h�@@like�@@����.�����R�[�h% ---------�i�������A����.�����R�[�h��null�ł͖����ꍇ�Ɍ���j
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
            l_lisQueryStrings.add(l_strAccountCode);
        }

        //���K�p����From�@@���@@����.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j��null�ł͖����ꍇ�Ɍ���j
        if (l_tsAppliStartDate != null)
        {
            l_sbQueryString.append(" and appli_start_date >= ? ");
            l_lisQueryStrings.add(
                WEB3DateUtility.toDay(l_tsAppliStartDate));
        }

        //���K�p����From�@@���@@����.�K�p�J�n���i���j -�i�������A����.�K�p�J�n���i���j��null�ł͖����ꍇ�Ɍ���j
        if (l_tsAppliEndDate != null)
        {
            l_sbQueryString.append(" and appli_start_date <= ? ");
            l_lisQueryStrings.add(
                WEB3DateUtility.toDay(l_tsAppliEndDate));
        }

        Object[] l_queryContainers = new Object[l_lisQueryStrings.size()];
        l_lisQueryStrings.toArray(l_queryContainers);

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                OtherOrgInfoAdminRow.TYPE,
                l_sbQueryString.toString(),
                l_strOrderBy,
                null,
                l_queryContainers);
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        OtherOrgInfoAdminParams[] l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams[l_lisOtherOrgInfoAdminRows.size()];

        for (int i = 0; i < l_lisOtherOrgInfoAdminRows.size(); i++)
        {
            OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
                (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(i);

            l_otherOrgInfoAdminParams[i] = new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);
        }
        //3) 2)�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_otherOrgInfoAdminParams;
    }

    /**
     * (get���g�p�ʔԏ��)<BR>
     * �O���A�g���Ǘ��e�[�u���ɂ����āA�X�e�[�^�X�����g�p�̃��R�[�h�̒��ŁA<BR>
     * �ʔԂ��ŏ��̃��R�[�h�i�O���A�g���Ǘ��I�u�W�F�N�g�j��ԋp����B<BR>
     * <BR>
     * 1)�@@�ȉ��̏����Łu�O���A�g���Ǘ��e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �T�[�r�X�敪 = ����.�T�[�r�X�敪( )<BR>
     * �ʔ� = ( select MIN(�ʔ�) FROM �O���A�g���Ǘ��e�[�u��<BR>
     * �@@WHERE �T�[�r�X�敪 = ����.�T�[�r�X�敪( ) AND �X�e�[�^�X = 9 �j<BR>
     * <BR>
     * 2) �߂�l�̐���<BR>
     * �@@2-1) �������ʂ� 0���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03019<BR>
     * <BR>
     * �@@2-2)�@@�������ʂ� 1���̏ꍇ�A<BR>
     * �@@�@@��L�������ʂł��関�g�p�ʔԏ��Params�I�u�W�F�N�g�������ɁA<BR>
     * �@@�@@�@@�O���A�g���Ǘ��N���X�̃R���X�g���N�^���R�[������B<BR>
     * <BR>
     * 3)�@@����.is�s���b�N = true �̏ꍇ<BR>
     * �@@�@@���������O���A�g���Ǘ��I�u�W�F�N�g.createForUpdateParams( ) ���R�[������B<BR>
     * <BR>
     * 4)�@@���������O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * is�s���b�N<BR>
     * true�F�s���b�N������B�@@false�F�s���b�N�����Ȃ��B<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getUnUseSequenceNumberInfo(
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnUseSequenceNumberInfo(String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) �ȉ��̏����Łu�O���A�g���Ǘ��e�[�u���v����������B
        // �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)
        // [��������]
        //�T�[�r�X�敪=����.�T�[�r�X�敪
        //�ʔ� = ( select MIN(�ʔ�) FROM �O���A�g���Ǘ��e�[�u�� WHERE �T�[�r�X�敪 = ����.�T�[�r�X�敪
        //( ) AND �X�e�[�^�X = 9 �j
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" srv_div = ? and status = ? ");

        Object[] l_queryContainers = {l_strServiceDiv, WEB3OtherOrgStatusDef.DEFAULT};

        List l_lisOtherOrgInfoAdminRows = null;
        try
        {
            String l_strOrderBy = " sequence_number asc ";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (l_blnIsRowLock)
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    l_strOrderBy,
                    " for update ",
                    l_queryContainers);
            }
            else
            {
                l_lisOtherOrgInfoAdminRows = l_queryProcessor.doFindAllQuery(
                    OtherOrgInfoAdminRow.TYPE,
                    l_sbQueryString.toString(),
                    l_strOrderBy,
                    null,
                    l_queryContainers);
            }
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //2) �߂�l�̐���
        //2-1) �������ʂ� 0���̏ꍇ�A��O���X���[����B
        if (l_lisOtherOrgInfoAdminRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�O���A�g�����擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�O���A�g�����擾�ł��܂���B");
        }

        //2-2) �������ʂ� 1���̏ꍇ�A
        //��L�������ʂł��関�g�p�ʔԏ��Params�I�u�W�F�N�g�������ɁA�O���A�g���Ǘ��N���X�̃R���X�g���N�^���R�[������B
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
            (OtherOrgInfoAdminRow)l_lisOtherOrgInfoAdminRows.get(0);

        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);
        //3) ����.is�s���b�N = true �̏ꍇ
        //���������O���A�g���Ǘ��I�u�W�F�N�g.createForUpdateParams( ) ���R�[������B
        if (l_blnIsRowLock)
        {
            l_srvRegiOtherOrgInfoAdmin.createForUpdateParams();
        }

        //4) ���������O���A�g���Ǘ��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (submit�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u����UPDATE���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jsubmit�O���A�g���v�Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} �i�T�[�r�X���p�jsubmit�O���A�g���: <BR>
     * �@@�@@�@@�@@�@@1.2.2�@@get�O���A�g���() = null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_blnIsNewApplyDiv - (�V�K�\���敪)<BR>
     * �V�K�\���敪<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOtherOrgInfo(String, String, String,"
            + " String, Timestamp, Timestamp, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin = null;
        //����.�V�K�\���敪 = true �̏ꍇ�A�ȉ��̏������s���B
        if (l_blnIsNewApplyDiv)
        {
            //get���g�p�ʔԏ��(String, boolean)
            //�T�[�r�X�敪 = ����.�T�[�r�X�敪
            //is�s���b�N = true
            l_srvRegiOtherOrgInfoAdmin =
                this.getUnUseSequenceNumberInfo(l_strServiceDiv, true);

            // set�X�e�[�^�X(String)
            //0�F�g�p��
            l_srvRegiOtherOrgInfoAdmin.setStatus(WEB3OtherOrgStatusDef.USING);

            //set�،���ЃR�[�h(String)
            //�،���ЃR�[�h = ����.�،���ЃR�[�h
            l_srvRegiOtherOrgInfoAdmin.setInstitutionCode(l_strInstitutionCode);

            //set���X�R�[�h(String)
            //���X�R�[�h = ����.���X�R�[�h
            l_srvRegiOtherOrgInfoAdmin.setBranchCode(l_strBranchCode);

            //set�����R�[�h(String)
            //�����R�[�h = ����.�����R�[�h
            l_srvRegiOtherOrgInfoAdmin.setAccountCode(l_strAccountCode);
        }
        else
        {
            //��L�ȊO�̏ꍇ�i����.�V�K�\���敪 = false �̏ꍇ�j
            //get�O���A�g���(String, String, String, String, boolean)
            //�T�[�r�X�敪 = ����.�T�[�r�X�敪
            //�،���ЃR�[�h = ����.�،���ЃR�[�h
            //���X�R�[�h = ����.���X�R�[�h
            //�����R�[�h = ����.�����R�[�h
            //is�s���b�N = false
            l_srvRegiOtherOrgInfoAdmin = this.getOtherOrgInfo(
                l_strServiceDiv,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                false);

            if (l_srvRegiOtherOrgInfoAdmin == null)
            {
                log.debug("�O���A�g�����擾�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���A�g�����擾�ł��܂���B");
            }
        }

        //set�K�p����From(Timestamp)
        //�K�p����From = ����.�K�p�J�n��
        l_srvRegiOtherOrgInfoAdmin.setAppliStartDate(
            new Timestamp(WEB3DateUtility.toDay(l_tsAppliStartDate).getTime()));

        //set�K�p����To(Timestamp)
        //�K�p����To = ����.�K�p�I����
        l_srvRegiOtherOrgInfoAdmin.setAppliEndDate(
            new Timestamp(WEB3DateUtility.toDay(l_tsAppliEndDate).getTime()));

        // save�O���A�g���Ǘ�( )
        l_srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();
    }

    /**
     * (submit�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u����UPDATE���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jsubmit�O���A�g���`�폜�ύX�`�v�Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strApplyLotteryDiv - (�\�����I�敪)<BR>
     * �\�����I�敪<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_blnIsNewApplyDiv - (�V�K�\���敪)<BR>
     * �V�K�\���敪<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strApplyLotteryDiv,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOtherOrgInfo(String, String, String,"
            + " String, String, Timestamp, Timestamp, boolean)";
        log.entering(STR_METHOD_NAME);

        //get�O���A�g���(String, String, String, String, boolean)
        //�T�[�r�X�敪 = ����.�T�[�r�X�敪
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h = ����.���X�R�[�h
        //�����R�[�h = ����.�����R�[�h
        //is�s���b�N = false
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            this.getOtherOrgInfo(
                l_strServiceDiv,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                false);

        //get�O���A�g���() = null �ȊO�̏ꍇ
        if (l_srvRegiOtherOrgInfoAdmin != null)
        {
            if (WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_strApplyLotteryDiv))
            {
                // set�X�e�[�^�X(String)
                //1�F����
                l_srvRegiOtherOrgInfoAdmin.setStatus(WEB3OtherOrgStatusDef.INVALIDITY);

                //save�O���A�g���Ǘ�( )
                l_srvRegiOtherOrgInfoAdmin.saveOtherOrgInfoAdmin();
            }
            else
            {
                //submit�O���A�g���(String, String, String, String, Timestamp, Timestamp, boolean)
                //�V�K�\���敪 = false
                this.submitOtherOrgInfo(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strServiceDiv,
                    l_tsAppliStartDate,
                    l_tsAppliEndDate,
                    false);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
