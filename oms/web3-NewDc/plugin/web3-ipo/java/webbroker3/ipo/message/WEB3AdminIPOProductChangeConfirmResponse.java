head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���������m�F���X�|���X(WEB3AdminIPOProductChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �d�� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO���������m�F���X�|���X)<BR>
 * �Ǘ���IPO���������m�F���X�|���X�f�[�^�N���X
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161034L;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * @@roseuid 4112E37F0215
     */
    public WEB3AdminIPOProductChangeConfirmResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO���������m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D1423103DE
     */
    public WEB3AdminIPOProductChangeConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
     
    }
}
@
