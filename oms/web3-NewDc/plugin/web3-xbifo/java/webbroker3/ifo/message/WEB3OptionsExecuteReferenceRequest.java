head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����������Ɖ�N�G�X�g(WEB3OptionsExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 羉s (���u) �V�K�쐬
Revesion History : 2007/10/16 �����F(���u)���f�� 785
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoExecStatusTypeDef;
import webbroker3.ifo.define.WEB3IfoReferenceTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (�����w���I�v�V�����������Ɖ�N�G�X�g)<BR>
 * �����w���I�v�V�����������Ɖ�N�G�X�g�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsExecuteReferenceRequest extends WEB3GenRequest
{

     /**
      * Logger<BR>
      */
    private static WEB3LogUtility log =
	    WEB3LogUtility.getInstance(WEB3OptionsExecuteReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111551L;

    /**
     * (�Ɖ�敪)<BR>
     * <BR>
     * 0�F�������Ɖ�<BR>
     * 1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j<BR>
     */
    public String referenceType;

    /**
     * (�����R�[�h)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * �����R�[�h<BR>
     * <BR>
     *�������R�[�h�ɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String opProductCode;

    /**
     * (����ԋ敪)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * 0�F�����<BR>
     * 1�F�ꕔ����<BR>
     * 2�F�S������<BR>
     * <BR>
     *������Ԏw��̏ꍇ�A�ݒ肳���<BR>
     */
    public String execType;
    
    /**
     * (������)<BR>
     * <BR>
     * (���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * ������<BR>
     * <BR>
     * ���������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public Date orderBizDate;
    
    /**
     * (����s��)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * 1�F����<BR>
     * 2�F���<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String marketCode;
    
    /**
     * (�w�����)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * 0005�FTOPIX<BR>
     * 0018�F���o225<BR>
     * 0016�F���o300<BR>
     * 0019�F�~�j���o225<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String targetProductCode;
    
    /**
     * (����)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * ����(YYYYMM�`��)<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String delivaryMonth;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * P�F�v�b�g�I�v�V����<BR>
     * C�F�R�[���I�v�V����<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String opProductType;
    
    /**
     * (�s�g���i)<BR>
     * <BR>
     *(���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ�<BR>
     * �s�g���i<BR>
     * <BR>
     *���������荀�ڂɂ������w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String strikePrice;

    /**
     * (���������敪)<BR>
     * (���������w�莞�Ɏg�p) <BR>
     * <BR>
     * 0�F�w��Ȃ� <BR>
     * 1�F�t�w�l <BR>
     * 2�FW�w�l <BR>
     * <BR>
     * �����������敪�w��̏ꍇ�A�ݒ肳���<BR>
     */
    public String orderCondType;
    
    /**
     * (�����w���敨�I�v�V�����\�[�g�L�[)<BR>
     * <BR>
     * �Ώۍ��ځF�����R�[�h�A����s��A����敪�A�������ԁA�������A�����L������<BR>
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 40C075390251
     */
    public WEB3OptionsExecuteReferenceRequest()
    {

    }
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�Ɖ�敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�Ɖ�敪��null�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00081<BR>
     * �@@�P�|�Q�jthis.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�������Ɖ�h�A<BR>
     * �@@�@@�@@�@@�E�h1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j�h<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00082<BR>
     * <BR>
     * �Q�j�@@����ԋ敪�`�F�b�N <BR>
     *      this.����ԋ敪��null ���A<BR> 
     *      this.����ԋ敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR> 
     *      �E�h0�F�����h<BR>
     *      �E�h1�F�ꕔ�����h<BR>
     *      �E�h2�F�S�������h<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00626<BR>
     * <BR>
     * �R�j�@@�����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N<BR><BR>
     * �@@�R�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��<BR>
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * �@@�R�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * �@@�R�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�R�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * �@@�@@�R�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@�@@�@@�E�����R�[�h<BR>
     * �@@�@@�@@�@@�E����s��<BR>
     * �@@�@@�@@�@@�E����敪<BR>
     * �@@�@@�@@�@@�E��������<BR>
     * �@@�@@�@@�@@�E������<BR>
     * �@@�@@�@@�@@�E�����L������<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00086<BR>     
     * �@@�@@�R�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * �@@�@@�R�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�S�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00089<BR>
     * �@@�S�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�T�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00091<BR>
     * �@@�T�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �U�j�@@�����ݒ�`�F�b�N<BR>
     *   �U�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
�@@�@@ *        �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)<BR>
�@@�@@ *      �E�����R�[�h<BR>
�@@�@@ *      �E����s��<BR>
�@@�@@ *      �E�w�����<BR>
�@@�@@ *      �E����<BR>
�@@�@@ *      �E�I�v�V�������i�敪<BR>
�@@�@@ *      �E�s�g���i<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
�@@�@@ *�@@�@@  ����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i�̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
�@@�@@ *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * <BR>
     * �V�j�@@���������敪�`�F�b�N <BR>
     * �@@this.���������敪��null���A <BR>
     * �@@this.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B <BR>
     * �@@�@@�@@�E�h0�F�w��Ȃ��h <BR>
     * �@@�@@�@@�E�h1�F�t�w�l�h <BR>
     * �@@�@@�@@�E�h2�FW�w�l�h<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00212<BR>
     * @@throws WEB3BaseException
     * @@roseuid 406A15B301E6
     */
    public void validate() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
		
        // �P�j�@@�Ɖ�敪�`�F�b�N<BR>
        // �@@�P�|�P�jthis.�Ɖ�敪��null�̒l�ł���Η�O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪��null�̒l�ł���B");
        }

        // �@@�P�|�Q�jthis.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
        // �@@�@@�@@�@@�E�h0�F�������Ɖ�h�A<BR>
        // �@@�@@�@@�@@�E�h1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j�h<BR>
        if (!WEB3IfoReferenceTypeDef.ORDER_PROMISE.equals(this.referenceType) 
            && !WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(this.referenceType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪���g0:�����Ɖ�h�A�g1:����ꗗ(����\�Ȃ��̂̂ݕ\��)�h�ȊO�ł���B");
        }

        // �Q�j�@@����ԋ敪�`�F�b�N <BR>
        //      this.����ԋ敪��null ���A<BR> 
        //      this.����ԋ敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR> 
        //      �E�h0�F�����h<BR>
        //      �E�h1�F�ꕔ�����h<BR>
        //      �E�h2�F�S�������h<BR>
        if (WEB3StringTypeUtility.isNotEmpty(this.execType)
            && !WEB3IfoExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType)
            && !WEB3IfoExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType)
            && !WEB3IfoExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00626, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �R�j�@@�����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N<BR><BR>
        // �@@�R�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��<BR>
        // �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
        if (this.futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[��null�̒l�ł���B");
        }

        int l_intOpSortKeysLength = this.futOpSortKeys.length;

        // �@@�R�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
        // �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
        if (l_intOpSortKeysLength == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł���B");
        }

        // �@@�R�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
        // �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B<BR>
        int i;
        for (i = 0; i < l_intOpSortKeysLength; i++)
        {
            // �@@�@@�R�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[���ڂ�null�̒l�ł���B");
            }

            // �@@�@@�R�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
            // �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
            // �@@�@@�@@�E�����R�[�h<BR>
            // �@@�@@�@@�E����s��<BR>
            // �@@�@@�@@�E����敪<BR>
            // �@@�@@�@@�E��������<BR>
            // �@@�@@�@@�E������ <BR>
            // �@@�@@�@@�E�����L������<BR>
            if (!WEB3IfoKeyItemDef.PRODUCTCODE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.TRADE_MARKET.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.TRADE_DIVISION.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.ORDER_TIME.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.BIZ_DATE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.ORDER_EXPIRATION_DATE.equals(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[���ڂɖ�����ށA����s��A����敪�A�������ԁA�����L�������̍��ږ��ȊO�̒l�����݂��Ă��܂��B");
            }

            // �@@�@@�R�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~����null�̒l�ł���B");
            }

            // �@@�@@�R�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
            // �@@�@@�@@�E�hA�F�����h<BR>
            // �@@�@@�@@�E�hD�F�~���h<BR>
            if (!WEB3AscDescDef.ASC.equals(this.futOpSortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        // �S�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
        // �@@�S�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ���null�̒l�ł���B");
        }

        // �@@�S�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł���B");
        }

        // �T�j�@@�y�[�W���\���s���`�F�b�N<BR>
        // �@@�T�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�B");
        }

        // �@@�T�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
		//�U�j �����ݒ�`�F�b�N<BR>
		//�U�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
		//    �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)<BR>               
		if((opProductCode!=null)               
			&&(marketCode!=null)               
			&&(targetProductCode!=null)                
			&&(delivaryMonth!=null)                
			&&(opProductType!=null)                
			&&(strikePrice!=null))             
		{              
			throw new WEB3BusinessLayerException(         
				WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
				this.getClass().getName() + "." + STR_METHOD_NAME,        
				"�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă��܂��B");      
		}              
                
		// �U�|�Q�j�����I�����Ɏ���s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�̂����ꂩ�̍��ڂ�              
		// �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B             
		if((marketCode==null)
			&&(targetProductCode==null)
			&&(delivaryMonth==null)                
			&&(opProductType==null)
			&&(strikePrice==null))          
		{              

		}
		else              
		{              
			if((marketCode==null)
				||(targetProductCode==null)
				||(delivaryMonth==null)            
				||(opProductType==null)
				||(strikePrice==null))      
			{          
				throw new WEB3BusinessLayerException(  
					WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�������荀�ڂ̂����ꂩ���ݒ肳��Ă��܂���B");
			}          
		} 

        //�V�j�@@���������敪�`�F�b�N
        //�@@this.���������敪��null���A
        //�@@this.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //�@@�@@�@@�E�h0�F�w��Ȃ��h
        //�@@�@@�@@�E�h1�F�t�w�l�h
        //�@@�@@�@@�E�h2�FW�w�l�h
        if (orderCondType != null
            && !WEB3OrderingConditionDef.DEFAULT.equals(orderCondType)
            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(orderCondType)
            && !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(orderCondType))
        {
            log.debug("���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
		log.exiting(STR_METHOD_NAME);             
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsExecuteReferenceResponse(this);
    }
}
@
