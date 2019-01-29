head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fظ���(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338,No.347
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fظ��ăN���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101411L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.class);

    /**
     * (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;

    /**
     * (�A�b�v���[�h�t�@@�C��)<BR>
     * �A�b�v���[�h�t�@@�C��<BR>
     */
    public String[] lines;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�Fظ���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D72F02A2
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�T�[�r�X�敪�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00758<BR>
     * �@@�P�|�Q�j�@@this.�T�[�r�X�敪!=null�ł���A������!=2���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00831<BR>
     * �@@�P�|�R�j�@@this.�T�[�r�X�敪!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00882<BR>
     * <BR>
     * �Q�j�@@�A�b�v���[�h�t�@@�C���̃`�F�b�N<BR>
     * �@@this.�A�b�v���[�h�t�@@�C��==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3EF7502C5
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�T�[�r�X�敪�̃`�F�b�N
        //this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null)
        {
            log.debug("�T�[�r�X�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�����w��ł��B");
        }

        //this.�T�[�r�X�敪!=null�ł���A������!=2���̏ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2)
        {
            log.debug("�T�[�r�X�敪�̌������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�̌������s���ł��B");
        }

        //this.�T�[�r�X�敪!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A
        //��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
        {
            log.debug("�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
        }

        //�A�b�v���[�h�t�@@�C���̃`�F�b�N
        //this.�A�b�v���[�h�t�@@�C��==null�̏ꍇ�A��O���X���[����B
        if (this.lines == null)
        {
            log.debug("�A�b�v���[�h�t�@@�C�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�h�t�@@�C�������w��ł��B");
        }
    }

    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފm�F���X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D73C01A6
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse(this);
    }
}
@
