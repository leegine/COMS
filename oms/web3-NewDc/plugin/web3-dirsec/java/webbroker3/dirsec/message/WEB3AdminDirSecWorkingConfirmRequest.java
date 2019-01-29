head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g(WEB3AdminDirSecWorkingConfirmRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/25 �đo�g(���u) �V�K�쐬 ���f��No.117�ANo.124
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingConfirmRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecWorkingConfirmRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_confirm";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804251811L;

    /**
     * (�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�<BR>
     */
    public WEB3AdminDirSecBatoPreferenceRecordDetail[] batoPreferenceRecord;

    /**
     * @@roseuid 481169C40344
     */
    public WEB3AdminDirSecWorkingConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�`�F�b�N<BR>
     * <BR>
     * �P�j�@@this.���N�G�X�g�f�[�^==null�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03076<BR>
     * <BR>
     * �Q�j�@@this.���N�G�X�g�f�[�^�̗v�f��==0 �̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03077<BR>
     * <BR>
     * �R�j�@@this.���N�G�X�g�f�[�^�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@�R�|�P�j�@@this.���N�G�X�g�f�[�^.���X�R�[�h ==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.���N�G�X�g�f�[�^.���X�R�[�h�����p�����ȊO�̕�����<BR>
     * �@@�@@�@@���͂���Ă���܂��́A����!=3���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00834<BR>
     * <BR>
     * �@@�R�|�R�j�@@this.���N�G�X�g�f�[�^.�V�X�e����Q�t���O ==null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03080<BR>
     * <BR>
     * �@@�R�|�S�j�@@this.���N�G�X�g�f�[�^.�V�X�e����Q�t���O�����p�����ȊO�̕�����<BR>
     * �@@�@@�@@���͂���Ă���܂��́A����!=1���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03078<BR>
     * <BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03079<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C4AB7F020E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.���N�G�X�g�f�[�^==null�̏ꍇ�A ��O���X���[����
        if (this.batoPreferenceRecord == null)
        {
            log.debug("���N�G�X�g�f�[�^�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03076,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^�����w��ł��B");
        }

        //this.���N�G�X�g�f�[�^�̗v�f��==0 �̏ꍇ�A ��O���X���[����
        if (this.batoPreferenceRecord.length == 0)
        {
            log.debug("���N�G�X�g�f�[�^�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03077,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^�̗v�f�����O�ł��B");
        }

        //this.���N�G�X�g�f�[�^�̗v�f�����A�ȉ����J��Ԃ�
        for (int i = 0; i < this.batoPreferenceRecord.length; i++)
        {
            //this.���N�G�X�g�f�[�^.���X�R�[�h ==null�̏ꍇ�A��O���X���[����
            if (this.batoPreferenceRecord[i].branchCode == null)
            {
                log.debug("���X�R�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����w��ł��B");
            }

            //this.���N�G�X�g�f�[�^.���X�R�[�h�����p�����ȊO�̕��������͂���Ă���܂��́A
            // ����!=3���ꍇ�A��O���X���[����
            if (!WEB3StringTypeUtility.isDigit(this.batoPreferenceRecord[i].branchCode))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }

            if (this.batoPreferenceRecord[i].branchCode.length() != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }

            //this.���N�G�X�g�f�[�^.�V�X�e����Q�t���O ==null �̏ꍇ�A��O���X���[����
            if (this.batoPreferenceRecord[i].systemTroubleDiv == null)
            {
                log.debug("�V�X�e����Q�t���O�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03080,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�V�X�e����Q�t���O�����w��ł��B");
            }

            //this.���N�G�X�g�f�[�^.�V�X�e����Q�t���O�����p�����ȊO�̕�����
            // ���͂���Ă���܂��́A����!=1���ꍇ�A��O���X���[����
            if (!WEB3StringTypeUtility.isDigit(this.batoPreferenceRecord[i].systemTroubleDiv))
            {
                log.debug("�V�X�e����Q�t���O�����p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03078,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�V�X�e����Q�t���O�����p�����ȊO�̒l�ł��B");
            }

            if (this.batoPreferenceRecord[i].systemTroubleDiv.length() != 1)
            {
                log.debug("�V�X�e����Q�t���O�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03079,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�V�X�e����Q�t���O�̃T�C�Y���s���ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecWorkingConfirmResponse(this);
    }
}
@
