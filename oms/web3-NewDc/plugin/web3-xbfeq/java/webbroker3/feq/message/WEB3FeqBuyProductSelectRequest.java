head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������I�����N�G�X�g(WEB3FeqBuyProductSelectRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 �������F (SRA) �V�K�쐬 
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�����������I�����N�G�X�g)<BR>
 * �O�����������I�����N�G�X�g�N���X<BR>
 * 
 * @@author �������F(SRA)
 * @@version 1.0
 */
public class WEB3FeqBuyProductSelectRequest extends WEB3GenRequest
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
     * (�f�t�H���g�R���X�g���N�^)<BR>
     */
    public WEB3FeqBuyProductSelectRequest()
    {
    }
    
    /**
     * (createResponse)<BR>
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBuyProductSelectResponse(this);
    }
}
@
