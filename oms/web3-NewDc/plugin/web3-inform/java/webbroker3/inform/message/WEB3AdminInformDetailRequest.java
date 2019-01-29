head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��ڍ׃��N�G�X�g�N���X(WEB3AdminInformDetailRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
Revesion History    : 2007/05/29 �Ӑ� (���u) ���f��No.60
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�A����񌟍��ڍ׃��N�G�X�g)<BR>
 * �A����񌟍��ڍ׃��N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformDetailRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformDetailRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (�A�����)<BR>
     * �A�����
     */
    public String informType;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;
    
    /**
     * @@roseuid 41EE625B00BB
     */
    public WEB3AdminInformDetailRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformDetailResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�A�����<BR>
     * <BR>
     *    this.�A����� == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01817<BR>
     *    this.�A����� != ���p�p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02778<BR>
     *    this.�A�����.length() != 2<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01819<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j���X�R�[�h<BR>
     * <BR>
     *    this.���X�R�[�h == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00833<BR>
     *    this.���X�R�[�h != ���p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01729<BR>
     *    this.���X�R�[�h.length() != 3<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00834<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �R�j���ʃR�[�h<BR>
     * <BR>
     *    this.���ʃR�[�h == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00829<BR>
     *    this.���ʃR�[�h != ���p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01820<BR>
     *    this.���ʃR�[�h.length() != 13<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01821<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B
     * @@roseuid 41B93F3002F8
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
        //  this.���X�R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
        // �@@�@@ class: WEB3BusinessLayerException<BR>
        // �@@�@@ tag: BUSINESS_ERROR_00833<BR>
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                getClass().getName() + "validate",
                "���X�R�[�h�����w��ł��B");
        }

        //  this.���X�R�[�h != ���p�����̏ꍇ�A��O���X���[����B<BR>
        // �@@�@@  class: WEB3BusinessLayerException<BR>
        // �@@�@@  tag: BUSINESS_ERROR_01729<BR>
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729, 
                getClass().getName() + "validate",
                "���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
        }

        //  this.���X�R�[�h.length() != 3�̏ꍇ�A��O���X���[����B<BR>
        // �@@  �@@class: WEB3BusinessLayerException<BR>
        // �@@  �@@tag: BUSINESS_ERROR_00834<BR>
        if (this.branchCode.length() != 3)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                getClass().getName() + "validate",
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�R�j���ʃR�[�h<BR>
        //  this.���ʃR�[�h == null�̏ꍇ�A��O���X���[����B<BR>
        //    �@@class: WEB3BusinessLayerException<BR>
        //  �@@�@@tag: BUSINESS_ERROR_00829<BR>
        if (this.requestNumber == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829, 
                getClass().getName() + "validate",
                "���ʃR�[�h�����w��ł��B");
        }

        //  this.���ʃR�[�h != ���p�����̏ꍇ�A��O���X���[����B<BR>
        //  �@@�@@class: WEB3BusinessLayerException<BR>
        //  �@@�@@tag: BUSINESS_ERROR_01820<BR>
        if (!WEB3StringTypeUtility.isDigit(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01820, 
                getClass().getName() + "validate",
                "���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
        }

        //  this.���ʃR�[�h.length() != 13�̏ꍇ�A��O���X���[����B<BR>
        //  �@@�@@class: WEB3BusinessLayerException<BR>
        //  �@@�@@tag: BUSINESS_ERROR_01821<BR>
        if (this.requestNumber.length() != 13)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01821, 
                getClass().getName() + "validate",
                "���ʃR�[�h�̃T�C�Y���s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
