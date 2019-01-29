head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v���׃\�[�g�L�[(WEB3PLSProfitLossSpecsSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/15 �����q(���u) �V�K�쐬�@@���f��068
*/
package webbroker3.tradehistory.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.tradehistory.define.WEB3PLSProfitLossSpecsSortKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���v���׃\�[�g�L�[)<BR>
 * ���v���׃\�[�g�L�[�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsSortKeyUnit extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsSortKeyUnit.class);

    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     * calcDate�F�@@�v�Z�N����<BR>
     */
    public String keyItem;

    /**
     * (�����^�~��)<BR>
     * �����^�~��<BR>
     * <BR>
     * A�F�@@����<BR>
     * D�F�@@�~��<BR>
     */
    public String ascDesc;

    /**
     * (���v���׃\�[�g�L�[)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3PLSProfitLossSpecsSortKeyUnit()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jthis.�L�[���ځ�null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_00085<BR>
     * <BR>
     * �Q�jthis.�����^�~����null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_00087<BR>
     * <BR>
     * �R�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_00088<BR>
     * <BR>
     * �S�jthis.�L�[���ڂ����L�̍��ڈȊO�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�hcalcDate�F�v�Z�N�����h<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag           :  BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.�L�[���ڂ̃`�F�b�N
        //     this.�L�[���ځ�null�̏ꍇ�A
        //     �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
        if (this.keyItem == null)
        {
            log.error(" �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
        }

        // �Q�jthis.�����^�~���̃`�F�b�N
        //     this.�����^�~����null�̏ꍇ�A
        // �@@  �u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
        if (this.ascDesc == null)
        {
            log.error("�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�������w��ł��B");
        }

        // �R�jthis.�����^�~���̃`�F�b�N
        //     this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A
        // �@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E�hA�F�����h
        // �@@�@@�@@�E�hD�F�~���h
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) &&
            !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.error("�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
        }

        // �S�jthis.�L�[���ڂ̃`�F�b�N
        //     this.�L�[���ڂ����L�̍��ڈȊO�̏ꍇ�A
        // �@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E�hcalcDate�F�v�Z�N�����h
        if (!WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(this.keyItem))
        {
            log.error("�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
