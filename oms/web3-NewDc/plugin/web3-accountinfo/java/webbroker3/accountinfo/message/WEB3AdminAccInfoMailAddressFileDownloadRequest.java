head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ظ���(WEB3AdminAccInfoMailAddressFileDownloadRequest.java)
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


/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ظ���)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ظ���<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressFileDownloadRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressFileDownloadRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082114L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * �ڋq�R�[�h�i���j
     */
    public String accountCodeFrom;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * �ڋq�R�[�h�i���j
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
     * @@roseuid 418F38570203
     */
    public WEB3AdminAccInfoMailAddressFileDownloadRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressFileDownloadResponse(this);
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
     *�Q�j�ڋq�R�[�h�i���j�`�F�b�N<BR>
     *�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02007<BR>
     *�R�j�ڋq�R�[�h�i���j�`�F�b�N<BR>
     *�@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02008<BR>
     *<BR>
     *�S�j���M�t���O�`�F�b�N<BR>
     *�@@�S�|�P�j�@@�ȉ��̒l�ɊY�����Ȃ��ꍇ�A��O���X���[����B<BR>
     *�@@�@@�@@�@@�@@�@@�E�v<BR>
     *�@@�@@�@@�@@�@@�@@�E�s�v<BR>
     *�@@�@@�@@�@@�@@�@@�E�w��Ȃ�<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02009<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41749AAB0182
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
        if (this.accountCodeFrom == null)
        {
            log.error("�ڋq�R�[�h�i���j�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02007, getClass().getName() + STR_METHOD_NAME, "�ڋq�R�[�h�i���j������");
        }
        if (this.accountCodeTo == null)
        {
            log.error("�ڋq�R�[�h�i���j�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02008, getClass().getName() + STR_METHOD_NAME, "�ڋq�R�[�h�i���j������");
        }
        if (!(this.sendFlag == null) && !(WEB3AccountOpenMailFlagDef.sendFlag.equals(this.sendFlag))
        		 && !(WEB3AccountOpenMailFlagDef.unSendFlag.equals(this.sendFlag)))
        {
            log.error("���M�t���O�ȉ��̒l�ɊY�����Ȃ��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02009, getClass().getName() + STR_METHOD_NAME, "���M�t���O�ȉ��̒l�ɊY�����Ȃ�");

        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
