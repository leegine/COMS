head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDeleteCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����폜�������X�|���X(WEB3AdminIPOProductDeleteCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���o�� �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����폜�������X�|���X)<BR>
 * �Ǘ���IPO�����폜�������X�|���X�f�[�^�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIPOProductDeleteCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDeleteComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161034L;
    
    /**
     * @@roseuid 4112E33902FB
     */
    public WEB3AdminIPOProductDeleteCompleteResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO�����폜�������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D141C80390
     */
    public WEB3AdminIPOProductDeleteCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
