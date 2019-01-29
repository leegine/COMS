head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInqCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�\���q�ꗗ�⍇�����������N���X(WEB3AdminInformPTSAccountListInqCondition.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁEPTS�\���q�ꗗ�⍇�����������N���X)<BR>
 * �Ǘ��ҁEPTS�\���q�ꗗ�⍇�����������N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInqCondition extends Message
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListInqCondition.class);

    /**
     * (�A�����)<BR>
     * �A�����
     */
    public String informType;

    /**
     * (���X�R�[�h�̔z��)<BR>
     * ���X�R�[�h�̔z��
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�\���敪)<BR>
     * �\���敪
     * <BR>
     * 0�F���J��<BR>
     * 1�F�J��<BR>
     */
    public String ptsAccOpenDiv;

    /**
     * (�\�������i���j)<BR>
     * �\�������i���j
     */
    public Date applyDateFrom;

    /**
     * (�\�������i���j)<BR>
     * �\�������i���j
     */
    public Date applyDateTo;

    /**
     * @@roseuid 47C522D403C5
     */
    public WEB3AdminInformPTSAccountListInqCondition()
    {

    }

    /**
     * �������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�A����ʂ̃`�F�b�N<BR>
     * <BR>
     * �@@�A����� == null �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01817<BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * <BR>
     * �Q�|�P�j<BR>
     * <BR>
     * �@@���X�R�[�h == null �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01429<BR>
     * �@@���X�R�[�h.length() == 0 �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02175<BR>
     * <BR>
     * �Q�|�Q�j�@@���X�R�[�h�̊e�v�f�ɂ��ă`�F�b�N����B<BR>
     * <BR>
     * �@@���X�R�[�h != ���p���� �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01729<BR>
     * �@@���X�R�[�h.length() != 3 �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00834<BR>
     * <BR>
     * �R�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * <BR>
     * �R�|�P�j�@@�ڋq�R�[�h != null  �̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�ڋq�R�[�h != ���p���� �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01043<BR>
     * �@@�ڋq�R�[�h.length() > 6 �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00836<BR>
     * <BR>
     * �S�j�@@�\���敪�̃`�F�b�N<BR>
     * <BR>
     * �S�|�P�j�@@�\���敪 != null  �̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�\���敪 != ���p���� �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03042<BR>
     * <BR>
     * �T�j�@@�\�������̃`�F�b�N<BR>
     * <BR>
     * �T�|�P�j�@@�\�������i���j != null and �\�������i���j != null  �̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�\�������i���j > �\�������i���j �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03041<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BA660C01D8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A����ʂ̃`�F�b�N
        //�A����� == null �̏ꍇ�A��O��throw����B
        if (this.informType == null)
        {
            log.debug("�A����ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        //�Q�j�@@���X�R�[�h�̃`�F�b�N
        //�Q�|�P�j���X�R�[�h == null �̏ꍇ�A��O��throw����B
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h�ꗗ�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�ꗗ�����w��ł��B");
        }

        //���X�R�[�h.length() == 0 �̏ꍇ�A��O��throw����B
        if (this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f����0�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f����0�ł��B");
        }

        //�Q�|�Q�j�@@���X�R�[�h�̊e�v�f�ɂ��ă`�F�b�N����B
        for (int i = 0; i < this.branchCode.length; i++)
        {
            //���X�R�[�h != ���p���� �̏ꍇ�A��O��throw����B
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }

            //���X�R�[�h.length() != 3 �̏ꍇ�A��O��throw����B
            if (this.branchCode[i].length() != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }

        //�R�j�@@�ڋq�R�[�h�̃`�F�b�N
        //�R�|�P�j�@@�ڋq�R�[�h != null  �̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.accountCode != null)
        {
            //�ڋq�R�[�h != ���p���� �̏ꍇ�A��O��throw����B
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            }

            //�ڋq�R�[�h.length() > 6 �̏ꍇ�A��O��throw����B
            if (this.accountCode.length() > 6)
            {
                log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            }
        }

        //�S�j�@@�\���敪�̃`�F�b�N
        //�S�|�P�j�@@�\���敪 != null  �̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.ptsAccOpenDiv != null)
        {
            //�\���敪 != ���p���� �̏ꍇ�A��O��throw����B
            if (!WEB3StringTypeUtility.isDigit(this.ptsAccOpenDiv))
            {
                log.debug("�\���敪�͔��p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03042,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\���敪�͔��p�����ȊO�̒l�ł��B");
            }
        }

        //�T�j�@@�\�������̃`�F�b�N
        //�T�|�P�j�@@�\�������i���j != null and
        //�\�������i���j != null  �̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.applyDateFrom != null && this.applyDateTo != null)
        {
            //�\�������i���j > �\�������i���j �̏ꍇ�A��O��throw����B
            if (WEB3DateUtility.compare(this.applyDateFrom, this.applyDateTo) > 0)
            {
                log.debug("�\�������i���j�͐\�������i���j ���傫���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03041,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�������i���j�͐\�������i���j ���傫���ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
