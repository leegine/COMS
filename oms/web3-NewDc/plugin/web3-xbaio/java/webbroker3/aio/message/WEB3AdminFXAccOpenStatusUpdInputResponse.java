head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��X�|���X(WEB3AdminFXAccOpenStatusUpdInputResponse)
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
 * (�Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��X�|���X) <BR>
 * �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_status_upd_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

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
     * (�iFX�j���O�C��ID) <BR>
     * �ב֕ۏ؋�����p�̃��O�C��ID
     */
    public String fxLoginId;

    /**
     * (�iFX�j���O�i���j) <BR>
     * �ב֕ۏ؋�����p�̖��O�i���j
     */
    public String fxLastName;

    /**
     * (�iFX�j���O�i���j) <BR>
     * �ב֕ۏ؋�����p�̖��O�i���j
     */
    public String fxFirstName;

    /**
     * (�iFX�j���[���A�h���X) <BR>
     * �ב֕ۏ؋�����p�̃��[���A�h���X
     */
    public String fxMailAddress;

    /**
     * (FX�������ꗗ) <BR>
     * FX�������ꗗ
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * 0�F�����J�ݒ� <BR>
     * 1�F�����J�݊��� <BR>
     * 2�F�����J�݃G���[ <BR>
     * 3�F�_�E�����[�h�� <BR>
     * 9�F�폜
     */
    public String statusDiv;

    /**
     * (������敪)<BR>
     * 0�F�����M<BR>
     * 1�F���M��<BR>
     * 2�F��̍�<BR>
     */
    public String agreementDiv;
    
    /**
     * @@roseuid 41E78F660119
     */
    public WEB3AdminFXAccOpenStatusUpdInputResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXAccOpenStatusUpdInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
