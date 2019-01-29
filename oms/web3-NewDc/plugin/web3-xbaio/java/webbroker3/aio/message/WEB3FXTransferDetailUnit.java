head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransferDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX�U�֖���(WEB3FXTransferDetailUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/08 ����(���u) �d�l�ύX�E���f��481
                    2006/07/12 ������ (���u) �d�l�ύX�E���f��595,601
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
*/

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX�U�֖���) <BR>
 * FX�U�֖���
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXTransferDetailUnit extends Message
{
    /**
     * (�I���\�t���O) <BR>
     * �I���\�̏ꍇ�Atrue�B�ȊO�Afalse�B
     */
    public boolean selectableFlag;

    /**
     * (�U�֋敪) <BR>
     * 1�F�����iFX�j<BR>
     * 2�F�o���iFX�j<BR>
     * 3�F�o���i��OP�j<BR>
     * 4�F�����i��OP�j<BR>
     */
    public String fxTransferDiv;

    /**
     * (���X�R�[�h) <BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h) <BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�ڋq��) <BR>
     * �ڋq��
     */
    public String accountName;

    /**
     * (���ʃR�[�h) <BR>
     * ���ʃR�[�h
     */
    public String requestNumber;

    /**
     * (��t����) <BR>
     * ��t���� <BR>
     * YYYYMMDDHHMMSS
     */
    public Date receiptTime;

    /**
     * (�U�֓�) <BR>
     * �U�֓� <BR>
     * YYYYMMDD
     */
    public Date transferDate;

    /**
     * (�U�֋��z) <BR>
     * �U�֋��z
     */
    public String transferAmount;

    /**
     * (�iFX�j�����ԍ�) <BR>
     * �U�ւ��s��ꂽ�ב֕ۏ؋���������ԍ�
     */
    public String fxAccountCode;

    /**
     * (�iFX�j�R�[�X�敪) <BR>
     * �U�ւ��s��ꂽ�ב֕ۏ؋�����R�[�X�敪 <BR>
     * <BR>
     * 1�F 1���ʉ݃R�[�X <BR>
     * 2�F 10���ʉ݃R�[�X
     */
    public String fxCourseDiv;

    /**
     * (FX�V�X�e����t����) <BR>
     * FX�V�X�e����t���� <BR>
     * YYYYMMDDHHMMSS
     */
    public String fxReceiptTime;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * 0�F���ϒ� <BR>
     * 1�F���ϊ��� <BR>
     * 2�F���σG���[ <BR>
     * 3�F���
     */
    public String statusDiv;

    /**
     * (���b�Z�[�W) <BR>
     * 10000000�F���ϒ� <BR>
     * 90000000�F������� <BR>
     * 99999999�F���ώ��s�i�V�X�e���G���[�j <BR>
     * 00000000�F���ϊ��� <BR>
     * 00000105�FGFT��t���ԊO�G���[ <BR>
     * 00000199�FGFT�V�X�e���N���G���[ <BR>
     * 00000204�F�c���s���G���[ <BR>
     * 00000299�F���[�U�[�N���G���[ <BR>
     * 00000501�F�Y���ۏ؋����������G���[ <BR>
     * 00000502�F���o�����z�G���[ <BR>
     * 00000601�F�d�������G���[�i�K�{���ږ����́j <BR>
     * 00000602�F�d�������G���[�i�s�������g�p�j <BR>
     * 00000603�F�d�������G���[�i�����s���j <BR>
     * 00000609�F�d�������N���G���[ <BR>
     * 00000701�F�����敪�G���[ <BR>
     * 00000801�F2�d�����G���[ <BR>
     * 00000901�FGFT�V�X�e���G���[ <BR>
     * 00000909�F�n�b�V���l�G���[ <BR>
     * 00000910�F�^�C���A�E�g�G���[ <BR>
     * 90000009:��������<BR>
     * 00000911�F��n���G���[<BR>
     */
    public String fxMessage;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * (FX�U�֖���) <BR>
     * �R���X�g���N�^�B
     * 
     * @@roseuid 41B456EF0195
     */
    public WEB3FXTransferDetailUnit()
    {
    }
}@
