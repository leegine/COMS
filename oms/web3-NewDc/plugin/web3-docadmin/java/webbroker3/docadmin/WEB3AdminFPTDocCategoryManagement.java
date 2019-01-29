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
filename	WEB3AdminFPTDocCategoryManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʎ�ފǗ�(WEB3AdminFPTDocCategoryManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 ���g (���u) �V�K�쐬 ���f��No.023,No.029,No.031
*/
package webbroker3.docadmin;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ʎ�ފǗ�)<BR>
 * ���ʎ�ފǗ��N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTDocCategoryManagement
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocCategoryManagement.class);

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�z��<BR>
     */
    private String[] branchCode;

    /**
     * (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     */
    private String documentCategory;

    /**
     * (get���ʎ�ޖ���)<BR>
     * ���ʎ�ފǗ��e�[�u����菑�ʎ�ޖ��̂��擾����B<BR>
     * <BR>
     * �P�j this.get���ʎ�ފǗ�()���R�[������B<BR>
     * <BR>
     * �Q�j �P�j �ɂĎ擾����List�̒��� > 0 �̏ꍇ�A�擾�����s��菑�ʎ�ޖ��̂�ԋp����B<BR>
     * �@@�@@�@@�i���ʎ�ޖ��̂�null�̏ꍇ��null��ԋp����B�j<BR>
     * �@@�@@�@@List�̒��� == 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�u���ʎ�ފǗ��e�[�u���Ƀ��R�[�h�����݂��܂���B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03000<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getDocumentCateName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentCateName()";
        log.entering(STR_METHOD_NAME);

        //�P�j this.get���ʎ�ފǗ�()���R�[������B
        List l_lisDocCategoryManagements = this.getDocCategoryManagement();

        //�Q�j �P�j �ɂĎ擾����List�̒��� > 0 �̏ꍇ�A�擾�����s��菑�ʎ�ޖ��̂�ԋp����B
        //�i���ʎ�ޖ��̂�null�̏ꍇ��null��ԋp����B�j
        //List�̒��� == 0 �̏ꍇ�A
        //�u���ʎ�ފǗ��e�[�u���Ƀ��R�[�h�����݂��܂���B�v��O���X���[����B
        if (l_lisDocCategoryManagements.size() == 0)
        {
            log.debug("���ʎ�ފǗ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03000,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʎ�ފǗ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
        }


        DocCategoryManagementRow l_docCategoryManagementRow =
            (DocCategoryManagementRow)l_lisDocCategoryManagements.get(0);

        log.exiting(STR_METHOD_NAME);
        return l_docCategoryManagementRow.getDocumentCateName();
    }

    /**
     * (is���ʎ�ރR�[�h)<BR>
     * ���ʎ�ފǗ��e�[�u����菑�ʎ�ޖ��̂��擾����B<BR>
     * <BR>
     * �P�j this.get���ʎ�ފǗ�()���R�[������B<BR>
     * <BR>
     * �Q�j �P�j �ɂĎ擾����List�̒��� > 0 �̏ꍇ true�A<BR>
     * �@@�@@List�̒��� == 0 �̏ꍇ false ��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDocumentCategory() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDocumentCategory()";
        log.entering(STR_METHOD_NAME);

        //�P�j this.get���ʎ�ފǗ�()���R�[������B
        List l_lisDocCategoryManagements = this.getDocCategoryManagement();

        //�Q�j �P�j �ɂĎ擾����List�̒��� > 0 �̏ꍇ true�A
        //List�̒��� == 0 �̏ꍇ false ��ԋp����B
        if (l_lisDocCategoryManagements.size() > 0)
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
     * (get���ʎ�ފǗ�)<BR>
     * ���ʎ�ފǗ��e�[�u����背�R�[�h���擾����B<BR>
     * <BR>
     * �P�j �ȉ������ɂď��ʎ�ލs���擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h �F this.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h �F this.���X�R�[�h[]�̑S�Ă̗v�f(in����)<BR>
     * �@@�@@���ʎ�ރR�[�h �F this.���ʎ�ރR�[�h<BR>
     * <BR>
     * �Q�j �P�j�ɂĎ擾����List��ԋp����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocCategoryManagement() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocCategoryManagement()";
        log.entering(STR_METHOD_NAME);

        List l_lisDocCategoryManagementRows = null;
        try
        {
            //�P�j �ȉ������ɂď��ʎ�ލs���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[��������]
            //�،���ЃR�[�h = this.�،���ЃR�[�h
            //���X�R�[�h �F this.���X�R�[�h[]�̑S�Ă̗v�f(in����)
            //���ʎ�ރR�[�h �F this.���ʎ�ރR�[�h
            String l_strBranchCodeIn = "";
            Object[] l_queryContainers = new Object[this.branchCode.length + 2];
            l_queryContainers[0] = this.institutionCode;

            int l_intIndex = 0;
            for (int i = 0; i < this.branchCode.length; i++)
            {
                if (i == this.branchCode.length -1)
                {
                    l_strBranchCodeIn += " ? ";
                }
                else
                {
                    l_strBranchCodeIn += " ?, ";
                }
                l_intIndex += 1;
                l_queryContainers[l_intIndex] = this.branchCode[i];
            }

            l_queryContainers[l_queryContainers.length -1] = this.documentCategory;

            String l_strQueryString = " institution_code = ? and branch_code in ( "
                + l_strBranchCodeIn + " ) and document_category = ? ";

            l_lisDocCategoryManagementRows = l_queryProcessor.doFindAllQuery(
                DocCategoryManagementRow.TYPE,
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
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�P�j�ɂĎ擾����List��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisDocCategoryManagementRows;
    }

    /**
     * (���ʎ�ފǗ�)<BR>
     * �f�B�t�H���g�R���X�g���N�^
     */
    public WEB3AdminFPTDocCategoryManagement()
    {

    }

    /**
     * (���ʎ�ފǗ�)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * this.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * this.���X�R�[�h = ����.���X�R�[�h<BR>
     * this.���ʎ�ރR�[�h = ����.���ʎ�ރR�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     */
    public WEB3AdminFPTDocCategoryManagement(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strDocumentCategory)
    {
        this.institutionCode = l_strInstitutionCode;
        this.branchCode = l_strBranchCodes;
        this.documentCategory = l_strDocumentCategory;
    }
}
@
