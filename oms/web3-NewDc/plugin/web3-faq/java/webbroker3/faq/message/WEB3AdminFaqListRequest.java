head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g(WEB3AdminFaqListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.define.WEB3FaqKeyItemDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g)<BR>
 * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqListRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171303L;

    /**
     * (�⍇���R�[�h)<BR>
     * �⍇���R�[�h<BR>
     */
    public String faqCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�z��<BR>
     * <BR>
     * �� "000"�͕��X�Ȃ��̎w��<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public String subject;

    /**
     * (�⍇�����i���j)<BR>
     * �⍇�����i���j<BR>
     */
    public Date faqDateFrom;

    /**
     * (�⍇�����i���j)<BR>
     * �⍇�����i���j<BR>
     */
    public Date faqDateTo;

    /**
     * (�@@�\�h�c)<BR>
     * �@@�\�h�c�̔z��<BR>
     * <BR>
     * �� �e�Љ�ʂŔC�ӂɎg�p����R�[�h�B<BR>
     */
    public String[] transactionId;

    /**
     * (�@@�\�J�e�S���R�[�h)<BR>
     * �@@�\�J�e�S���R�[�h�̔z��<BR>
     */
    public String[] transactionCategoryCode;

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
     */
    public WEB3FaqSortKey[] sortKeys;

    /**
     * @@roseuid 41C25C0800BB
     */
    public WEB3AdminFaqListRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFaqListResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h[]�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�⍇�����i���j�C�⍇�����i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�Q�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01686<BR>
     * <BR>
     * �R�j�@@�@@�\�h�c[]�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01687<BR>
     * <BR>
     * �S�j�@@�@@�\�J�e�S���R�[�h[]�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01220<BR>
     * <BR>
     * �T�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00090<BR>
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �U�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00091<BR>
     * �@@�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00092<BR>
     * �@@�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�V�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * �@@�V�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * �@@�V�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�V�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�V�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A <BR>
     * ��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�@@�@@ �⍇�����.�⍇���R�[�h<BR>
     * �@@�@@�@@�@@ �⍇�����.���X�R�[�h <BR>
     * �@@�@@�@@�@@ �⍇�����.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@ �⍇�����.�⍇������ <BR>
     * �@@�@@�@@�@@ �⍇�����.�@@�\�h�c<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6FB4301B2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h[]�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");  
        }
        
        //�Q�j�@@�⍇�����i���j�C�⍇�����i���j�̃`�F�b�N
        //���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�
        //�Q�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
        if (this.faqDateFrom != null && this.faqDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.faqDateFrom, this.faqDateTo) > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01686, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�⍇�����i���j�͖⍇�����i���j���傫���ł��B" +
                    " [�⍇�����i���j] = " + this.faqDateFrom + 
                    " [�⍇�����i���j] = " + this.faqDateTo);  
            }
        }
        
        //�R�j�@@�@@�\�h�c[]�̃`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.transactionId == null 
            || this.transactionId.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01687, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�@@�\�h�c�����w��ł��B");  
        }
        
        //�S�j�@@�@@�\�J�e�S���R�[�h[]�̃`�F�b�N
        //�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.transactionCategoryCode == null 
            || this.transactionCategoryCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01220, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�@@�\�J�e�S���R�[�h�����w��ł��B");  
        }
        
        //�T�j�@@�v���y�[�W�ԍ��`�F�b�N
        //�T�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        //�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B" +
                " [�v���y�[�W�ԍ�] = " + this.pageIndex);
        }
        
        //�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        int l_lngPageIndex = Integer.parseInt(this.pageIndex);
        if (l_lngPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B" +
                " [�v���y�[�W�ԍ�] = " + this.pageIndex);
        }
        
        //�U�j�@@�y�[�W���\���s���`�F�b�N 
        //�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B" +
                " [�y�[�W���\���s��] = " + this.pageSize);
        }
        
        //�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B" +
                " [�y�[�W���\���s��] = " + this.pageSize);
        }
        
        //�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        int l_lngPageSize = Integer.parseInt(this.pageSize);
        if (l_lngPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B" +
                " [�y�[�W���\���s��] = " + this.pageSize);
        }
        
        //�V�j�@@�\�[�g�L�[�̃`�F�b�N 
        //�V�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�V�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�V�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //�V�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            this.sortKeys[i].validate();

            //�V�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A��O���X���[����B
            //�⍇�����.�⍇���R�[�h
            //�⍇�����.���X�R�[�h
            //�⍇�����.�ڋq�R�[�h
            //�⍇�����.�⍇������
            //�⍇�����.�@@�\�h�c
            if (!WEB3FaqKeyItemDef.FAQ_NUMBER.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.FAQ_DATETIME.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.TRANSACTION_ID.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����L�̍��ږ��ȊO�B" + 
                    " [�⍇�����.�⍇���R�[�h]�A[�⍇�����.���X�R�[�h]�A[�⍇�����.�ڋq�R�[�h]" + 
                    " [�⍇�����.�⍇������]�A[�⍇�����.�@@�\�h�c]" +
                    " [�\�[�g�L�[.�L�[����] = " + this.sortKeys[i].keyItem);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
