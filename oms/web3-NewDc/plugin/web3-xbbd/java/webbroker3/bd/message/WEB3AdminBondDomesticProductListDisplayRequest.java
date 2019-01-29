head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������ꗗ��ʕ\�����N�G�X�g(WEB3AdminBondDomesticProductListDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ���n�m (���u) �V�K�쐬 ���f��192
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҍ����������ꗗ��ʕ\�����N�G�X�g)<BR>
 * �Ǘ��ҍ����������ꗗ��ʕ\�����N�G�X�g<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListDisplayRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListDisplayRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_list_display";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3BondSortKey[] sortKeys;

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
     * (��������)<BR>
     * ��������<BR>
     */
    public WEB3AdminBondDomesticProductListSearchConditionUnit searchCondition;

    /**
     * @@roseuid 4691C5EB03E0
     */
    public WEB3AdminBondDomesticProductListDisplayRequest()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�P�|�P�j�\�[�g�L�[ == null �̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�|�R�j�\�[�g�L�[�̔z��̌����A<BR>
     * �@@�@@�@@�@@�@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɖ����R�[�h�A�񍆃R�[�h�A���s���A���ғ��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����@@�ȊO�����݂����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�j�v���y�[�W�ԍ� == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�Q�|�Q�j�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�Q�|�R�j�v���y�[�W�ԍ����O �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�R�|�P�j�y�[�W���\���s�� == null �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�R�|�Q�j�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�R�|�R�j�y�[�W���\���s�����O �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * �S�j���������`�F�b�N<BR>
     * �@@�S�|�P�j�Ǘ��ҍ����������ꗗ��������.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 46636FE40334
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�\�[�g�L�[�`�F�b�N
        //�@@�P�|�P�j�\�[�g�L�[ == null �̏ꍇ�A
        //�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //�@@�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A
        //�@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B
        int l_intSortKeysLen = this.sortKeys.length;
        if (l_intSortKeysLen == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        //�@@�P�|�R�j�\�[�g�L�[�̔z��̌����A
        //�@@�@@�@@�@@�@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B
        for (int i = 0 ; i < l_intSortKeysLen; i++)
        {
            //�@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
            sortKeys[i].validate();

            //�@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɖ����R�[�h�A�񍆃R�[�h�A���s���A���ғ��A
            //�@@�@@�@@�@@�@@�@@�@@�@@�����@@�ȊO�����݂����ꍇ�A
            //�@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B
            if (!WEB3BondSortKeyDef.PRODUCT_CODE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.ISSUE_DATE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.MATURITY_DATE.equals(sortKeys[i].keyItem)
                && !WEB3BondSortKeyDef.COUPON.equals(sortKeys[i].keyItem))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //�Q�j�v���y�[�W�ԍ��`�F�b�N
        //�@@�Q�|�P�j�v���y�[�W�ԍ� == null �̏ꍇ�A
        //�@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�@@�Q�|�Q�j�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        //�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //�@@�Q�|�R�j�v���y�[�W�ԍ����O �̏ꍇ�A
        //�@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�R�j�y�[�W���\���s���`�F�b�N
        //�@@�R�|�P�j�y�[�W���\���s�� == null �̏ꍇ�A
        //�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        //�@@�R�|�Q�j�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        //�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //�@@�R�|�R�j�y�[�W���\���s�����O �̏ꍇ�A
        //�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�S�j���������`�F�b�N
        //�@@�S�|�P�j�Ǘ��ҍ����������ꗗ��������.validate()���R�[������B
        searchCondition.validate();


        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �Ǘ��ҍ����������ꗗ��ʕ\�����X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondDomesticProductListDisplayResponse(this);
    }

}
@
