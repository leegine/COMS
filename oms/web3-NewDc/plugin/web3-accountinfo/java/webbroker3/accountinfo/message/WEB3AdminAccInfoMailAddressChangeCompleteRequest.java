head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������N�G�X�g(WEB3AdminAccInfoMailAddressChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                   2005/12/23 �A���� (���u) �d�l�ύXNo.072
Revesion History : 2007/08/28 ���g (���u) �d�l�ύX�E���f��No.217
Revesion History : 2010/02/21 ���g (���u) �d�l�ύX�E���f��No.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������N�G�X�g)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������N�G�X�g<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082118L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ύX�チ�[���A�h���X)<BR>
     * �ύX�チ�[���A�h���X<BR>
     */
    public String changedMailAddress;

    /**
     * (���[���A�h���X�폜�t���O)<BR>
     * ���[���A�h���X�폜�t���O<BR>
     * <BR>
     * true�F�@@�폜<BR>
     * false�F�@@�폜�łȂ�<BR>
     */
    public boolean mailAddressDelFlag;
    
    /**
     * (�����J�݊������[�����M�t���O)<BR>
     * �����J�݊������[�����M�t���O<BR>
     * <BR>
     * true�F�@@���M<BR>
     * false�F�@@���M���Ȃ�<BR>
     */
    public boolean accountOpenMailFlag;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�ē����[�����M�t���O)<BR>
     * �ē����[�����M�t���O<BR>
     * <BR>
     * true�F�@@�v<BR>
     * false�F�@@�s�v<BR>
     */
    public boolean guideMailFlag;

    /**
     * (�����A�h���X���)<BR>
     * �����A�h���X���<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo multiMailAddressInfo;

    /**
     * (���[���A�h���X���)<BR>
     * ���[���A�h���X���<BR>
     */
    public WEB3AccInfoMailAddressInfoUnit[] mailAddressInfoList;

    /**
     * @@roseuid 418F385800FA
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressChangeCompleteResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�ύX�チ�[���A�h���X�C���[���A�h���X�폜�t���O�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�i���[���A�h���X�폜�t���O == true�j�̏ꍇ�A<BR>
     * �ύX�チ�[���A�h���X�ɓ��͂�����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01154<BR>
     * <BR>
     * �S�j�@@���[���A�h���X�폜�t���O�C�����J�݊������[�����M�t���O�̃`�F�b�N<BR>
�@@   *   �S�|�P�j�@@�i�����J�݊������[�����M�t���O == true�j &&<BR>
�@@�@@ *     �i���[���A�h���X�폜�t���O == true�j�ł���΁A��O���X���[����B<BR>
�@@   *      �� �폜�ƌ����J�݊������[���͓����Ɏw��ł��Ȃ��B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01280<BR>
     * �T�j�@@���[���A�h���X�폜�t���O�C�ē�Ұّ��M�t���O�̃`�F�b�N<BR> 
     * �@@ �T�|�P�j�@@�i�ē�Ұّ��M�t���O == true�j &&<BR> 
     * �@@ �i���[���A�h���X�폜�t���O == true�j�ł���΁A��O���X���[����B<BR> 
     * �@@ �� �폜�ƈē�Ұّ��M�i�v�j�����Ɏw��ł��Ȃ��B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02296<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147F8F0002A
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
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            log.error("�ڋq�R�[�h�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, getClass().getName() + STR_METHOD_NAME, "�ڋq�R�[�h������");
        }
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            log.error("�ڋq�R�[�h������6�łȂ��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, getClass().getName() + STR_METHOD_NAME, "������6�łȂ��ꍇ");
        }
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.error("�ڋq�R�[�h�����ȊO�̕������܂܂��ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, getClass().getName() + STR_METHOD_NAME, "�ڋq�R�[�h�����ȊO�̕���");
        }
        if (this.mailAddressDelFlag && (this.changedMailAddress != null && (!"".equals(this.changedMailAddress))))
        {
            log.error("�i���[���A�h���X�폜�t���O == true�j�̏ꍇ�A�ύX�チ�[���A�h���X�����͂ł���Η�O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01154, getClass().getName() + STR_METHOD_NAME, "(���[���A�h���X�폜�t���O == true�j�̏ꍇ�A�ύX�チ�[���A�h���X������");
        }
        if (this.accountOpenMailFlag && this.mailAddressDelFlag)
        {
            log.error("(�����J�݊������[�����M�t���O == true�j &&�i���[���A�h���X�폜�t���O == true�j�ł���΁A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01280, getClass().getName() + STR_METHOD_NAME, "(�����J�݊������[�����M�t���O == true�j &&�i���[���A�h���X�폜�t���O == true)");
        }        

        // �T�j�@@���[���A�h���X�폜�t���O�C�ē�Ұّ��M�t���O�̃`�F�b�N<BR> 
        // �@@ �T�|�P�j�@@�i�ē�Ұّ��M�t���O == true�j &&<BR> 
        // �@@ �i���[���A�h���X�폜�t���O == true�j�ł���΁A��O���X���[����B<BR> 
        // �@@ �� �폜�ƈē�Ұّ��M�i�v�j�����Ɏw��ł��Ȃ��B<BR>
        if (this.guideMailFlag && this.mailAddressDelFlag)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02296, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "�폜�ƈē�Ұّ��M�i�v�j�����Ɏw��ł��Ȃ��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
