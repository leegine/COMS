head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ���(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ��ăN���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListReferenceRequest extends WEB3AdminSrvRegiOtherOrgIdCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgId_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101403L;

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.class);

    /**
     * (�T�[�r�X���p�\�[�g�L�[)<BR>
     * �Ώۍ���<BR>
     * �@@�ʔԁ^ID�^�X�e�[�^�X�^���X�R�[�h�^�����R�[�h�^�K�p����From<BR>
     * �@@�@@�@@/�K�p����To�^�ŏI�X�V���^�ŏI�X�V��<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D1790005
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@�T�[�r�X���p�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00231<BR>
     * �@@�Q�|�Q�j�@@this.�T�[�r�X���p�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00232<BR>
     * �@@�Q�|�R�j�@@this.�T�[�r�X���p�\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@�@@�Q�|�R�|�P�j�@@this.�T�[�r�X���p�\�[�g�L�[.�L�[����==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00085<BR>
     * �@@�@@�Q�|�R�|�Q�j�@@this.�T�[�r�X���p�\�[�g�L�[.�����^�~��==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00087<BR>
     * �@@�@@�Q�|�R�|�R�j�@@this.�T�[�r�X���p�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@"A:����"<BR>
     * �@@�@@�@@"D:�~��"<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * �R�j �v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00089<BR>
     * �@@�R�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �S�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02224<BR>
     * �@@�S�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3DE9D0287
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�X�[�p�[�N���X��validate���\�b�h���Ăяo���B
        super.validate();

        //�T�[�r�X���p�\�[�g�L�[�̃`�F�b�N
        //this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //this.�T�[�r�X���p�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        //this.�T�[�r�X���p�\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B
        int l_intCnt = this.sortKeys.length;
        for (int i = 0;i < l_intCnt;i++)
        {
            //this.�T�[�r�X���p�\�[�g�L�[.�L�[����==null�̏ꍇ�A��O���X���[����B
            if (this.sortKeys[i].keyItem == null)
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            }

            // this.�T�[�r�X���p�\�[�g�L�[.�����^�~��==null�̏ꍇ�A��O���X���[����B
            if (this.sortKeys[i].ascDesc == null)
            {
                log.debug("�����^�~�������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�������w��ł��B");
            }

            // this.�T�[�r�X���p�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A��O���X���[����B
            // �@@"A:����"
            // �@@"D:�~��"
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc.trim())
                    || WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc.trim())))
            {
                log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        //�v���y�[�W�ԍ��`�F�b�N
        //this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //�y�[�W���\���s���`�F�b�N
        //this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        // this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
    }

    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�X�|���X�𐶐����ĕԋp����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 47B8D19802B0
     */
    public WEB3GenResponse createResponse()
    {
     return new WEB3AdminSrvRegiOtherOrgIdListReferenceResponse(this);
    }
}
@
