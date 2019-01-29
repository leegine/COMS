head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLRestraintMoneyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���o���S�����ꗗ���N�G�X�g(WEB3AdminSLRestraintMoneyListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��764
Revision History : 2007/09/18 �����i���u�j���f��No.768
Revision History : 2007/09/20 �����i���u�j���f��No.780
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��S�ۃ��[���o���S�����ꗗ���N�G�X�g)<BR>
 * �،��S�ۃ��[���o���S�����ꗗ���N�G�X�g�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminSLRestraintMoneyListRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLRestraintMoneyListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_restraint_money_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131520L;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�o����~�敪)<BR>
     * 0:�ʏ� 1:��~��<BR>
     */
    public String cashOutStopDiv;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3SLSortKey[] sortKeys;

    public WEB3AdminSLRestraintMoneyListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * 1) ���X�R�[�h�̃`�F�b�N<BR>
     * �@@1-1) this.���X�R�[�h !=null �ł���A������!=3���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00834<BR>
     * �@@1-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * 2) �ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@2-1) this.�ڋq�R�[�h !=null �ł���A������!=6���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00836<BR>
     * �@@2-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01043<BR>
     * <BR>
     * 3) �o����~�敪�̃`�F�b�N<BR>
     * �@@3-1) this.�o����~�敪 !=null �ł���A��������2���ȏ�̏ꍇ�A<BR>
     * �@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR__02918<BR>
     * �@@3-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR__02919<BR>
     * <BR>
     * 4) �v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@4-1) this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>
     * �@@4-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * �@@4-3) this.�v���y�[�W�ԍ�<= 0�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * 5) �y�[�W���\���s���`�F�b�N<BR>
     * �@@5-1) this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00091<BR>
     * �@@5-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>
     * �@@5-3) this.�y�[�W���\���s��<= 0�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * 6) �S�ۃ��[���\�[�g�L�[�̃`�F�b�N<BR>
     * �@@6-1) this.�S�ۃ��[���\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00231<BR>
     * �@@6-2) this.�S�ۃ��[���\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00232<BR>
     * �@@6-3) this.�S�ۃ��[���\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@�@@6-3-1) this.�S�ۃ��[���\�[�g�L�[.�L�[����==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00085<BR>
     * �@@�@@6-3-2) this.�S�ۃ��[���\�[�g�L�[.�����^�~��==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00087<BR>
     * �@@�@@6-3-3) this.�S�ۃ��[���\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@"A:����" <BR>
     * �@@�@@�@@�@@�@@"D:�~��" <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1) ���X�R�[�h�̃`�F�b�N
        //  1-1) this.���X�R�[�h !=null�̏ꍇ
        if (this.branchCode != null)
        {
            // this.���X�R�[�h !=3���̏ꍇ�A��O���X���[����B
            if (this.branchCode.length() != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
            //  1-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.branchCode))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }

        // 2) �ڋq�R�[�h�̃`�F�b�N
        //  2-1) this.�ڋq�R�[�h !=null�̏ꍇ
        if (this.accountCode != null)
        {
            // this.accountCode.length() != 6�̏ꍇ�A��O���X���[����
            if (this.accountCode.length() != 6)
            {
                log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            }
            //  2-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            }
        }

        // 3) �o����~�敪�̃`�F�b�N
        //  3-1) this.�o����~�敪 !=null�̏ꍇ
        if (this.cashOutStopDiv != null)
        {
            // this.cashOutStopDiv.length() >= 2�̏ꍇ�A��O���X���[����
            if (this.cashOutStopDiv.length() >= 2)
            {
                log.debug("�o����~�敪�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02918,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�o����~�敪�̃T�C�Y���s���ł��B");
            }
            //  3-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.cashOutStopDiv))
            {
                log.debug("�o����~�敪�����p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02919,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "�o����~�敪�����p�����ȊO�̒l�ł��B");
            }
        }

        // 4) �v���y�[�W�ԍ��`�F�b�N
        //  4-1) this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        //  4-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        //  4-3) this.�v���y�[�W�ԍ�<= 0�ł���Η�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // 5) �y�[�W���\���s���`�F�b�N
        //  5-1) this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        //  5-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        //  5-3) this.�y�[�W���\���s��<= 0�ł���Η�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        // 6) �S�ۃ��[���\�[�g�L�[�̃`�F�b�N
        //  6-1) this.�S�ۃ��[���\�[�g�L�[==null�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        //  6-2) this.�S�ۃ��[���\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        //  6-3) this.�S�ۃ��[���\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B
        int l_intCntForSortKeys = this.sortKeys.length;
        for (int i = 0; i < l_intCntForSortKeys; i++)
        {
          // 6-3-1) this.�S�ۃ��[���\�[�g�L�[.�L�[����==null�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            }

          // 6-3-2) this.�S�ۃ��[���\�[�g�L�[.�����^�~��==null�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�������w��ł��B");
            }

            // 6-3-3) this.�S�ۃ��[���\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A��O���X���[����B
            //�@@�@@"A:����"
            //�@@�@@"D:�~��"
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLRestraintMoneyListResponse(this);
    }
}
@
