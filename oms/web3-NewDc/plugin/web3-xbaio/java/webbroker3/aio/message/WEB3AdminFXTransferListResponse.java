head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�U�ֈꗗ���X�|���X(WEB3AdminFXTransferListResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 Revesion History : 2008/09/23 �g�C�� (���u) �d�l�ύX�E���f��No.998
 */

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�U�ֈꗗ���X�|���X) <BR>
 * �Ǘ��ҁEFX�U�ֈꗗ���X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXTransferListResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (�Ǘ��ҁEFX�U�ֈꗗ���X�|���X) <BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h <BR>
     * ��null�F�S���X
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h) <BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�h <BR>
     * ��null�F�w��Ȃ�
     */
    public String accountCode;

    /**
     * (�U�֋敪) <BR>
     * ��ʂɂđI�����ꂽ�U�֋敪 <BR>
     * <BR>
     * 1�F���� <BR>
     * 2�F�o�� <BR>
     * <BR>
     * ���S�Ă̏ꍇ�́Anull
     */
    public String fxTransferDiv;

    /**
     * (��t���i���j) <BR>
     * ��ʂɂē��͂��ꂽ��t���i���j <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date receiptDateFrom;

    /**
     * (��t���i���j) <BR>
     * ��ʂɂē��͂��ꂽ��t���i���j <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date receiptDateTo;

    /**
     * (�U�֓�) <BR>
     * ��ʂɂē��͂��ꂽ�U�֓� <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date transferDate;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * ��ʂɂđI�����ꂽ�X�e�[�^�X <BR>
     * <BR>
     * 1�F���ϊ��� <BR>
     * 5�F���̑� <BR>
     * <BR>
     * <BR>
     * ���S�X�e�[�^�X�̏ꍇ�́Anull
     */
    public String statusDiv;

    /**
     * (FX�U�֖��׈ꗗ) <BR>
     * FX�U�֖��ׂ̈ꗗ
     */
    public WEB3FXTransferDetailUnit[] fxTransferDetailList;

    /**
     * (�\���y�[�W�ԍ�) <BR>
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (���y�[�W��) <BR>
     * ���y�[�W��
     */
    public String totalPages;

    /**
     * (�����R�[�h��) <BR>
     * �����R�[�h��
     */
    public String totalRecords;

    /**
     * (�U�֏o��)
     * �U�֏o��
     */
    public String fxTotalDepositToGuaranty;

    /**
     * (�U�֓���)
     * �U�֓���
     */
    public String fxTotalGuarantyToDeposit;

    /**
     * (�U�֍��v)
     * �U�֍��v
     */
    public String fxTransferTotal;

    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXTransferListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
