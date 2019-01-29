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
filename	WEB3AdminFPTDocDivManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʋ敪�Ǘ�(WEB3AdminFPTDocDivManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 �����q (���u) �V�K�쐬
Revision History : 2008/01/28 ���g (���u) ���f��No.023,No.031
*/

package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.gentrade.data.DocDivManagementPK;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ʋ敪�Ǘ�)<BR>
 * ���ʋ敪�Ǘ��N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminFPTDocDivManagement
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDivManagement.class);

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
    private String docDiv;

    /**
     * (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     */
    private String documentCategory;

    /**
     * (���ʋ敪�Ǘ�)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * this.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * this.���X�R�[�h = ����.���X�R�[�h<BR>
     * this.���ʋ敪 = ����.���ʋ敪<BR>
     * this.���ʎ�ރR�[�h = ����.���ʎ�ރR�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strDocDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     * @@roseuid 46F3ADDE007E
     */
    public WEB3AdminFPTDocDivManagement(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strDocDiv,
        String l_strDocumentCategory)
    {
        final String STR_METHOD_NAME = "WEB3FPTDocumentDivAdmin(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //this.�،���ЃR�[�h = ����.�،���ЃR�[�h
        this.institutionCode = l_strInstitutionCode;

        //this.���X�R�[�h = ����.���X�R�[�h
        this.branchCode = l_strBranchCode;

        //this.���ʋ敪 = ����.���ʋ敪
        this.docDiv = l_strDocDiv;

        //this.���ʎ�ރR�[�h = ����.���ʎ�ރR�[�h
        this.documentCategory = l_strDocumentCategory;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (���ʋ敪�Ǘ�)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 46F391F6001F
     */
    public WEB3AdminFPTDocDivManagement()
    {

    }

    /**
     * (get���ʋ敪�Ǘ��e�[�u���s)<BR>
     * ���ʋ敪�Ǘ��e�[�u����茟�����s���B<BR>
     * <BR>
     * �P�j�@@�ȉ������ŏ��ʋ敪�Ǘ��e�[�u����茟�����s���B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h = this.���X�R�[�h<BR>
     * �@@�@@�@@���ʋ敪 = this.���ʋ敪<BR>
     * �@@�@@�@@���ʎ�ރR�[�h = this.���ʎ�ރR�[�h<BR>
     * <BR>
     * �Q�j�@@�������ʂ��擾�ł����ꍇ�A�擾�������ʋ敪�Ǘ��e�[�u���s��ԋp����B<BR>
     * <BR>
     * �R�j�@@�������ʂ��擾�ł��Ȃ������ꍇ�A��O�𐶐�����B<BR>
     * @@throws WEB3BaseException
     * @@return DocDivManagementRow
     * @@roseuid 46F3AEF701C9
     */
    public DocDivManagementRow getDocDivManagementParams() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagementParams()";
        log.entering(STR_METHOD_NAME);

        DocDivManagementRow l_docDeliveryManagementRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            DocDivManagementPK l_docDivManagementPK =
                new DocDivManagementPK(this.institutionCode, this.branchCode, this.docDiv, this.documentCategory);

            // �ȉ������ŏ��ʋ敪�Ǘ��e�[�u����茟�����s���B
            l_docDeliveryManagementRow =
                (DocDivManagementRow)l_queryProcessor.doFindByPrimaryKeyQuery(
                    l_docDivManagementPK);
        }
        catch (DataFindException l_ex)
        {
            // �������ʂ��擾�ł��Ȃ������ꍇ�A��O�𐶐�����B
            log.debug("�������ʂȂ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01279,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂȂ��B");
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

        // �Q�j�@@�������ʂ��擾�ł����ꍇ�A�擾�������ʋ敪�Ǘ��e�[�u���s��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_docDeliveryManagementRow;
    }

    /**
     * (get���ʋ敪�Ǘ��ꗗ)<BR>
     * ���ʋ敪�Ǘ��e�[�u������،���Ж��Ɉ������ʋ敪���擾����B<BR>
     * <BR>
     * <BR>
     * <BR>
     * �P�j�@@���ʋ敪�Ǘ��e�[�u�����烌�R�[�h���擾<BR>
     * <BR>
     * [��������]<BR>
     * �،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * ���X�R�[�h = this.���X�R�[�h<BR>
     * <BR>
     * [�\�[�g����]<BR>
     * ���ʋ敪.asc<BR>
     * <BR>
     * ���������ʂ��擾�ł��Ȃ��ꍇ�A�u���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���v��O���X���[����B<BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@BUSINESS_ERROR_02998<BR>
     * <BR>
     * �Q�j�@@ArrayList�I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �R�j�@@�P�j�Ŏ擾�������R�[�h���ALoop����<BR>
     * <BR>
     * �@@�R�|�P�j�@@���ʋ敪�Ǘ��e�[�u���s�̎擾<BR>
     * <BR>
     * �@@�R-�Q�j�@@���ʎ�ޖ��̂̎擾<BR>
     * �@@�@@�@@�@@�@@�@@���ʎ�ފǗ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h = this.���X�R�[�h��v�f�Ƃ�������1��String�z��<BR>
     * �@@�@@�@@�@@�@@�@@���ʎ�ރR�[�h = ���ʋ敪�Ǘ��e�[�u���s.get���ʎ�ރR�[�h()<BR>
     * <BR>
     * �@@ �R-�R�j ���ʋ敪�Ǘ����I�u�W�F�N�g�𐶐����A�ȉ��ɃZ�b�g<BR>
     * �@@�@@���ʋ敪�Ǘ����I�u�W�F�N�g.���ʋ敪 = <BR>
     * �@@�@@�@@�@@���ʋ敪�Ǘ��e�[�u���s.get���ʋ敪<BR>
     * �@@�@@���ʋ敪�Ǘ����I�u�W�F�N�g.���ʖ��� = <BR>
     * �@@�@@�@@�@@���ʋ敪�Ǘ��e�[�u���s.get���ʖ���<BR>
     * �@@�@@���ʋ敪�Ǘ����I�u�W�F�N�g.���ʃ`�F�b�N�敪 = <BR>
     * �@@�@@�@@�@@���ʋ敪�Ǘ��e�[�u���s.get���ʃ`�F�b�N�敪<BR>
     * �@@�@@���ʋ敪�Ǘ����I�u�W�F�N�g.���ʎ�ރR�[�h = <BR>
     * �@@�@@�@@�@@���ʋ敪�Ǘ��e�[�u���s.get���ʎ�ރR�[�h()<BR>
     * �@@�@@���ʋ敪�Ǘ����I�u�W�F�N�g.���ʎ�ޖ��� = <BR>
     * �@@�@@�@@�@@���ʎ�ފǗ�#get���ʎ�ޖ���() �̖߂�l<BR>
     * <BR>
     * �@@�R�|�S�j�@@���ʋ敪�Ǘ����I�u�W�F�N�g��List��add()<BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g�����ʋ敪�Ǘ����[]�ɕϊ�<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ������l��return<BR>
     * @@throws WEB3BaseException
     * @@return WEB3FPTDocumentDivAdminInfoUnit[]
     * @@roseuid 46F39433004C
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] getDocDivManagementLists() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagementLists()";
        log.entering(STR_METHOD_NAME);

        //�P)�ꎞ�ۑ��p��List���쐬�B
        List l_lisReturns = new ArrayList();
        List l_lisDocDivManagementLists = new ArrayList();

        // �@@[��������]
        // �@@�،���ЃR�[�h = this.�،���ЃR�[�h
        // �@@���X�R�[�h = this.���X�R�[�h
        String l_strQueryCondition = " institution_code = ? and branch_code = ? ";
        String l_strSortCond = " document_div asc";

        Object[] l_objBindVars = new Object[2];
        l_objBindVars[0] = this.institutionCode;
        l_objBindVars[1] = this.branchCode;

        try
        {
            // �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDocDivManagementLists = l_queryProcessor.doFindAllQuery(
                DocDivManagementRow.TYPE,
                l_strQueryCondition,
                l_strSortCond,
                null,
                l_objBindVars);
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

        //���������ʂ��擾�ł��Ȃ��ꍇ�A�u���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���v��O���X���[����B
        if (l_lisDocDivManagementLists.isEmpty())
        {
            log.debug("���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02998,
                WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                "���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
        }

        int l_intSize = l_lisDocDivManagementLists.size();
        // �R�j�@@�P�j�Ŏ擾�������R�[�h���ALoop����
        for (int i = 0; i < l_intSize; i++)
        {
            // �R�|�P�j�@@���ʋ敪�Ǘ��e�[�u���s�̎擾
            DocDivManagementRow l_docDivManagementRow =
                (DocDivManagementRow)l_lisDocDivManagementLists.get(i);
            // �@@�R-�Q�j  ���ʎ�ޖ��̂̎擾�I�u�W�F�N�g�̐���
            //�،���ЃR�[�h = this.�،���ЃR�[�h
            //���X�R�[�h = this.���X�R�[�h��v�f�Ƃ�������1��String�z��
            //���ʎ�ރR�[�h = ���ʋ敪�Ǘ��e�[�u���s.get���ʎ�ރR�[�h()
            String[] l_strBranchCodes = new String[1];
            l_strBranchCodes[0] = this.branchCode;
            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement(
                    this.institutionCode,
                    l_strBranchCodes,
                    l_docDivManagementRow.getDocumentCategory());

            //�R-�R�j ���ʋ敪�Ǘ����I�u�W�F�N�g�𐶐����A�ȉ��ɃZ�b�g
            WEB3FPTDocumentDivAdminInfoUnit l_fptDocumentDivAdminInfoUnit =
                new WEB3FPTDocumentDivAdminInfoUnit();

            // ���ʋ敪�Ǘ����I�u�W�F�N�g.���ʋ敪 = ���ʋ敪�Ǘ��e�[�u���s.get���ʋ敪
            l_fptDocumentDivAdminInfoUnit.documentDiv = l_docDivManagementRow.getDocumentDiv();

            // ���ʋ敪�Ǘ����I�u�W�F�N�g.���ʖ��� = ���ʋ敪�Ǘ��e�[�u���s.get���ʖ���
            l_fptDocumentDivAdminInfoUnit.documentNames = l_docDivManagementRow.getDocumentName();

            // ���ʋ敪�Ǘ����I�u�W�F�N�g.���ʃ`�F�b�N�敪 = ���ʋ敪�Ǘ��e�[�u���s.get���ʃ`�F�b�N�敪
            l_fptDocumentDivAdminInfoUnit.docuCheckDiv = l_docDivManagementRow.getDocumentCheckDiv();

            //���ʋ敪�Ǘ����I�u�W�F�N�g.���ʎ�ރR�[�h = ���ʋ敪�Ǘ��e�[�u���s.get���ʎ�ރR�[�h()
            l_fptDocumentDivAdminInfoUnit.documentCategory =
                l_docDivManagementRow.getDocumentCategory();

            //���ʋ敪�Ǘ����I�u�W�F�N�g.���ʎ�ޖ��� = ���ʎ�ފǗ�#get���ʎ�ޖ���() �̖߂�l
            l_fptDocumentDivAdminInfoUnit.documentCategoryName =
                l_docCategoryManagement.getDocumentCateName();

            // �R�|�S�j�@@���ʋ敪�Ǘ����I�u�W�F�N�g��List��add()
            l_lisReturns.add(l_fptDocumentDivAdminInfoUnit);
        }

        // �S�j�@@ArrayList�I�u�W�F�N�g�����ʋ敪�Ǘ����[]�ɕϊ�
        WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits =
            new WEB3FPTDocumentDivAdminInfoUnit[l_lisReturns.size()];
        l_lisReturns.toArray(l_fptDocumentDivAdminInfoUnits);

        // �T�j�@@�S�j�ŕϊ������l��return
        log.entering(STR_METHOD_NAME);
        return l_fptDocumentDivAdminInfoUnits;
    }
}
@
