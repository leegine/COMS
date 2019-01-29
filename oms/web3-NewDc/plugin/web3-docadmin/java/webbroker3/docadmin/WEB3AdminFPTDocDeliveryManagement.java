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
filename	WEB3AdminFPTDocDeliveryManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t�Ǘ�(WEB3AdminFPTDocDeliveryManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 �����q (���u) �V�K�쐬
Revision History : 2007/10/19 Inomata (SCS) ���f��009
Revision History : 2007/11/06 ���g (���u) ���f��No.011
Revision History : 2008/01/28 ���g (���u) ���f��No.023
Revision History : 2008/03/04 �g�C�� (���u) ���f��No.038
*/

package webbroker3.docadmin;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.DocDeliveryManagementPK;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ʌ�t�Ǘ�)<BR>
 * ���ʌ�t�Ǘ��N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminFPTDocDeliveryManagement
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDeliveryManagement.class);

    /**
     * (���ʌ�t�Ǘ�)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 46F8D9280130
     */
    public WEB3AdminFPTDocDeliveryManagement()
    {

    }

    /**
     * (insert���ʌ�t�Ǘ��e�[�u��)<BR>
     * ���ʌ�t�Ǘ��e�[�u����Insert���s���B<BR>
     * <BR>
     * QueryProcessor#doInsertQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *   [����]<BR>
     *     arg0�F ����.���ʌ�t�Ǘ��e�[�u��Params<BR>
     * @@param l_docDeliveryManagementParams - (���ʌ�t�Ǘ��e�[�u��Params)<BR>
     * ���ʌ�t�Ǘ��e�[�u��Params
     * @@throws WEB3BaseException
     * @@roseuid 46F8C8E8020D
     */
    public void insertDocDeliveryManagementParams(
        DocDeliveryManagementParams l_docDeliveryManagementParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertDocDeliveryManagementParams(DocDeliveryManagementParams)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_docDeliveryManagementParams);
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

        log.exiting(STR_METHOD_NAME);
    }


	/**
	 * (get���ʌ�t�Ǘ��s)<BR>
	 * ���ʌ�t�Ǘ��e�[�u����茟�����s���B<BR>
	 * <BR>
	 * �P�j�@@�ȉ������ŏ��ʌ�t�Ǘ��e�[�u����茟�����s���B<BR>
	 * <BR>
	 * �@@�@@[����]<BR>
	 * �@@�@@�@@�@@����ID = ����:����ID<BR>
     * �@@�@@�@@�@@���ʋ敪 = ����:���ʋ敪<BR>
     * �@@�@@�@@�@@�����R�[�h = ����:�����R�[�h<BR>
     * �@@�@@�@@�@@���ʌ�t�� = ����:���ʌ�t��<BR>
     * �@@�@@�@@�@@���ʎ�ރR�[�h = ����:���ʎ�ރR�[�h<BR>
	 * <BR>
	 * �Q�j�@@�������ʂ��擾�ł����ꍇ�A�擾�������ʌ�t�Ǘ��e�[�u���s��ԋp����B<BR>
	 * <BR>
	 * �R�j�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * @@param l_accountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_documentDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_productCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_docuDeliDate - (���ʌ�t��)<BR>
     * ���ʌ�t��<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
	 * @@throws WEB3BaseException
	 * @@return DocDivManagementRow
	 */
	public DocDeliveryManagementRow getDocDivManagementParams(
	        long l_accountId,
			String l_documentDiv,
			String l_productCode,
			Date l_docuDeliDate,
            String l_strDocumentCategory) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getDocDivManagementParams(long, String, String, Date, String)";
		log.entering(STR_METHOD_NAME);

		DocDeliveryManagementRow l_docDeliveryManagementRow = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

			DocDeliveryManagementPK l_docDeliveryManagementPK =
				new DocDeliveryManagementPK(l_accountId,
				l_documentDiv, l_productCode, new Timestamp(l_docuDeliDate.getTime()),
                l_strDocumentCategory);

			// �ȉ������ŏ��ʌ�t�Ǘ��e�[�u����茟�����s���B
			l_docDeliveryManagementRow =
				(DocDeliveryManagementRow)l_queryProcessor.doFindByPrimaryKeyQuery(
                    l_docDeliveryManagementPK);
		}
		catch (DataFindException l_ex)
		{
			// �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
			log.exiting(STR_METHOD_NAME);
			return null;
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

		// �Q�j�@@�������ʂ��擾�ł����ꍇ�A�擾�������ʌ�t�Ǘ��e�[�u���s��ԋp����B
		log.exiting(STR_METHOD_NAME);
		return l_docDeliveryManagementRow;

	}

    /**
     * (get���ʌ�t�Ǘ�)<BR>
     * ���ʌ�t�Ǘ��e�[�u�����珑�ʎ�ނ��L�[�Ƃ��ă��R�[�h����������B�i���X�s��j<BR>
     * <BR>
     * �P�j ������������쐬����B<BR>
     * <BR>
     * �@@�@@"institution_code = ? and document_div = ? and product_code = ? and <BR>
     * �@@�@@document_category = ? "<BR>
     * <BR>
     * �Q�j �����f�[�^�R���e�i�i�ȉ��l��v�f�Ƃ���Object�z��j���쐬����B<BR>
     * <BR>
     * �@@�@@�@@�E����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E����.���ʋ敪<BR>
     * �@@�@@�@@�E����.�d�q�������R�[�h<BR>
     * �@@�@@�@@�E����.���ʎ�ރR�[�h<BR>
     * <BR>
     * �R�j ���ʌ�t�Ǘ��e�[�u���֌������s���B<BR>
     * <BR>
     * �@@�@@�@@this.get���ʌ�t�Ǘ�()���R�[������<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@����������F �P�j �ō쐬��������������<BR>
     * �@@�@@�@@�@@�@@�����f�[�^�R���e�i�F �Q�j �ō쐬���������f�[�^�R���e�i<BR>
     * <BR>
     * �S�j ��������List��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strDocumentDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_strBatoProduct_code - (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     * @@return List
     * @@throws WEB3BaseException<BR>
     */
    public List getDocDivManagement(
            String l_strInstitutionCode,
            String l_strDocumentDiv,
            String l_strBatoProduct_code,
            String l_strDocumentCategory) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagement(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j ������������쐬����B<BR>
        //"institution_code = ? and document_div = ? and product_code = ? and document_category = ? "
        String l_strQueryCondition = 
            " institution_code = ? and " +
            "document_div = ? and " +
            "product_code = ? and " +
            "document_category = ? ";

        //�Q�j �����f�[�^�R���e�i�i�ȉ��l��v�f�Ƃ���Object�z��j���쐬����B
        //�E����.�،���ЃR�[�h
        //�E����.���ʋ敪
        //�E����.�d�q�������R�[�h
        //�E����.���ʎ�ރR�[�h
        Object[] l_queryContainers = new Object[4];
        l_queryContainers[0] = l_strInstitutionCode;
        l_queryContainers[1] = l_strDocumentDiv;
        l_queryContainers[2] = l_strBatoProduct_code;
        l_queryContainers[3] = l_strDocumentCategory;

        //�R�j ���ʌ�t�Ǘ��e�[�u���֌������s���B
        //this.get���ʌ�t�Ǘ�()���R�[������
        //[����]
        //����������F �P�j �ō쐬��������������
        //�����f�[�^�R���e�i�F �Q�j �ō쐬���������f�[�^�R���e�i
        List l_lisDocDivManagements = this.getDocDivManagement(l_strQueryCondition, l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_lisDocDivManagements;
    }

    /**
     * (get���ʌ�t�Ǘ�)<BR>
     * ���ʌ�t�Ǘ��e�[�u����茟�����s���B<BR>
     * <BR>
     * �P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *      [����]<BR>
     *      rowType�F ���ʌ�t�Ǘ��e�[�u��RowType<BR>
     *      where�F ����:����������<BR>
     *      bindVars�F ����:�����f�[�^�R���e�i<BR>
     * <BR>
     * �Q�j �擾����List��ԋp����B<BR>
     * @@param l_strQueryString - (����������)<BR>
     * ����������<BR>
     * @@param l_queryContainers - (�����f�[�^�R���e�i)<BR>
     * �����f�[�^�R���e�i<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocDivManagement(String l_strQueryString, Object[] l_queryContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDivManagement(String, Object[])";
        log.entering(STR_METHOD_NAME);

        List l_lisDocDivManagements = null;
        try
        {
            //�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[����]
            //rowType�F ���ʌ�t�Ǘ��e�[�u��RowType
            //where�F ����:����������
            //bindVars�F ����:�����f�[�^�R���e�i
            l_lisDocDivManagements = l_queryProcessor.doFindAllQuery(
                DocDeliveryManagementRow.TYPE,
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

        //�Q�j �擾����List��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisDocDivManagements;
    }

    /**
     * (delete���ʌ�t�Ǘ�)<BR>
     * ���ʌ�t�Ǘ��e�[�u���̍폜���s���B<BR>
     * <BR>
     * �P�j �ȉ������ŏ��ʌ�t�Ǘ��e�[�u�����폜���s���B<BR>
     * <BR>
     *     [����]<BR>
     *       ����ID = ����:����ID<BR>
     *       ���ʋ敪 = ����:���ʋ敪<BR>
     *       �����R�[�h = ����:�����R�[�h<BR>
     *       ���ʌ�t�� = ����:���ʌ�t��<BR>
     *       ���ʎ�ރR�[�h = ����:���ʎ�ރR�[�h<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strDocumentDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_datDocuDeliDate - (���ʌ�t��)<BR>
     * ���ʌ�t��<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void deleteDocDivManagement(
        long l_lngAccountId,
        String l_strDocumentDiv,
        String l_strProductCode,
        Date l_datDocuDeliDate,
        String l_strDocumentCategory) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteDocDivManagement(long, String, String, Date, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[����]
            //����ID = ����:����ID
            //���ʋ敪 = ����:���ʋ敪
            //�����R�[�h = ����:�����R�[�h
            //���ʌ�t�� = ����:���ʌ�t��
            String l_strQueryString = " account_id = ? and document_div = ?"
                + " and product_code = ? and delivery_date = ? and document_category = ? ";

            Object[] l_queryContainers = {
                new Long(l_lngAccountId),
                l_strDocumentDiv,
                l_strProductCode,
                l_datDocuDeliDate,
                l_strDocumentCategory};
            l_queryProcessor.doDeleteAllQuery(
                DocDeliveryManagementRow.TYPE,
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
    }
}
@
