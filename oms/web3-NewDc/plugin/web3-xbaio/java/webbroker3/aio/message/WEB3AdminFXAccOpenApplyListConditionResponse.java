head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyListConditionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��X�|���X(WEB3AdminFXAccOpenApplyListConditionResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��X�|���X) <BR>
 * �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyListConditionResponse extends
    WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_list_condition";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (�\�����i���j) <BR>
     * �\�����i���j <BR>
     * (YYYYMMDD)
     */
    public String applyDateFrom;

    /**
     * (�\�����i���j) <BR>
     * �\�����i���j <BR>
     * (YYYYMMDD)
     */
    public String applyDateTo;

    /**
     * @@roseuid 41E78FB7036B
     */
    public WEB3AdminFXAccOpenApplyListConditionResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXAccOpenApplyListConditionResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
