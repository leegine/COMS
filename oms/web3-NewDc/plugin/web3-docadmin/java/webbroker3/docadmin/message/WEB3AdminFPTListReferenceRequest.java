head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTListReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g(WEB3AdminFPTListReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
Revision History : 2007/10/15 ���g (���u) ���f��No.005
Revision History : 2007/10/16 Inomata (SCS) ���f��No.007
Revision History : 2008/01/25 ���n�m (���u) �d�l�ύX�E���f��No.022
*/

package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t�{���ꗗ�Ɖ�N�G�X�g�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291429L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String acceptCode;

    /**
     * (���ʌ�t��from)<BR>
     * ���ʌ�t��from<BR>
     */
    public Date docuDeliDateFrom;

    /**
     * (���ʌ�t��to)<BR>
     * ���ʌ�t��to<BR>
     */
    public Date docuDeliDateTo;

    /**
     * (���ʋ敪�Ǘ��ꗗ)<BR>
     * ���ʋ敪�Ǘ��ꗗ<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;

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
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * �L�[���ڂ͈ȉ��̂Ƃ���<BR>
     * <BR>
     * �E���X�R�[�h<BR>
     * �E�ڋq�R�[�h<BR>
     * �E���ʌ�t��<BR>
     */
    public WEB3AdminFPTSortKey[] sortKeys;

    /**
     * @@roseuid 46FDDD370138
     */
    public WEB3AdminFPTListReferenceRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTListReferenceResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �P�j ���X�R�[�h�`�F�b�N<BR>
     * <BR>
     * �P-�P�j<BR>
     *    this.���X�R�[�h == null or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00833<BR>
     *    this.���X�R�[�h.length() == 0<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01757<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�|�Q�j���X�R�[�h�̊e�v�f�ɂ��ă`�F�b�N����B<BR>
     * <BR>
     *    ���X�R�[�h != ���p���� or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01729<BR>
     *    ���X�R�[�h.length() != 3<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00834<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * <BR>
     * �Q�j �ڋq�R�[�h�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�ڋq�R�[�h != null and<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00835<BR>
     * ���N�G�X�g.�ڋq�R�[�h�����p�����ȊO �܂���<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_01043<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@6���ȊO<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00836<BR>
     * <BR>
     * <BR>
     * �R�j�@@���ʌ�t���召�`�F�b�N<BR>
     * �@@�S�|�P�j�@@���N�G�X�g.���ʌ�t��from != null and<BR>
     * �@@�@@���N�G�X�g.���ʌ�t��to !=null �̎��A<BR>
     * �@@�@@�@@�@@�@@�@@���N�G�X�g.���ʌ�t��from > ���N�G�X�g.���ʌ�t��to<BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02947<BR>
     * <BR>
     * <BR>
     * �S�j �v���y�[�W�ԍ��`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�v���y�[�W�ԍ� = null or<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00089<BR>
     * ���N�G�X�g.�v���y�[�W�ԍ��������ȊO or<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00090<BR>
     * ���N�G�X�g.�v���y�[�W�ԍ� <= 0<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * <BR>
     * �T�j�y�[�W���\���s���`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�y�[�W���\���s�� = null or<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02224<BR>
     * ���N�G�X�g.�y�[�W���\���s���������ȊO or<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00092<BR>
     * ���N�G�X�g.�y�[�W���\���s�� <= 0<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * �U�j�\�[�g�L�[�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�\�[�g�L�[ = null or<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00231<BR>
     * ���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46F202FF00F5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

		//�P�j���X�R�[�h<BR>
		//�P�|�P�j<BR>
		//this.���X�R�[�h == null�̏ꍇ�A��O���X���[����B<BR>

		if (this.branchCode == null)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00833,
				getClass().getName() + "validate",
				"���X�R�[�h�����w��ł��B");
		}

		//this.���X�R�[�h.length() == 0�̏ꍇ�A��O���X���[����B<BR>

		if (this.branchCode.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01757,
				getClass().getName() + "validate",
				"���X�R�[�h�̗v�f�����O�ł��B");
		}

		int l_intBranchCodeLth = this.branchCode.length;
		for (int i = 0; i < l_intBranchCodeLth; i++)
		{
			// �P�|�Q�j���X�R�[�h�̊e�v�f�ɂ��ă`�F�b�N����B<BR>
			//  ���X�R�[�h != ���p���� �̏ꍇ�A��O���X���[����B<BR>

			if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01729,
					getClass().getName() + "validate",
					"���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
			}

			//  ���X�R�[�h.length() != 3�̏ꍇ�A��O���X���[����B<BR>

			if (this.branchCode[i].length() != 3)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00834,
					getClass().getName() + "validate",
					"���X�R�[�h�̃T�C�Y���s���ł��B");
			}
		}

        //�Q�j �ڋq�R�[�h�`�F�b�N
        //���N�G�X�g.�ڋq�R�[�h != null and
        //���N�G�X�g.�ڋq�R�[�h�����p�����ȊO �܂���6���ȊO
        if (this.acceptCode != null)
        {
           if (!WEB3StringTypeUtility.isDigit(this.acceptCode))
           {
                log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
           }

           else if (this.acceptCode.length() != 6)
           {
                log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
           }
        }

        //�R�j���ʌ�t���召�`�F�b�N
        //�R�|�P�j�@@���N�G�X�g.���ʌ�t��from != null and  ���N�G�X�g.���ʌ�t��to != null �̎�
        //���N�G�X�g.���ʌ�t��from > ���N�G�X�g.���ʌ�t��to�̏ꍇ��O���X���[
        if (this.docuDeliDateFrom != null && this.docuDeliDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.docuDeliDateFrom, this.docuDeliDateTo) > 0)
            {
                log.debug("���ʌ�t��From/To�������G���[�B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02947,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʌ�t��From/To�������G���[�B");
            }
        }

        //�S�j �v���y�[�W�ԍ��`�F�b�N
        //���N�G�X�g.�v���y�[�W�ԍ� = null or
        //���N�G�X�g.�v���y�[�W�ԍ��������ȊO or
        //���N�G�X�g.�v���y�[�W�ԍ� <= 0 �̏ꍇ�A��O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        else if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        else if ((Integer.parseInt(this.pageIndex) <= 0))
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�T�j�y�[�W���\���s���`�F�b�N
        //���N�G�X�g.�y�[�W���\���s�� = null or
        //���N�G�X�g.�y�[�W���\���s���������ȊO or
        //���N�G�X�g.�y�[�W���\���s�� <= 0�̏ꍇ�A��O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        else if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        else if ((Integer.parseInt(this.pageSize) <= 0))
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�U�j�\�[�g�L�[�`�F�b�N
        //���N�G�X�g�f�[�^.�\�[�g�L�[ = null or
        //���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0 �̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        else if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
