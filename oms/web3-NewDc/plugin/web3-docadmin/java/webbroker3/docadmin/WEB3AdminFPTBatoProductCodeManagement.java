head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTBatoProductCodeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �d�q�������R�[�h�Ǘ�(WEB3AdminFPTBatoProductCodeManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 ���g (���u) �V�K�쐬 ���f��No.023,No.033
Revision History : 2008/03/04 �g�C�� (���u)�@@�d�l�ύX ���f��No.038�A���f��No.041�A���f��No.051
*/
package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�d�q�������R�[�h�Ǘ�)<BR>
 * �d�q�������R�[�h�Ǘ��N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTBatoProductCodeManagement
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTBatoProductCodeManagement.class);

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    private String branchCode;

    /**
     * (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     */
    private String documentDiv;

    /**
     * (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     */
    private String batoProductCode;

    /**
     * (���X�R�[�h���X�g)<BR>
     * ���X�R�[�h���X�g<BR>
     */
    private String[] branchCodeList;

    /**
     * (�Ǘ���)<BR>
     *  �Ǘ���<BR>
     */
    private WEB3Administrator admin;

    /**
     *(���ʏ��)<BR>
     * ���ʏ��<BR>
     */
    private WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList;

    /**
     *(�����t���O)<BR>
     * �����t���O<BR>
     */
    private String processFlag;

    /**
     * (delete�d�q�������R�[�h�Ǘ�)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u���ւ�DELETE���s���B<BR>
     * <BR>
     * �P�jthis.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j<BR>
     * <BR>
     * �@@�P-�P�j�ȉ������ō폜���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@[�폜����]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���ʋ敪 = this.���ʏ��[idx].���ʋ敪<BR>
     * �@@�@@�@@�@@�@@�d�q�������R�[�h = <BR>
     * �@@�@@�@@�@@�@@�@@this.���ʏ��[idx].���ʎ�ރR�[�h + this.���ʏ��[idx].���ʒʔ� <BR>
     * @@throws WEB3BaseException
     */
    public void deleteBatoProductCodeAdmin() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteBatoProductCodeAdmin()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            //�N�G���v���Z�b�T���擾����B
            l_queryProcessor = Processors.getDefaultProcessor();

            //�폜����������
            StringBuffer l_strDeleteString = new StringBuffer();
            l_strDeleteString.append(" institution_code = ? ");
            l_strDeleteString.append(" and document_div = ? ");
            l_strDeleteString.append(" and bato_product_code = ? ");

            //�폜�����f�[�^�R���e�i
            Object[] l_deleteContainer = new Object[3];
            //�،���ЃR�[�h = this.�،���ЃR�[�h
            l_deleteContainer[0] = this.institutionCode;
            for (int i = 0; i < this.documentUpdateList.length; i++)
            {
                //���ʋ敪 = this.���ʏ��[idx].���ʋ敪
                l_deleteContainer[1] = this.documentUpdateList[i].documentDiv;
                //�d�q�������R�[�h = this.���ʏ��[idx].���ʎ�ރR�[�h + this.���ʏ��[idx].���ʒʔ�
                l_deleteContainer[2] =
                    this.documentUpdateList[i].documentCategory + this.documentUpdateList[i].documentNumber;

                l_queryProcessor.doDeleteAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_strDeleteString.toString(),
                    l_deleteContainer);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�d�q�������R�[�h�Ǘ�)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u����茟�����s���B <BR>
     * <BR>
     * �P�j�@@�ȉ������œd�q�������R�[�h�Ǘ��e�[�u����茟�����s���B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h = this.���X�R�[�h<BR>
     * �@@�@@�@@���ʋ敪 = this.���ʋ敪<BR>
     * �@@�@@�@@�d�q�������R�[�h�R�[�h = this.�d�q�������R�[�h<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂĎ擾����List��ԋp����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getBatoProductManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        List l_lisBatoProductManagementRows = null;
        try
        {
            //�P�j�@@�ȉ������œd�q�������R�[�h�Ǘ��e�[�u����茟�����s���B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[��������]
            //�،���ЃR�[�h = this.�،���ЃR�[�h
            //���X�R�[�h = this.���X�R�[�h
            //���ʋ敪 = this.���ʋ敪
            //�d�q�������R�[�h�R�[�h = this.�d�q�������R�[�h
            String l_strQueryString = " institution_code = ? and branch_code = ?"
                + " and document_div = ? and bato_product_code = ? ";

            Object[] l_queryContainers = {
                this.institutionCode,
                this.branchCode,
                this.documentDiv,
                this.batoProductCode};

            l_lisBatoProductManagementRows = l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
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

        //�Q�j�@@�P�j�ɂĎ擾����List��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisBatoProductManagementRows;
    }

    /**
     * (get�d�q�������R�[�h�Ǘ��i�S���X�j)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u����背�R�[�h����������B<BR>
     * �����l�̂Ȃ��ꍇ�͕ԋp�l�pList�̗v�f��null��ǉ�����B<BR>
     * �i�ԋp�lList�̒����͌������s�����񐔂Ɠ������ƂȂ�j<BR>
     * <BR>
     * <BR>
     * �P�j�ԋp�l�p��List�𐶐�����B<BR>
     * <BR>
     * <BR>
     * �Q�j���X�R�[�h���X�g�̒����̉�Loop���s���B�i�C���f�b�N�X�Findex�j<BR>
     * <BR>
     * �@@�Q-�P�j���X�R�[�h���C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * �@@�@@�@@�@@�@@this.���X�R�[�h = this.���X�R�[�h���X�g[index]<BR>
     * <BR>
     * �@@�Q-�Q�jthis.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j<BR>
     * <BR>
     * �@@�@@�Q-�Q-�P�j���ʋ敪���C���X�^���X�ϐ��ɃZ�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@this.���ʋ敪 = this.���ʏ��[idx].���ʋ敪<BR>
     * <BR>
     * �@@�@@�Q-�Q-�Q�j�d�q�������R�[�h���C���X�^���X�ϐ��ɃZ�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@this.�d�q�������R�[�h = this.���ʏ��[idx].���ʎ�ރR�[�h +<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@this.���ʏ��[idx].���ʒʔ�<BR>
     * <BR>
     * �@@�@@�Q-�Q-�R�j�������s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@this.get�d�q�������R�[�h�Ǘ�() ���R�[������B<BR>
     * <BR>
     * �@@�@@�Q-�R-�S�jget�d�q�������R�[�h�Ǘ�() �̖߂�l���� > 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l��ԋp�l�pList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@get�d�q�������R�[�h�Ǘ�() �̖߂�l���� == 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@null ��ԋp�l�pList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �R�j�ԋp�l�pList��ԋp����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    private List getBatoProductManagementAllBranch() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductManagementAllBranch()";
        log.entering(STR_METHOD_NAME);

        //�P�j �ԋp�l�p��List�𐶐�����B
        List l_lisBatoProductManageMentAllBranch = new ArrayList();

        //�Q�j ���X�R�[�h���X�g�̒����̉�Loop���s���B�i�C���f�b�N�X�Findex�j
        for (int i = 0; i < this.branchCodeList.length; i++)
        {
            //�Q-�P�j ���X�R�[�h���C���X�^���X�ϐ��ɃZ�b�g����B
            //        this.���X�R�[�h = this.���X�R�[�h���X�g[index]
            this.branchCode = this.branchCodeList[i];

            //�Q-�Q�j this.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j
            for (int j = 0; j < this.documentUpdateList.length; j++)
            {
                //�Q-�Q-�P�j ���ʋ敪���C���X�^���X�ϐ��ɃZ�b�g����
                //            this.���ʋ敪 = this.���ʏ��[idx].���ʋ敪
                this.documentDiv = this.documentUpdateList[j].documentDiv;

                //�Q-�Q-�Q�j �d�q�������R�[�h���C���X�^���X�ϐ��ɃZ�b�g����
                //        this.�d�q�������R�[�h =
                //        this.���ʏ��[idx].���ʎ�ރR�[�h + this.���ʏ��[idx].���ʒʔ�
                this.batoProductCode =
                    this.documentUpdateList[j].documentCategory + this.documentUpdateList[j].documentNumber;

                //�Q-�Q-�R�j �������s���B
                //            this.get�d�q�������R�[�h�Ǘ�() ���R�[������B
                List l_lisBatoProductManagements = this.getBatoProductManagement();

                if (l_lisBatoProductManagements.size() > 0)
                {
                    //�Q-�R-�S�j get�d�q�������R�[�h�Ǘ�() �̖߂�l���� > 0 �̏ꍇ�A
                    //�߂�l��ԋp�l�pList�ɒǉ�����B
                    l_lisBatoProductManageMentAllBranch.add(l_lisBatoProductManagements);
                }
                else
                {
                    //get�d�q�������R�[�h�Ǘ�() �̖߂�l���� == 0 �̏ꍇ�A
                    //null ��ԋp�l�pList�ɒǉ�����B
                    l_lisBatoProductManageMentAllBranch.add(null);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        //�R�j �ԋp�l�pList��ԋp����B
        return l_lisBatoProductManageMentAllBranch;
    }

    /**
     * (get�d�q�������R�[�h�Ǘ��ꗗ)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u����背�R�[�h����������B<BR>
     * <BR>
     * �P�jQueryProcessor#doFindAllQuery()���R�[������B<BR>
     * <BR>
     * �@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@rowType�F �d�q�������R�[�h�Ǘ��e�[�u��Row�I�u�W�F�N�g<BR>
     * �@@�@@�@@where�F ����.����������<BR>
     * �@@�@@�@@orderBy�F ����.�\�[�g�L�[<BR>
     * �@@�@@�@@conditions�F null<BR>
     * �@@�@@�@@bindVars�F ����.�����f�[�^�R���e�i<BR>
     * <BR>
     * �Q�j�P�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_strQueryString - (����������)<BR>
     * ����������<BR>
     * @@param l_queryContainers - (�����f�[�^�R���e�i)<BR>
     * �����f�[�^�R���e�i<BR>
     * @@param l_strSortKey - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getBatoProductManagementList(
        String l_strQueryString,
        Object[] l_queryContainers,
        String l_strSortKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductManagementList(String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //�P�j QueryProcessor#doFindAllQuery()���R�[������B
        List l_lisBatoProductManagementRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_strQueryString,
                    l_strSortKey,
                    null,
                    l_queryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTBatoProductCodeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTBatoProductCodeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBatoProductManagementRows;
    }

    /**
     * (get�d�q�������R�[�h�Ǘ��ꗗ)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u������ꗗ���擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�d�q�������R�[�h�Ǘ��e�[�u�����烌�R�[�h���擾<BR>
     * <BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h = this.���X�R�[�h<BR>
     * �@@�@@�@@�@@���ʋ敪 = this.���ʋ敪<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h = this.�d�q�������R�[�h + "%"�i�O����v�j<BR>
     * <BR>
     * �@@�@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h.asc<BR>
     * <BR>
     * �@@�@@�@@���������ʂ��擾�ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�u�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���v��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02999<BR>
     * <BR>
     * <BR>
     * �Q�j�@@ArrayList�I�u�W�F�N�g�̐���<BR>
     * <BR>
     * <BR>
     * �R�j�@@�P�j�Ŏ擾�������R�[�h���ALoop����<BR>
     * <BR>
     * �@@�R�|�P�j�@@�d�q�������R�[�h�Ǘ��e�[�u���s�̎擾<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g�𐶐����A�ȉ��ɃZ�b�g<BR>
     * <BR>
     * �@@�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.�d�q�������R�[�h =<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h�Ǘ��e�[�u���s.get�d�q�������R�[�h()<BR>
     * �@@�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.�L���敪 =<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h�Ǘ��e�[�u���s.get�L���敪() <BR>
     * �@@�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.�E�v = �d�q�������R�[�h�Ǘ��e�[�u���s.�E�v()<BR>
     * �@@�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.���ʎ�ޒʔ� =<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h�Ǘ��e�[�u���s.get�d�q�������R�[�h()�̉E4��<BR>
     * <BR>
     * �@@�R�|�R�j�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g��List��add()<BR>
     * <BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g��d�q�������R�[�h�Ǘ����[]�ɕϊ�<BR>
     * <BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ������l��return<BR>
     * <BR>
     * @@return WEB3FPTBatoProductCodeAdminInfoUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FPTBatoProductCodeAdminInfoUnit[] getBatoProductCodeAdminInfoUnit() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoProductCodeAdminInfoUnit()";
        log.entering(STR_METHOD_NAME);

        List l_lisBatoProductManagementRows = null;
        try
        {
            //�P�j�@@�ȉ������œd�q�������R�[�h�Ǘ��e�[�u����茟�����s���B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[��������]
            //�،���ЃR�[�h = this.�،���ЃR�[�h
            //���X�R�[�h = this.���X�R�[�h
            //���ʋ敪 = this.���ʋ敪
            //�d�q�������R�[�h = this.�d�q�������R�[�h + "%"�i�O����v�j
            String l_strQueryString = " institution_code = ? and branch_code = ?"
                + " and document_div = ? and bato_product_code like ? || '%' ";

            Object[] l_queryContainers = {
                this.institutionCode,
                this.branchCode,
                this.documentDiv,
                this.batoProductCode};

            //[�\�[�g����]
            //�d�q�������R�[�h.asc
            l_lisBatoProductManagementRows = l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
                l_strQueryString,
                " bato_product_code asc ",
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

        //���������ʂ��擾�ł��Ȃ��ꍇ�A
        //�u�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���v��O���X���[����B
        int l_intBatoProductManagementsSize = l_lisBatoProductManagementRows.size();
        if (l_intBatoProductManagementsSize == 0)
        {
            log.debug("�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
        }

        //�Q�j�@@ArrayList�I�u�W�F�N�g�̐���
        List l_lisBatoProductCodeAdminInfoUnits = new ArrayList();

        //�R�j�@@�P�j�Ŏ擾�������R�[�h���ALoop����
        Iterator l_iteratorBatoProductManagementRows =
            l_lisBatoProductManagementRows.iterator();
        while (l_iteratorBatoProductManagementRows.hasNext())
        {
            //�R�|�P�j�@@�d�q�������R�[�h�Ǘ��e�[�u���s�̎擾
            BatoProductManagementRow l_batoProductManagementRow =
                (BatoProductManagementRow)l_iteratorBatoProductManagementRows.next();

            //�R�|�Q�j�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g�𐶐����A�ȉ��ɃZ�b�g
            WEB3FPTBatoProductCodeAdminInfoUnit l_batoProductCodeAdminInfoUnit =
                new WEB3FPTBatoProductCodeAdminInfoUnit();

            //�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.�d�q�������R�[�h = �d�q�������R�[�h�Ǘ��e�[�u���s.get�d�q�������R�[�h()
            String l_strBatoProductCode = l_batoProductManagementRow.getBatoProductCode();
            l_batoProductCodeAdminInfoUnit.batoProductCode = l_strBatoProductCode;

            //�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.�L���敪 = �d�q�������R�[�h�Ǘ��e�[�u���s.get�L���敪()
            l_batoProductCodeAdminInfoUnit.validFlag =
                l_batoProductManagementRow.getValidFlag();

            //�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.�E�v = �d�q�������R�[�h�Ǘ��e�[�u���s.�E�v()
            l_batoProductCodeAdminInfoUnit.remarks =
                l_batoProductManagementRow.getRemarks();

            //�d�q�������R�[�h�Ǘ����I�u�W�F�N�g.���ʎ�ޒʔ� = �d�q�������R�[�h�Ǘ��e�[�u���s.get�d�q�������R�[�h()�̉E4��
            int l_intBatoProductCode = l_strBatoProductCode.length();
            l_batoProductCodeAdminInfoUnit.documentCategoryNumber =
                l_batoProductManagementRow.getBatoProductCode().substring(l_intBatoProductCode - 4);

            //�R�|�R�j�@@�d�q�������R�[�h�Ǘ����I�u�W�F�N�g��List��add()
            l_lisBatoProductCodeAdminInfoUnits.add(l_batoProductCodeAdminInfoUnit);
        }

        //�S�j�@@ArrayList�I�u�W�F�N�g��d�q�������R�[�h�Ǘ����[]�ɕϊ�
        WEB3FPTBatoProductCodeAdminInfoUnit[] l_batoProductCodeAdminInfoUnits =
            new WEB3FPTBatoProductCodeAdminInfoUnit[l_lisBatoProductCodeAdminInfoUnits.size()];
        l_lisBatoProductCodeAdminInfoUnits.toArray(l_batoProductCodeAdminInfoUnits);

        //�T�j�@@�S�j�ŕϊ������l��return
        log.exiting(STR_METHOD_NAME);
        return l_batoProductCodeAdminInfoUnits;
    }

    /**
     * (insert�d�q�������R�[�h�Ǘ�)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u���ւ�INSERT���s���B<BR>
     * <BR>
     * �P�j���X�R�[�h���X�g�̒����̉�Loop���s���B�i�C���f�b�N�X�Findex�j<BR>
     * <BR>
     * �@@�P-�P�jthis.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j<BR>
     * <BR>
     * �@@�@@�P-�P-�P�j�d�q�������R�[�h�Ǘ��e�[�u����Params�𐶐����A�ȉ����e���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@���X�R�[�h = this.���X�R�[�h���X�g[index]<BR>
     * �@@�@@�@@�@@�@@�@@�@@���ʋ敪 = this.���ʏ��[idx].���ʋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�d�q�������R�[�h = this.���ʏ��[idx].���ʎ�ރR�[�h + <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ʏ��[idx].���ʒʔ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h = this.�Ǘ���.get�Ǘ��҃R�[�h() �̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�쐬���t = ���ݓ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�X�V���t = ���ݓ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�L���敪 = this.���ʏ��[idx].�L���敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�v = this.���ʏ��[idx].�E�v<BR>
     * <BR>
     * �@@�@@�P-�Q-�Q�j�d�q�������R�[�h�Ǘ��e�[�u����Params�̓��e��INSERT���s���B<BR>
     * @@throws WEB3BaseException
     */
    public void insertBatoProductManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            //�N�G���v���Z�b�T���擾����B
            l_queryProcessor = Processors.getDefaultProcessor();

            //�P�j ���X�R�[�h���X�g�̒����̉�Loop���s���B�i�C���f�b�N�X�Findex�j
            for (int i = 0; i < this.branchCodeList.length; i++)
            {
                //�P-�P�j this.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j
                for (int j = 0; j < this.documentUpdateList.length; j++)
                {
                    //�P-�P-�P�j �d�q�������R�[�h�Ǘ��e�[�u����Params�𐶐����A�ȉ����e���Z�b�g����B
                    BatoProductManagementParams l_batoProductManagementParams =
                        new BatoProductManagementParams();

                    //�،���ЃR�[�h = this.�،���ЃR�[�h
                    l_batoProductManagementParams.setInstitutionCode(this.institutionCode);
                    //���X�R�[�h = this.���X�R�[�h���X�g[index]
                    l_batoProductManagementParams.setBranchCode(this.branchCodeList[i]);
                    //���ʋ敪 = this.���ʏ��[idx].���ʋ敪
                    l_batoProductManagementParams.setDocumentDiv(this.documentUpdateList[j].documentDiv);
                    //�d�q�������R�[�h = this.���ʏ��[idx].���ʎ�ރR�[�h + this.���ʏ��[idx].���ʒʔ�
                    l_batoProductManagementParams.setBatoProductCode(
                        this.documentUpdateList[j].documentCategory + this.documentUpdateList[j].documentNumber);
                    //�X�V�҃R�[�h = this.�Ǘ���.get�Ǘ��҃R�[�h() �̖߂�l
                    l_batoProductManagementParams.setLastUpdater(this.admin.getAdministratorCode());
                    //�쐬���t = ���ݓ���
                    Date l_datNowTime = GtlUtils.getSystemTimestamp();
                    l_batoProductManagementParams.setCreatedTimestamp(l_datNowTime);
                    //�X�V���t = ���ݓ���
                    l_batoProductManagementParams.setLastUpdatedTimestamp(l_datNowTime);
                    //�L���敪 = this.���ʏ��[idx].�L���敪
                    l_batoProductManagementParams.setValidFlag(this.documentUpdateList[j].validFlag);
                    //�E�v = this.���ʏ��[idx].�E�v
                    l_batoProductManagementParams.setRemarks(this.documentUpdateList[j].remarks);

                    //�P-�Q-�Q�j �d�q�������R�[�h�Ǘ��e�[�u����Params�̓��e��INSERT���s���B
                    l_queryProcessor.doInsertQuery(l_batoProductManagementParams);
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u���ɓd�q�������R�[�h�����݂��邩���肷��B<BR>
     * <BR>
     * <BR>
     * �P�j�@@this.get�d�q�������R�[�h�Ǘ�()���R�[������B<BR>
     * <BR>
     * <BR>
     * �Q�j �P�j�̖߂�l���� > 0 �̏ꍇ true�A�P�j�̖߂�l���� == 0 �̏ꍇ false ��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isBatoProductCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isBatoProductCode()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.get�d�q�������R�[�h�Ǘ�()���R�[������B
        List l_lisBatoProductManagements = this.getBatoProductManagement();
        int l_intBatoProductManagementsSize = l_lisBatoProductManagements.size();

        //�Q�j �P�j�̖߂�l���� > 0 �̏ꍇ true�A�P�j�̖߂�l���� == 0 �̏ꍇ false ��ԋp����B
        if (l_intBatoProductManagementsSize > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (set��Е��X���)<BR>
     * this.�Ǘ��҂������Ă���،���ЃR�[�h�y�я،���Ђ̑S���X�R�[�h�̃��X�g��ݒ肷��B<BR>
     * <BR>
     * �P�j�Ǘ��҂��A�،���ЃR�[�h���擾���Athis.�،���ЃR�[�h �� �Z�b�g����B<BR>
     * �@@�@@�@@this.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h() �̖߂�l<BR>
     * <BR>
     * �Q�j�Ǘ��҂��،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@this.�Ǘ���.get�،����()<BR>
     * <BR>
     * �R�j�،���ЃI�u�W�F�N�g��蕔�X�I�u�W�F�N�g�z����擾����B<BR>
     * �@@�@@�@@�،����.getBranches()<BR>
     * <BR>
     * �S�jList�𐶐����A���X�I�u�W�F�N�g�z��̑S�Ă̗v�f���畔�X�R�[�h���擾���AList�ɒǉ�����B<BR>
     * �@@�@@�@@���X[index].getBranchCode()<BR>
     * <BR>
     * �T�jList��z��ɕϊ����Athis.���X�R�[�h���X�g �փZ�b�g����B<BR>
     * @@throws WEB3BaseException
     */
    private void setInstBranchInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setInstBranchInfo()";
        log.entering(STR_METHOD_NAME);

        //�P�j �Ǘ��҂��A�،���ЃR�[�h���擾���Athis.�،���ЃR�[�h �� �Z�b�g����B
        //    this.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h() �̖߂�l
        this.institutionCode = this.admin.getInstitutionCode();

        //�Q�j �Ǘ��҂��،���ЃI�u�W�F�N�g���擾����B
        //       this.�Ǘ���.get�،����()
        Institution l_institution = this.admin.getInstitution();

        //�R�j �،���ЃI�u�W�F�N�g��蕔�X�I�u�W�F�N�g�z����擾����B
        //      �،����.getBranches()
        Branch[] l_branchs = l_institution.getBranches();

        //�S�j List�𐶐����A���X�I�u�W�F�N�g�z��̑S�Ă̗v�f
        //    ���畔�X�R�[�h���擾���AList�ɒǉ�����B
        List l_lisBranchs = new ArrayList();
        for (int i = 0; i < l_branchs.length; i++)
        {
            //     ���X[index].getBranchCode()
            l_lisBranchs.add(l_branchs[i].getBranchCode());
        }

        //�T�j List��z��ɕϊ����Athis.���X�R�[�h���X�g �փZ�b�g����B
        String[] l_branchCodes = new String[l_lisBranchs.size()];
        l_lisBranchs.toArray(l_branchCodes);
        this.branchCodeList = l_branchCodes;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�d�q�������R�[�h�Ǘ�)<BR>
     * �d�q�������R�[�h�Ǘ��e�[�u���ւ�UPDATE���s���B<BR>
     * <BR>
     * �P�j���X�R�[�h���C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * �@@�@@�@@�@@�@@this.���X�R�[�h = this.�Ǘ���.get���X�R�[�h()<BR>
     * <BR>
     * �Q�jthis.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j<BR>
     * <BR>
     * �@@�Q-�P�j���ʋ敪���C���X�^���X�ϐ��ɃZ�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@this.���ʋ敪 = this.���ʏ��[idx].���ʋ敪<BR>
     * <BR>
     * �@@�Q-�Q�j�d�q�������R�[�h���C���X�^���X�ϐ��ɃZ�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@this.�d�q�������R�[�h = this.���ʏ��[idx].���ʎ�ރR�[�h +<BR>
     * �@@�@@�@@�@@�@@�@@�@@this.���ʏ��[idx].���ʒʔ�<BR>
     * <BR>
     * �@@�Q-�R�j�������s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@this.get�d�q�������R�[�h�Ǘ�() ���R�[������B<BR>
     * <BR>
     * �@@�@@�Q-�R-�P�jget�d�q�������R�[�h�Ǘ�()�̖߂�l�̒��� = 0 �̏ꍇ�A<BR>
     * �@@�u�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B�v��O���X���[����B<BR>
     * <BR>
     * �@@�@@�Q-�R-�Q�jget�d�q�������R�[�h�Ǘ�()�̖߂�l�̒��� > 0 �̏ꍇ�A�L���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@�Q-�R-�Q-�P�j�擾�����L���敪 == this.���ʏ��[idx].�L���敪 �̏ꍇ�Acontinue<BR>
     * <BR>
     * �@@�@@�@@�Q-�R-�Q-�Q�j�擾�����L���敪 != this.���ʏ��[idx].�L���敪 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ����e�ōX�V���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[�X�V�l]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�L���敪 = this.���ʏ��[idx].�L���敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V�҃R�[�h = this.�Ǘ���.get�Ǘ��҃R�[�h() �̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[�X�V����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ʋ敪 = this.���ʋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�d�q�������R�[�h = this.�d�q�������R�[�h<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     */
    public void updateBatoProductManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateBatoProductManagement()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        try
        {
            //�N�G���v���Z�b�T���擾����B
            l_queryProcessor = Processors.getDefaultProcessor();

            //�P�j ���X�R�[�h���C���X�^���X�ϐ��ɃZ�b�g����B
            //          this.���X�R�[�h = this.�Ǘ���.get���X�R�[�h()
            this.branchCode = this.admin.getBranchCode();

            //�Q�j this.���ʏ��̒����̉�Loop���s���B�i�C���f�b�N�X�Fidx�j
            for (int i = 0; i < this.documentUpdateList.length; i++)
            {
                //�Q-�P�j ���ʋ敪���C���X�^���X�ϐ��ɃZ�b�g����
                //             this.���ʋ敪 = this.���ʏ��[idx].���ʋ敪
                this.documentDiv = this.documentUpdateList[i].documentDiv;
                //�Q-�Q�j �d�q�������R�[�h���C���X�^���X�ϐ��ɃZ�b�g����
                //  this.�d�q�������R�[�h = this.���ʏ��[idx].���ʎ�ރR�[�h + this.���ʏ��[idx].���ʒʔ�
                this.batoProductCode =
                    this.documentUpdateList[i].documentCategory + this.documentUpdateList[i].documentNumber;
                //�Q-�R�j �������s���B
                //   this.get�d�q�������R�[�h�Ǘ�() ���R�[������B
                List l_lisBatoProductManagements = this.getBatoProductManagement();

                //�Q-�R-�P�j get�d�q�������R�[�h�Ǘ�()�̖߂�l�̒��� = 0 �̏ꍇ�A
                //  �u�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B�v��O���X���[����B
                if (l_lisBatoProductManagements.size() == 0)
                {
                    log.debug("�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03038,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B");
                }
                else
                {
                    //�Q-�R-�Q�j get�d�q�������R�[�h�Ǘ�()�̖߂�l�̒��� > 0 �̏ꍇ�A�L���敪���擾����B
                    BatoProductManagementRow l_batoProductManagementRow =
                        (BatoProductManagementRow)l_lisBatoProductManagements.get(0);
                    String l_strValidFlag = l_batoProductManagementRow.getValidFlag();
                    //�Q-�R-�Q-1�j �擾�����L���敪 != this.���ʏ��[idx].�L���敪 �̏ꍇ�A
                    //�ȉ����e�ōX�V���s���B
                    if (!l_strValidFlag.equals(this.documentUpdateList[i].validFlag))
                    {
                        Map l_updateMap = new HashMap();
                        //[�X�V�l]
                        // �L���敪 = this.���ʏ��[idx].�L���敪
                        l_updateMap.put("valid_flag", this.documentUpdateList[i].validFlag);
                        // �X�V�҃R�[�h = this.�Ǘ���.get�Ǘ��҃R�[�h() �̖߂�l
                        l_updateMap.put("last_updater", this.admin.getAdministratorCode());
                        // �X�V���t = ���ݓ���
                        l_updateMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

                        //[�X�V����]
                        StringBuffer l_sbQueryString = new StringBuffer();
                        Object[] l_queryDatas = new Object[3];
                        //  �،���ЃR�[�h = this.�،���ЃR�[�h
                        l_sbQueryString.append(" institution_code = ? ");
                        l_queryDatas[0] = this.institutionCode;
                        //  ���ʋ敪 = this.���ʋ敪
                        l_sbQueryString.append(" and document_div = ? ");
                        l_queryDatas[1] = this.documentDiv;
                        //  �d�q�������R�[�h = this.�d�q�������R�[�h
                        l_sbQueryString.append(" and bato_product_code = ? ");
                        l_queryDatas[2] = this.batoProductCode;

                        l_queryProcessor.doUpdateAllQuery(
                            BatoProductManagementRow.TYPE,
                            l_sbQueryString.toString(),
                            l_queryDatas,
                            l_updateMap);
                    }
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�d�q�������R�[�h�Ǘ��s)<BR>
     * �C���X�^���X�ϐ��֐ݒ�ς݂̌��������œd�q�������R�[�h�Ǘ��e�[�u�����������A<BR>
     * �X�V�Ώۃ��R�[�h�̊e��`�F�b�N���s���B<BR>
     * <BR>
     * �P�j���������Ń��R�[�h���擾����B<BR>
     * �@@�@@this.get�d�q�������R�[�h�Ǘ��i�S���X�j()���R�[������B<BR>
     * <BR>
     * <BR>
     * �Q�jget�d�q�������R�[�h�Ǘ��i�S���X�j() �̖߂�lList�����̉�Loop���s���B<BR>
     * <BR>
     * �@@�Q-�P�jthis.�����t���O == 0�i�o�^�j && �߂�lList[index] != null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�쐬���̏��ʎ�ނ̏��ʒʔԂ͊��ɓo�^����Ă��܂��B�v��O���X���[����B<BR>
     * <BR>
     * �@@�Q-�Q�jthis.�����t���O == (1 || 2)�i�X�V�A�폜�j && �߂�lList[index] == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B�v��O���X���[����B<BR>
     * <BR>
     * �@@�Q-�R�jthis.�����t���O == 2�i�폜�j && �߂�lList[index].get�L���敪 == 0�ivalid�j �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�L���ȏ��ʎ�ނ����݂���ׁA�폜�ł��܂���B�v��O���X���[����B<BR>
     * <BR>
     * <BR>
     * �R�jthis.�����t���O == 2�i�폜�j�̏ꍇ�A��t�����m�F���s���B<BR>
     * <BR>
     * �@@�R-�P�jthis.���ʏ��̒����̉�Loop���s���B<BR>
     * <BR>
     * �@@�@@�R-�P-�P�j���ʌ�t�Ǘ��e�[�u���֌������s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���ʌ�t�Ǘ�.get���ʌ�t�Ǘ�()���R�[������<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���ʋ敪�F this.���ʏ��[idx].���ʋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�d�q�������R�[�h�F this.���ʏ��[idx].���ʎ�ރR�[�h + <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.���ʏ��[idx].���ʒʔ�<BR>
     * �@@�@@�@@�@@�@@�@@���ʎ�ރR�[�h�F this.���ʏ��[idx].���ʎ�ރR�[�h<BR>
     * <BR>
     * <BR>
     * �@@�@@�R-�P-�Q�jget���ʌ�t�Ǘ�() �̖߂�l���� > 0 �̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�@@�u��t���������݂���ׁA�폜�ł��܂���B�v��O���X���[����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateBatoProductManagementParams() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBatoProductManagementParams()";
        log.entering(STR_METHOD_NAME);

        //�P�j ���������Ń��R�[�h���擾����B
        //this.get�d�q�������R�[�h�Ǘ��i�S���X�j()���R�[������B
        List l_lisBatoProductManagementAllBranchs = this.getBatoProductManagementAllBranch();

        //�Q�j get�d�q�������R�[�h�Ǘ��i�S���X�j() �̖߂�lList�����̉�Loop���s���B
        int l_intBatoProductManagementAllBranchCnt = l_lisBatoProductManagementAllBranchs.size();
        for (int i = 0; i < l_intBatoProductManagementAllBranchCnt; i++)
        {
            // �Q-�P�j this.�����t���O == 0�i�o�^�j && �߂�lList[index] != null �̏ꍇ�A
            //  �u�쐬���̏��ʎ�ނ̏��ʒʔԂ͊��ɓo�^����Ă��܂��B�v��O���X���[����B
            if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(this.processFlag)
                && l_lisBatoProductManagementAllBranchs.get(i) != null)
            {
                log.debug("�쐬���̏��ʎ�ނ̏��ʒʔԂ͊��ɓo�^����Ă��܂��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�쐬���̏��ʎ�ނ̏��ʒʔԂ͊��ɓo�^����Ă��܂��B");
            }

            //�Q-�Q�j this.�����t���O == (1 || 2)�i�X�V�A�폜�j && �߂�lList[index] == null �̏ꍇ�A
            //    �u�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B�v��O���X���[����B
            if ((WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.processFlag)
                || WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.processFlag))
                && l_lisBatoProductManagementAllBranchs.get(i) == null)
            {
                log.debug("�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03038,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�V�E�폜�Ώۂ̏��ʎ�ނ̏��ʒʔԂ͊��ɍ폜����Ă��܂��B");
            }

            //�Q-�R�j  this.�����t���O == 2�i�폜�j && �߂�lList[index].get�L���敪 == 0�ivalid�j �̏ꍇ�A
            //  �u�L���ȏ��ʎ�ނ����݂���ׁA�폜�ł��܂���B�v��O���X���[����B
            if (WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.processFlag))
            {
                BatoProductManagementRow l_batoProductManagementRow =
                    (BatoProductManagementRow)((List)l_lisBatoProductManagementAllBranchs.get(i)).get(0);
                if (WEB3EffectiveDivDef.EFFECTIVE.equals(l_batoProductManagementRow.getValidFlag()))
                {
                    log.debug("�L���ȏ��ʎ�ނ����݂���ׁA�폜�ł��܂���B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03039,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�L���ȏ��ʎ�ނ����݂���ׁA�폜�ł��܂���B");
                }
            }
        }

        //�R�j this.�����t���O == 2�i�폜�j�̏ꍇ�A��t�����m�F���s���B
        if (WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.processFlag))
        {
            //�R-�P�j this.���ʏ��̒����̉�Loop���s���B
            for (int i = 0; i < this.documentUpdateList.length; i++)
            {
                //���ʌ�t�Ǘ�.get���ʌ�t�Ǘ�()���R�[������
                WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
                    new WEB3AdminFPTDocDeliveryManagement();
                List l_lisDocDivManagements = l_adminFPTDocDeliveryManagement.getDocDivManagement(
                    this.institutionCode,
                    this.documentUpdateList[i].documentDiv,
                    this.documentUpdateList[i].documentCategory + this.documentUpdateList[i].documentNumber,
                    this.documentUpdateList[i].documentCategory);

                //�R-�P-�Q�j get���ʌ�t�Ǘ�() �̖߂�l���� > 0 �̏ꍇ�A
                //  �u��t���������݂���ׁA�폜�ł��܂���B�v��O���X���[����B
                if (l_lisDocDivManagements.size() > 0)
                {
                    log.debug("��t���������݂���ׁA�폜�ł��܂���B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03040,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��t���������݂���ׁA�폜�ł��܂���B");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�d�q�������R�[�h�Ǘ�)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * this.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * this.���X�R�[�h = ����.���X�R�[�h<BR>
     * this.���ʋ敪 = ����.���ʋ敪<BR>
     * this.�d�q�������R�[�h = ����.�d�q�������R�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strDocumentDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_strBatoProductCode - (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     */
    public WEB3AdminFPTBatoProductCodeManagement(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strDocumentDiv,
        String l_strBatoProductCode)
    {
        this.institutionCode = l_strInstitutionCode;
        this.branchCode = l_strBranchCode;
        this.documentDiv = l_strDocumentDiv;
        this.batoProductCode = l_strBatoProductCode;
    }

    /**
     * (�d�q�������R�[�h�Ǘ�)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3AdminFPTBatoProductCodeManagement()
    {

    }

    /**
     * (�d�q�������R�[�h�Ǘ�)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�C���X�^���X�ϐ��ֈ������Z�b�g����B<BR>
     * �@@�@@�@@this.�Ǘ��� = ����.�Ǘ���<BR>
     * �@@�@@�@@this.���ʏ�� = ����.���ʏ��<BR>
     * �@@�@@�@@this.�����t���O = ����.�����t���O<BR>
     * <BR>
     * �Q�jthis.set��Е��X���()���R�[������B<BR>
     * <BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_documentUpdateLists - (���ʏ��)<BR>
     * ���ʏ��<BR>
     * @@param l_strProcessFlag - (�����t���O)<BR>
     * �����t���O<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AdminFPTBatoProductCodeManagement(
        WEB3Administrator l_admin,
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateLists,
        String l_strProcessFlag) throws WEB3BaseException
    {
        //�P�j �C���X�^���X�ϐ��ֈ������Z�b�g����B
        //  this.�Ǘ��� = ����.�Ǘ���
        this.admin = l_admin;
        //  this.���ʏ�� = ����.���ʏ��
        this.documentUpdateList = l_documentUpdateLists;
        // this.�����t���O = ����.�����t���O
        this.processFlag = l_strProcessFlag;

        //�Q�j this.set��Е��X���()���R�[������B
        this.setInstBranchInfo();
    }
}
@
