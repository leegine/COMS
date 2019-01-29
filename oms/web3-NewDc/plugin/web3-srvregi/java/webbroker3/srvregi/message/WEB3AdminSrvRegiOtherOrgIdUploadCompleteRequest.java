head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ظ���(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338,No.347
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiUploadDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ظ��ăN���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101409L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.class);

    /**
     * (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadId;

    /**
     * (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     */
    public String uploadDiv;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ���ظ���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D764014C
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest()
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
     * �Q�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>
     * �@@this.�A�b�v���[�hID==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00973<BR>
     * <BR>
     * �R�j�@@�A�b�v���[�h�敪�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�A�b�v���[�h�敪==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03056<BR>
     * �@@�R�|�Q�j�@@this.�A�b�v���[�h�敪!=null�ł���A���ȉ��̒l�ɊY�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E0�F�o�^<BR>
     * �@@�@@�@@�@@�@@�@@�E1�F�ύX<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01020<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3EF9E00BB
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

        //this.�T�[�r�X�敪!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
        {
            log.debug("�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
        }

        //�A�b�v���[�hID�̃`�F�b�N
        //this.�A�b�v���[�hID==null�̏ꍇ�A��O���X���[����B
        if (this.uploadId == null)
        {
            log.debug("�A�b�v���[�hID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�hID�����w��ł��B");
        }

        //�A�b�v���[�h�敪�̃`�F�b�N
        //this.�A�b�v���[�h�敪==null�̏ꍇ�A��O���X���[����B
        if (this.uploadDiv == null)
        {
            log.debug("�A�b�v���[�h�敪�����w��ł��B�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03056,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�h�敪�����w��ł��B");
        }

        //this.�A�b�v���[�h�敪!=null�ł���A���ȉ��̒l�ɊY�����Ȃ��ꍇ�A��O���X���[����B
        // �@@�@@�@@�@@�@@�@@�E0�F�o�^
        // �@@�@@�@@�@@�@@�@@�E1�F�ύX
        if (!WEB3SrvRegiUploadDivDef.REGIST_RECORD.equals(this.uploadDiv.trim())
            && !WEB3SrvRegiUploadDivDef.CHANGE_RECORD.equals(this.uploadDiv.trim()))
        {
            log.debug("�A�b�v���[�h�敪�̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01020,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�h�敪�̒l���s���ł��B");
        }
    }

    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰�ފ������X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D76B0033
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse(this);
    }
}
@
