head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ظ���(WEB3AdminAccInfoMailAddressDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccountOpenMailFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ظ���)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ظ���<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082115L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * �ڋq�R�[�h�i���j<BR>
     */
    public String accountCodeFrom;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * �ڋq�R�[�h�i���j<BR>
     */
    public String accountCodeTo;
    
    /**
     * (���M�t���O)<BR>
     * ���M�t���O<BR>
     * �E�v<BR>
     * �E�s�v<BR>
     * �E�w��Ȃ�<BR>
     */
    public String sendFlag;

    /**
     * @@roseuid 418F3857000F
     */
    public WEB3AdminAccInfoMailAddressDownloadRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * �@@�Q�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * �@@�R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * �S�j�ڋq�R�[�h�i���j�`�F�b�N<BR>
     *�@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02007<BR>
     *<BR>
     *�T�j�ڋq�R�[�h�i���j�`�F�b�N<BR>
     *�@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02008<BR>
     *<BR>
     *�U�j���M�t���O�`�F�b�N<BR>
     *�@@�U�|�P�j�@@�ȉ��̒l�ɊY�����Ȃ��ꍇ�A��O���X���[����B<BR>
     *�@@�@@�@@�@@�@@�@@�E�v<BR>
     *�@@�@@�@@�@@�@@�@@�E�s�v<BR>
     *�@@�@@�@@�@@�@@�@@�E�w��Ȃ�<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02009<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147E5C80308
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("���X�R�[�h�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, getClass().getName() + STR_METHOD_NAME, "���X�R�[�h������");
        }

        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.error("�v���y�[�W�ԍ������ȊO�̕������܂܂��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ������ȊO�̕���" + this.pageIndex);
        }
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.error("�v���y�[�W�ԍ��}�C�i�X�l�̕������܂܂��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ��}�C�i�X�l�̕���" + this.pageIndex);
        }

        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("�y�[�W���\���s�������͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s��������" + this.pageSize);
        }
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.error("�y�[�W���\���s�������ȊO�̕������܂܂��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s�������ȊO�̕���" + this.pageSize);
        }
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            log.error("�y�[�W���\���s���}�C�i�X�l�̕������܂܂��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���}�C�i�X�l�̕���" + this.pageSize);
        }
        if (this.accountCodeFrom == null)
        {
            log.error("�ڋq�R�[�h�i���j�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02007, getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���}�C�i�X�l�̕���" + this.pageSize);
        }
        if (this.accountCodeTo == null)
        {
            log.error("�ڋq�R�[�h�i���j�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02008, getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���}�C�i�X�l�̕���" + this.pageSize);
        }
        if (!(this.sendFlag == null) && !(WEB3AccountOpenMailFlagDef.sendFlag.equals(this.sendFlag))
        		 && !(WEB3AccountOpenMailFlagDef.unSendFlag.equals(this.sendFlag)))
        {
            log.error("���M�t���O�ȉ��̒l�ɊY�����Ȃ��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02009, getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���}�C�i�X�l�̕���" + this.pageSize);

        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
