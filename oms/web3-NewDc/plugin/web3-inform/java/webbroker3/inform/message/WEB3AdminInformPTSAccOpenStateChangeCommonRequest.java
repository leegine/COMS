head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�����J�ݏ󋵕ύX���ʃ��N�G�X�g(WEB3AdminInformPTSAccOpenStateChangeCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �đo�g(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * �Ǘ��ҁEPTS�����J�ݏ󋵕ύX���ʃ��N�G�X�g<BR>
 * �Ǘ��ҁEPTS�����J�ݏ󋵕ύX���ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeCommonRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeCommonRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�ύX��\���敪)<BR>
     * �ύX��\���敪<BR>
     * <BR>
     * 0�F���J��<BR>
     * 1�F�J��<BR>
     */
    public String afterPtsAccOpenDiv;

    /**
     * @@roseuid 47C522D401A2
     */
    public WEB3AdminInformPTSAccOpenStateChangeCommonRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
     public WEB3GenResponse createResponse()
     {
         return null;
     }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�P�|�Q�j�@@������3�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     * �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00835<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00836<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�ύX��\���敪�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03045<BR>
     * <BR>
     * @@param WEB3BaseException
     * @@roseuid 47B4F3110293
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //���X�R�[�h�̃`�F�b�N
        //�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        //������3�łȂ��ꍇ�A��O���X���[����B
        if (this.branchCode.length() != 3)
        {
            log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }

        //�ڋq�R�[�h�̃`�F�b�N
        //�����͂̏ꍇ�A��O���X���[����B
        if (this.accountCode == null)
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }

        //������6�łȂ��ꍇ�A��O���X���[����B
        if (this.accountCode.length() != 6)
        {
            log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        //�ύX��\���敪�̃`�F�b�N
        //�����͂̏ꍇ�A��O���X���[����B
        if (this.afterPtsAccOpenDiv == null)
        {
            log.debug("�ύX��\���敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03045,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX��\���敪�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
