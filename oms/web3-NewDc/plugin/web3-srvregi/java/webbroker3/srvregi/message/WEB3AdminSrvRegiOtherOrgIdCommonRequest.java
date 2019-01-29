head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdCommonRequest.java;


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
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��ظ���(WEB3AdminSrvRegiOtherOrgIdCommonRequest.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338,No.347
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��ظ���)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��ظ��ăN���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdCommonRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101400L;

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

    /**
     * (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;

    /**
     * (�ʔ�)<BR>
     * �ʔ�<BR>
     */
    public String seqNumber;

    /**
     * (ID)<BR>
     * ID<BR>
     */
    public String id;

    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     */
    public String status;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�ꗗ<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�K�p�J�n���i���j)<BR>
     * �K�p�J�n���i���j<BR>
     */
    public Date appliStartFrom;

    /**
     * (�K�p�J�n���i���j)<BR>
     * �K�p�J�n���i���j<BR>
     */
    public Date appliStartTo;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��ظ���)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B926EF0037
     */
    public WEB3AdminSrvRegiOtherOrgIdCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�T�[�r�X�敪�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00758<BR>
     * �@@�P�|�Q�j�@@this.�T�[�r�X�敪!=null�ł���A<BR>
     * �@@�@@�@@�@@�@@�@@������!=2���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00831<BR>
     * �@@�P�|�R�j�@@this.�T�[�r�X�敪!=null�ł���A<BR>
     * �@@�@@�@@�@@�@@�@@�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00882<BR>
     * <BR>
     * �Q�j�@@�ʔԂ̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�ʔ�!=null�ł���A������>18���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03054<BR>
     * �@@�Q�|�Q�j�@@this.�ʔ�!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03055<BR>
     * <BR>
     * �R�j�@@ID�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.ID!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00954<BR>
     * <BR>
     * �S�j�@@�X�e�[�^�X�̃`�F�b�N <BR>
     * �@@�S�|�P�j�@@this.�X�e�[�^�X���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�E0�F�g�p�� <BR>
     * �@@�@@�@@�E1�F�����i�폜�j<BR>
     * �@@�@@�@@�E9�F���g�p<BR>
     * �@@�@@�@@�Enull�F�S��<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00890<BR>
     * <BR>
     * �T�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.���X�R�[�h==null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�܂���this.���X�R�[�h�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02175<BR>
     * �@@�T�|�Q�j�@@this.���X�R�[�h!=null�ł���A<BR>
     * �@@�@@�@@�@@�@@�@@������!=3���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00834<BR>
     * �@@�T�|�R�j�@@this.���X�R�[�h!=null�ł���A<BR>
     * �@@�@@�@@�@@�@@�@@�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * �U�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�U�|�P�j�@@this.�ڋq�R�[�h!=null�ł���A������!=6���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00836<BR>
     * �@@�U�|�Q�j�@@this.�ڋq�R�[�h!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01043<BR>
     * <BR>
     * �V�j�@@�K�p�J�n���̃`�F�b�N<BR>
     * �@@�V�|�P�j�@@this.�K�p�J�n���i���j!=null�ł���A����this.�K�p�J�n���i���j!=null�ŁA<BR>
     * �@@�@@�@@�@@�@@�@@�K�p�J�n���i���j>�K�p�J�n���i���j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03068<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B3E3C80375
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�T�[�r�X�敪�̃`�F�b�N
        //this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null)
        {
            log.debug("�T�[�r�X�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�����w��ł��B");
        }

        //this.�T�[�r�X�敪!=null�ł���A������!=2���̏ꍇ�A��O���X���[����B
        if (this.serviceDiv != null && this.serviceDiv.length() != 2)
        {
            log.debug("�T�[�r�X�敪�̌������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�̌������s���ł��B");
        }

        // this.�T�[�r�X�敪!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
        {
            log.debug("�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00882,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�敪�����l�ȊO�̒l�ł��B");
        }

        //�ʔԂ̃`�F�b�N
        //this.�ʔ�!=null�ł���A������>18���̏ꍇ�A��O���X���[����B
        if (this.seqNumber != null && this.seqNumber.length() > 18)
        {
            log.debug("�ʔԂ̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03054,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ʔԂ̃T�C�Y���s���ł��B");
        }

        //this.�ʔ�!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
        if (this.seqNumber != null && !WEB3StringTypeUtility.isDigit(this.seqNumber))
        {
            log.debug("�ʔԂ����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03055,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ʔԂ����l�ȊO�̒l�ł��B");
        }

        //ID�̃`�F�b�N
        //this.ID!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B
        if (this.id != null && this.id.length() != 8)
        {
            log.debug("ID�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ID�̃T�C�Y���s���ł��B");
        }

        //�X�e�[�^�X�̃`�F�b�N
        //this.�X�e�[�^�X���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        // �@@�@@�E0�F�g�p��
        // �@@�@@�E1�F�����i�폜)
        // �@@�@@�E9�F���g�p
        // �@@�@@�Enull�F�S��
        if (!(this.status == null
            || WEB3OtherOrgStatusDef.USING.equals(this.status.trim())
            || WEB3OtherOrgStatusDef.INVALIDITY.equals(this.status.trim())
            || WEB3OtherOrgStatusDef.DEFAULT.equals(this.status.trim())))
        {
            log.debug("�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //���X�R�[�h�̃`�F�b�N
        //this.���X�R�[�h==null�̏ꍇ�A�܂���this.���X�R�[�h�̗v�f��==0�̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }
        else if (this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f����0�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f����0�ł��B");
        }

        int l_intCnt = this.branchCode.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            if (this.branchCode[i] != null)
            {
                //this.���X�R�[�h!=null�ł���A������!=3���̏ꍇ�A��O���X���[����B
                if (this.branchCode[i].length() != 3)
                {
                    log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "���X�R�[�h�̃T�C�Y���s���ł��B");
                }

                //this.���X�R�[�h!=null�ł���A�����l�ȊO���i�[����Ă���ꍇ�A
                //��O���X���[����B
                if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
                {
                    log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "���X�R�[�h�����l�ȊO�̒l�ł��B");
                }
            }
        }

        //�ڋq�R�[�h�̃`�F�b�N
        //this.�ڋq�R�[�h!=null�ł���A������!=6���̏ꍇ�A��O���X���[����B 
        if (this.accountCode != null && this.accountCode.length() != 6)
        {
            log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }

        //this.�ڋq�R�[�h!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        //�K�p�J�n���̃`�F�b�N
        //this.�K�p�J�n���i���j!=null�ł���A����this.�K�p�J�n���i���j!=null�ŁA
        //�K�p�J�n���i���j>�K�p�J�n���i���j�̏ꍇ�A��O���X���[����B
        if (this.appliStartFrom != null && this.appliStartTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.appliStartFrom, this.appliStartTo) > 0)
            {
                log.debug("�K�p�J�n���i���j���K�p�J�n���i���j��薢�����t�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03068,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�K�p�J�n���i���j���K�p�J�n���i���j��薢�����t�ł��B");
            }
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
