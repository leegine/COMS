head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\�����ʃ��N�G�X�g(WEB3InformPTSAccOpenApplyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS�����J�ݐ\�����ʃ��N�G�X�g)<BR>
 * PTS�����J�ݐ\�����ʃ��N�G�X�g
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCommonRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformPTSAccOpenApplyCommonRequest.class);

    /**
     * (�A�����)<BR>
     * �A�����
     */
    public String informType;

    /**
     * (PTS�����J�݋敪)<BR>
     * PTS�����J�݋敪<BR>
     * <BR>
     * 0�F���J��<BR>
     * 1�F�J��
     */
    public String ptsAccOpenDiv;

    /**
     * (PTS������ӎ�����ꗗ)<BR>
     * PTS������ӎ�����ꗗ
     */
    public WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList;

    /**
     * @@roseuid 47B9271A01D4
     */
    public WEB3InformPTSAccOpenApplyCommonRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�A����ʃ`�F�b�N<BR>
     * �@@this.�A����� == null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01817<BR>
     * <BR>
     * �Q�j�@@PTS�����J�݋敪�`�F�b�N<BR>
     * �@@this.PTS�����J�݋敪 == null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03020<BR>
     * <BR>
     * �R�j�@@PTS������ӎ�������e�`�F�b�N<BR>
     * �@@this.PTS������ӎ�����ꗗ != null�̏ꍇ�A<BR>
     * �@@�v�f���ƂɈȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B<BR>
     * <BR>
     * �@@�@@�EPTS������ӎ�����.����ԍ� == null�̏ꍇ<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03021<BR>
     * <BR>
     * �@@�@@�EPTS������ӎ�����.����� == null�̏ꍇ<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03022<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2B642012D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�A����ʃ`�F�b�N
        // this.�A����� == null�̏ꍇ�A��O��throw����
        if (this.informType == null)
        {
            log.debug("�A����ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        //PTS�����J�݋敪�`�F�b�N
        // this.PTS�����J�݋敪 == null�̏ꍇ�A��O��throw����B
        if (this.ptsAccOpenDiv == null)
        {
            log.debug("PTS�����J�݋敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03020,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "PTS�����J�݋敪�����w��ł��B");
        }

        //PTS������ӎ�������e�`�F�b�N
        // this.PTS������ӎ�����ꗗ != null�̏ꍇ�A
        // �v�f���ƂɈȉ��̃`�F�b�N���s���B
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B
        //  �EPTS������ӎ�����.����ԍ� == null�̏ꍇ
        //  �EPTS������ӎ�����.����� == null�̏ꍇ
        if (this.ptsTradeAgreementList != null)
        {
            int l_intCnt = this.ptsTradeAgreementList.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                if (this.ptsTradeAgreementList[i].questionNumber == null)
                {
                    log.debug("����ԍ������w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03021,
                        WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                        "����ԍ������w��ł��B");
                }

                if (this.ptsTradeAgreementList[i].questionAnswer == null)
                {
                    log.debug("����񓚂����w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03022,
                        WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                        "����񓚂����w��ł��B");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
