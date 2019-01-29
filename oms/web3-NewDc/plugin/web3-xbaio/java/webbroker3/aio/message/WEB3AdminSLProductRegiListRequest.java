head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegiListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�ꗗ���N�G�X�g�N���X(WEB3AdminSLProductRegiListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �����F (���u) �V�K�쐬 ���f�� 760
Revision History : 2007/11/08 �g�E�N�| (���u) ���f��822
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�S�ۖ����o�^�ꗗ���N�G�X�g)<BR>
 * �S�ۖ����o�^�ꗗ���N�G�X�g�N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminSLProductRegiListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductRegiListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_sl_product_regi_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709141544L;

    /**
     * (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F����<BR>
     * 2�F��<BR>
     * 3�F�����M��<BR>
     * 4�F�O����<BR>
     * 5�F����<BR>
     * 6�F�敨�I�v�V����<BR>
     * 7�F�ݐϓ���<BR>
     */
    public String productType;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�K�i�敪)<BR>
     * �K�i�敪<BR>
     * <BR>
     * 0�F�s�K�i<BR>
     * 1�F�K�i<BR>
     */
    public String qualifiedDiv;

    /**
     * (�K�p���ԋ敪)<BR>
     * �K�p���ԋ敪<BR>
     * <BR>
     * 0�F�K�p���Ԓ�<BR>
     * 1�F�K�p���ԊO<BR>
     */
    public String targetPeriodDiv;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3SLSortKey[] sortKeys;

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
     * @@roseuid 46E890840387
     */
    public WEB3AdminSLProductRegiListRequest() 
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �P�j �����^�C�v�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�����^�C�v != null and<BR>
     * ���N�G�X�g.�����^�C�v�����p�����ȊO<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02916 <BR>
     * <BR>
     * �Q�j �����R�[�h�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�����R�[�h != null and<BR>
     * ���N�G�X�g.�����R�[�h�����p�����ȊO<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00815<BR>
     * <BR>
     * �R�j�K�i�敪�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�K�i�敪 != null and<BR>
     * (���N�G�X�g.�K�i�敪�����p�����ȊO or<BR>
     * ���N�G�X�g.�K�i�敪.length() != 1)<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02925<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02931<BR>
     * <BR>
     * �S�j�K�p���ԋ敪�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�K�p���ԋ敪 != null and<BR>
     * (���N�G�X�g.�K�p���ԋ敪�����p�����ȊO or<BR>
     * ���N�G�X�g.�K�p���ԋ敪.length() != 1)<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02932<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02933<BR>
     * <BR>
     * <BR>
     * �T�j �v���y�[�W�ԍ��`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�v���y�[�W�ԍ� = null or<BR>
     * ���N�G�X�g.�v���y�[�W�ԍ��������ȊO or<BR>
     * ���N�G�X�g.�v���y�[�W�ԍ� <= 0<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00089<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00090<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00616<BR>
     * <BR>
     * �U�j�y�[�W���\���s���`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g.�y�[�W���\���s�� = null or<BR>
     * ���N�G�X�g.�y�[�W���\���s���������ȊO or<BR>
     * ���N�G�X�g.�y�[�W���\���s�� <= 0<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02224<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00092<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * �V�j�\�[�g�L�[�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�\�[�g�L�[ = null or<BR>
     * ���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00231<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00232<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D7FC5A0020
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �����^�C�v�`�F�b�N  ���N�G�X�g.�����^�C�v != null
        //and ���N�G�X�g.�����^�C�v�����p�����ȊO �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isEmpty(this.productType)
            && !WEB3StringTypeUtility.isDigit(this.productType))
        {
            log.debug("�����^�C�v�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02916,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�C�v�����p�����ȊO�̒l�ł��B");
        }

        //�Q�j �����R�[�h�`�F�b�N  ���N�G�X�g.�����R�[�h != null
        //and  ���N�G�X�g.�����R�[�h�����p�����ȊO �̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isEmpty(this.productCode)
            && !WEB3StringTypeUtility.isDigit(this.productCode))
        {
            log.debug("�����R�[�h�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�������ȊO�̒l�ł��B");
        }

        //�R�j�K�i�敪�`�F�b�N  ���N�G�X�g.�K�i�敪 != null and
        //(���N�G�X�g.�K�i�敪�����p�����ȊO or  
        //���N�G�X�g.�K�i�敪.length() != 1)  
        //�̏ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isEmpty(this.qualifiedDiv)
            && !WEB3StringTypeUtility.isDigit(this.qualifiedDiv))
        {
            log.debug("�K�i�敪�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02925,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�i�敪�����p�����ȊO�̒l�ł��B");
        }
        if (!WEB3StringTypeUtility.isEmpty(this.qualifiedDiv)
            && this.qualifiedDiv.length() != 1)
        {
            log.debug("�K�i�敪�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02931,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�i�敪�̃T�C�Y���s���ł��B");
        }

        //�S�j�K�p���ԋ敪�`�F�b�N  ���N�G�X�g.�K�p���ԋ敪 != null and
        //(���N�G�X�g.�K�p���ԋ敪�����p�����ȊO or
        //���N�G�X�g.�K�p���ԋ敪.length() != 1)�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isEmpty(this.targetPeriodDiv)
             && !WEB3StringTypeUtility.isDigit(this.targetPeriodDiv))
        {
            log.debug("�K�p���ԋ敪�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02932,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p���ԋ敪�����p�����ȊO�̒l�ł��B");
        }
        if (!WEB3StringTypeUtility.isEmpty(this.targetPeriodDiv)
                && this.targetPeriodDiv.length() != 1)
        {
            log.debug("�K�p���ԋ敪�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02933,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p���ԋ敪�̃T�C�Y���s���ł��B");
        }

        //�T�j �v���y�[�W�ԍ��`�F�b�N ���N�G�X�g.�v���y�[�W�ԍ� = null or
        //���N�G�X�g.�v���y�[�W�ԍ��������ȊO or
        //���N�G�X�g.�v���y�[�W�ԍ� <= 0  �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�U�j�y�[�W���\���s���`�F�b�N  ���N�G�X�g.�y�[�W���\���s�� = null or
        //���N�G�X�g.�y�[�W���\���s���������ȊO or
        //���N�G�X�g.�y�[�W���\���s�� <= 0 �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        
        //�V�j�\�[�g�L�[�`�F�b�N ���N�G�X�g�f�[�^.�\�[�g�L�[ = null
        //or  ���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0 �̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        if (this.sortKeys.length == 0)
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

    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �S�ۖ����o�^�ꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLProductRegiListResponse(this);
    }
}
@
