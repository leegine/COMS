head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍����ʃ��N�G�X�g�N���X(WEB3AdminInformCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 ������(���u) �쐬
Revesion History    : 2007/05/11 ������ (���u) ���f��No.34
Revesion History    : 2007/05/29 �Ӑ� (���u) ���f��No.59
Revesion History    : 2007/06/11 ���؎q (���u) ���f��No.75
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�A����񌟍����ʃ��N�G�X�g)<BR>
 * �A����񌟍����ʃ��N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformCommonRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformCommonRequest.class);
    
    /**
     * (�A�����)<BR>
     * �A�����
     */
    public String informType;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��
     */
    public String[] branchCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * (��t�����i���j)<BR>
     * ��t�����i���j
     */
    public Date receptionDateFrom;
    
    /**
     * (��t�����i���j)<BR>
     * ��t�����i���j
     */
    public Date receptionDateTo;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h
     */
    public String traderCode;
    
    /**
     * (�`�[�쐬���)<BR>
     * �`�[�쐬���
     */
    public String[] voucherInfoList;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * �L�[���ڂ͈ȉ��̂Ƃ���<BR>
     * <BR>
     * �E���ʃR�[�h<BR>
     * �E���X�R�[�h<BR>
     * �E�ڋq�R�[�h<BR>
     * �E��t����
     */
    public WEB3InformSortKey[] sortKeys;
    
    /**
     * @@roseuid 41EE625A0399
     */
    public WEB3AdminInformCommonRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�A�����<BR>
     * <BR>
     *    this.�A����� == null or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01817<BR>
     *    this.�A����� != ���p�p���� or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_02778<BR>
     *    this.�A�����.length() != 2<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01819<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j���X�R�[�h<BR>
     * <BR>
     * �Q�|�P�j<BR>
     * <BR>
     *    this.���X�R�[�h == null or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00833<BR>
     *    this.���X�R�[�h.length() == 0<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01757<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�|�Q�j���X�R�[�h�̊e�v�f�ɂ��ă`�F�b�N����B<BR>
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
     * �R�j���ʃR�[�h<BR>
     * <BR>
     *    this.���ʃR�[�h != null and<BR>
     *    ( this.���ʃR�[�h != ���p���� or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01820<BR>
     *    this.���ʃR�[�h != null and<BR>
     *    this.���ʃR�[�h.length() > 13 )<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01821<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     *    this.�ڋq�R�[�h != null and<BR>
     *    ( this.�ڋq�R�[�h != ���p���� or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01043<BR>
     *    this.�ڋq�R�[�h != null and<BR>
     *    this.�ڋq�R�[�h.length() > 6 )<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00836<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �T�j�ڋq��<BR>
     * <BR>
     *    this.�ڋq�� != null and<BR>
     *    this.�ڋq�� != �S�p����<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01691<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �U�j��t����<BR>
     * <BR>
     *    this.��t�����i���j != null and<BR>
     *    this.��t�����i���j != null and<BR>
     *    this.��t�����i���j > this.��t�����i���j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01822<BR>
     * <BR>
     * �V�j�����R�[�h<BR>
     * <BR>
     *  �@@this.�����R�[�h != null and<BR>
     *  �@@this.�����R�[�h != ���p����<BR>
     * <BR>
     *  �@@�̏ꍇ�A��O���X���[����B<BR>
     *  �@@class: WEB3BusinessLayerException<BR>
     *  �@@tag: BUSINESS_ERROR_01067<BR>
     * <BR>
     * �W�j���҃R�[�h<BR>
     * <BR>
     *  �@@this.���҃R�[�h != null and<BR>
     *  �@@this.���҃R�[�h != ���p����<BR>
     *  �@@class: WEB3BusinessLayerException<BR>
     *  �@@tag: BUSINESS_ERROR_02782<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �X�j�\�[�g�L�[<BR>
     * <BR>
     * �X�|�P�j <BR>
     * <BR>
     *    this.�\�[�g�L�[ == null or <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00231<BR>
     *    this.�\�[�g�L�[.length() == 0 <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00232<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �X�|�Q�j�\�[�g�L�[�̊e�v�f�ɂ���<BR>
     * <BR>
     *   �L�[���� != (�f���X�R�[�h�f or �f���ʃR�[�h�f or �f�ڋq�R�[�h�f or �f��t�����f) or<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00086<BR>
     *   ����/�~�� != ('A' or 'D')<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00088<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * @@roseuid 41B93F2B00C6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�A�����<BR>
        //  this.�A����� == null�̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01817<BR>
        if (this.informType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817, 
                getClass().getName() + "validate",
                "�A����ʂ����w��ł��B");
        }

        //  this.�A����� != ���p�p�����̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_02778<BR>
        if (!WEB3StringTypeUtility.isLetterOrDigit(this.informType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02778, 
                getClass().getName() + "validate",
                "�A����ʂ����p�p�����ȊO�̒l�ł��B");
        }

        //  this.�A�����.length() != 2�̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01819<BR>
        if (this.informType.length() != 2)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01819, 
                getClass().getName() + "validate",
                "�A����ʂ̃T�C�Y���s���ł��B");
        }

        //�Q�j���X�R�[�h<BR>
        // �Q�|�P�j<BR>
        //  this.���X�R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //�@@    tag: BUSINESS_ERROR_00833<BR>
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                getClass().getName() + "validate",
                "���X�R�[�h�����w��ł��B");
        }

        //  this.���X�R�[�h.length() == 0�̏ꍇ�A��O���X���[����B<BR>
        //    �@@class: WEB3BusinessLayerException<BR>
        //    �@@tag: BUSINESS_ERROR_01757<BR>
        if (this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757, 
                getClass().getName() + "validate",
                "���X�R�[�h�̗v�f�����O�ł��B");
        }

        int l_intBranchCodeLth = this.branchCode.length;
        for (int i=0; i<l_intBranchCodeLth; i++)
        {
            // �Q�|�Q�j���X�R�[�h�̊e�v�f�ɂ��ă`�F�b�N����B<BR>
            //  ���X�R�[�h != ���p���� �̏ꍇ�A��O���X���[����B<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_01729<BR>
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729, 
                    getClass().getName() + "validate",
                    "���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
            }

            //  ���X�R�[�h.length() != 3�̏ꍇ�A��O���X���[����B<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_00834<BR>
            if (this.branchCode[i].length() != 3)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                    getClass().getName() + "validate",
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }

        //�R�j���ʃR�[�h<BR>
        //  this.���ʃR�[�h != null����<BR>
        //  this.���ʃR�[�h != ���p���� �̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01820<BR>
        if (this.requestNumber != null
            && !WEB3StringTypeUtility.isDigit(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01820, 
                getClass().getName() + "validate",
                "���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
        }

        //  this.���ʃR�[�h != null����<BR>
        //  this.���ʃR�[�h.length() > 13 �̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01821<BR>
        if (this.requestNumber != null
            && this.requestNumber.length() > 13)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01821, 
                getClass().getName() + "validate",
                "���ʃR�[�h�̃T�C�Y���s���ł��B");
        }

        //�S�j�ڋq�R�[�h<BR>
        //  this.�ڋq�R�[�h != null����<BR>
        //  this.�ڋq�R�[�h != ���p���� �̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01043<BR>
        if (this.accountCode != null
            && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                getClass().getName() + "validate",
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        //  this.�ڋq�R�[�h != null����<BR>
        //  this.�ڋq�R�[�h.length() > 6 �̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_00836<BR>
        if (this.accountCode != null
            && this.accountCode.length() > 6)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                getClass().getName() + "validate",
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�T�j�ڋq��<BR>
        //  this.�ڋq�� != null����<BR>
        //  this.�ڋq�� != �S�p�����̏ꍇ�A��O���X���[����B<BR>
        //    �@@class: WEB3BusinessLayerException<BR>
        //    �@@tag: BUSINESS_ERROR_01691<BR>
        if (this.accountName != null
            && !WEB3StringTypeUtility.isMulti(this.accountName))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01691, 
                getClass().getName() + "validate",
                "�ڋq�����S�p�����ł͂���܂���B");
        }

        //�U�j��t����<BR>
        //  this.��t�����i���j != null and<BR>
        //  this.��t�����i���j != null and<BR>
        //  this.��t�����i���j > this.��t�����i���j<BR>
        //  �̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01822<BR>
        if (this.receptionDateFrom != null
            && this.receptionDateTo != null
            && WEB3DateUtility.compare(this.receptionDateFrom, this.receptionDateTo) > 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01822, 
                getClass().getName() + "validate",
                "��t�����i���j�͎�t�����i���j���傫���ł��B");
        }

        // �V�j�����R�[�h
        // this.�����R�[�h != null and this.�����R�[�h != ���p����
        // �̏ꍇ�A��O���X���[����B
        if (this.productCode != null && !WEB3StringTypeUtility.isDigit(this.productCode))
        {
            log.debug("�����R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�̓��͂��s���ł��B");
        }
        
        // �W�j���҃R�[�h
        // this.���҃R�[�h != null and this.���҃R�[�h != ���p����
        // �̏ꍇ�A��O���X���[����B
        if (this.traderCode != null && !WEB3StringTypeUtility.isDigit(this.traderCode))
        {
            log.debug("���҃R�[�h�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02782,
                this.getClass().getName() + STR_METHOD_NAME,
                "���҃R�[�h�G���[�B");
        }

        //�X�j�\�[�g�L�[<BR>
        // �X�|�P�j <BR>
        //  this.�\�[�g�L�[ == null �̏ꍇ�A��O���X���[����B<BR>
        //    �@@class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_00231<BR>
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                getClass().getName() + "validate",
                "�\�[�g�L�[�����w��ł��B");
        }

        //  this.�\�[�g�L�[.length() == 0 �̏ꍇ�A��O���X���[����B<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_00232<BR>
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                getClass().getName() + "validate",
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        int l_intSortKeysLth = this.sortKeys.length;
        for (int i=0; i<l_intSortKeysLth; i++)
        {
            // �X�|�Q�j�\�[�g�L�[�̊e�v�f�ɂ���<BR>
            //  �L�[���� != (�f���X�R�[�h�f or �f���ʃR�[�h�f or �f�ڋq�R�[�h�f or �f��t�����f) or<BR>
            //  �̏ꍇ�A��O���X���[����B<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_00086<BR>
            if (!WEB3InformKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3InformKeyItemDef.REQUEST_NUMBER.equals(this.sortKeys[i].keyItem)
                && !WEB3InformKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3InformKeyItemDef.ACCEPT_TIME.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + "validate",
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }

            //  ����/�~�� != ('A' or 'D')�̏ꍇ�A��O���X���[����B<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_00088<BR>
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    getClass().getName() + "validate",
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
