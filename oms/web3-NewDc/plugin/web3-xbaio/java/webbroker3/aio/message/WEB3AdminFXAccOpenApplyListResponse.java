head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�ݐ\���ꗗ���X�|���X(WEB3AdminFXAccOpenApplyListResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/09 �]�V�q(���u) �d�l�ύX�E���f��458
                    2006/02/09 �A����(���u) �d�l�ύX�E���f��475
                    2006/02/22 ���(SRA) �d�l�ύX�E���f��500
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�ݐ\���ꗗ���X�|���X) <BR>
 * �Ǘ��ҁEFX�����J�ݐ\���ꗗ���X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (���X�R�[�h) <BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h <BR>
     * ��null�F�S���X
     */
    public String branchCode;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * ��ʂɂđI�����ꂽ�X�e�[�^�X <BR>
     * <BR>
     * 0�F�����J�ݒ� <BR>
     * 1�F�����J�݊��� <BR>
     * 2�F�����J�݃G���[ <BR>
     * 3�F�_�E�����[�h�� <BR>
     * 9�F�폜
     */
    public String statusDiv;

    /**
     * (MRF�����󋵋敪) <BR>
     * ��ʂɂđI�����ꂽMRF������ <BR>
     * <BR>
     * 1�F�J�� <BR>
     * 2�F���J��
     */
    public String mrfAccountStatusDiv;

    /**
     * (�\�����i���j) <BR>
     * ��ʂɂē��͂��ꂽ�\�����i���j <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String applyHourFrom;

    /**
     * (�\�����i���j) <BR>
     * ��ʂɂē��͂��ꂽ�\�����i���j <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String applyHourTo;

    /**
     * (FX�����J�ݐ\�����׈ꗗ) <BR>
     * FX�����J�ݐ\�����ׂ̈ꗗ
     */
    public WEB3FXAccOpenApplyUnit[] fxAccOpenApplyList;

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
     * (������敪)<BR>
     * ��ʂɂđI�����ꂽ������敪<BR>
     * <BR>
     * 0�F�����M<BR>
     * 1�F���M��<BR>
     * 2�F��̍�<BR>
     * <BR>
     * ���S�Ă̏ꍇ�́Anull���Z�b�g�B<BR>
     */
    public String agreementDiv;
    
    /**
     * @@roseuid 41E78FB800EA
     */
    public WEB3AdminFXAccOpenApplyListResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXAccOpenApplyListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
