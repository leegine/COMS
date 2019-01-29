head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�ɗ������N�G�X�g(WEB3AioInputOutputHistoryListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 䈋� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioInputOutputSortkeyItemDef;
import webbroker3.aio.define.WEB3InformProductGroupDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���o�ɗ������N�G�X�g)<BR>
 * ���o�ɗ������N�G�X�g�N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryListRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioInputOutputHistoryListRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_inputOutputHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501271304L;

    /**
     * (�\�����ԁi���j)<BR>
     * �\�����ԁi���j<BR>
     * <BR>
     * �����j���[��ʂ���̍ŏ��̃��N�G�X�g�̎��́Anull
     */
    public Date displayStartDate;

    /**
     * (�\�����ԁi���j)<BR>
     * �\�����ԁi���j<BR>
     * <BR>
     * �����j���[��ʂ���̍ŏ��̃��N�G�X�g�̎��́Anull
     */
    public Date displayEndDate;

    /**
     * (���i�O���[�v)<BR>
     * ���i�O���[�v<BR>
     * <BR>
     * Z�F �S���i<BR>
     * A�F ����<BR>
     * B�F �O������<BR>
     * C�F ���M<BR>
     * D�F ��<BR>
     */
    public String productGroup;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;

    /**
     * (�o���O���[�v)<BR>
     * �o���O���[�v<BR>
     * <BR>
     * Z�F ���o��<BR>
     * A�F �o��<BR>
     * B�F ����
     */
    public String inputOutputGroup;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     */
    public WEB3AioSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 41EC84F800AB
     */
    public WEB3AioInputOutputHistoryListRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�\�����ԁi���j�ƕ\�����ԁi���j�̐�����<BR>
     * <BR>
     * �P�|�P�j<BR>
     *   this.�\�����ԁi���j != null and<BR>
     *   this.�\�����ԁi���j != null and<BR>
     *   this.�\�����ԁi���j > this.�\�����ԁi���j<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01051 <BR>
     * <BR>
     * �P�|�Q�j<BR>
     *   ( this.�\�����ԁi���j != null and this.�\�����ԁi���j == null ) or<BR>
     *   ( this.�\�����ԁi���j == null and this.�\�����ԁi���j != null )<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01795 <BR>
     * <BR>
     * �Q�j�����R�[�h<BR>
     * <BR>
     * �Q�|�P�j<BR>
     *   this.���i�O���[�v != ('Z'�i�S���i�j or 'A'�i�����j�j and<BR>
     *   this.�����R�[�h != null<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01814 <BR>
     * <BR>
     * �Q�|�Q�j<BR>
     *   this.�����R�[�h.length() != (4 or 5)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00439 <BR>
     * <BR>
     * �Q�|�R�j<BR>
     *   this.�����R�[�h != ����<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00815<BR>
     * <BR>
     * �R�j�v���y�[�W�ԍ�<BR>
     * <BR>
     *   this.�v���y�[�W�ԍ� == null or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00089<BR>
     *   this.�v���y�[�W�ԍ� != ���� or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00090<BR>
     *   this.�v���y�[�W�ԍ� <= 0<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �S�j�y�[�W���\���s��<BR>
     * <BR>
     *   this.�y�[�W���\���s�� == null or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00091<BR>
     *   this.�y�[�W���\���s�� != ���� or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00092<BR>
     *   this.�y�[�W���\���s�� <= 0<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �T�j�\�[�g�L�[<BR>
     * <BR>
     * �T�|�P�j<BR>
     *       this.�\�[�g�L�[ == null�@@or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00231<BR>
     *   �@@�@@this.�\�[�g�L�[�̗v�f�� == 0 <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * 
     * <BR>
     * �T�|�Q�j<BR>
     *   �z��̊e�v�f�ɂ���<BR>
     * <BR>
     *   �L�[���� != (�f��n���f or �f���i�O���[�v�f or �f�����R�[�h�f) or<BR>
     *   ����/�~�� != ('A' or 'D')<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00086
     * @@roseuid 41B6D9FA02D5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        //�P�j�\�����ԁi���j�ƕ\�����ԁi���j�̐�����
        //�P�|�P�jthis.�\�����ԁi���j != null and
        //this.�\�����ԁi���j != null and
        //this.�\�����ԁi���j > this.�\�����ԁi���j
        //�̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_01051 
        if ((this.displayStartDate != null) && (this.displayEndDate != null) 
            && (WEB3DateUtility.compareToDay(this.displayStartDate,this.displayEndDate) > 0))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01051, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B");  
        }
        //�P�|�Q�j
        //( this.�\�����ԁi���j != null and this.�\�����ԁi���j == null ) or
        //( this.�\�����ԁi���j == null and this.�\�����ԁi���j != null )
        //�̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_01795 
        if (((this.displayStartDate != null) && (this.displayEndDate == null)) 
            || ((this.displayStartDate == null) && (this.displayEndDate != null)))
        {            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01795,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�����ԁi���j�ƕ\�����ԁi���j�͂����ꂩ�����͂���鎞�A�c���������͂����͂��ł��B");
        }
        
        //�Q�j�����R�[�h
        //�Q�|�P�j
        //this.���i�O���[�v != ('Z'�i�S���i�j or 'A'�i�����j�j and
        //this.�����R�[�h != null
        //�̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_01814 
        if ((!WEB3InformProductGroupDef.ALL_PRODUCT.equals(this.productGroup) && 
            !WEB3InformProductGroupDef.EQUITY.equals(this.productGroup)) && this.productCode != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01814,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�Łh�S���i�h�A�h�����h�ȊO���I������Ă���ꍇ�́A�����R�[�h�͎w��ł��܂���B");
        }
        //�Q�|�Q�j
        //this.�����R�[�h.length() != (4 or 5)
        //�̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00439 
        if ((this.productCode != null) && (this.productCode.length() !=4) && (this.productCode.length() != 5))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�̃T�C�Y���s���ł��B");
        }
        //�Q�|�R�j
        //this.�����R�[�h != ����
        //�̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00815
        if ((this.productCode != null) && !WEB3StringTypeUtility.isInteger(this.productCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�������ȊO�̒l�ł��B");
        }
        //�R�j�v���y�[�W�ԍ�
        //  this.�v���y�[�W�ԍ� == null or
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00089
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        //  this.�v���y�[�W�ԍ� != ���� or
        //       class: WEB3BusinessLayerException
        //       tag:   BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        //   this.�v���y�[�W�ԍ� <= 0
        //      class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00616
        //  �̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        //�S�j�y�[�W���\���s��
        // this.�y�[�W���\���s�� == null or
        //   class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00091
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        // this.�y�[�W���\���s�� != ���� or
        //       class: WEB3BusinessLayerException
        //       tag:   BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        //this.�y�[�W���\���s�� <= 0
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00617
        //  �̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        // �T�j�\�[�g�L�[
        // �T�|�P�j
        //   this.�\�[�g�L�[ == null
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00231
        //  �̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");            
        }

        //  this.�\�[�g�L�[�̗v�f�� == 0 or
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }


        //�T�|�Q�j�z��̊e�v�f�ɂ��� �L�[����
        //!= (�f ��n�� �f or �f ���i�O���[�v �f or �f �����R�[�h �f)or  ���� / �~��
        //!= ('A' or 'D')
        // �̏ꍇ �A ��O���X���[���� �B
        //class : WEB3BusinessLayerException
        //tag : BUSINESS_ERROR_00086 
        int l_length = this.sortKeys.length;
        for (int i = 0; i < l_length; i++)
        {
            if ((!WEB3AioInputOutputSortkeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3AioInputOutputSortkeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AioInputOutputSortkeyItemDef.PRODUCT_GROUP.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");                        
            }
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B"); 
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41EC84F80177
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AioInputOutputHistoryListResponse(this);
    }
}
@
