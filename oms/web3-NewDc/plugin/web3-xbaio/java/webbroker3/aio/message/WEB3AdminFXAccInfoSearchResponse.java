head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX������񌟍����X�|���X(WEB3AdminFXAccInfoSearchResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/08 ����(���u) �d�l�ύX�E���f��481
                    2006/02/09 �]�V�q(���u) �d�l�ύX�E���f��458
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX������񌟍����X�|���X) <BR>
 * �Ǘ��ҁEFX������񌟍����X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoSearchResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_search";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

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
     * FX�������̈ꗗ
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (�����J�ݏ󋵋敪) <BR>
     * 1�F�J�ݍ� <BR>
     * 2�F�U�֒�~<BR>
     * 9�F����<BR>
     * 99�F��������
     */
    public String accountOpenStatusDiv;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FFF02DE
     */
    public WEB3AdminFXAccInfoSearchResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXAccInfoSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
