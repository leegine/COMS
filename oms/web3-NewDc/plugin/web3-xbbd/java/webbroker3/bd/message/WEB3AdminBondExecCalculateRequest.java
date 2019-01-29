head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCalculateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��n����v�Z���N�G�X�g(WEB3AdminBondExecCalculateRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
                     2006/10/08 ���� (���u) �d�l�ύX�E���f��108
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��n����v�Z���N�G�X�g)<BR>
 * ��n����v�Z���N�G�X�g�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecCalculateRequest extends WEB3AdminBondExecInputCommonRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecCalculateRequest.class);

    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_calculate";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;

    /**
     * (���͎�������)<BR>
     * ���͎�������
     */
    public Date inpOrderDate;

    /**
     * (��n����v�Z���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 44BDD87E0295
     */
    public WEB3AdminBondExecCalculateRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P)�@@�������`�F�b�N�B<BR>
     * �@@this.�������.validate()���R�[������B <BR>
     * <BR>
     * �Q)�@@�����`�F�b�N  <BR>
     * �@@this.�����.validate()���R�[������B  <BR>
     * <BR>
     * �R)�@@�����R�[�h�iWEB3�j�`�F�b�N  <BR>
     * �@@this.�����R�[�h�iWEB3�j==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00079<BR>
     * <BR>
     * �S)�@@���͎��������`�F�b�N <BR>
     * �@@this.���͎�������==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00406<BR>
     * <BR>
     * �T)�@@���X�R�[�h�`�F�b�N  <BR>
     * �@@this.�ڋq��� != null�̏ꍇ�A�ȉ����`�F�b�N����B <BR>
     * �@@�@@this.�ڋq���.���X�R�[�h !=null�̏ꍇ�A�ȉ����`�F�b�N����B <BR>
     * �@@�@@�@@this.�ڋq���.���X�R�[�h���R���łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00834<BR>
     * <BR>
     * �@@�@@�@@this.�ڋq���.���X�R�[�h�����l�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01729<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44BDD87E02D4
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P)�@@�������`�F�b�N�B
        //this.�������.validate()���R�[������B
        this.orderInfo.validate();

        //�Q)�@@�����`�F�b�N
        //this.�����.validate()���R�[������B
        this.execInfo.validate();

        //�R)�@@�����R�[�h�iWEB3�j�`�F�b�N
        //this.�����R�[�h�iWEB3�j==null�̏ꍇ�A��O���X���[����B
        if (this.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B");
        }

        //�S)�@@���͎��������`�F�b�N
        //this.���͎�������==null�̏ꍇ�A��O���X���[����B
        if (this.inpOrderDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }

        //�T)�@@���X�R�[�h�`�F�b�N
        //  this.�ڋq��� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //   this.�ڋq���.���X�R�[�h !=null�̏ꍇ�A�ȉ����`�F�b�N����B
        //   �@@this.�ڋq���.���X�R�[�h���R���łȂ��ꍇ�A��O���X���[����B
        //   �@@this.�ڋq���.���X�R�[�h�����l�łȂ��ꍇ�A��O���X���[����B
        if (this.accountInfo != null)
        {
            if (this.accountInfo.branchCode != null)
            {
                if (this.accountInfo.branchCode.length() != 3)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���X�R�[�h�̃T�C�Y���s���ł��B");
                }

                if (!WEB3StringTypeUtility.isNumber(this.accountInfo.branchCode))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���X�R�[�h�����l�ȊO�̒l�ł��B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ��n����v�Z���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     * @@roseuid 44BED96701D4
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondExecCalculateResponse(this);
    }
}
@
