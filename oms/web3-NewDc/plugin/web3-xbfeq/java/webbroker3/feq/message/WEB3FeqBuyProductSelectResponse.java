head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������I�����X�|���X(WEB3FeqBuyProductSelectResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 �������F (SRA) �V�K�쐬
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�����������I�����X�|���X)<BR>
 * �O�����������I�����X�|���X�N���X<BR>
 * 
 * @@author �������F(SRA)
 * @@version 1.0
 */
public class WEB3FeqBuyProductSelectResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_productSelect";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511071500L;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I��������\������s��R�[�h�̈ꗗ�B<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (�f�t�H���g�R���X�g���N�^)<BR>
     */
    public WEB3FeqBuyProductSelectResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqBuyProductSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
