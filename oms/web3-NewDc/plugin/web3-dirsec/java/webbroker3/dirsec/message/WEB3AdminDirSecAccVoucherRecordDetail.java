head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccVoucherRecordDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���o�^�`�[���R�[�h�ڍ�(WEB3AdminDirSecAccVoucherRecordDetail.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
*/

package webbroker3.dirsec.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ڋq���o�^�`�[���R�[�h�ڍ�)<BR>
 * �ڋq���o�^�`�[���R�[�h�ڍ׃N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccVoucherRecordDetail extends Message
{
    /**
     * (�،���ЃR�[�h )<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     */
    public String dataCode;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (�`�[�쐬��)<BR>
     * �`�[�쐬��<BR>
     * <BR>
     * �e��A���e�[�u�����R�[�h�̏ꍇ<BR>
     * 0�F���쐬<BR>
     * 1�F�쐬��<BR>
     * 2�F��t��<BR>
     * 3�F��t����<BR>
     * 4�F��t�G���[<BR>
     * <BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X���R�[�h�̏ꍇ<BR>
     * 0�F�`�[���쐬<BR>
     * 1�F�쐬��<BR>
     * 2�F���M�ۗ���<BR>
     * 3�F���M��<BR>
     * 4�F��M��<BR>
     * 5�F���M�G���[<BR>
     * 6�F��M�G���[<BR>
     */
    public String voucherMakeStatus;

    /**
     * (�G���[���R�R�[�h)<BR>
     * �G���[���R�R�[�h<BR>
     */
    public String errorReasonCode;

    /**
     * (�`�[���M����)<BR>
     * �`�[���M����<BR>
     */
    public Date voucherSendTimestamp;

    /**
     * (�`�[��M����)<BR>
     * �`�[��M����<BR>
     */
    public Date voucherRecvTimestamp;

    /**
     * (�����J�ݓ`�[�t���O)<BR>
     * �����J�ݓ`�[�t���O<BR>
     * <BR>
     * TRUE  �F�����J�ݓ`�[�쐬�X�e�[�^�X���R�[�h<BR>
     * FALSE �F�e��A���e�[�u�����R�[�h<BR>
     */
    public boolean voucherFlag;

    /**
     * (�A�����)<BR>
     * �A����ʁB<BR>
     */
    public String infoType;

    /**
     * (�`�[�ʔ�)<BR>
     * �`�[�ʔԁB<BR>
     */
    public String voucherNumber;

    /**
     * @@roseuid 466E27F10126
     */
    public WEB3AdminDirSecAccVoucherRecordDetail()
    {

    }
}
@
