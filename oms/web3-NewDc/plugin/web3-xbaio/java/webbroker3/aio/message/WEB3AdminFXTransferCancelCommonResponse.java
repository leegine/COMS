head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�U�֎�����ʃ��X�|���X(WEB3AdminFXTransferCancelCommonResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�U�֎�����ʃ��X�|���X) <BR>
 * �Ǘ��ҁEFX�U�֎�����ʃ��X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCommonResponse extends WEB3GenResponse
{
    /**
     * (FX�U�֖��׈ꗗ) <BR>
     * FX�U�֖��ׂ̈ꗗ
     */
    public WEB3FXTransferDetailUnit[] fxTransferDetailList;

    /**
     * @@roseuid 41E7902002DE
     */
    public WEB3AdminFXTransferCancelCommonResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFXTransferCancelCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
