head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g(WEB3AdminPvInfoDirectChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/26 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectChangeConfirmRequest extends WEB3GenRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_directChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�A�b�v���[�h�t�@@�C��)<BR>
     * <BR>
     * ��CSV�t�@@�C���̔z��<BR>
     */
    public String[] uploadFile;

    /**
     * (�\�����e���)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit displayContentsUnit;

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�A�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.���X�R�[�h != null �܂��� this.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h == null<BR>
     * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3<BR>
     * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h != ���l<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h == null<BR>
     * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h.length != 6<BR>
     * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h != ���l<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * �Q�j�A�b�v���[�h�t�@@�C���`�F�b�N<BR>
     * �@@this.���X�R�[�h == null ���� this.�ڋq�R�[�h == null�̏ꍇ<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�A�b�v���[�h�t�@@�C�� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�A�b�v���[�h�t�@@�C����null�v�̗�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00976<BR>
     * <BR>
     * �R�j���͓��e�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�\�����e���.�\�����eID == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�����eID��null�v�̗�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_01040<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�\�����e���.validate()���R�[������B<BR>
     * @@roseuid 4161099202BB
     */
    public void validate() throws WEB3BusinessLayerException
    {

        String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�R�[�h�A�ڋq�R�[�h�`�F�b�N
        if (this.branchCode != null || this.accountCode != null)
        {
            //�P�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A�u���X�R�[�h�G���[�v�̗�O���X���[����
            if (this.branchCode == null || WEB3StringTypeUtility.getByteLength(this.branchCode) != 3 || !WEB3StringTypeUtility.isNumber(this.branchCode))
            {
                log.error("���X�R�[�h�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //�P�|�Q�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A�ڋq�R�[�h�G���[�v�̗�O���X���[����B
            if (this.accountCode == null || WEB3StringTypeUtility.getByteLength(this.accountCode) != 6 || !WEB3StringTypeUtility.isNumber(this.accountCode))
            {
                log.error("�ڋq�R�[�h�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //�Q�j�A�b�v���[�h�t�@@�C���`�F�b�N
        if (this.branchCode == null && this.accountCode == null)
        {
            //�Q�|�P�jthis.�A�b�v���[�h�t�@@�C�� == null�̏ꍇ�u�\�����eID��null�v�̗�O���X���[����B
            if (this.uploadFile == null)
            {
                log.error("�A�b�v���[�h�t�@@�C����null");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //�R�j���͓��e�`�F�b�N
        //�R�|�P�jthis.�\�����e���.�\�����eID == null�̏ꍇ�A�u�\�����eID��null�v�̗�O���X���[����B
        if (this.displayContentsUnit.displayContentsId == null)
        {
            log.error("�\�����eID��null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01040,
                getClass().getName() + STR_METHOD_NAME);
        }

        //�R�|�Q�jthis.�\�����e���.validate()���R�[������B
        this.displayContentsUnit.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327BD037A
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPvInfoDirectChangeConfirmResponse(this);
    }
}
@