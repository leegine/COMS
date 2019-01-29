head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g(WEB3AdminDirSecWorkingListRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 �đo�g(���u) �V�K�쐬 ���f��No.117�ANo.124�ANo.126
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
 * (�Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingListRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecWorkingListRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_list";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804281135L;

    /**
     * (���X�R�[�h)<BR>
     * ���O�C���Ǘ��҂������������X�R�[�h�̔z��<BR>
     */
    public String[] branchCode;

    /**
     * @@roseuid 481155FD0315
     */
    public WEB3AdminDirSecWorkingListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �����X�ꗗ�̃`�F�b�N<BR>
     * <BR>
     * �P�j�@@this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01429<BR>
     * <BR>
     * �Q�j�@@this.���X�R�[�h�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02175<BR>
     * <BR>
     * �R�j�@@this.���X�R�[�h�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@�R�|�P�jthis.�R�[�h.���X�R�[�h ==null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�R�|�Q�j���p�����ȊO�̕��������͂���Ă���܂��́A����!=3���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00834<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D08BD2002B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.���X�R�[�h==null�̏ꍇ�A��O���X���[����
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h�ꗗ�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�ꗗ�����w��ł��B");
        }

        //this.���X�R�[�h�̗v�f��==0�̏ꍇ�A��O���X���[����
        if (this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f����0�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f����0�ł��B");
        }

        //this.���X�R�[�h�̗v�f�����A�ȉ����J��Ԃ�
        for (int i = 0; i < this.branchCode.length; i++)
        {
            //this.�R�[�h.���X�R�[�h ==null �̏ꍇ�A��O���X���[����
            if (this.branchCode[i] == null)
            {
                log.debug("���X�R�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����w��ł��B");
            }

            //���p�����ȊO�̕��������͂���Ă���܂��́A����!=3���ꍇ�A��O���X���[����
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }

            if (this.branchCode[i].length() != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
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
        return new WEB3AdminDirSecWorkingListResponse(this);
    }
}
@
