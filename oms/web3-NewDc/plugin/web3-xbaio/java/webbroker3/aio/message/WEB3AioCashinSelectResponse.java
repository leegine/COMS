head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C�������I����ʃ��X�|���X(WEB3AioCashinSelectResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬     
                   2004/10/22 ���� (���u) ���r���[                  
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�I�����C�������I����ʃ��X�|���X)<BR>
 * �I�����C�������I����ʃ��X�|���X�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSelectResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_select";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (���ϋ@@�ֈꗗ)<BR>
     * �I�����C�������̑ΏۂƂȂ錈�ϋ@@�ւ̈ꗗ<BR>
     */
    public WEB3AioSelectSettleInstitutionUnit[] selectSettleInstitutionUnit;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158EB33006D
     */
    public WEB3AioCashinSelectResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashinSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
