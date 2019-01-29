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
filename	WEB3AdminFPTDocDeliveryManagementHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t�Ǘ�����(WEB3AdminFPTDocDeliveryManagementHistory.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 ���g (���u) �V�K�쐬 �d�l�ύX�E���f�� No.011
Revision History : 2008/01/28 ���g (���u) ���f��No.023
*/

package webbroker3.docadmin;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.DocDeliveryManagementHistPK;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementHistRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;


/**
 * (���ʌ�t�Ǘ�����)<BR>
 * ���ʌ�t�Ǘ������N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTDocDeliveryManagementHistory
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDeliveryManagementHistory.class);

    /**
     * (���ʌ�t�Ǘ�����)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4727D4960121
     */
    public WEB3AdminFPTDocDeliveryManagementHistory()
    {

    }

    /**
     * (insert���ʌ�t�Ǘ������e�[�u��)<BR>
     * ���ʌ�t�Ǘ��e�[�u����Insert���s���B<BR>
     * <BR>
     * QueryProcessor#doInsertQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@arg0�F ����.���ʌ�t�Ǘ������e�[�u��Params<BR>
     * @@param l_docDeliveryManagementHistParams - (���ʌ�t�Ǘ������e�[�u��Params)<BR>
     * ���ʌ�t�Ǘ������e�[�u��Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4726F2AF019C
     */
    public void insertDocDeliveryManagementHist(
        DocDeliveryManagementHistParams l_docDeliveryManagementHistParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertDocDeliveryManagementHist(DocDeliveryManagementHistParams)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j QueryProcessor#doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[����]
            //arg0�F ����.���ʌ�t�Ǘ������e�[�u��Params
            l_queryProcessor.doInsertQuery(l_docDeliveryManagementHistParams);
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

    /**
     * (get���ʌ�t�Ǘ������s)<BR>
     * ���ʌ�t�Ǘ������e�[�u����茟�����s���B<BR>
     * <BR>
     * �P�j �ȉ������ŏ��ʌ�t�Ǘ������e�[�u����茟�����s���B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID = ����:����ID<BR>
     * �@@�@@�@@���ʋ敪 = ����:���ʋ敪<BR>
     * �@@�@@�@@�����R�[�h = ����:�����R�[�h<BR>
     * �@@�@@�@@���ʌ�t�� = ����:���ʌ�t<BR>
     * �@@�@@�@@�쐬���t = ����:�쐬���t<BR>
     * �@@�@@�@@���ʎ�ރR�[�h = ����.���ʎ�ރR�[�h<BR>
     * <BR>
     * �Q�j �������ʂ��擾�ł����ꍇ�A�擾�������ʌ�t�Ǘ������e�[�u���s��ԋp����B<BR>
     * <BR>
     * �R�j �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strDocumentDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_datDocuDeliDate - (���ʌ�t��)<BR>
     * ���ʌ�t��<BR>
     * @@param l_datCurrentTime - (�쐬���t)<BR>
     * �쐬���t<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     * @@return DocDeliveryManagementHistRow
     * @@throws WEB3BaseException
     * @@roseuid 4727CD88034B
     */
    public DocDeliveryManagementHistRow getDocDeliveryManagementHist(
        long l_lngAccountId,
        String l_strDocumentDiv,
        String l_strProductCode,
        Date l_datDocuDeliDate,
        Date l_datCurrentTime,
        String l_strDocumentCategory)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDocDeliveryManagementHist(long, String, String, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        DocDeliveryManagementHistRow l_docDeliveryManagementHistRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[����]
            //����ID = ����:����ID
            //���ʋ敪 = ����:���ʋ敪
            //�����R�[�h = ����:�����R�[�h
            //���ʌ�t�� = ����:���ʌ�t��
            //�쐬���t = ����:�쐬���t
            DocDeliveryManagementHistPK l_DocDeliveryManagementHistPK =
                new DocDeliveryManagementHistPK(
                    l_lngAccountId,
                    l_strDocumentDiv,
                    l_strProductCode,
                    new Timestamp(l_datDocuDeliDate.getTime()),
                    new Timestamp(l_datCurrentTime.getTime()),
                    l_strDocumentCategory);

            l_docDeliveryManagementHistRow =
                (DocDeliveryManagementHistRow)l_queryProcessor.doFindByPrimaryKeyQuery(
                    l_DocDeliveryManagementHistPK);
        }
        catch (DataFindException l_ex)
        {
            //�R�j �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
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

        //�Q�j �������ʂ��擾�ł����ꍇ�A�擾�������ʌ�t�Ǘ������e�[�u���s��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_docDeliveryManagementHistRow;
    }
}
@
