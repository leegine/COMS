head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g�ꗗ���N�G�X�g�N���X(WEB3AdminAioSettleReportListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioSettleReportSortkeyDef;
import webbroker3.aio.define.WEB3AioTransactionStatusDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���ϘA�g���|�[�g�ꗗ���N�G�X�g)<BR>
 * ���ϘA�g���|�[�g�ꗗ���N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportListRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121545L;       

    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (���ϋ@@��ID)<BR>
     * ��ʂɂđI�����ꂽ���ϋ@@��ID
     */
    public String paySchemeId;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�������i���j
     */
    public Date minOrtderDate;
    
    /**
     * (�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�������i���j
     */
    public Date maxOrtderDate;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (.com�f�r�b�g���ώ���ԍ��i���j)<BR>
     * ��ʂɂē��͂��ꂽ���ώ���ԍ��i���j
     */
    public String minComDebitNumber;
    
    /**
     * (.com�f�r�b�g���ώ���ԍ��i���j)<BR>
     * ��ʂɂē��͂��ꂽ���ώ���ԍ��i���j
     */
    public String maxComDebitNumber;
    
    /**
     * (�������)<BR>
     * ��ʂɂđI�����ꂽ�������<BR>
     * <BR>
     * �O�F������<BR>
     * �P�F���ϊ���<BR>
     * �Q�F���ϒ��~<BR>
     * �R�F�G���[<BR>
     * �S�F�S��<BR>
     */
    public String transactionStatus;
    
    /**
     * (�v���y�[�W�ԍ�)
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)
     */
    public String pageSize;  
    
    /**
     * (�\�[�g�L�[)
     * AIO�\�[�g�L�[�̔z�� <BR>
     * ���L�[���� <BR>
     * �E�ڋq�R�[�h <BR>
     * �E.com�f�r�b�g���ώ���ԍ� <BR>
     * �E�����X�����ԍ� <BR>
     * �E��t���� <BR>
     */
    public WEB3AioSortKeyUnit[] sortKeys;  
        
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.���X�R�[�h = null or<BR>
     *   ���N�G�X�g�f�[�^.���X�R�[�h.length() != 3<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * <BR>
     * �Q�j���ϋ@@��ID�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.���ϋ@@��ID = null or<BR>
     *   ���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or<BR>
     *   ���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith("ComOndebi") = false
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00767<BR>
     * <BR>
     * <BR>
     * �R�j�ڋq�R�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h != null and<BR>
     *   (���N�G�X�g�f�[�^.�ڋq�R�[�h�ɐ����ȊO�̕��������� or<BR>
     *    ���N�G�X�g�f�[�^.�ڋq�R�[�h.length() != 6)<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * <BR>
     * �S�j�������`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�������i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�������i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�������i���j > ���N�G�X�g�f�[�^.�������i���j<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00781<BR>
     * <BR>
     * <BR>
     * �T�j.com�f�r�b�g���ώ���ԍ��`�F�b�N<BR>
     * �T�|�P�j
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and<BR>
     *   (���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() != 15 or<BR>
     *    ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�ɐ����ȊO�̕������܂܂��)<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00782<BR>
     * <BR>
     * <BR>
     * �T�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and<BR>
     *   (���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() != 15 or<BR>
     *    ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�ɐ����ȊO�̕������܂܂��)<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00787<BR>
     * <BR>
     * <BR>
     * �T�|�R�j<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j > ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00783<BR>
     * <BR>
     * <BR>
     * �T�|�S�j<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null and<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00788<BR>
     * <BR>
     * <BR>
     * �U�j������ԃ`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.������� != �i�S�āj and<BR>
     *   ���N�G�X�g�f�[�^.������� != �i���ϊ����j and<BR>
     *   ���N�G�X�g�f�[�^.������� != �i�������j and<BR>
     *   ���N�G�X�g�f�[�^.������� != �i�G���[�j and<BR>
     *   ���N�G�X�g�f�[�^.������� != �i���ϒ��~�j<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00784<BR>
     * <BR>
     * <BR>
     * �V�j���݃`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h = null and<BR>
     *   ���N�G�X�g�f�[�^.�������i���j = null and<BR>
     *   ���N�G�X�g�f�[�^.�������i���j = null and<BR>
     *   ���N�G�X�g�f�[�^.��n�� = null and<BR>
     *   ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00785<BR>
     * <BR>
     * <BR>
     * �W�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �W�|�P�j<BR>
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null or<BR>
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00786<BR>
     * <BR>
     * <BR>
     * �W�|�Q�j
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕���������  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * <BR>
     * �X�j�y�[�W���\���s���`�F�b�N<BR>
     * �X�|�P�j<BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * �X�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���������  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �P�O�j�\�[�g�L�[�`�F�b�N <BR>
     * <BR>
     * �P�O�|�P�j <BR>
     * <BR>
     *  ���N�G�X�g�f�[�^.�\�[�g�L�[ = null or <BR>
     *  ���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0 <BR>
     * <BR>
     *  �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �P�O�|�Q�j�\�[�g�L�[�̊e�v�f�ɂ��āA�ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�O�|�Q�|�P�j�L�[���ڂ̃`�F�b�N <BR>
     * <BR>
     *  �\�[�g�L�[.�L�[���ڂ��ȉ��̍��ڈȊO�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �E�ڋq�R�[�h <BR>
     * �E.com�f�r�b�g���ώ���ԍ� <BR>
     * �E�����X�����ԍ� <BR>
     * �E��t���� <BR>
     * <BR>
     * �P�O�|�Q�|�Q�j����/�~���̃`�F�b�N <BR>
     * <BR>
     * �\�[�g�L�[.����/�~�� != ('A' or 'D') <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * @@roseuid 40E530EB038C
     */
    public void validate() throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�R�[�h�`�F�b�N 
        //���N�G�X�g�f�[�^.���X�R�[�h = null or 
        //���N�G�X�g�f�[�^.���X�R�[�h.length() != 3 

        if (WEB3StringTypeUtility.isEmpty(this.branchCode) ||
            this.branchCode.length() != 3)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���X�R�[�h = null or " +
                "���N�G�X�g�f�[�^.���X�R�[�h.length() != 3" );
        }

        //�Q�j���ϋ@@��ID�`�F�b�N 
        //���N�G�X�g�f�[�^.���ϋ@@��ID = null or 
        //���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or 
        //���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith("ComOndebi") = false 

        if (WEB3StringTypeUtility.isEmpty(this.paySchemeId) || 
            this.paySchemeId.length() != 11 ||
            !this.paySchemeId.startsWith("ComOndebi"))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���ϋ@@��ID = null or " +
                "���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or " +
                "���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith(ComOndebi) = false");
        }
            
        //�R�j�ڋq�R�[�h�`�F�b�N
        //���N�G�X�g�f�[�^.�ڋq�R�[�h != null and 
        //(���N�G�X�g�f�[�^.�ڋq�R�[�h�ɐ����ȊO�̕��������� or 
        //���N�G�X�g�f�[�^.�ڋq�R�[�h.length() != 6)

        //=======remain zhou-yong NO.1 begin ============
        
        boolean l_blnIsDigit = WEB3StringTypeUtility.isDigit(this.accountCode);
        
        if (WEB3StringTypeUtility.isNotEmpty(this.accountCode) &&
            (!l_blnIsDigit || this.accountCode.length() != 6))
        {            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�ɐ����ȊO�̕��������� or " +
                "���N�G�X�g�f�[�^.�ڋq�R�[�h.length() != 6, " +
                "���N�G�X�g�f�[�^.�ڋq�R�[�h = " + this.accountCode +  
                "���N�G�X�g�f�[�^.�ڋq�R�[�h.length() = " + this.accountCode.length());
        }
        
        //=======remain zhou-yong NO.1 end ============
        
        //�S�j�������`�F�b�N 
        //���N�G�X�g�f�[�^.�������i���j != null and 
        //���N�G�X�g�f�[�^.�������i���j != null and 
        //���N�G�X�g�f�[�^.�������i���j > ���N�G�X�g�f�[�^.�������i���j 

        if (this.minOrtderDate != null &&
            this.maxOrtderDate != null &&
            this.minOrtderDate.compareTo(this.maxOrtderDate) > 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00781,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������i���j[" + this.minOrtderDate + "] " +
                "> ���N�G�X�g�f�[�^.�������i���j[" + this.maxOrtderDate + "]");
        }
        
        //�T�j.com�f�r�b�g���ώ���ԍ��`�F�b�N 
        //�T�|�P�j 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //(���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() != 15 or 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�ɐ����ȊO�̕������܂܂��) 
               
        l_blnIsDigit = WEB3StringTypeUtility.isDigit(this.minComDebitNumber);
        
        if (WEB3StringTypeUtility.isNotEmpty(this.minComDebitNumber) &&
            (this.minComDebitNumber.length() != 15 ||
            !l_blnIsDigit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00782,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() != 15 or " +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�ɐ����ȊO�̕������܂܂��," +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() = " + this.minComDebitNumber.length() +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j= " + this.minComDebitNumber);
        }
        
        //�T�|�Q�j        
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //(���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() != 15 or 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�ɐ����ȊO�̕������܂܂��)
        l_blnIsDigit = WEB3StringTypeUtility.isDigit(this.maxComDebitNumber);
         
        if (WEB3StringTypeUtility.isNotEmpty(this.maxComDebitNumber) &&
            (this.maxComDebitNumber.length() != 15 ||
            !l_blnIsDigit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00787,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length() != 15 or " +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�ɐ����ȊO�̕������܂܂�, " +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j.length()= " + this.maxComDebitNumber.length() + 
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j= " + this.maxComDebitNumber);
        }
        
        //�T�|�R�j 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j > ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j
        if (WEB3StringTypeUtility.isNotEmpty(this.minComDebitNumber) &&
            WEB3StringTypeUtility.isNotEmpty(this.maxComDebitNumber) &&
            Double.parseDouble(this.minComDebitNumber) > 
            Double.parseDouble(this.maxComDebitNumber))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00783,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j[" + this.minComDebitNumber + "] " +
                "> ���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j[" + this.maxComDebitNumber + "]");
        }
        
        //�T�|�S�j
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null and 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null 
        if (WEB3StringTypeUtility.isEmpty(this.minComDebitNumber) &&
            WEB3StringTypeUtility.isNotEmpty(this.maxComDebitNumber))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00788,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j= null and " +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null, " +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = " + this.maxComDebitNumber);
        }
        
        //�U�j������ԃ`�F�b�N
        //���N�G�X�g�f�[�^.������� != �i�S�āj and 
        //���N�G�X�g�f�[�^.������� != �i���ϊ����j and 
        //���N�G�X�g�f�[�^.������� != �i�������j and 
        //���N�G�X�g�f�[�^.������� != �i�G���[�j and 
        //���N�G�X�g�f�[�^.������� != �i���ϒ��~�j 
                
        if (!WEB3AioTransactionStatusDef.ALL.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.SETTLE_COMPLETE.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.NOT_TRANSACTION.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.ERROR.equals(this.transactionStatus) &&
            !WEB3AioTransactionStatusDef.SETTLE_STOP.equals(this.transactionStatus))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00784,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.������� != (�i�S�āj,�i���ϊ���), �i�������j,�i�G���[�j,�i���ϒ��~�j), " +
                "���N�G�X�g�f�[�^.������� = " + this.transactionStatus);
        }

        //�V�j���݃`�F�b�N 
        //���N�G�X�g�f�[�^.�ڋq�R�[�h = null and 
        //���N�G�X�g�f�[�^.�������i���j = null and 
        //���N�G�X�g�f�[�^.�������i���j = null and 
        //���N�G�X�g�f�[�^.��n�� = null and 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null 
        if (WEB3StringTypeUtility.isEmpty(this.accountCode) &&
            this.minOrtderDate == null &&
            this.maxOrtderDate == null &&
            this.deliveryDate == null &&
            WEB3StringTypeUtility.isEmpty(this.minComDebitNumber))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00785,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�ڋq�R�[�h = null and " +
                "���N�G�X�g�f�[�^.�������i���j = null and " +
                "���N�G�X�g�f�[�^.�������i���j = null and " +
                "���N�G�X�g�f�[�^.��n�� = null and " +
                "���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null");
        }
        
        //�W�j�v���y�[�W�ԍ��`�F�b�N 

        //�W�|�P�j
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null        
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))            
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null");
        }

        //�W�|�Q�j 
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕��������� 
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕���, " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = " + this.pageIndex);
        }
        
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0, " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = " + this.pageIndex);
        }
        
        //�X�j�y�[�W���\���s���`�F�b�N 

        //�X�|�P�j
        //���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or 
        //���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0 
        
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or " +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0");
        }
        
        //�X�|�Q�j 
        //���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���������
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���," +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� =" + this.pageSize);
        }
        
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s�� <= 0," +
                "�y�[�W���\���s�� = " + this.pageSize);
        }
        
        //�P�O�j�\�[�g�L�[�`�F�b�N 
        //�P�O�|�P�j 
        //���N�G�X�g�f�[�^.�\�[�g�L�[ = null �̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");            
        }

        //���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0 �̏ꍇ�A��O���X���[����B 
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�P�O�|�Q�j�\�[�g�L�[�̊e�v�f�ɂ��āA�ȉ��̃`�F�b�N���s���B 
        //�P�O�|�Q�|�P�j�L�[���ڂ̃`�F�b�N 
        for (int i = 0; i < this.sortKeys.length; i++)
        {           
            //�\�[�g�L�[.�L�[���ڂ��ȉ��̍��ڈȊO�̏ꍇ�A��O���X���[����B 
            //�E�ڋq�R�[�h 
            //�E.com�f�r�b�g���ώ���ԍ� 
            //�E�����X�����ԍ� 
            //�E��t����
            if ((!WEB3AioSettleReportSortkeyDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AioSettleReportSortkeyDef.COMDEBIT_NUMBER.equals(this.sortKeys[i].keyItem)
                && !WEB3AioSettleReportSortkeyDef.SHOP_ORDERID.equals(this.sortKeys[i].keyItem)
                && !WEB3AioSettleReportSortkeyDef.RECEPTION_DATE.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");                        
            }
            //�P�O�|�Q�|�Q�j����/�~���̃`�F�b�N 
            //�\�[�g�L�[.����/�~�� != ('A' or 'D') 
            //�̏ꍇ�A��O���X���[����B 
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B"); 
            }
        }
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB670202
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioSettleReportListResponse(this);
    }
}
@
